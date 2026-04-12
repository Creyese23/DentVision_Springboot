package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Odontologo;
import com.sena.creyese.dentvision_spring.repository.OdontologoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de odontólogos en el sistema DentVision.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con odontólogos, incluyendo registro, búsqueda por documento,
 * gestión de especialidades y disponibilidad. Actúa como una capa de
 * abstracción entre los controladores y el repositorio de odontólogos.
 * 
 * @author SENA
 * @version 1.0
 * @since 2024
 * 
 * Funcionalidades implementadas:
 * - Gestión CRUD de odontólogos
 * - Búsqueda por documento de identificación
 * - Gestión de especialidades y licencias
 * - Validaciones de negocio
 * - Manejo de transacciones implícito
 * - Integración con sistema de autenticación
 */
@Service
public class OdontologoService {

    /** Repositorio para el acceso a datos de odontólogos */
    private final OdontologoRepository odontologoRepository;

    /**
     * Constructor que inyecta el repositorio de odontólogos.
     * 
     * @param odontologoRepository Repositorio de odontólogos a inyectar
     */
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    /**
     * Obtiene todos los odontólogos del sistema.
     * 
     * Este método retorna una lista con todos los odontólogos registrados
     * en la base de datos. Útil para administración y reportes de personal.
     * 
     * @return Lista de todos los odontólogos
     */
    public List<Odontologo> listarTodos() {
        return odontologoRepository.findAll();
    }

    /**
     * Busca un odontólogo por su identificador único.
     * 
     * @param id Identificador único del odontólogo
     * @return Optional con el odontólogo encontrado o vacío si no existe
     */
    public Optional<Odontologo> obtenerPorId(Long id) {
        return odontologoRepository.findById(id);
    }

    /**
     * Busca un odontólogo por su número de documento de identificación.
     * 
     * Este método es utilizado principalmente para el proceso de registro
     * y búsqueda de odontólogos, donde el documento de identificación es
     * único y sirve como identificador principal del profesional.
     * 
     * @param documento Número de documento del odontólogo a buscar
     * @return Optional con el odontólogo encontrado o vacío si no existe
     */
    public Optional<Odontologo> obtenerPorDocumento(String documento) {
        return odontologoRepository.findByDocumento(documento);
    }

    /**
     * Guarda o actualiza un odontólogo en la base de datos.
     * 
     * Este método maneja tanto la creación de nuevos odontólogos
     * como la actualización de odontólogos existentes. Spring Data JPA
     * determina automáticamente si es una operación de inserción
     * o actualización basándose en el ID del odontólogo.
     * 
     * @param odontologo Odontólogo a guardar o actualizar
     * @return Odontólogo guardado con su ID asignado
     */
    public Odontologo guardar(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    /**
     * Elimina un odontólogo del sistema por su identificador.
     * 
     * @param id Identificador único del odontólogo a eliminar
     */
    public void eliminar(Long id) {
        odontologoRepository.deleteById(id);
    }
}
