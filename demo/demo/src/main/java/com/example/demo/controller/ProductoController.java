package com.example.demo.controller;

import com.example.demo.model.EnumCategoria;
import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    @GetMapping
    public List<Producto> listar(){
        return productoRepository.findAll();
    }

    @GetMapping("/{categoria}")
    public List<Producto> listarPorCategoria(@PathVariable EnumCategoria categoria){ return productoRepository.findByCategoria(categoria);}
    @PostMapping
    public void crear(@RequestBody Producto producto){
        productoRepository.save(producto);
    }
}