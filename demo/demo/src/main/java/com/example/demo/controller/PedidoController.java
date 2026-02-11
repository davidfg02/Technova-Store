package com.example.demo.controller;
    import com.example.demo.model.Pedido;
import com.example.demo.repository.PedidoRepository;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoRepository repository;

    public PedidoController(PedidoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Pedido> listar_pedido() {
        return repository.findAll();
    }

    @PostMapping
    public Pedido crear(@RequestBody Pedido pedido) {
        return repository.save(pedido);
    }
}