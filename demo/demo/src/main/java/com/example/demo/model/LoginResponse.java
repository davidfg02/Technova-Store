package com.example.demo.model;


public class LoginResponse {
    private String status;
    private String mensaje;
    private Pedido pedido;

    public LoginResponse(String status, String mensaje, Pedido pedido) {
        this.status = status;
        this.mensaje = mensaje;
        this.pedido = pedido;
    }
}


/*public class LoginResponse {

    private String status;
    private String rol;

    public LoginResponse(String status, String rol) {
        this.status = status;
        this.rol = rol;
    }

    public String getStatus() { return status; }
    public String getRol() { return rol; }
}*/

