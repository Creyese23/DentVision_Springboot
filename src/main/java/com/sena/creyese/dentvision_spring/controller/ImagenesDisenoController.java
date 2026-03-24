package com.sena.creyese.dentvision_spring.controller;

import com.sena.creyese.dentvision_spring.modelo.ImagenesDiseno;
import com.sena.creyese.dentvision_spring.service.ImagenesDisenoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/imagenes-diseno")
public class ImagenesDisenoController {

    @Autowired
    private final ImagenesDisenoService imagenesDisenoService;

    public ImagenesDisenoController(ImagenesDisenoService imagenesDisenoService) {
        this.imagenesDisenoService = imagenesDisenoService;
    }

    @GetMapping
    public List<ImagenesDiseno> listarTodos() {
        return imagenesDisenoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImagenesDiseno> obtenerPorId(@PathVariable Long id) {
        return imagenesDisenoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ImagenesDiseno> crear(@Valid @RequestBody ImagenesDiseno imagenesDiseno) {
        ImagenesDiseno nueva = imagenesDisenoService.guardar(imagenesDiseno);
        return ResponseEntity.ok(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImagenesDiseno> actualizar(@PathVariable Long id, @Valid @RequestBody ImagenesDiseno imagenesDiseno) {
        return imagenesDisenoService.obtenerPorId(id)
                .map(existente -> {
                    existente.setImagen(imagenesDiseno.getImagen());
                    existente.setDescripcion(imagenesDiseno.getDescripcion());
                    existente.setProcedimiento(imagenesDiseno.getProcedimiento());
                    ImagenesDiseno actualizada = imagenesDisenoService.guardar(existente);
                    return ResponseEntity.ok(actualizada);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (imagenesDisenoService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        imagenesDisenoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
