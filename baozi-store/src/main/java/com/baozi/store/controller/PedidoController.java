package com.baozi.store.controller;

import com.baozi.store.model.Pedido;
import com.baozi.store.repository.PedidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepository;

    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    // POST /pedidos
    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody Pedido pedido) {
        Pedido salvo = pedidoRepository.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // GET /pedidos 
    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return ResponseEntity.ok(pedidos);
    }

    // GET /pedidos/{id} 
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /pedidos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @RequestBody Pedido pedidoAtualizado) {
        return pedidoRepository.findById(id).map(pedido -> {
            pedido.setClienteId(pedidoAtualizado.getClienteId());
            pedido.setProdutoId(pedidoAtualizado.getProdutoId());
            pedido.setQuantidade(pedidoAtualizado.getQuantidade());
            Pedido salvo = pedidoRepository.save(pedido);
            return ResponseEntity.ok(salvo);
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE /pedidos/{id} 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        if (!pedidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pedidoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
