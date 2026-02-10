package com.grupo3.technova.model;


public class Usuario {

    private Long id_usuario;
    private String email;
    private String password;
    private String rol;

    public Usuario() {}

    public Usuario(Long id_usuario, String email, String password, String rol) {
        this.id_usuario = id_usuario;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }
    public Long getId() { return id_usuario; }
    public String getEmail()
    {
        return email;
    }
    public String getPassword() { return password;}
    public String getRol() { return rol; }
}