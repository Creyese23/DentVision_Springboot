package com.sena.creyese.dentvision_spring.repository;


import com.sena.creyese.dentvision_spring.modelo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByDocumento(String documento);
}
