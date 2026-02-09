package com.technova;

public class Producto {

    public int id_producto;
    public String sku;
    public String nombre;
    public String descripcion;
    public double precio;
    public int stock;
    public String categoria;
    public String imagen;

    public Producto(int id_producto, String sku, String nombre,
                    String descripcion, double precio,
                    int stock, String categoria, String imagen) {
        this.id_producto = id_producto;
        this.sku = sku;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.imagen = imagen;
    }
}
