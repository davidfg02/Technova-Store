package com.example.demo.controller;

import com.example.demo.model.Pedido;
import com.example.demo.model.PedidoRequest;
import com.example.demo.model.PedidoResponse;
import com.example.demo.repository.PedidoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoRepository repository;

    public PedidoController(PedidoRepository repository) {
        this.repository = repository;
    }

    /**
     * Endpoint opcional de lectura (si lo usáis en evidencias): GET /api/pedidos
     * Usa procedimientos almacenados (CALL sp_pedidos_listar).
     */
    @GetMapping
    public List<Pedido> listarPedidos(
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) Date fechaIni,
            @RequestParam(required = false) Date fechaFin
    ) {
        return repository.findAll(estado, fechaIni, fechaFin);
    }

    /**
     * Endpoint requerido: POST /api/pedidos
     */
    @PostMapping
    public ResponseEntity<PedidoResponse> crear(@RequestBody PedidoRequest request,
                                                @RequestParam(defaultValue = "false") boolean descontarStock) {
        long idPedido = repository.crearPedido(request, descontarStock);
        return ResponseEntity.ok(new PedidoResponse("ok", idPedido));
    }
}