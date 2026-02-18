package com.example.demo.controller;

import com.example.demo.model.Pedido;
import com.example.demo.model.PedidoRequest;
import com.example.demo.model.PedidoResponse;
import com.example.demo.repository.PedidoRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
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
    @GetMapping("/fecha")
    public List<Pedido> listarPedidos(
            @RequestParam(required = false) String pedido_estado,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            @RequestParam(required = false) LocalDateTime fechaIni,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            @RequestParam(required = false) LocalDateTime fechaFin
    ) {
        return repository.findAll(pedido_estado, fechaIni, fechaFin);
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