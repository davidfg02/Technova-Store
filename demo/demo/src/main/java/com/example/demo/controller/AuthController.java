package com.example.demo.controller;


import com.example.demo.model.LoginRequest;
import com.example.demo.model.LoginResponse;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        LoginResponse response = usuarioRepository.login(
                request.getEmail(),
                request.getPassword()
        );

        if (response == null) {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }

        return ResponseEntity.ok(response);
    }
}

