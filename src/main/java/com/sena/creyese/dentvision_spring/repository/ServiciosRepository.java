package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Servicios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiciosRepository extends JpaRepository<Servicios, Long> {

    List<Servicios> findByNombreContainingIgnoreCase(String nombre);

    List<Servicios> findByPrecioLessThan(Double precioServicios);
}
