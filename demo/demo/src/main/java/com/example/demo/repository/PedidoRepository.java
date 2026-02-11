package com.example.demo.repository;

import com.example.demo.model.Pedido;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PedidoRepository {
    private final DataSource dataSource;

    public PedidoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Pedido> findAll() {
        List<Pedido> pedidos = new ArrayList<>();

        String sql = "{CALL obtener_pedidos()}";

        try (Connection con = dataSource.getConnection();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()){

            while (rs.next()) {
                Pedido p = new Pedido(
                        rs.getLong(Pedido.ID_PEDIDO),
                        rs.getLong(Pedido.ID_USUARIO),
                        rs.getDate(Pedido.FECHA),
                        rs.getDouble(Pedido.TOTAL_PEDIDO)
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