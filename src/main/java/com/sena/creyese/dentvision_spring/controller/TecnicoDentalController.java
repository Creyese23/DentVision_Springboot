package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.TecnicoDental;
import com.sena.creyese.dentvision_spring.service.TecnicoDentalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tecnico_dental")
public class TecnicoDentalController {

    @Autowired
    private final TecnicoDentalService tecnicoDentalService;

    public TecnicoDentalController(TecnicoDentalService tecnicoDentalService) {
        this.tecnicoDentalService = tecnicoDentalService;
    }

    @GetMapping
    public List<TecnicoDental> listarTodos() {
        return tecnicoDentalService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TecnicoDental> obtenerPorId(@PathVariable Long id) {
        return tecnicoDentalService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TecnicoDental> crear(@Valid @RequestBody TecnicoDental tecnicoDental) {
        TecnicoDental nuevo = tecnicoDentalService.guardar(tecnicoDental);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TecnicoDental> actualizar(@PathVariable Long id, @Valid @RequestBody TecnicoDental tecnicoDental) {
        return tecnicoDentalService.obtenerPorId(id)
                .map(existente -> {
                    existente.setTipoDocumento(tecnicoDental.getTipoDocumento());
                    existente.setDocumento(tecnicoDental.getDocumento());
                    existente.setTelefono(tecnicoDental.getTelefono());
                    existente.setGenero(tecnicoDental.getGenero());
                    existente.setEspecialidad(tecnicoDental.getEspecialidad());
                    existente.setFecha_nacimiento(tecnicoDental.getFecha_nacimiento());
                    existente.setUsuario(tecnicoDental.getUsuario());
                    TecnicoDental actualizado = tecnicoDentalService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (tecnicoDentalService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        tecnicoDentalService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
