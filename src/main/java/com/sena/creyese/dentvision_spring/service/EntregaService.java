package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Entrega;
import com.sena.creyese.dentvision_spring.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntregaService {

    @Autowired
    private final EntregaRepository entregaRepository;

    public EntregaService(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    public List<Entrega> listarTodos() {
        return entregaRepository.findAll();
    }

    public Optional<Entrega> obtenerPorId(Long id) {
        return entregaRepository.findById(id);
    }

    public Entrega guardar(Entrega entrega) {
        return entregaRepository.save(entrega);
    }

    public void eliminar(Long id) {
        entregaRepository.deleteById(id);
    }
}
