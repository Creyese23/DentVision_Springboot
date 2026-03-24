package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Insumo;
import com.sena.creyese.dentvision_spring.repository.InsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsumoService {

    @Autowired
    private final InsumoRepository insumoRepository;

    public InsumoService(InsumoRepository insumoRepository) {
        this.insumoRepository = insumoRepository;
    }

    public List<Insumo> listarTodos() {
        return insumoRepository.findAll();
    }

    public Optional<Insumo> obtenerPorId(Long id) {
        return insumoRepository.findById(id);
    }

    public Insumo guardar(Insumo insumo) {
        return insumoRepository.save(insumo);
    }

    public void eliminar(Long id) {
        insumoRepository.deleteById(id);
    }
}
