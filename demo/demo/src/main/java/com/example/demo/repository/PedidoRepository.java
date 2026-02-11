package com.example.demo.repository;

import com.example.demo.model.Pedido;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

@Repository
public class PedidoRepository {
    private final DataSource dataSource;

    public PedidoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Pedido> findAll() {
        List<Pedido> pedidos = new ArrayList<>();

        String sql = "SELECT id_pedido, id_usuario,fecha, total_pedido FROM pedido";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pedido p = new Pedido(
                        rs.getLong("id_pedido"),
                        rs.getLong("id_usuario"),
                        rs.getDate("fecha"),
                        rs.getDouble("total_pedido")
                );
                pedidos.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pedidos;
    }

    public Pedido save(Pedido pedido) {
        String sql = "INSERT INTO pedido (id_pedido, id_usuario, fecha, total_pedido) VALUES ( ?, ?, ?, ?)";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, pedido.getId_usuario());
            ps.setDate(2, pedido.getFecha());
            ps.setDouble(3, pedido.getTotal_pedido());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pedido;
    }
}