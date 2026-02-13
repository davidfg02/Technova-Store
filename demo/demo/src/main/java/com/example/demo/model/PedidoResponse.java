package com.example.demo.model;

public class PedidoResponse {
    private String status;
    private Long idPedido;

    public PedidoResponse() {}

    public PedidoResponse(String status, Long idPedido) {
        this.status = status;
        this.idPedido = idPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }
}
