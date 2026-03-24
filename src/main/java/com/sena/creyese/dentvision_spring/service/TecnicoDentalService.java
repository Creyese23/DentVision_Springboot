package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.TecnicoDental;
import com.sena.creyese.dentvision_spring.repository.TecnicoDentalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TecnicoDentalService {
    private final TecnicoDentalRepository tecnicoDentalRepository;

    public TecnicoDentalService(TecnicoDentalRepository tecnicoDentalRepository) {
        this.tecnicoDentalRepository = tecnicoDentalRepository;
    }

    public List<TecnicoDental> listarTodos() {
        return tecnicoDentalRepository.findAll();
    }

    public Optional<TecnicoDental> obtenerPorId(Long id) {
        return tecnicoDentalRepository.findById(id);
    }

    public Optional<TecnicoDental> obtenerPorDocumento(String documento) {
        return tecnicoDentalRepository.findByDocumento(documento);
    }

    public TecnicoDental guardar(TecnicoDental tecnicoDental) {
        return tecnicoDentalRepository.save(tecnicoDental);
    }

    public void eliminar(Long id) {
        tecnicoDentalRepository.deleteById(id);
    }
}
