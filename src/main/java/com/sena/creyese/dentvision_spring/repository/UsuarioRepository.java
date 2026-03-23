package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
