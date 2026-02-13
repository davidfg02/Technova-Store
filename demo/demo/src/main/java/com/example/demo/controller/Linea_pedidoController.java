package com.example.demo.controller;
import com.example.demo.model.Linea_pedido;
import com.example.demo.repository.Linea_pedidoRepository;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/linea_pedido")
public class Linea_pedidoController {

    private final Linea_pedidoRepository repository;

    public Linea_pedidoController(Linea_pedidoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Linea_pedido> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Linea_pedido crear(@RequestBody Linea_pedido linea_pedido) {
        return repository.save(linea_pedido);
    }
}