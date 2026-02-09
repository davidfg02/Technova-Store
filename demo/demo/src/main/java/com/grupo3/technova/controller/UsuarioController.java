package com.grupo3.technova.controller;
import com.grupo3.technova.model.Usuario;
import com.grupo3.technova.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Usuario> listar() {
        return repository.findAll();
    }

    @PostMapping
    public void crear(@RequestBody Usuario usuario) {
        repository.save(usuario);
    }
}