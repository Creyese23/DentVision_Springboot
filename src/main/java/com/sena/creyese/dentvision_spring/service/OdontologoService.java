package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Odontologo;
import com.sena.creyese.dentvision_spring.repository.OdontologoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    private final OdontologoRepository odontologoRepository;

    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public List<Odontologo> listarTodos() {
        return odontologoRepository.findAll();
    }

    public Optional<Odontologo> obtenerPorId(Long id) {
        return odontologoRepository.findById(id);
    }

    public Optional<Odontologo> obtenerPorDocumento(String documento) {
        return odontologoRepository.findByDocumento(documento);
    }

    public Odontologo guardar(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    public void eliminar(Long id) {
        odontologoRepository.deleteById(id);
    }
}
