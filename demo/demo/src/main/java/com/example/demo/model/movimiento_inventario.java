package com.example.demo.model;
import java.util.Date;

public class movimiento_inventario {
    private Integer id_movimiento;
    private  Integer id_producto;
    private TipoMovimiento tipo_movimiento;
    private Date fecha;
    private  int cantidad;
    private  String motivo;

    public movimiento_inventario(Integer id_movimiento, String motivo, int cantidad, Date fecha, TipoMovimiento tipo_movimiento, Integer id_producto) {
        this.id_movimiento = id_movimiento;
        this.motivo = motivo;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.tipo_movimiento = tipo_movimiento;
        this.id_producto = id_producto;
    }

    public Integer getId_movimiento() {return id_movimiento;}
    public String getMotivo() {return motivo;}
    public int getCantidad() {return cantidad;}
    public Date getFecha() {return fecha;}
    public TipoMovimiento gettipo_movimiento() {return tipo_movimiento;}
    public Integer getId_producto() {return id_producto;}}
