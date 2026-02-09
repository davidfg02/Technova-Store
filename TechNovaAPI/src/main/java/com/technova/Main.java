package com.technova;

import io.javalin.Javalin;
import com.google.gson.Gson;

import java.sql.*;

public class Main {

    private static final Gson gson = new Gson();

    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            // CORS para que el frontend no se bloquee en el futuro
            config.plugins.enableCors(cors -> cors.add(it -> it.anyHost()));
        }).start(7000);

        // Endpoint de prueba (andamiaje)
        app.get("/hola", ctx -> ctx.json("{\"mensaje\":\"hola\"}"));

        // Test conexión BD
        app.get("/api/test-db", ctx -> {
            try (var con = DBConnection.getConnection()) {
                ctx.json("{\"status\":\"ok\",\"db\":\"db_technova\"}");
            } catch (Exception e) {
                e.printStackTrace();
                ctx.status(500).json("{\"status\":\"error\",\"mensaje\":\"No conecta a MySQL\"}");
            }
        });

        // ============================================================
        // GET /api/productos (CALL procedimiento)
        // ============================================================
        app.get("/api/productos", ctx -> {

            var lista = new java.util.ArrayList<Producto>();

            try (var con = DBConnection.getConnection();
                 var stmt = con.prepareCall("{CALL sp_get_productos()}");
                 var rs = stmt.executeQuery()) {

                while (rs.next()) {
                    lista.add(new Producto(
                            rs.getInt("id_producto"),
                            rs.getString("sku"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getDouble("precio"),
                            rs.getInt("stock"),
                            rs.getString("categoria"),
                            rs.getString("imagen")
                    ));
                }

                ctx.json(lista);

            } catch (Exception e) {
                e.printStackTrace();
                ctx.status(500).json("{\"error\":\"" + e.getMessage().replace("\"", "'") + "\"}");
            }
        });

        // ============================================================
        // GET /api/productos/{categoria} (CALL procedimiento con parámetro)
        // ============================================================
        app.get("/api/productos/{categoria}", ctx -> {

            String categoria = ctx.pathParam("categoria");

            var lista = new java.util.ArrayList<Producto>();

            try (var con = DBConnection.getConnection();
                 var stmt = con.prepareCall("{CALL sp_get_productos_categoria(?)}")) {

                stmt.setString(1, categoria);

                try (var rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        lista.add(new Producto(
                                rs.getInt("id_producto"),
                                rs.getString("sku"),
                                rs.getString("nombre"),
                                rs.getString("descripcion"),
                                rs.getDouble("precio"),
                                rs.getInt("stock"),
                                rs.getString("categoria"),
                                rs.getString("imagen")
                        ));
                    }
                }

                ctx.json(lista);

            } catch (Exception e) {
                e.printStackTrace();
                ctx.status(500).json("{\"error\":\"Error filtrando productos\"}");
            }
        });

        // ============================================================
        // POST /api/login
        // ============================================================
        app.post("/api/login", ctx -> {

            LoginRequest login = gson.fromJson(ctx.body(), LoginRequest.class);

            if (login == null || login.email == null || login.password == null) {
                ctx.status(400).json("{\"error\":\"JSON inválido\"}");
                return;
            }

            String sql = "SELECT rol FROM usuario WHERE email = ? AND password = ?";

            try (var con = DBConnection.getConnection();
                 var stmt = con.prepareStatement(sql)) {

                stmt.setString(1, login.email);
                stmt.setString(2, login.password);

                try (var rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String rol = rs.getString("rol");
                        ctx.status(200).json("{\"status\":\"ok\",\"rol\":\"" + rol + "\"}");
                    } else {
                        ctx.status(401).json("{\"error\":\"Credenciales incorrectas\"}");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                ctx.status(500).json("{\"error\":\"Error interno\"}");
            }
        });

        // ============================================================
        // POST /api/pedidos  (Transacción + ID + líneas + descuenta stock)
        // ============================================================
        app.post("/api/pedidos", ctx -> {

            PedidoRequest pedidoReq = gson.fromJson(ctx.body(), PedidoRequest.class);

            if (pedidoReq == null || pedidoReq.lineas == null || pedidoReq.lineas.isEmpty()) {
                ctx.status(400).json("{\"error\":\"Pedido inválido\"}");
                return;
            }

            Connection con = null;

            try {
                con = DBConnection.getConnection();
                con.setAutoCommit(false); // ✅ Transacción

                // 1) Validar productos + stock y calcular total desde BD
                double total = 0.0;

                String sqlPrecioStock = "SELECT precio, stock FROM producto WHERE id_producto = ?";
                try (PreparedStatement ps = con.prepareStatement(sqlPrecioStock)) {

                    for (LineaPedidoRequest linea : pedidoReq.lineas) {

                        if (linea.cantidad <= 0) {
                            con.rollback();
                            ctx.status(400).json("{\"error\":\"Cantidad inválida\"}");
                            return;
                        }

                        ps.setInt(1, linea.id_producto);

                        try (ResultSet rs = ps.executeQuery()) {
                            if (!rs.next()) {
                                con.rollback();
                                ctx.status(400).json("{\"error\":\"Producto no existe: " + linea.id_producto + "\"}");
                                return;
                            }

                            double precio = rs.getDouble("precio");
                            int stock = rs.getInt("stock");

                            if (stock < linea.cantidad) {
                                con.rollback();
                                ctx.status(400).json("{\"error\":\"Stock insuficiente para producto " + linea.id_producto + "\"}");
                                return;
                            }

                            total += precio * linea.cantidad;
                        }
                    }
                }

                // 2) Insertar pedido (cabecera) y obtener id generado
                int idPedido;

                String sqlInsertPedido = "INSERT INTO pedido (id_usuario, total_pedido) VALUES (?, ?)";
                try (PreparedStatement psPedido = con.prepareStatement(sqlInsertPedido, Statement.RETURN_GENERATED_KEYS)) {

                    psPedido.setInt(1, pedidoReq.id_usuario);
                    psPedido.setDouble(2, total);
                    psPedido.executeUpdate();

                    try (ResultSet keys = psPedido.getGeneratedKeys()) {
                        if (!keys.next()) {
                            con.rollback();
                            ctx.status(500).json("{\"error\":\"No se pudo obtener id_pedido\"}");
                            return;
                        }
                        idPedido = keys.getInt(1);
                    }
                }

                // 3) Insertar líneas + descontar stock
                String sqlPrecio = "SELECT precio FROM producto WHERE id_producto = ?";
                String sqlInsertLinea =
                        "INSERT INTO linea_pedido (id_pedido, id_producto, cantidad, precio_unitario_momento) VALUES (?, ?, ?, ?)";
                String sqlUpdateStock = "UPDATE producto SET stock = stock - ? WHERE id_producto = ?";

                try (PreparedStatement psPrecio = con.prepareStatement(sqlPrecio);
                     PreparedStatement psLinea = con.prepareStatement(sqlInsertLinea);
                     PreparedStatement psStock = con.prepareStatement(sqlUpdateStock)) {

                    for (LineaPedidoRequest linea : pedidoReq.lineas) {

                        // precio actual en el momento
                        psPrecio.setInt(1, linea.id_producto);
                        double precioUnit;
                        try (ResultSet rs = psPrecio.executeQuery()) {
                            rs.next();
                            precioUnit = rs.getDouble("precio");
                        }

                        // insertar línea
                        psLinea.setInt(1, idPedido);
                        psLinea.setInt(2, linea.id_producto);
                        psLinea.setInt(3, linea.cantidad);
                        psLinea.setDouble(4, precioUnit);
                        psLinea.executeUpdate();

                        // descontar stock ✅
                        psStock.setInt(1, linea.cantidad);
                        psStock.setInt(2, linea.id_producto);
                        psStock.executeUpdate();
                    }
                }

                con.commit();

                ctx.status(201).json("{\"status\":\"ok\",\"id_pedido\":" + idPedido + ",\"total\":" + total + "}");

            } catch (Exception e) {
                e.printStackTrace();
                if (con != null) {
                    try { con.rollback(); } catch (SQLException ignored) {}
                }
                ctx.status(500).json("{\"error\":\"Error interno\"}");

            } finally {
                if (con != null) {
                    try { con.setAutoCommit(true); con.close(); } catch (SQLException ignored) {}
                }
            }
        });

        System.out.println("Servidor corriendo en http://localhost:7000");
    }
}
