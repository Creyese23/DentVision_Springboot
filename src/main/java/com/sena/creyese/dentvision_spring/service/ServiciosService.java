package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Servicios;
import com.sena.creyese.dentvision_spring.repository.ServiciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiciosService {

    @Autowired
    private final ServiciosRepository serviciosRepository;

    public ServiciosService(ServiciosRepository serviciosRepository) {
        this.serviciosRepository = serviciosRepository;
    }

    public List<Servicios> listarTodos() {
        return serviciosRepository.findAll();
    }

    public Optional<Servicios> obtenerPorId(Long id) {
        return serviciosRepository.findById(id);
    }

    public Servicios guardar(Servicios servicios) {
        return serviciosRepository.save(servicios);
    }

    public void eliminar(Long id) {
        serviciosRepository.deleteById(id);
    }
}
