package com.example.demo.repository;

import com.example.demo.model.EnumPedidoEstado;
import com.example.demo.model.Pedido;
import com.example.demo.model.PedidoRequest;
import com.example.demo.model.LineaPedidoRequest;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public class PedidoRepository {
    private final DataSource dataSource;

    public PedidoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Pedido> findAll(String estado, LocalDateTime fechaIni, LocalDateTime fechaFin) {
        List<Pedido> pedidos = new ArrayList<>();

        // Requisito Entregable 3: endpoints GET -> procedimientos almacenados
        String sql = "{CALL sp_pedidos_filtro(?, ?, ?)}";

        try (Connection con = dataSource.getConnection();
             CallableStatement cs = con.prepareCall(sql)){

            // p_estado no existe en la tabla pedido del hito 2; se pasa igual por compatibilidad.
            cs.setString(1, estado);
            cs.setTimestamp(2, Timestamp.valueOf(fechaIni));

            cs.setTimestamp(3, Timestamp.valueOf(fechaFin));


            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {

                    Pedido p = new Pedido(
                            rs.getLong(Pedido.ID_PEDIDO),
                            rs.getLong(Pedido.ID_USUARIO),
                            rs.getTimestamp(Pedido.FECHA).toLocalDateTime(),
                            rs.getDouble(Pedido.TOTAL_PEDIDO),
                            EnumPedidoEstado.valueOf(rs.getString(Pedido.PEDIDO_ESTADO))
                    );
                    pedidos.add(p);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pedidos;
    }

    /**
     * Inserta pedido + líneas en transacción.
     * (Opcional para nota): descuenta stock.
     */
    public long crearPedido(PedidoRequest request, boolean descontarStock) {
        if (request == null || request.getIdUsuario() == null || request.getLineas() == null || request.getLineas().isEmpty()) {
            throw new IllegalArgumentException("Pedido inválido: falta idUsuario o líneas");
        }

        double total = 0.0;
        for (LineaPedidoRequest l : request.getLineas()) {
            if (l.getCantidad() <= 0) {
                throw new IllegalArgumentException("Cantidad inválida en una línea");
            }
            total += (l.getPrecioUnitario() * l.getCantidad());
        }

        String sqlPedido = "INSERT INTO pedido (id_usuario, total_pedido) VALUES (?, ?)";
        String sqlLinea = "INSERT INTO linea_pedido (id_pedido, id_producto, cantidad, precio_unitario_momento) VALUES (?, ?, ?, ?)";
        String sqlStock = "UPDATE producto SET stock = stock - ? WHERE id_producto = ? AND stock >= ?";

        Connection con = null;
        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);

            long idPedido;
            try (PreparedStatement psPedido = con.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS)) {
                psPedido.setLong(1, request.getIdUsuario());
                psPedido.setDouble(2, total);
                psPedido.executeUpdate();

                try (ResultSet keys = psPedido.getGeneratedKeys()) {
                    if (!keys.next()) {
                        throw new SQLException("No se pudo recuperar el ID del pedido");
                    }
                    idPedido = keys.getLong(1);
                }
            }

            // Insertar líneas
            try (PreparedStatement psLinea = con.prepareStatement(sqlLinea)) {
                for (LineaPedidoRequest l : request.getLineas()) {
                    psLinea.setLong(1, idPedido);
                    psLinea.setLong(2, l.getIdProducto());
                    psLinea.setInt(3, l.getCantidad());
                    psLinea.setDouble(4, l.getPrecioUnitario());
                    psLinea.addBatch();
                }
                psLinea.executeBatch();
            }

            // (Opcional) descontar stock
            if (descontarStock) {
                try (PreparedStatement psStock = con.prepareStatement(sqlStock)) {
                    for (LineaPedidoRequest l : request.getLineas()) {
                        psStock.setInt(1, l.getCantidad());
                        psStock.setLong(2, l.getIdProducto());
                        psStock.setInt(3, l.getCantidad());
                        int updated = psStock.executeUpdate();
                        if (updated == 0) {
                            throw new SQLException("Stock insuficiente para id_producto=" + l.getIdProducto());
                        }
                    }
                }
            }

            con.commit();
            return idPedido;

        } catch (SQLException e) {
            if (con != null) {
                try { con.rollback(); } catch (SQLException ignored) {}
            }
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try { con.close(); } catch (SQLException ignored) {}
            }
        }
    }
}