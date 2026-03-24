package com.sena.creyese.dentvision_spring.service;


import com.sena.creyese.dentvision_spring.modelo.AuxiliarAdmin;
import com.sena.creyese.dentvision_spring.repository.AuxiliarAdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuxiliarAdminService {
    private final AuxiliarAdminRepository auxiliarAdminRepository;

    public AuxiliarAdminService(AuxiliarAdminRepository auxiliarAdminRepository) {
        this.auxiliarAdminRepository = auxiliarAdminRepository;
    }

    public List<AuxiliarAdmin> listarTodos() {
        return auxiliarAdminRepository.findAll();
    }

    public Optional<AuxiliarAdmin> obtenerPorId(Long id) {
        return auxiliarAdminRepository.findById(id);
    }

    public Optional<AuxiliarAdmin> obtenerPorDocumento(String documento) {
        return auxiliarAdminRepository.findByDocumento(documento);
    }

    public AuxiliarAdmin guardar(AuxiliarAdmin auxiliarAdmin) {
        return auxiliarAdminRepository.save(auxiliarAdmin);
    }

    public void eliminar(Long id) {
        auxiliarAdminRepository.deleteById(id);
    }
}
