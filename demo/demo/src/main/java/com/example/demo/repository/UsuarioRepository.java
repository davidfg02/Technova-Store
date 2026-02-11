package com.example.demo.repository;

import com.example.demo.model.EnumRol;
import com.example.demo.model.Usuario;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {
    private final DataSource dataSource;

    public UsuarioRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();

        String sql = "{CALL obtener_usuarios()}";

        try (Connection con = dataSource.getConnection();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getLong(Usuario.ID_USUARIO),
                        rs.getString(Usuario.EMAIL),
                        rs.getString(Usuario.PASSWORD),
                        EnumRol.valueOf(rs.getString(Usuario.ROL))
                );
                usuarios.add(u);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usuarios;  
    }

    public Usuario save(Usuario usuario) {
        String sql = "INSERT INTO usuario (email, password, rol) VALUES ( ?, ?, ?)";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getRol().name());

            ps.executeUpdate();
            
            // Obtener el ID generado
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    // Podría retornar el usuario con el ID si fuera necesario
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return usuario;
    }
}