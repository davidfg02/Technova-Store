package com.example.demo.config;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Tarea 3.1: Al arrancar, comprueba la conexión y muestra un mensaje en consola.
 */
@Component
public class DbConnectionChecker {

    private final DataSource dataSource;

    public DbConnectionChecker(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void checkConnection() {
        try (Connection con = dataSource.getConnection()) {
            if (con != null && !con.isClosed()) {
                System.out.println("Conexión a MySQL establecida correctamente");
            }
        } catch (Exception e) {
            System.err.println("Error conectando a MySQL: " + e.getMessage());
        }
    }
}
