package com.example.demo.repository;

import com.example.demo.model.Linea_pedido;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class Linea_pedidoRepository {
    private final DataSource dataSource;

    public Linea_pedidoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Linea_pedido> findAll() {
        List<Linea_pedido> linea_pedido = new ArrayList<>();

        String sql = "SELECT id_linea_pedido, id_pedido, id_producto, cantidad, precio_unitario_momento FROM linea_pedido";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Linea_pedido u = new Linea_pedido(
                        rs.getInt("id_linea_pedido"),
                        rs.getInt("id_pedido"),
                        rs.getInt("id_producto"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio_unitario_momento")
                );
                linea_pedido.add(u);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return linea_pedido;  
    }

    public Linea_pedido save(Linea_pedido linea_pedido) {
        String sql = "INSERT INTO linea_pedido (id_linea_pedido, id_pedido, id_producto, cantidad, precio_unitario_momento) VALUES ( ?, ?, ?, ?, ?)";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, linea_pedido.getId_linea_pedido());
            ps.setInt(2, linea_pedido.getId_pedido());
            ps.setInt(3, linea_pedido.getId_producto());
            ps.setInt(4, linea_pedido.getCantidad());
            ps.setDouble(5, linea_pedido.getPrecio_unitario_momento());

            ps.executeUpdate(); 

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return linea_pedido;
    }
}