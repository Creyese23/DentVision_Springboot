package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.AuxiliarAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuxiliarAdminRepository extends JpaRepository<AuxiliarAdmin, Long> {
    Optional<AuxiliarAdmin> findByDocumento(String documento);
}
