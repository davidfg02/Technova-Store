package com.example.demo.model;


import org.springframework.web.bind.annotation.RequestMapping;

public class Usuario {

    public static final String ID_USUARIO = "id_usuario";
    public static final String EMAIL =  "email";
    public static final String PASSWORD = "password";
    public static final String ROL = "rol";

    private Long id_usuario;
    private String email;
    private String password;
    private EnumRol rol;

    public Usuario() {}

    public Usuario(Long id_usuario, String email, String password, EnumRol rol) {
        this.id_usuario = id_usuario;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }
    public Long getId() { return id_usuario; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public EnumRol getRol() { return rol; }
}