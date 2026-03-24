package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.Odontologo;
import com.sena.creyese.dentvision_spring.service.OdontologoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/odontologos")
public class OdontologoController {

    private final OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping
    public List<Odontologo> listarTodos() {
        return odontologoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> obtenerPorId(@PathVariable Long id) {
        return odontologoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Odontologo> crear(@Valid @RequestBody Odontologo odontologo) {
        Odontologo nuevo = odontologoService.guardar(odontologo);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Odontologo> actualizar(@PathVariable Long id, @Valid @RequestBody Odontologo odontologo) {
        return odontologoService.obtenerPorId(id)
                .map(existente -> {
                    existente.setTipoDocumento(odontologo.getTipoDocumento());
                    existente.setDocumento(odontologo.getDocumento());
                    existente.setGenero(odontologo.getGenero());
                    existente.setTelefono(odontologo.getTelefono());
                    existente.setDireccion(odontologo.getDireccion());
                    existente.setEspecialidad(odontologo.getEspecialidad());
                    existente.setN_Licencia(odontologo.getN_Licencia());
                    existente.setFecha_nacimiento(odontologo.getFecha_nacimiento());
                    existente.setUsuario(odontologo.getUsuario());
                    Odontologo actualizado = odontologoService.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (odontologoService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        odontologoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
