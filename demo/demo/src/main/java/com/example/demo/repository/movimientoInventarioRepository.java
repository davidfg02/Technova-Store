package com.example.demo.repository;
import com.example.demo.model.EnumTipoMovimiento;
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

//=================Se debe cambiar por el call correcto==============================//
        String sql = "CALL obtener_linea_pedido()";

        try (Connection con = dataSource.getConnection();
             PreparedStatement p = con.prepareStatement(sql);
             ResultSet rs = p.executeQuery()) {

            while (rs.next()) {
                movimiento_inventario u = new movimiento_inventario(
                        rs.getLong(com.example.demo.model.movimiento_inventario.ID_MOVIMIENTO),
                        rs.getString(com.example.demo.model.movimiento_inventario.MOTIVO),
                        rs.getInt(com.example.demo.model.movimiento_inventario.CANTIDAD),
                        rs.getDate(com.example.demo.model.movimiento_inventario.FECHA),
                        EnumTipoMovimiento.valueOf(rs.getString(com.example.demo.model.movimiento_inventario.ENUMTIPOMOVIMIENTO)),
                        rs.getLong(com.example.demo.model.movimiento_inventario.ID_PRODUCTO)

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
            ps.setLong(3, movimiento_inventario.getId_producto());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}