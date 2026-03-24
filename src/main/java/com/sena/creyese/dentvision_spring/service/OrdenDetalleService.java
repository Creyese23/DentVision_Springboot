package com.sena.creyese.dentvision_spring.service;


import com.sena.creyese.dentvision_spring.modelo.OrdenDetalle;
import com.sena.creyese.dentvision_spring.repository.OrdenDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenDetalleService {

    @Autowired
    private final OrdenDetalleRepository ordenDetalleRepository;

    public OrdenDetalleService(OrdenDetalleRepository ordenDetalleRepository) {
        this.ordenDetalleRepository = ordenDetalleRepository;
    }

    public List<OrdenDetalle> listarTodos() {
        return ordenDetalleRepository.findAll();
    }

    public Optional<OrdenDetalle> obtenerPorId(Long id) {
        return ordenDetalleRepository.findById(id);
    }

    public OrdenDetalle guardar(OrdenDetalle ordenDetalle) {
        return ordenDetalleRepository.save(ordenDetalle);
    }

    public void eliminar(Long id) {
        ordenDetalleRepository.deleteById(id);
    }
}
