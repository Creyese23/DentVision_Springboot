package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Admin;
import com.sena.creyese.dentvision_spring.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> listarTodos() {
        return adminRepository.findAll();
    }

    public Optional<Admin> obtenerPorId(Long id) {
        return adminRepository.findById(id);
    }

    public Optional<Admin> obtenerPorDocumento(String documento) {
        return adminRepository.findByDocumento(documento);
    }

    public Admin guardar(Admin admin) {
        return adminRepository.save(admin);
    }

    public void eliminar(Long id) {
        adminRepository.deleteById(id);
    }
}
