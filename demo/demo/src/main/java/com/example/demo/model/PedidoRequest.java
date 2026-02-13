package com.example.demo.model;

import java.util.List;

/**
 * DTO de entrada para POST /api/pedidos
 *
 * Ejemplo JSON:
 * {
 *   "idUsuario": 1,
 *   "lineas": [
 *     {"idProducto": 2, "cantidad": 1, "precioUnitario": 199.99},
 *     {"idProducto": 5, "cantidad": 2, "precioUnitario": 9.50}
 *   ]
 * }
 */
public class PedidoRequest {
    private Long idUsuario;
    private List<LineaPedidoRequest> lineas;

    public PedidoRequest() {}

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<LineaPedidoRequest> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaPedidoRequest> lineas) {
        this.lineas = lineas;
    }
}
