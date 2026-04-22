package com.sena.creyese.dentvision_spring.repository;



import com.sena.creyese.dentvision_spring.modelo.TecnicoDental;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;



import java.util.List;

import java.util.Optional;



/**

 * Repositorio Spring Data JPA para la gestión de técnicos dentales en el sistema DentVision.

 * 

 * Esta interfaz proporciona métodos personalizados para realizar operaciones de base de datos

 * sobre la entidad TecnicoDental, incluyendo consultas por documento/especialización,

 * gestión de estados, búsqueda por certificado y control de disponibilidad.

 * 

 * @author Creyese

 * @version 1.0

 * @since 2026

 * 

 * Funcionalidades principales:

 * - Búsqueda por documento de identidad

 * - Consulta por nombres, apellidos y especialización

 * - Filtrado por estado (activos/inactivos)

 * - Búsqueda por número de certificado

 * - Consultas por email de usuario asociado

 * - Filtrado por años de experiencia

 * - Gestión de disponibilidad

 */

@Repository

public interface TecnicoDentalRepository extends JpaRepository<TecnicoDental,Long> {

    

    /**

     * Busca un técnico dental por su número de documento de identidad.

     * 

     * @param documento Número de documento del técnico

     * @return Optional con el técnico encontrado o vacío si no existe

     */

    Optional<TecnicoDental> findByDocumento(String documento);

    

    /**

     * Busca técnicos dentales cuyos nombres contengan el texto especificado (ignora mayúsculas/minúsculas).

     * 

     * @param nombres Texto a buscar en los nombres

     * @return Lista de técnicos que coinciden con la búsqueda

     */

    List<TecnicoDental> findByNombresContainingIgnoreCase(String nombres);

    

    /**

     * Busca técnicos dentales cuyos apellidos contengan el texto especificado (ignora mayúsculas/minúsculas).

     * 

     * @param apellidos Texto a buscar en los apellidos

     * @return Lista de técnicos que coinciden con la búsqueda

     */

    List<TecnicoDental> findByApellidosContainingIgnoreCase(String apellidos);

    

    /**

     * Busca técnicos dentales cuya especialización contenga el texto especificado (ignora mayúsculas/minúsculas).

     * 

     * @param especializacion Especialización a buscar (PROTESIS, ORTODONCIA, LABORATORIO)

     * @return Lista de técnicos con esa especialización

     */

    List<TecnicoDental> findByEspecializacionContainingIgnoreCase(String especializacion);

    

    /**

     * Obtiene todos los técnicos dentales con estado activo (true).

     * 

     * @return Lista de técnicos activos

     */

    List<TecnicoDental> findByEstadoTrue();

    

    /**

     * Obtiene todos los técnicos dentales con estado inactivo (false).

     * 

     * @return Lista de técnicos inactivos

     */

    List<TecnicoDental> findByEstadoFalse();

    

    /**

     * Obtiene todos los técnicos dentales activos ordenados alfabéticamente por nombres.

     * 

     * @return Lista de técnicos activos ordenados por nombres

     */

    @Query("SELECT t FROM TecnicoDental t WHERE t.estado = true ORDER BY t.nombres ASC")

    List<TecnicoDental> findActivosOrderByNombres();

    

    /**

     * Cuenta el número total de técnicos dentales activos en el sistema.

     * 

     * @return Número de técnicos con estado true

     */

    @Query("SELECT COUNT(t) FROM TecnicoDental t WHERE t.estado = true")

    Long countTecnicosActivos();

    

    /**

     * Busca técnicos dentales cuyo número de certificado contenga el texto especificado (ignora mayúsculas/minúsculas).

     * 

     * @param numeroCertificado Número de certificado a buscar

     * @return Lista de técnicos que coinciden con la búsqueda

     */

    List<TecnicoDental> findByNumeroCertificadoContainingIgnoreCase(String numeroCertificado);

    

    /**

     * Busca un técnico dental por el email de su usuario asociado.

     * 

     * @param email Email del usuario asociado al técnico

     * @return Optional con el técnico encontrado o vacío si no existe

     */

    @Query("SELECT t FROM TecnicoDental t WHERE t.usuario.email = :email")

    Optional<TecnicoDental> findByUsuarioEmail(@Param("email") String email);

    

    /**

     * Obtiene todos los técnicos dentales cuya experiencia sea mayor al número de años especificado.

     * 

     * @param years Años mínimos de experiencia

     * @return Lista de técnicos con experiencia mayor a la especificada

     */

    List<TecnicoDental> findByExperienciaYearsGreaterThan(Integer years);

    

    /**

     * Obtiene todos los técnicos dentales que están disponibles y activos.

     * 

     * @return Lista de técnicos disponibles y activos

     */

    @Query("SELECT t FROM TecnicoDental t WHERE t.disponibilidad = true AND t.estado = true")

    List<TecnicoDental> findTecnicosDisponibles();

}

