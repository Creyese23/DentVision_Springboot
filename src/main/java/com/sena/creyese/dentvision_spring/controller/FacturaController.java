package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Factura;
import com.sena.creyese.dentvision_spring.service.FacturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public List<Factura> listarTodos() {
        return facturaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> obtenerPorId(@PathVariable Long id) {
        return facturaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Factura> crear(@Valid @RequestBody Factura factura) {
        Factura nueva = facturaService.guardar(factura);
        return ResponseEntity.ok(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factura> actualizar(@PathVariable Long id, @Valid @RequestBody Factura factura) {
        return facturaService.obtenerPorId(id)
                .map(existente -> {
                    existente.setFecha(factura.getFecha());
                    existente.setValor(factura.getValor());
                    existente.setEstado(factura.isEstado());
                    existente.setPaciente(factura.getPaciente());
                    Factura actualizada = facturaService.guardar(existente);
                    return ResponseEntity.ok(actualizada);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (facturaService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        facturaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
