package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.TecnicoDental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TecnicoDentalRepository extends JpaRepository<TecnicoDental,Long> {
    Optional<TecnicoDental> findByDocumento(String documento);
}
