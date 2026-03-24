package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.OrdenTrabajo;
import com.sena.creyese.dentvision_spring.repository.OrdenTrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenTrabajoService {

    @Autowired
    private final OrdenTrabajoRepository ordenTrabajoRepository;

    public OrdenTrabajoService(OrdenTrabajoRepository ordenTrabajoRepository) {
        this.ordenTrabajoRepository = ordenTrabajoRepository;
    }

    public List<OrdenTrabajo> listarTodos() {
        return ordenTrabajoRepository.findAll();
    }

    public Optional<OrdenTrabajo> obtenerPorId(Long id) {
        return ordenTrabajoRepository.findById(id);
    }

    public OrdenTrabajo guardar(OrdenTrabajo ordenTrabajo) {
        return ordenTrabajoRepository.save(ordenTrabajo);
    }

    public void eliminar(Long id) {
        ordenTrabajoRepository.deleteById(id);
    }
}
