package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Procedimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcedimientoRepository extends JpaRepository<Procedimiento,Long> {

    List<Procedimiento> findByDescripcionContainingIgnoreCase(String descripcion);

    List<Procedimiento> findByEstadoIsContainingIgnoreCase(boolean estado);
}
