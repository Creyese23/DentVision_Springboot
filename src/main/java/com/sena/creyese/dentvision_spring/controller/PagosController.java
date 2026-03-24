package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Pagos;
import com.sena.creyese.dentvision_spring.service.PagosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagosController {

    @Autowired
    private final PagosService pagosService;

    public PagosController(PagosService pagosService) {
        this.pagosService = pagosService;
    }

    @GetMapping
    public List<Pagos> listarTodos() {
        return pagosService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagos> obtenerPorId(@PathVariable Long id) {
        return pagosService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pagos> crear(@Valid @RequestBody Pagos pagos) {
        Pagos nuevo = pagosService.guardar(pagos);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagos> actualizar(@PathVariable Long id, @Valid @RequestBody Pagos pagos) {
        return pagosService.obtenerPorId(id)
                .map(existente -> {
                    existente.setFecha(pagos.getFecha());
                    existente.setMetodPago(pagos.getMetodPago());
                    existente.setMonto(pagos.getMonto());
                    existente.setFacturas(pagos.getFacturas());
                    Pagos actualizado = pagosService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (pagosService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        pagosService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
