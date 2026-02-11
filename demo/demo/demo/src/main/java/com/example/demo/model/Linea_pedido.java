package com.example.demo.model;


public class Linea_pedido {

    private int id_linea_pedido;
    private int id_pedido;
    private int id_producto;
    private int cantidad;
    private double precio_unitario_momento;

    public Linea_pedido() {}

    public Linea_pedido(int id_linea_pedido, int id_pedido, int id_producto, int cantidad, double precio_unitario__momento) {
        this.id_linea_pedido = id_linea_pedido;
        this.id_pedido = id_pedido;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio_unitario_momento = precio_unitario__momento;
    }
    
    public int getId_linea_pedido() { return id_linea_pedido; }
    public void setId_linea_pedido(int id_linea_pedido) { this.id_linea_pedido = id_linea_pedido; }
    
    public int getId_pedido() { return id_pedido; }
    public void setId_pedido(int id_pedido) { this.id_pedido = id_pedido; }
    
    public int getId_producto() { return id_producto; }
    public void setId_producto(int id_producto) { this.id_producto = id_producto; }
    
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    
    public double getPrecio_unitario_momento() { return precio_unitario_momento; }
    public void setPrecio_unitario_momento(double precio_unitario_momento) { this.precio_unitario_momento = precio_unitario_momento; }
}