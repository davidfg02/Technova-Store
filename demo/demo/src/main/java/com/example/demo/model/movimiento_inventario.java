package com.example.demo.model;
import java.util.Date;

public class movimiento_inventario {
    public static final String ID_MOVIMIENTO = "id_movimiento";
    public static final String ID_PRODUCTO= "id_producto";
    public static final String ENUMTIPOMOVIMIENTO = "tipo_movimiento";
    public static final String FECHA = "fecha";
    public static final String CANTIDAD = "cantidad";
    public static final String MOTIVO = "motivo";

    private Long id_movimiento;
    private  Long id_producto;
    private EnumTipoMovimiento tipo_movimiento;
    private Date fecha;
    private  int cantidad;
    private  String motivo;

    public movimiento_inventario(Long id_movimiento, String motivo, int cantidad, Date fecha, EnumTipoMovimiento tipo_movimiento, Long id_producto) {
        this.id_movimiento = id_movimiento;
        this.motivo = motivo;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.tipo_movimiento = tipo_movimiento;
        this.id_producto = id_producto;
    }

    public Long getId_movimiento() {return id_movimiento;}
    public String getMotivo() {return motivo;}
    public int getCantidad() {return cantidad;}
    public Date getFecha() {return fecha;}
    public EnumTipoMovimiento gettipo_movimiento() {return tipo_movimiento;}
    public Long getId_producto() {return id_producto;}}