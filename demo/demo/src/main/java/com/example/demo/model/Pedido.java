package com.example.demo.model;
import java.sql.Date;

public class Pedido {
    private Long id_pedido;
    private Long id_usuario;
    private Date fecha;
    private double total_pedido;

    public Pedido() {}

    public Pedido(Long id_pedido, Long id_usuario, Date fecha, double total_pedido) {
        this.id_pedido = id_pedido;
        this.id_usuario = id_usuario;
        this.fecha = fecha;
        this.total_pedido = total_pedido;
    }
    public Long getId_pedido() {return id_pedido;}
    public Long getId_usuario() { return id_usuario; }
    public Date getFecha() { return fecha; }
    public double getTotal_pedido() { return total_pedido; }
}