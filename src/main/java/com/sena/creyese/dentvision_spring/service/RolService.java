package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Roles;
import com.sena.creyese.dentvision_spring.repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {
    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public List<Roles> listarTodos() {
        return rolRepository.findAll();
    }

    public Optional<Roles> obtenerPorId(Long id) {
        return rolRepository.findById(id);
    }

    public Roles guardar(Roles rol) {
        return rolRepository.save(rol);
    }

    public void eliminar(Long id) {
        rolRepository.deleteById(id);
    }
}
