package com.example.demo.model;

import java.sql.Date;
import java.time.LocalDateTime;

public class Pedido {

    public static final String ID_PEDIDO = "id_pedido";
    public static final String ID_USUARIO = "id_usuario";
    public static final String FECHA = "fecha";
    public static final String TOTAL_PEDIDO = "total_pedido";
    public static final String PEDIDO_ESTADO = "pedido_estado";

    private Long id_pedido;
    private Long id_usuario;
    private LocalDateTime fecha;
    private double total_pedido;
    private EnumPedidoEstado pedido_estado;

    public Pedido(Long id_pedido, Long id_usuario, LocalDateTime fecha, double total_pedido, EnumPedidoEstado pedido_estado) {
        this.id_pedido = id_pedido;
        this.id_usuario = id_usuario;
        this.fecha = fecha;
        this.total_pedido = total_pedido;
        this.pedido_estado = pedido_estado;
    }

    public Long getId_pedido() {
        return id_pedido;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public double getTotal_pedido() {
        return total_pedido;
    }
    public EnumPedidoEstado getPedido_estado() {return pedido_estado;}
}