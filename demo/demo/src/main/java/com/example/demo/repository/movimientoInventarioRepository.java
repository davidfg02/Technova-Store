package com.example.demo.repository;
import com.example.demo.model.TipoMovimiento;
import com.example.demo.model.movimiento_inventario;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class movimientoInventarioRepository {

        private final DataSource dataSource;

        public movimientoInventarioRepository(DataSource dataSource) {
            this.dataSource = dataSource;
        }

        public List<movimiento_inventario> findAll() {
            List<movimiento_inventario> movimiento_inventario = new ArrayList<>();

            String sql = "SELECT * FROM movimiento_inventario";

            try (Connection con = dataSource.getConnection();
                 PreparedStatement p = con.prepareStatement(sql);
                 ResultSet r = p.executeQuery()) {

                while (r.next()) {
                    movimiento_inventario u = new movimiento_inventario(
                            r.getInt("id_movimiento"),
                            r.getString("motivo"),
                            r.getInt("cantidad"),
                            r.getDate("fecha"),
                            TipoMovimiento.valueOf(r.getString("tipo_movimiento")),
                            r.getInt("id_producto")
                            );
                    movimiento_inventario.add(u);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return movimiento_inventario;
        }

        public void save(movimiento_inventario movimiento_inventario) {
            String sql = "INSERT INTO movimiento_inventario ( motivo,cantidad,id_producto) VALUES ( ?, ?, ?)";

            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, movimiento_inventario.getMotivo());
                ps.setInt(2, movimiento_inventario.getCantidad());
                ps.setInt(3, movimiento_inventario.getId_producto());

                ps.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }