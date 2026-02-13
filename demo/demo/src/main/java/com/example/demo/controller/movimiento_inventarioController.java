package com.example.demo.controller;
import com.example.demo.model.movimiento_inventario;
import com.example.demo.repository.movimientoInventarioRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/movimientoinventario")
public class movimiento_inventarioController {

    private final movimientoInventarioRepository repository;

    public movimiento_inventarioController(movimientoInventarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<movimiento_inventario> listar() {
        return repository.findAll();}

    @PostMapping
    public void crear(@RequestBody movimiento_inventario movimiento_inventario) {
        repository.save(movimiento_inventario);
    }
}