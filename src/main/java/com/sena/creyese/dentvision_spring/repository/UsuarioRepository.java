package com.sena.creyese.dentvision_spring.repository;

import com.sena.creyese.dentvision_spring.enums.Estado;
import com.sena.creyese.dentvision_spring.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio Spring Data JPA para la gestión de usuarios en el sistema DentVision.
 * 
 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos
 * sobre la entidad Usuario, incluyendo consultas por email/datos personales y gestión de estados.
 * 
 * @author Creyese
 * @version 1.0
 * @since 2026
 * 
 * Funcionalidades principales:
 * - Autenticación y búsqueda por email
 * - Consulta por nombres y apellidos
 * - Filtrado por estado (activos/inactivos)
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    
    /**
     * Busca un usuario por su dirección de email.
     * 
     * @param email Email del usuario a buscar
     * @return Optional con el usuario encontrado o vacío si no existe
     */
    Optional<Usuario> findByEmail(String email);
    
    /**
     * Busca usuarios cuyos nombres contengan el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param nombres Texto a buscar en los nombres
     * @return Lista de usuarios que coinciden con la búsqueda
     */
    List<Usuario> findByNombresContainingIgnoreCase(String nombres);
    
    /**
     * Busca usuarios cuyos apellidos contengan el texto especificado (ignora mayúsculas/minúsculas).
     * 
     * @param apellidos Texto a buscar en los apellidos
     * @return Lista de usuarios que coinciden con la búsqueda
     */
    List<Usuario> findByApellidosContainingIgnoreCase(String apellidos);
    
    /**
     * Obtiene todos los usuarios con estado específico.
     * 
     * @param estado Estado del usuario a buscar
     * @return Lista de usuarios con ese estado
     */
    List<Usuario> findByEstado(Estado estado);
}
