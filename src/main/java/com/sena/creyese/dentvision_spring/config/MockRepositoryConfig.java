package com.sena.creyese.dentvision_spring.config;

import com.sena.creyese.dentvision_spring.repository.*;
import com.sena.creyese.dentvision_spring.modelo.*;
import com.sena.creyese.dentvision_spring.enums.TipoRol;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.repository.query.FluentQuery;

/**
 * Configuración de repositorios mock para entorno de desarrollo.
 * 
 * Esta clase proporciona implementaciones mock de los repositories excluidos
 * para permitir que la aplicación inicie sin errores de dependencias.
 */
@Configuration
public class MockRepositoryConfig {

    @Bean
    @Primary
    public AdminRepository adminRepository() {
        return new AdminRepositoryMock();
    }

    @Bean
    @Primary
    public AuxiliarAdminRepository auxiliarAdminRepository() {
        return new AuxiliarAdminRepositoryMock();
    }

    @Bean
    @Primary
    public CitaRepository citaRepository() {
        return new CitaRepositoryMock();
    }

    @Bean
    @Primary
    public DetalleFacturaRepository detalleFacturaRepository() {
        return new DetalleFacturaRepositoryMock();
    }

    @Bean
    @Primary
    public EntregaRepository entregaRepository() {
        return new EntregaRepositoryMock();
    }

    @Bean
    @Primary
    public FacturaRepository facturaRepository() {
        return new FacturaRepositoryMock();
    }

    @Bean
    @Primary
    public InventarioRepository inventarioRepository() {
        return new InventarioRepositoryMock();
    }

    @Bean
    @Primary
    public MensajesRepository mensajesRepository() {
        return new MensajesRepositoryMock();
    }

    @Bean
    @Primary
    public NotificacionesRepository notificacionesRepository() {
        return new NotificacionesRepositoryMock();
    }

    @Bean
    @Primary
    public OrdenDetalleRepository ordenDetalleRepository() {
        return new OrdenDetalleRepositoryMock();
    }

    @Bean
    @Primary
    public OrdenTrabajoRepository ordenTrabajoRepository() {
        return new OrdenTrabajoRepositoryMock();
    }

    @Bean
    @Primary
    public PagosRepository pagosRepository() {
        return new PagosRepositoryMock();
    }

    @Bean
    @Primary
    public PacienteRepository pacienteRepository() {
        return new PacienteRepositoryMock();
    }

    @Bean
    @Primary
    public ProcedimientoRepository procedimientoRepository() {
        return new ProcedimientoRepositoryMock();
    }

    @Bean
    @Primary
    public RolRepository rolRepository() {
        return new RolRepositoryMock();
    }

    @Bean
    @Primary
    public ServiciosRepository serviciosRepository() {
        return new ServiciosRepositoryMock();
    }

    @Bean
    @Primary
    public TecnicoDentalRepository tecnicoDentalRepository() {
        return new TecnicoDentalRepositoryMock();
    }

    @Bean
    @Primary
    public UsuarioRepository usuarioRepository() {
        return new UsuarioRepositoryMock();
    }

    // Implementaciones Mock básicas
    private static class AdminRepositoryMock implements AdminRepository {
        @Override
        public List<Admin> findAll() { return new ArrayList<>(); }
        @Override
        public Optional<Admin> findById(Long id) { return Optional.empty(); }
        @Override
        public Admin save(Admin entity) { return entity; }
        @Override
        public void deleteById(Long id) {}
        @Override
        public boolean existsById(Long id) { return false; }
        @Override
        public long count() { return 0; }
        @Override
        public void delete(Admin entity) {}
        @Override
        public void deleteAll() {}
        @Override
        public <S extends Admin> List<S> saveAll(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public List<Admin> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override
        public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override
        public void deleteAll(Iterable<? extends Admin> entities) {}
        @Override
        public Page<Admin> findAll(Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public List<Admin> findAll(Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends Admin> S saveAndFlush(S entity) { return entity; }
        @Override
        public void flush() {}
        @Override
        public void deleteInBatch(Iterable<Admin> entities) {}
        @Override
        public void deleteAllInBatch(Iterable<Admin> entities) {}
        @Override
        public void deleteAllInBatch() {}
        @Override
        public Admin getOne(Long id) { return null; }
        @Override
        public Admin getById(Long id) { return null; }
        @Override
        public Admin getReferenceById(Long id) { return null; }
        @Override
        public <S extends Admin> List<S> saveAllAndFlush(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        
        // Missing JpaRepository methods
        @Override
        public <S extends Admin> List<S> findAll(org.springframework.data.domain.Example<S> example) { return new ArrayList<>(); }
        @Override
        public <S extends Admin> List<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends Admin> org.springframework.data.domain.Page<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends Admin> long count(org.springframework.data.domain.Example<S> example) { return 0; }
        @Override
        public <S extends Admin> boolean exists(org.springframework.data.domain.Example<S> example) { return false; }
        @Override
        public <S extends Admin> Optional<S> findOne(org.springframework.data.domain.Example<S> example) { return Optional.empty(); }
        @Override
        public <S extends Admin, R> R findBy(org.springframework.data.domain.Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return queryFunction.apply(null); }
        
        // Custom methods from AdminRepository
        @Override
        public Optional<Admin> findByDocumento(String documento) { return Optional.empty(); }
        @Override
        public List<Admin> findByNombresContainingIgnoreCase(String nombres) { return new ArrayList<>(); }
        @Override
        public List<Admin> findByApellidosContainingIgnoreCase(String apellidos) { return new ArrayList<>(); }
        @Override
        public List<Admin> findByEstadoTrue() { return new ArrayList<>(); }
        @Override
        public List<Admin> findByEstadoFalse() { return new ArrayList<>(); }
        @Override
        public List<Admin> findActivosOrderByNombres() { return new ArrayList<>(); }
        @Override
        public Long countAdminsActivos() { return 0L; }
        @Override
        public List<Admin> findByNivelAccesoContainingIgnoreCase(String nivelAcceso) { return new ArrayList<>(); }
        @Override
        public Optional<Admin> findByUsuarioEmail(String email) { return Optional.empty(); }
        @Override
        public List<Admin> findSuperAdminsActivos() { return new ArrayList<>(); }
        @Override
        public List<Admin> findByDepartamentoContainingIgnoreCase(String departamento) { return new ArrayList<>(); }
    }

    // Clases mock similares para los otros repositories...
    private static class AuxiliarAdminRepositoryMock implements AuxiliarAdminRepository {
        @Override
        public List<AuxiliarAdmin> findAll() { return new ArrayList<>(); }
        @Override
        public Optional<AuxiliarAdmin> findById(Long id) { return Optional.empty(); }
        @Override
        public AuxiliarAdmin save(AuxiliarAdmin entity) { return entity; }
        @Override
        public void deleteById(Long id) {}
        @Override
        public boolean existsById(Long id) { return false; }
        @Override
        public long count() { return 0; }
        @Override
        public void delete(AuxiliarAdmin entity) {}
        @Override
        public void deleteAll() {}
        @Override
        public <S extends AuxiliarAdmin> List<S> saveAll(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public List<AuxiliarAdmin> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override
        public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override
        public void deleteAll(Iterable<? extends AuxiliarAdmin> entities) {}
        @Override
        public Page<AuxiliarAdmin> findAll(Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public List<AuxiliarAdmin> findAll(Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends AuxiliarAdmin> S saveAndFlush(S entity) { return entity; }
        @Override
        public void flush() {}
        @Override
        public void deleteInBatch(Iterable<AuxiliarAdmin> entities) {}
        @Override
        public void deleteAllInBatch(Iterable<AuxiliarAdmin> entities) {}
        @Override
        public void deleteAllInBatch() {}
        @Override
        public AuxiliarAdmin getOne(Long id) { return null; }
        @Override
        public AuxiliarAdmin getById(Long id) { return null; }
        @Override
        public AuxiliarAdmin getReferenceById(Long id) { return null; }
        @Override
        public <S extends AuxiliarAdmin> List<S> saveAllAndFlush(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        
        // Missing JpaRepository methods
        @Override
        public <S extends AuxiliarAdmin> List<S> findAll(Example<S> example) { return new ArrayList<>(); }
        @Override
        public <S extends AuxiliarAdmin> List<S> findAll(Example<S> example, Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends AuxiliarAdmin> Page<S> findAll(Example<S> example, Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends AuxiliarAdmin> long count(Example<S> example) { return 0; }
        @Override
        public <S extends AuxiliarAdmin> boolean exists(Example<S> example) { return false; }
        @Override
        public <S extends AuxiliarAdmin> Optional<S> findOne(Example<S> example) { return Optional.empty(); }
        @Override
        public <S extends AuxiliarAdmin, R> R findBy(Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return queryFunction.apply(null); }
        
        // Custom methods from AuxiliarAdminRepository
        @Override
        public Optional<AuxiliarAdmin> findByDocumento(String documento) { return Optional.empty(); }
        @Override
        public List<AuxiliarAdmin> findByNombresContainingIgnoreCase(String nombres) { return new ArrayList<>(); }
        @Override
        public List<AuxiliarAdmin> findByApellidosContainingIgnoreCase(String apellidos) { return new ArrayList<>(); }
        @Override
        public List<AuxiliarAdmin> findByEstadoTrue() { return new ArrayList<>(); }
        @Override
        public List<AuxiliarAdmin> findByEstadoFalse() { return new ArrayList<>(); }
        @Override
        public List<AuxiliarAdmin> findActivosOrderByNombres() { return new ArrayList<>(); }
        @Override
        public Long countAuxiliaresActivos() { return 0L; }
        @Override
        public List<AuxiliarAdmin> findByAreaTrabajoContainingIgnoreCase(String areaTrabajo) { return new ArrayList<>(); }
        @Override
        public Optional<AuxiliarAdmin> findByUsuarioEmail(String email) { return Optional.empty(); }
        @Override
        public List<AuxiliarAdmin> findByTurnoContainingIgnoreCase(String turno) { return new ArrayList<>(); }
        @Override
        public List<AuxiliarAdmin> findAuxiliaresByAreaYActivos(String area) { return new ArrayList<>(); }
        @Override
        public List<AuxiliarAdmin> findByExperienciaYearsGreaterThan(Integer years) { return new ArrayList<>(); }
    }

    private static class CitaRepositoryMock implements CitaRepository {
        @Override
        public List<Cita> findAll() { return new ArrayList<>(); }
        @Override
        public Optional<Cita> findById(Long id) { return Optional.empty(); }
        @Override
        public Cita save(Cita entity) { return entity; }
        @Override
        public void deleteById(Long id) {}
        @Override
        public boolean existsById(Long id) { return false; }
        @Override
        public long count() { return 0; }
        @Override
        public void delete(Cita entity) {}
        @Override
        public void deleteAll() {}
        @Override
        public <S extends Cita> List<S> saveAll(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public List<Cita> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override
        public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override
        public void deleteAll(Iterable<? extends Cita> entities) {}
        @Override
        public Page<Cita> findAll(Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public List<Cita> findAll(Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends Cita> S saveAndFlush(S entity) { return entity; }
        @Override
        public void flush() {}
        @Override
        public void deleteInBatch(Iterable<Cita> entities) {}
        @Override
        public void deleteAllInBatch(Iterable<Cita> entities) {}
        @Override
        public void deleteAllInBatch() {}
        @Override
        public Cita getOne(Long id) { return null; }
        @Override
        public Cita getById(Long id) { return null; }
        @Override
        public Cita getReferenceById(Long id) { return null; }
        @Override
        public <S extends Cita> List<S> saveAllAndFlush(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        
        // Missing JpaRepository methods
        @Override
        public <S extends Cita> List<S> findAll(Example<S> example) { return new ArrayList<>(); }
        @Override
        public <S extends Cita> List<S> findAll(Example<S> example, Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends Cita> Page<S> findAll(Example<S> example, Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends Cita> long count(Example<S> example) { return 0; }
        @Override
        public <S extends Cita> boolean exists(Example<S> example) { return false; }
        @Override
        public <S extends Cita> Optional<S> findOne(Example<S> example) { return Optional.empty(); }
        @Override
        public <S extends Cita, R> R findBy(Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return queryFunction.apply(null); }
        
        // Custom methods from CitaRepository
        @Override
        public List<Cita> findByPacienteOrderByFechaAscHoraAsc(com.sena.creyese.dentvision_spring.modelo.Paciente paciente) { return new ArrayList<>(); }
        @Override
        public List<Cita> findByOdontologoOrderByFechaAscHoraAsc(com.sena.creyese.dentvision_spring.modelo.Odontologo odontologo) { return new ArrayList<>(); }
        @Override
        public List<Cita> findByFechaOrderByHoraAsc(java.time.LocalDate fecha) { return new ArrayList<>(); }
        @Override
        public List<Cita> findByFechaBetweenOrderByFechaAscHoraAsc(java.time.LocalDate inicio, java.time.LocalDate fin) { return new ArrayList<>(); }
        @Override
        public List<Cita> findByOdontologoAndFechaOrderByHoraAsc(com.sena.creyese.dentvision_spring.modelo.Odontologo odontologo, java.time.LocalDate fecha) { return new ArrayList<>(); }
        @Override
        public List<Cita> findByEstadoTrueOrderByFechaAscHoraAsc() { return new ArrayList<>(); }
        @Override
        public List<Cita> findByEstadoFalseOrderByFechaAscHoraAsc() { return new ArrayList<>(); }
        @Override
        public Cita findByOdontologoAndFechaAndHora(Long idOdontologo, java.time.LocalDate fecha, java.time.LocalTime hora) { return null; }
        @Override
        public Long countByOdontologoAndFecha(Long idOdontologo, java.time.LocalDate fecha) { return 0L; }
        @Override
        public List<Cita> findProximasCitasByPaciente(Long idPaciente) { return new ArrayList<>(); }
        @Override
        public List<Cita> findByMotivoContainingIgnoreCase(String motivo) { return new ArrayList<>(); }
    }

    private static class DetalleFacturaRepositoryMock implements DetalleFacturaRepository {
        @Override
        public List<DetalleFactura> findAll() { return new ArrayList<>(); }
        @Override
        public Optional<DetalleFactura> findById(Long id) { return Optional.empty(); }
        @Override
        public DetalleFactura save(DetalleFactura entity) { return entity; }
        @Override
        public void deleteById(Long id) {}
        @Override
        public boolean existsById(Long id) { return false; }
        @Override
        public long count() { return 0; }
        @Override
        public void delete(DetalleFactura entity) {}
        @Override
        public void deleteAll() {}
        @Override
        public <S extends DetalleFactura> List<S> saveAll(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public List<DetalleFactura> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override
        public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override
        public void deleteAll(Iterable<? extends DetalleFactura> entities) {}
        @Override
        public Page<DetalleFactura> findAll(Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends DetalleFactura> S saveAndFlush(S entity) { return entity; }
        @Override
        public void flush() {}
        @Override
        public void deleteInBatch(Iterable<DetalleFactura> entities) {}
        @Override
        public void deleteAllInBatch(Iterable<DetalleFactura> entities) {}
        @Override
        public void deleteAllInBatch() {}
        @Override
        public DetalleFactura getOne(Long id) { return null; }
        @Override
        public DetalleFactura getById(Long id) { return null; }
        @Override
        public DetalleFactura getReferenceById(Long id) { return null; }
        @Override
        public <S extends DetalleFactura> List<S> saveAllAndFlush(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        
        // Missing JpaRepository methods
        @Override
        public List<DetalleFactura> findAll(Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends DetalleFactura> List<S> findAll(Example<S> example) { return new ArrayList<>(); }
        @Override
        public <S extends DetalleFactura> List<S> findAll(Example<S> example, Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends DetalleFactura> Page<S> findAll(Example<S> example, Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends DetalleFactura> long count(Example<S> example) { return 0; }
        @Override
        public <S extends DetalleFactura> boolean exists(Example<S> example) { return false; }
        @Override
        public <S extends DetalleFactura> Optional<S> findOne(Example<S> example) { return Optional.empty(); }
        @Override
        public <S extends DetalleFactura, R> R findBy(Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return queryFunction.apply(null); }
        
        // Custom methods from DetalleFacturaRepository
        @Override
        public List<DetalleFactura> findByFacturaId(Long facturaId) { return new ArrayList<>(); }
        @Override
        public List<DetalleFactura> findByPacienteId(Long pacienteId) { return new ArrayList<>(); }
        @Override
        public List<DetalleFactura> findByServicioOrderByFacturaFechaDesc(com.sena.creyese.dentvision_spring.modelo.Servicios servicio) { return new ArrayList<>(); }
        @Override
        public List<DetalleFactura> findByFacturaOrderByServicioNombre(com.sena.creyese.dentvision_spring.modelo.Factura factura) { return new ArrayList<>(); }
        @Override
        public Long countDetallesByFactura(Long facturaId) { return 0L; }
        @Override
        public Double sumSubtotalByFactura(Long facturaId) { return 0.0; }
        @Override
        public Double sumDescuentosByFactura(Long facturaId) { return 0.0; }
        @Override
        public List<DetalleFactura> findDetallesConDescuento() { return new ArrayList<>(); }
        @Override
        public List<DetalleFactura> findByCantidadGreaterThan(Integer cantidad) { return new ArrayList<>(); }
        @Override
        public List<DetalleFactura> findBySubtotalBetween(Double min, Double max) { return new ArrayList<>(); }
        @Override
        public List<DetalleFactura> findByPrecioUnitarioBetween(Double min, Double max) { return new ArrayList<>(); }
        @Override
        public List<DetalleFactura> findByServicioId(Long idServicio) { return new ArrayList<>(); }
        @Override
        public List<DetalleFactura> findByDescripcionContainingIgnoreCase(String descripcion) { return new ArrayList<>(); }
    }

    private static class EntregaRepositoryMock implements EntregaRepository {
        @Override
        public List<Entrega> findAll() { return new ArrayList<>(); }
        @Override
        public Optional<Entrega> findById(Long id) { return Optional.empty(); }
        @Override
        public Entrega save(Entrega entity) { return entity; }
        @Override
        public void deleteById(Long id) {}
        @Override
        public boolean existsById(Long id) { return false; }
        @Override
        public long count() { return 0; }
        @Override
        public void delete(Entrega entity) {}
        @Override
        public void deleteAll() {}
        @Override
        public <S extends Entrega> List<S> saveAll(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public List<Entrega> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override
        public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override
        public void deleteAll(Iterable<? extends Entrega> entities) {}
        @Override
        public Page<Entrega> findAll(Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends Entrega> S saveAndFlush(S entity) { return entity; }
        @Override
        public void flush() {}
        @Override
        public void deleteInBatch(Iterable<Entrega> entities) {}
        @Override
        public void deleteAllInBatch(Iterable<Entrega> entities) {}
        @Override
        public void deleteAllInBatch() {}
        @Override
        public Entrega getOne(Long id) { return null; }
        @Override
        public Entrega getById(Long id) { return null; }
        @Override
        public Entrega getReferenceById(Long id) { return null; }
        @Override
        public <S extends Entrega> List<S> saveAllAndFlush(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        @Override
        public <S extends Entrega, R> R findBy(org.springframework.data.domain.Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return null; }
        @Override
        public <S extends Entrega> long count(org.springframework.data.domain.Example<S> example) { return 0; }
        @Override
        public <S extends Entrega> boolean exists(org.springframework.data.domain.Example<S> example) { return false; }
        @Override
        public <S extends Entrega> List<S> findAll(org.springframework.data.domain.Example<S> example) { return new ArrayList<>(); }
        @Override
        public <S extends Entrega> List<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends Entrega> org.springframework.data.domain.Page<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends Entrega> Optional<S> findOne(org.springframework.data.domain.Example<S> example) { return Optional.empty(); }
        
        // Custom methods from EntregaRepository
        @Override
        public List<Entrega> findByTecnicoDentalOrderByFechaEntregaDesc(com.sena.creyese.dentvision_spring.modelo.TecnicoDental tecnicoDental) { return new ArrayList<>(); }
        @Override
        public List<Entrega> findByOrdenTrabajoOrderByFechaEntregaDesc(com.sena.creyese.dentvision_spring.modelo.OrdenTrabajo ordenTrabajo) { return new ArrayList<>(); }
        @Override
        public List<Entrega> findByFechaEntregaBetweenOrderByFechaEntregaDesc(java.time.LocalDate inicio, java.time.LocalDate fin) { return new ArrayList<>(); }
        @Override
        public List<Entrega> findByEstadoOrderByFechaEntregaDesc(String estado) { return new ArrayList<>(); }
        @Override
        public List<Entrega> findByEstadoAndFechaEntregaBetweenOrderByFechaEntregaDesc(String estado, java.time.LocalDate inicio, java.time.LocalDate fin) { return new ArrayList<>(); }
        @Override
        public List<Entrega> findEntregasPendientes() { return new ArrayList<>(); }
        @Override
        public List<Entrega> findEntregasRealizadas() { return new ArrayList<>(); }
        @Override
        public Long countEntregasPendientes() { return 0L; }
        @Override
        public List<Entrega> findByFechaEntrega(java.time.LocalDate fecha) { return new ArrayList<>(); }
        @Override
        public List<Entrega> findByPaciente(Long idPaciente) { return new ArrayList<>(); }
        @Override
        public List<Entrega> findByNotasContainingIgnoreCase(String notas) { return new ArrayList<>(); }
        @Override
        public List<Entrega> findAll(org.springframework.data.domain.Sort sort) { return new ArrayList<>(); }
    }

    private static class FacturaRepositoryMock implements FacturaRepository {
        @Override
        public List<Factura> findAll() { return new ArrayList<>(); }
        @Override
        public Optional<Factura> findById(Long id) { return Optional.empty(); }
        @Override
        public Factura save(Factura entity) { return entity; }
        @Override
        public void deleteById(Long id) {}
        @Override
        public boolean existsById(Long id) { return false; }
        @Override
        public long count() { return 0; }
        @Override
        public void delete(Factura entity) {}
        @Override
        public void deleteAll() {}
        @Override
        public <S extends Factura> List<S> saveAll(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public List<Factura> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override
        public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override
        public void deleteAll(Iterable<? extends Factura> entities) {}
        @Override
        public Page<Factura> findAll(Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends Factura> S saveAndFlush(S entity) { return entity; }
        @Override
        public void flush() {}
        @Override
        public void deleteInBatch(Iterable<Factura> entities) {}
        @Override
        public void deleteAllInBatch(Iterable<Factura> entities) {}
        @Override
        public void deleteAllInBatch() {}
        @Override
        public Factura getOne(Long id) { return null; }
        @Override
        public Factura getById(Long id) { return null; }
        @Override
        public Factura getReferenceById(Long id) { return null; }
        @Override
        public <S extends Factura> List<S> saveAllAndFlush(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        
        // Missing JpaRepository methods
        @Override
        public List<Factura> findAll(Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends Factura> List<S> findAll(Example<S> example) { return new ArrayList<>(); }
        @Override
        public <S extends Factura> List<S> findAll(Example<S> example, Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends Factura> Page<S> findAll(Example<S> example, Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends Factura> long count(Example<S> example) { return 0; }
        @Override
        public <S extends Factura> boolean exists(Example<S> example) { return false; }
        @Override
        public <S extends Factura> Optional<S> findOne(Example<S> example) { return Optional.empty(); }
        @Override
        public <S extends Factura, R> R findBy(Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return queryFunction.apply(null); }
        
        // Custom methods from FacturaRepository
        @Override
        public List<Factura> findByPacienteOrderByFechaFacturaDesc(com.sena.creyese.dentvision_spring.modelo.Paciente paciente) { return new ArrayList<>(); }
        @Override
        public List<Factura> findByEstadoOrderByFechaFacturaDesc(String estado) { return new ArrayList<>(); }
        @Override
        public List<Factura> findByFechaFacturaBetweenOrderByFechaFacturaDesc(java.time.LocalDate inicio, java.time.LocalDate fin) { return new ArrayList<>(); }
        @Override
        public List<Factura> findFacturasPendientes() { return new ArrayList<>(); }
        @Override
        public List<Factura> findFacturasPagadas() { return new ArrayList<>(); }
        @Override
        public List<Factura> findFacturasVencidas() { return new ArrayList<>(); }
        @Override
        public Long countFacturasPendientes() { return 0L; }
        @Override
        public Double sumTotalFacturasPendientes() { return 0.0; }
        @Override
        public Double sumTotalPagadasByRangoFechas(java.time.LocalDate inicio, java.time.LocalDate fin) { return 0.0; }
        @Override
        public List<Factura> findByMetodoPagoOrderByFechaFacturaDesc(String metodoPago) { return new ArrayList<>(); }
        @Override
        public List<Factura> findFacturasVencidasPorFecha(java.time.LocalDate currentDate) { return new ArrayList<>(); }
        @Override
        public List<Factura> findByNumeroFacturaContainingIgnoreCase(String numeroFactura) { return new ArrayList<>(); }
    }

    private static class InventarioRepositoryMock implements InventarioRepository {
        @Override
        public List<Inventario> findAll() { return new ArrayList<>(); }
        @Override
        public Optional<Inventario> findById(Long id) { return Optional.empty(); }
        @Override
        public Inventario save(Inventario entity) { return entity; }
        @Override
        public void deleteById(Long id) {}
        @Override
        public boolean existsById(Long id) { return false; }
        @Override
        public long count() { return 0; }
        @Override
        public void delete(Inventario entity) {}
        @Override
        public void deleteAll() {}
        @Override
        public <S extends Inventario> List<S> saveAll(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public List<Inventario> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override
        public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override
        public void deleteAll(Iterable<? extends Inventario> entities) {}
        @Override
        public Page<Inventario> findAll(Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends Inventario> S saveAndFlush(S entity) { return entity; }
        @Override
        public void flush() {}
        @Override
        public void deleteInBatch(Iterable<Inventario> entities) {}
        @Override
        public void deleteAllInBatch(Iterable<Inventario> entities) {}
        @Override
        public void deleteAllInBatch() {}
        @Override
        public Inventario getOne(Long id) { return null; }
        @Override
        public Inventario getById(Long id) { return null; }
        @Override
        public Inventario getReferenceById(Long id) { return null; }
        @Override
        public <S extends Inventario> List<S> saveAllAndFlush(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        
        // Missing JpaRepository methods
        @Override
        public List<Inventario> findAll(Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends Inventario> List<S> findAll(Example<S> example) { return new ArrayList<>(); }
        @Override
        public <S extends Inventario> List<S> findAll(Example<S> example, Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends Inventario> Page<S> findAll(Example<S> example, Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends Inventario> long count(Example<S> example) { return 0; }
        @Override
        public <S extends Inventario> boolean exists(Example<S> example) { return false; }
        @Override
        public <S extends Inventario> Optional<S> findOne(Example<S> example) { return Optional.empty(); }
        @Override
        public <S extends Inventario, R> R findBy(Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return queryFunction.apply(null); }
        
        // Custom methods from InventarioRepository
        @Override
        public List<Inventario> findByInsumoOrderByFechaMovimientoDesc(com.sena.creyese.dentvision_spring.modelo.Insumo insumo) { return new ArrayList<>(); }
        @Override
        public List<Inventario> findByTipoMovimientoOrderByFechaMovimientoDesc(String tipoMovimiento) { return new ArrayList<>(); }
        @Override
        public List<Inventario> findByFechaMovimientoBetweenOrderByFechaMovimientoDesc(java.time.LocalDate inicio, java.time.LocalDate fin) { return new ArrayList<>(); }
        @Override
        public List<Inventario> findMovimientosDeInsumosConBajoStock() { return new ArrayList<>(); }
        @Override
        public List<Inventario> findEntradas() { return new ArrayList<>(); }
        @Override
        public List<Inventario> findSalidas() { return new ArrayList<>(); }
        @Override
        public List<Inventario> findByInsumoAndTipoMovimiento(Long idInsumo, String tipoMovimiento) { return new ArrayList<>(); }
        @Override
        public Long sumTotalEntradasByInsumo(Long idInsumo) { return 0L; }
        @Override
        public Long sumTotalSalidasByInsumo(Long idInsumo) { return 0L; }
        @Override
        public List<Inventario> findByResponsableContainingIgnoreCase(String responsable) { return new ArrayList<>(); }
        @Override
        public List<Inventario> findByFechaMovimiento(java.time.LocalDate fecha) { return new ArrayList<>(); }
        @Override
        public List<Inventario> findByNotasContainingIgnoreCase(String notas) { return new ArrayList<>(); }
    }

    private static class MensajesRepositoryMock implements MensajesRepository {
        @Override
        public List<Mensajes> findAll() { return new ArrayList<>(); }
        @Override
        public Optional<Mensajes> findById(Long id) { return Optional.empty(); }
        @Override
        public Mensajes save(Mensajes entity) { return entity; }
        @Override
        public void deleteById(Long id) {}
        @Override
        public boolean existsById(Long id) { return false; }
        @Override
        public long count() { return 0; }
        @Override
        public void delete(Mensajes entity) {}
        @Override
        public void deleteAll() {}
        @Override
        public <S extends Mensajes> List<S> saveAll(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public List<Mensajes> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override
        public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override
        public void deleteAll(Iterable<? extends Mensajes> entities) {}
        @Override
        public Page<Mensajes> findAll(Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends Mensajes> S saveAndFlush(S entity) { return entity; }
        @Override
        public void flush() {}
        @Override
        public void deleteInBatch(Iterable<Mensajes> entities) {}
        @Override
        public void deleteAllInBatch(Iterable<Mensajes> entities) {}
        @Override
        public void deleteAllInBatch() {}
        @Override
        public Mensajes getOne(Long id) { return null; }
        @Override
        public Mensajes getById(Long id) { return null; }
        @Override
        public Mensajes getReferenceById(Long id) { return null; }
        @Override
        public <S extends Mensajes> List<S> saveAllAndFlush(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        
        // Missing JpaRepository methods
        @Override
        public List<Mensajes> findAll(Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends Mensajes> List<S> findAll(Example<S> example) { return new ArrayList<>(); }
        @Override
        public <S extends Mensajes> List<S> findAll(Example<S> example, Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends Mensajes> Page<S> findAll(Example<S> example, Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends Mensajes> long count(Example<S> example) { return 0; }
        @Override
        public <S extends Mensajes> boolean exists(Example<S> example) { return false; }
        @Override
        public <S extends Mensajes> Optional<S> findOne(Example<S> example) { return Optional.empty(); }
        @Override
        public <S extends Mensajes, R> R findBy(Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return queryFunction.apply(null); }
        
        // Custom methods from MensajesRepository
        @Override
        public List<Mensajes> findByRemitenteOrderByFechaEnvioDesc(com.sena.creyese.dentvision_spring.modelo.Usuario remitente) { return new ArrayList<>(); }
        @Override
        public List<Mensajes> findByDestinatarioOrderByFechaEnvioDesc(com.sena.creyese.dentvision_spring.modelo.Usuario destinatario) { return new ArrayList<>(); }
        @Override
        public List<Mensajes> findByAsuntoContainingIgnoreCase(String asunto) { return new ArrayList<>(); }
        @Override
        public List<Mensajes> findByEstadoOrderByFechaEnvioDesc(String estado) { return new ArrayList<>(); }
        @Override
        public List<Mensajes> findByFechaEnvioBetweenOrderByFechaEnvioDesc(java.time.LocalDateTime inicio, java.time.LocalDateTime fin) { return new ArrayList<>(); }
        @Override
        public List<Mensajes> findMensajesNoLeidosByDestinatario(Long idDestinatario) { return new ArrayList<>(); }
        @Override
        public List<Mensajes> findMensajesLeidos() { return new ArrayList<>(); }
        @Override
        public List<Mensajes> findMensajesNoLeidos() { return new ArrayList<>(); }
        @Override
        public Long countMensajesNoLeidosByDestinatario(Long idDestinatario) { return 0L; }
        @Override
        public List<Mensajes> findMensajesByUsuario(Long idRemitente, Long idUsuario) { return new ArrayList<>(); }
        @Override
        public List<Mensajes> findByContenidoContainingIgnoreCase(String contenido) { return new ArrayList<>(); }
        @Override
        public List<Mensajes> findMensajesDeAltaPrioridadNoLeidos() { return new ArrayList<>(); }
        @Override
        public List<Mensajes> findByFechaEnvio(java.time.LocalDateTime fecha) { return new ArrayList<>(); }
        @Override
        public List<Mensajes> findByRemitenteAndDestinatarioOrderByFechaEnvioDesc(com.sena.creyese.dentvision_spring.modelo.Usuario remitente, com.sena.creyese.dentvision_spring.modelo.Usuario destinatario) { return new ArrayList<>(); }
    }

    private static class NotificacionesRepositoryMock implements NotificacionesRepository {
        @Override
        public List<Notificaciones> findAll() { return new ArrayList<>(); }
        @Override
        public Optional<Notificaciones> findById(Long id) { return Optional.empty(); }
        @Override
        public Notificaciones save(Notificaciones entity) { return entity; }
        @Override
        public void deleteById(Long id) {}
        @Override
        public boolean existsById(Long id) { return false; }
        @Override
        public long count() { return 0; }
        @Override
        public void delete(Notificaciones entity) {}
        @Override
        public void deleteAll() {}
        @Override
        public <S extends Notificaciones> List<S> saveAll(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public List<Notificaciones> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override
        public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override
        public void deleteAll(Iterable<? extends Notificaciones> entities) {}
        @Override
        public Page<Notificaciones> findAll(Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends Notificaciones> S saveAndFlush(S entity) { return entity; }
        @Override
        public void flush() {}
        @Override
        public void deleteInBatch(Iterable<Notificaciones> entities) {}
        @Override
        public void deleteAllInBatch(Iterable<Notificaciones> entities) {}
        @Override
        public void deleteAllInBatch() {}
        @Override
        public Notificaciones getOne(Long id) { return null; }
        @Override
        public Notificaciones getById(Long id) { return null; }
        @Override
        public Notificaciones getReferenceById(Long id) { return null; }
        @Override
        public <S extends Notificaciones> List<S> saveAllAndFlush(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        
        // Missing JpaRepository methods
        @Override
        public List<Notificaciones> findAll(Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends Notificaciones> List<S> findAll(Example<S> example) { return new ArrayList<>(); }
        @Override
        public <S extends Notificaciones> List<S> findAll(Example<S> example, Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends Notificaciones> Page<S> findAll(Example<S> example, Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends Notificaciones> long count(Example<S> example) { return 0; }
        @Override
        public <S extends Notificaciones> boolean exists(Example<S> example) { return false; }
        @Override
        public <S extends Notificaciones> Optional<S> findOne(Example<S> example) { return Optional.empty(); }
        @Override
        public <S extends Notificaciones, R> R findBy(Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return queryFunction.apply(null); }
        
        // Custom methods from NotificacionesRepository
        @Override
        public List<Notificaciones> findByUsuarioOrderByFechaCreacionDesc(com.sena.creyese.dentvision_spring.modelo.Usuario usuario) { return new ArrayList<>(); }
        @Override
        public List<Notificaciones> findByTipoOrderByFechaCreacionDesc(String tipo) { return new ArrayList<>(); }
        @Override
        public List<Notificaciones> findByEstadoOrderByFechaCreacionDesc(String estado) { return new ArrayList<>(); }
        @Override
        public List<Notificaciones> findByFechaCreacionBetweenOrderByFechaCreacionDesc(java.time.LocalDateTime inicio, java.time.LocalDateTime fin) { return new ArrayList<>(); }
        @Override
        public List<Notificaciones> findNotificacionesNoLeidasByUsuario(Long idUsuario) { return new ArrayList<>(); }
        @Override
        public List<Notificaciones> findNotificacionesLeidas() { return new ArrayList<>(); }
        @Override
        public List<Notificaciones> findNotificacionesNoLeidas() { return new ArrayList<>(); }
        @Override
        public Long countNotificacionesNoLeidasByUsuario(Long idUsuario) { return 0L; }
        @Override
        public Long countTotalNotificacionesNoLeidas() { return 0L; }
        @Override
        public List<Notificaciones> findByTituloContainingIgnoreCase(String titulo) { return new ArrayList<>(); }
        @Override
        public List<Notificaciones> findByMensajeContainingIgnoreCase(String mensaje) { return new ArrayList<>(); }
        @Override
        public List<Notificaciones> findNotificacionesDeAltaPrioridadNoLeidas() { return new ArrayList<>(); }
        @Override
        public List<Notificaciones> findByFechaCreacion(java.time.LocalDateTime fecha) { return new ArrayList<>(); }
        @Override
        public List<Notificaciones> findNotificacionesVencidasNoLeidas(java.time.LocalDateTime fechaLimite) { return new ArrayList<>(); }
        @Override
        public List<Notificaciones> findByUsuarioAndTipoOrderByFechaCreacionDesc(com.sena.creyese.dentvision_spring.modelo.Usuario usuario, String tipo) { return new ArrayList<>(); }
    }

    private static class OrdenDetalleRepositoryMock implements OrdenDetalleRepository {
        @Override
        public List<OrdenDetalle> findAll() { return new ArrayList<>(); }
        @Override
        public Optional<OrdenDetalle> findById(Long id) { return Optional.empty(); }
        @Override
        public OrdenDetalle save(OrdenDetalle entity) { return entity; }
        @Override
        public void deleteById(Long id) {}
        @Override
        public boolean existsById(Long id) { return false; }
        @Override
        public long count() { return 0; }
        @Override
        public void delete(OrdenDetalle entity) {}
        @Override
        public void deleteAll() {}
        @Override
        public <S extends OrdenDetalle> List<S> saveAll(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public List<OrdenDetalle> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override
        public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override
        public void deleteAll(Iterable<? extends OrdenDetalle> entities) {}
        @Override
        public Page<OrdenDetalle> findAll(Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends OrdenDetalle> S saveAndFlush(S entity) { return entity; }
        @Override
        public void flush() {}
        @Override
        public void deleteInBatch(Iterable<OrdenDetalle> entities) {}
        @Override
        public void deleteAllInBatch(Iterable<OrdenDetalle> entities) {}
        @Override
        public void deleteAllInBatch() {}
        @Override
        public OrdenDetalle getOne(Long id) { return null; }
        @Override
        public OrdenDetalle getById(Long id) { return null; }
        @Override
        public OrdenDetalle getReferenceById(Long id) { return null; }
        @Override
        public <S extends OrdenDetalle> List<S> saveAllAndFlush(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        
        // Missing JpaRepository methods
        @Override
        public List<OrdenDetalle> findAll(Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends OrdenDetalle> List<S> findAll(Example<S> example) { return new ArrayList<>(); }
        @Override
        public <S extends OrdenDetalle> List<S> findAll(Example<S> example, Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends OrdenDetalle> Page<S> findAll(Example<S> example, Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends OrdenDetalle> long count(Example<S> example) { return 0; }
        @Override
        public <S extends OrdenDetalle> boolean exists(Example<S> example) { return false; }
        @Override
        public <S extends OrdenDetalle> Optional<S> findOne(Example<S> example) { return Optional.empty(); }
        @Override
        public <S extends OrdenDetalle, R> R findBy(Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return queryFunction.apply(null); }
        
        // Custom methods from OrdenDetalleRepository
        @Override
        public List<OrdenDetalle> findByOrdenTrabajoOrderByServicioNombre(com.sena.creyese.dentvision_spring.modelo.OrdenTrabajo ordenTrabajo) { return new ArrayList<>(); }
        @Override
        public List<OrdenDetalle> findByServicioOrderByOrdenTrabajoFechaCreacionDesc(com.sena.creyese.dentvision_spring.modelo.Servicios servicio) { return new ArrayList<>(); }
        @Override
        public Double sumSubtotalByOrdenTrabajo(Long idOrdenTrabajo) { return 0.0; }
        @Override
        public Long countDetallesByOrdenTrabajo(Long idOrdenTrabajo) { return 0L; }
        @Override
        public List<OrdenDetalle> findByOrdenTrabajoId(Long idOrdenTrabajo) { return new ArrayList<>(); }
        @Override
        public List<OrdenDetalle> findByServicioId(Long idServicio) { return new ArrayList<>(); }
        @Override
        public List<OrdenDetalle> findByCantidadGreaterThan(Integer cantidad) { return new ArrayList<>(); }
        @Override
        public List<OrdenDetalle> findByPrecioUnitarioBetween(Double min, Double max) { return new ArrayList<>(); }
        @Override
        public List<OrdenDetalle> findDetallesConDescuento() { return new ArrayList<>(); }
        @Override
        public Double sumDescuentosByOrdenTrabajo(Long idOrdenTrabajo) { return 0.0; }
        @Override
        public List<OrdenDetalle> findByPacienteId(Long idPaciente) { return new ArrayList<>(); }
        @Override
        public List<OrdenDetalle> findByDescripcionContainingIgnoreCase(String descripcion) { return new ArrayList<>(); }
        @Override
        public List<OrdenDetalle> findBySubtotalBetween(Double min, Double max) { return new ArrayList<>(); }
        @Override
        public List<OrdenDetalle> findDetallesCompletadosByOrdenTrabajo(Long idOrdenTrabajo) { return new ArrayList<>(); }
        @Override
        public List<OrdenDetalle> findDetallesPendientesByOrdenTrabajo(Long idOrdenTrabajo) { return new ArrayList<>(); }
    }

    private static class OrdenTrabajoRepositoryMock implements OrdenTrabajoRepository {
        @Override
        public List<OrdenTrabajo> findAll() { return new ArrayList<>(); }
        @Override
        public Optional<OrdenTrabajo> findById(Long id) { return Optional.empty(); }
        @Override
        public OrdenTrabajo save(OrdenTrabajo entity) { return entity; }
        @Override
        public void deleteById(Long id) {}
        @Override
        public boolean existsById(Long id) { return false; }
        @Override
        public long count() { return 0; }
        @Override
        public void delete(OrdenTrabajo entity) {}
        @Override
        public void deleteAll() {}
        @Override
        public <S extends OrdenTrabajo> List<S> saveAll(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public List<OrdenTrabajo> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override
        public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override
        public void deleteAll(Iterable<? extends OrdenTrabajo> entities) {}
        @Override
        public Page<OrdenTrabajo> findAll(Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends OrdenTrabajo> S saveAndFlush(S entity) { return entity; }
        @Override
        public void flush() {}
        @Override
        public void deleteInBatch(Iterable<OrdenTrabajo> entities) {}
        @Override
        public void deleteAllInBatch(Iterable<OrdenTrabajo> entities) {}
        @Override
        public void deleteAllInBatch() {}
        @Override
        public OrdenTrabajo getOne(Long id) { return null; }
        @Override
        public OrdenTrabajo getById(Long id) { return null; }
        @Override
        public OrdenTrabajo getReferenceById(Long id) { return null; }
        @Override
        public <S extends OrdenTrabajo> List<S> saveAllAndFlush(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        
        // Missing JpaRepository methods
        @Override
        public List<OrdenTrabajo> findAll(Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends OrdenTrabajo> List<S> findAll(Example<S> example) { return new ArrayList<>(); }
        @Override
        public <S extends OrdenTrabajo> List<S> findAll(Example<S> example, Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends OrdenTrabajo> Page<S> findAll(Example<S> example, Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends OrdenTrabajo> long count(Example<S> example) { return 0; }
        @Override
        public <S extends OrdenTrabajo> boolean exists(Example<S> example) { return false; }
        @Override
        public <S extends OrdenTrabajo> Optional<S> findOne(Example<S> example) { return Optional.empty(); }
        @Override
        public <S extends OrdenTrabajo, R> R findBy(Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return queryFunction.apply(null); }
        
        // Custom methods from OrdenTrabajoRepository
        @Override
        public List<OrdenTrabajo> findByPacienteOrderByFechaCreacionDesc(com.sena.creyese.dentvision_spring.modelo.Paciente paciente) { return new ArrayList<>(); }
        @Override
        public List<OrdenTrabajo> findByOdontologoOrderByFechaCreacionDesc(com.sena.creyese.dentvision_spring.modelo.Odontologo odontologo) { return new ArrayList<>(); }
        @Override
        public List<OrdenTrabajo> findByTecnicoDentalOrderByFechaCreacionDesc(com.sena.creyese.dentvision_spring.modelo.TecnicoDental tecnicoDental) { return new ArrayList<>(); }
        @Override
        public List<OrdenTrabajo> findByEstadoOrderByFechaCreacionDesc(String estado) { return new ArrayList<>(); }
        @Override
        public List<OrdenTrabajo> findByFechaCreacionBetweenOrderByFechaCreacionDesc(java.time.LocalDate inicio, java.time.LocalDate fin) { return new ArrayList<>(); }
        @Override
        public List<OrdenTrabajo> findByFechaEntregaBetweenOrderByFechaCreacionDesc(java.time.LocalDate inicio, java.time.LocalDate fin) { return new ArrayList<>(); }
        @Override
        public List<OrdenTrabajo> findOrdenesPendientes() { return new ArrayList<>(); }
        @Override
        public List<OrdenTrabajo> findOrdenesEnProgreso() { return new ArrayList<>(); }
        @Override
        public List<OrdenTrabajo> findOrdenesCompletadas() { return new ArrayList<>(); }
        @Override
        public List<OrdenTrabajo> findOrdenesEntregadas() { return new ArrayList<>(); }
        @Override
        public Long countOrdenesPendientes() { return 0L; }
        @Override
        public List<OrdenTrabajo> findOrdenesVencidas(java.time.LocalDate currentDate) { return new ArrayList<>(); }
        @Override
        public List<OrdenTrabajo> findOrdenesDeAltaPrioridad() { return new ArrayList<>(); }
        @Override
        public List<OrdenTrabajo> findByTipoTrabajoContainingIgnoreCase(String tipoTrabajo) { return new ArrayList<>(); }
        @Override
        public List<OrdenTrabajo> findByDescripcionContainingIgnoreCase(String descripcion) { return new ArrayList<>(); }
        @Override
        public List<OrdenTrabajo> findOrdenesPendientesByOdontologo(Long idOdontologo) { return new ArrayList<>(); }
    }

    private static class PagosRepositoryMock implements PagosRepository {
        @Override
        public List<Pagos> findAll() { return new ArrayList<>(); }
        @Override
        public Optional<Pagos> findById(Long id) { return Optional.empty(); }
        @Override
        public Pagos save(Pagos entity) { return entity; }
        @Override
        public void deleteById(Long id) {}
        @Override
        public boolean existsById(Long id) { return false; }
        @Override
        public long count() { return 0; }
        @Override
        public void delete(Pagos entity) {}
        @Override
        public void deleteAll() {}
        @Override
        public <S extends Pagos> List<S> saveAll(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public List<Pagos> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override
        public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override
        public void deleteAll(Iterable<? extends Pagos> entities) {}
        @Override
        public Page<Pagos> findAll(Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends Pagos> S saveAndFlush(S entity) { return entity; }
        @Override
        public void flush() {}
        @Override
        public void deleteInBatch(Iterable<Pagos> entities) {}
        @Override
        public void deleteAllInBatch(Iterable<Pagos> entities) {}
        @Override
        public void deleteAllInBatch() {}
        @Override
        public Pagos getOne(Long id) { return null; }
        @Override
        public Pagos getById(Long id) { return null; }
        @Override
        public Pagos getReferenceById(Long id) { return null; }
        @Override
        public <S extends Pagos> List<S> saveAllAndFlush(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        
        // Missing JpaRepository methods
        @Override
        public List<Pagos> findAll(Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends Pagos> List<S> findAll(Example<S> example) { return new ArrayList<>(); }
        @Override
        public <S extends Pagos> List<S> findAll(Example<S> example, Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends Pagos> Page<S> findAll(Example<S> example, Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends Pagos> long count(Example<S> example) { return 0; }
        @Override
        public <S extends Pagos> boolean exists(Example<S> example) { return false; }
        @Override
        public <S extends Pagos> Optional<S> findOne(Example<S> example) { return Optional.empty(); }
        @Override
        public <S extends Pagos, R> R findBy(Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return queryFunction.apply(null); }
        
        // Custom methods from PagosRepository
        @Override
        public List<Pagos> findByFacturaOrderByFechaPagoDesc(com.sena.creyese.dentvision_spring.modelo.Factura factura) { return new ArrayList<>(); }
        @Override
        public List<Pagos> findByMetodoPagoOrderByFechaPagoDesc(String metodoPago) { return new ArrayList<>(); }
        @Override
        public List<Pagos> findByFechaPagoBetweenOrderByFechaPagoDesc(java.time.LocalDate inicio, java.time.LocalDate fin) { return new ArrayList<>(); }
        @Override
        public List<Pagos> findPagosCompletados() { return new ArrayList<>(); }
        @Override
        public List<Pagos> findPagosPendientes() { return new ArrayList<>(); }
        @Override
        public List<Pagos> findPagosRechazados() { return new ArrayList<>(); }
        @Override
        public Double sumTotalPagosCompletadosByRangoFechas(java.time.LocalDate inicio, java.time.LocalDate fin) { return 0.0; }
        @Override
        public Double sumTotalPagosPendientes() { return 0.0; }
        @Override
        public Long countPagosPendientes() { return 0L; }
        @Override
        public List<Pagos> findByReferenciaContainingIgnoreCase(String referencia) { return new ArrayList<>(); }
        @Override
        public List<Pagos> findByFechaPago(java.time.LocalDate fecha) { return new ArrayList<>(); }
        @Override
        public List<Pagos> findByPaciente(Long idPaciente) { return new ArrayList<>(); }
        @Override
        public List<Pagos> findByEstadoOrderByFechaPagoDesc(String estado) { return new ArrayList<>(); }
    }

    private static class PacienteRepositoryMock implements PacienteRepository {
        @Override
        public List<Paciente> findAll() { return new ArrayList<>(); }
        @Override
        public Optional<Paciente> findById(Long id) { return Optional.empty(); }
        @Override
        public Paciente save(Paciente entity) { return entity; }
        @Override
        public void deleteById(Long id) {}
        @Override
        public boolean existsById(Long id) { return false; }
        @Override
        public long count() { return 0; }
        @Override
        public void delete(Paciente entity) {}
        @Override
        public void deleteAll() {}
        @Override
        public <S extends Paciente> List<S> saveAll(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public List<Paciente> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override
        public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override
        public void deleteAll(Iterable<? extends Paciente> entities) {}
        @Override
        public Page<Paciente> findAll(Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends Paciente> S saveAndFlush(S entity) { return entity; }
        @Override
        public void flush() {}
        @Override
        public void deleteInBatch(Iterable<Paciente> entities) {}
        @Override
        public void deleteAllInBatch(Iterable<Paciente> entities) {}
        @Override
        public void deleteAllInBatch() {}
        @Override
        public Paciente getOne(Long id) { return null; }
        @Override
        public Paciente getById(Long id) { return null; }
        @Override
        public Paciente getReferenceById(Long id) { return null; }
        @Override
        public <S extends Paciente> List<S> saveAllAndFlush(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        
        // Missing JpaRepository methods
        @Override
        public List<Paciente> findAll(Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends Paciente> List<S> findAll(Example<S> example) { return new ArrayList<>(); }
        @Override
        public <S extends Paciente> List<S> findAll(Example<S> example, Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends Paciente> Page<S> findAll(Example<S> example, Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends Paciente> long count(Example<S> example) { return 0; }
        @Override
        public <S extends Paciente> boolean exists(Example<S> example) { return false; }
        @Override
        public <S extends Paciente> Optional<S> findOne(Example<S> example) { return Optional.empty(); }
        @Override
        public <S extends Paciente, R> R findBy(Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return queryFunction.apply(null); }
        
        // Custom methods from PacienteRepository
        @Override
        public Optional<Paciente> findByDocumento(String documento) { return Optional.empty(); }
        @Override
        public List<Paciente> findByNombresContainingIgnoreCase(String nombres) { return new ArrayList<>(); }
        @Override
        public List<Paciente> findByApellidosContainingIgnoreCase(String apellidos) { return new ArrayList<>(); }
        @Override
        public List<Paciente> findByEstadoTrue() { return new ArrayList<>(); }
        @Override
        public List<Paciente> findByEstadoFalse() { return new ArrayList<>(); }
        @Override
        public List<Paciente> findActivosOrderByNombres() { return new ArrayList<>(); }
        @Override
        public Long countPacientesActivos() { return 0L; }
        @Override
        public List<Paciente> findByFechaNacimientoBetween(java.time.LocalDate inicio, java.time.LocalDate fin) { return new ArrayList<>(); }
        @Override
        public List<Paciente> findByAnioNacimiento(Integer anio) { return new ArrayList<>(); }
        @Override
        public List<Paciente> findByCiudadContainingIgnoreCase(String ciudad) { return new ArrayList<>(); }
        @Override
        public List<Paciente> findByDireccionContainingIgnoreCase(String direccion) { return new ArrayList<>(); }
        @Override
        public List<Paciente> findByTelefonoContainingIgnoreCase(String telefono) { return new ArrayList<>(); }
        @Override
        public List<Paciente> findByEmailDomain(String domain) { return new ArrayList<>(); }
        @Override
        public List<Paciente> findByFechaRegistro(java.time.LocalDate fecha) { return new ArrayList<>(); }
        @Override
        public List<Paciente> findByFechaRegistroBetween(java.time.LocalDate inicio, java.time.LocalDate fin) { return new ArrayList<>(); }
        @Override
        public List<Paciente> findActivosByFechaNacimientoBetween(java.time.LocalDate inicio, java.time.LocalDate fin) { return new ArrayList<>(); }
        @Override
        public List<Paciente> findByOcupacionContainingIgnoreCase(String ocupacion) { return new ArrayList<>(); }
        @Override
        public List<Paciente> findActivosConContactoEmergencia() { return new ArrayList<>(); }
    }

    private static class ProcedimientoRepositoryMock implements ProcedimientoRepository {
        @Override
        public List<Procedimiento> findAll() { return new ArrayList<>(); }
        @Override
        public Optional<Procedimiento> findById(Long id) { return Optional.empty(); }
        @Override
        public Procedimiento save(Procedimiento entity) { return entity; }
        @Override
        public void deleteById(Long id) {}
        @Override
        public boolean existsById(Long id) { return false; }
        @Override
        public long count() { return 0; }
        @Override
        public void delete(Procedimiento entity) {}
        @Override
        public void deleteAll() {}
        @Override
        public <S extends Procedimiento> List<S> saveAll(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public List<Procedimiento> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override
        public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override
        public void deleteAll(Iterable<? extends Procedimiento> entities) {}
        @Override
        public Page<Procedimiento> findAll(Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends Procedimiento> S saveAndFlush(S entity) { return entity; }
        @Override
        public void flush() {}
        @Override
        public void deleteInBatch(Iterable<Procedimiento> entities) {}
        @Override
        public void deleteAllInBatch(Iterable<Procedimiento> entities) {}
        @Override
        public void deleteAllInBatch() {}
        @Override
        public Procedimiento getOne(Long id) { return null; }
        @Override
        public Procedimiento getById(Long id) { return null; }
        @Override
        public Procedimiento getReferenceById(Long id) { return null; }
        @Override
        public <S extends Procedimiento> List<S> saveAllAndFlush(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        
        // Missing JpaRepository methods
        @Override
        public List<Procedimiento> findAll(Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends Procedimiento> List<S> findAll(Example<S> example) { return new ArrayList<>(); }
        @Override
        public <S extends Procedimiento> List<S> findAll(Example<S> example, Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends Procedimiento> Page<S> findAll(Example<S> example, Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends Procedimiento> long count(Example<S> example) { return 0; }
        @Override
        public <S extends Procedimiento> boolean exists(Example<S> example) { return false; }
        @Override
        public <S extends Procedimiento> Optional<S> findOne(Example<S> example) { return Optional.empty(); }
        @Override
        public <S extends Procedimiento, R> R findBy(Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return queryFunction.apply(null); }
        
        // Custom methods from ProcedimientoRepository
        @Override
        public List<Procedimiento> findByNombreContainingIgnoreCase(String nombre) { return new ArrayList<>(); }
        @Override
        public List<Procedimiento> findByDescripcionContainingIgnoreCase(String descripcion) { return new ArrayList<>(); }
        @Override
        public List<Procedimiento> findByEstadoTrue() { return new ArrayList<>(); }
        @Override
        public List<Procedimiento> findByEstadoFalse() { return new ArrayList<>(); }
        @Override
        public List<Procedimiento> findActivosOrderByNombre() { return new ArrayList<>(); }
        @Override
        public Long countProcedimientosActivos() { return 0L; }
        @Override
        public List<Procedimiento> findByCategoriaContainingIgnoreCase(String categoria) { return new ArrayList<>(); }
        @Override
        public List<Procedimiento> findByPrecioBetween(Double min, Double max) { return new ArrayList<>(); }
        @Override
        public List<Procedimiento> findByDuracionBetween(Integer min, Integer max) { return new ArrayList<>(); }
        @Override
        public List<Procedimiento> findByDuracionMinutosLessThan(Integer duracionMinutos) { return new ArrayList<>(); }
        @Override
        public List<Procedimiento> findByDuracionMinutosGreaterThan(Integer duracionMinutos) { return new ArrayList<>(); }
        @Override
        public List<Procedimiento> findProcedimientosConAnestesia() { return new ArrayList<>(); }
        @Override
        public List<Procedimiento> findProcedimientosConRadiografia() { return new ArrayList<>(); }
        @Override
        public List<Procedimiento> findProcedimientosDeAltaPrioridad() { return new ArrayList<>(); }
        @Override
        public List<Procedimiento> findByPrioridadContainingIgnoreCase(String prioridad) { return new ArrayList<>(); }
        @Override
        public List<Procedimiento> findByNombreOrDescripcionContaining(String keyword) { return new ArrayList<>(); }
        @Override
        public List<Procedimiento> findActivosOrderByPrecio() { return new ArrayList<>(); }
        @Override
        public List<Procedimiento> findActivosOrderByPrecioDesc() { return new ArrayList<>(); }
    }

    private static class RolRepositoryMock implements RolRepository {
        @Override
        public List<Roles> findAll() { return new ArrayList<>(); }
        @Override
        public Optional<Roles> findById(Long id) { return Optional.empty(); }
        @Override
        public Roles save(Roles entity) { return entity; }
        @Override
        public void deleteById(Long id) {}
        @Override
        public boolean existsById(Long id) { return false; }
        @Override
        public long count() { return 0; }
        @Override
        public void delete(Roles entity) {}
        @Override
        public void deleteAll() {}
        @Override
        public <S extends Roles> List<S> saveAll(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public List<Roles> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override
        public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override
        public void deleteAll(Iterable<? extends Roles> entities) {}
        @Override
        public Page<Roles> findAll(Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends Roles> S saveAndFlush(S entity) { return entity; }
        @Override
        public void flush() {}
        @Override
        public void deleteInBatch(Iterable<Roles> entities) {}
        @Override
        public void deleteAllInBatch(Iterable<Roles> entities) {}
        @Override
        public void deleteAllInBatch() {}
        @Override
        public Roles getOne(Long id) { return null; }
        @Override
        public Roles getById(Long id) { return null; }
        @Override
        public Roles getReferenceById(Long id) { return null; }
        @Override
        public <S extends Roles> List<S> saveAllAndFlush(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        
        // Missing JpaRepository methods
        @Override
        public List<Roles> findAll(Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends Roles> List<S> findAll(Example<S> example) { return new ArrayList<>(); }
        @Override
        public <S extends Roles> List<S> findAll(Example<S> example, Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends Roles> Page<S> findAll(Example<S> example, Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends Roles> long count(Example<S> example) { return 0; }
        @Override
        public <S extends Roles> boolean exists(Example<S> example) { return false; }
        @Override
        public <S extends Roles> Optional<S> findOne(Example<S> example) { return Optional.empty(); }
        @Override
        public <S extends Roles, R> R findBy(Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return queryFunction.apply(null); }
        
        // Custom methods from RolRepository
        @Override
        public Optional<Roles> findByNombreRol(com.sena.creyese.dentvision_spring.enums.TipoRol nombreRol) { return Optional.empty(); }
        @Override
        public List<Roles> findByNombreRolIn(List<com.sena.creyese.dentvision_spring.enums.TipoRol> nombresRol) { return new ArrayList<>(); }
        @Override
        public List<Roles> findByNombreRolContainingIgnoreCase(String nombreRol) { return new ArrayList<>(); }
        @Override
        public Optional<Roles> findByNombreRolQuery(com.sena.creyese.dentvision_spring.enums.TipoRol nombreRol) { return Optional.empty(); }
        @Override
        public Long countByNombreRol(com.sena.creyese.dentvision_spring.enums.TipoRol nombreRol) { return 0L; }
        @Override
        public List<Roles> findAllOrderByNombreRol() { return new ArrayList<>(); }
        @Override
        public List<Roles> findRolesWithDescripcion() { return new ArrayList<>(); }
        @Override
        public List<Roles> findByDescripcionContainingIgnoreCase(String descripcion) { return new ArrayList<>(); }
        @Override
        public List<Roles> findAdminRoles() { return new ArrayList<>(); }
        @Override
        public List<Roles> findClinicalRoles() { return new ArrayList<>(); }
        @Override
        public Optional<Roles> findPacienteRole() { return Optional.empty(); }
    }

    private static class ServiciosRepositoryMock implements ServiciosRepository {
        @Override
        public List<Servicios> findAll() { return new ArrayList<>(); }
        @Override
        public Optional<Servicios> findById(Long id) { return Optional.empty(); }
        @Override
        public Servicios save(Servicios entity) { return entity; }
        @Override
        public void deleteById(Long id) {}
        @Override
        public boolean existsById(Long id) { return false; }
        @Override
        public long count() { return 0; }
        @Override
        public void delete(Servicios entity) {}
        @Override
        public void deleteAll() {}
        @Override
        public <S extends Servicios> List<S> saveAll(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public List<Servicios> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override
        public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override
        public void deleteAll(Iterable<? extends Servicios> entities) {}
        @Override
        public Page<Servicios> findAll(Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends Servicios> S saveAndFlush(S entity) { return entity; }
        @Override
        public void flush() {}
        @Override
        public void deleteInBatch(Iterable<Servicios> entities) {}
        @Override
        public void deleteAllInBatch(Iterable<Servicios> entities) {}
        @Override
        public void deleteAllInBatch() {}
        @Override
        public Servicios getOne(Long id) { return null; }
        @Override
        public Servicios getById(Long id) { return null; }
        @Override
        public Servicios getReferenceById(Long id) { return null; }
        @Override
        public <S extends Servicios> List<S> saveAllAndFlush(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        
        // Missing JpaRepository methods
        @Override
        public List<Servicios> findAll(Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends Servicios> List<S> findAll(Example<S> example) { return new ArrayList<>(); }
        @Override
        public <S extends Servicios> List<S> findAll(Example<S> example, Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends Servicios> Page<S> findAll(Example<S> example, Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends Servicios> long count(Example<S> example) { return 0; }
        @Override
        public <S extends Servicios> boolean exists(Example<S> example) { return false; }
        @Override
        public <S extends Servicios> Optional<S> findOne(Example<S> example) { return Optional.empty(); }
        @Override
        public <S extends Servicios, R> R findBy(Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return queryFunction.apply(null); }
        
        // Custom methods from ServiciosRepository
        @Override
        public List<Servicios> findByNombreContainingIgnoreCase(String nombre) { return new ArrayList<>(); }
        @Override
        public List<Servicios> findByCategoriaContainingIgnoreCase(String categoria) { return new ArrayList<>(); }
        @Override
        public List<Servicios> findByEstadoTrue() { return new ArrayList<>(); }
        @Override
        public List<Servicios> findByEstadoFalse() { return new ArrayList<>(); }
        @Override
        public List<Servicios> findActivosOrderByNombre() { return new ArrayList<>(); }
        @Override
        public List<Servicios> findActivosOrderByPrecio() { return new ArrayList<>(); }
        @Override
        public List<Servicios> findActivosOrderByPrecioDesc() { return new ArrayList<>(); }
        @Override
        public Long countServiciosActivos() { return 0L; }
        @Override
        public List<Servicios> findByPrecioBetween(Double min, Double max) { return new ArrayList<>(); }
        @Override
        public List<Servicios> findByDuracionMinutos(Integer duracionMinutos) { return new ArrayList<>(); }
        @Override
        public List<Servicios> findByDuracionMinutosLessThan(Integer duracionMinutos) { return new ArrayList<>(); }
        @Override
        public List<Servicios> findByDuracionMinutosGreaterThan(Integer duracionMinutos) { return new ArrayList<>(); }
        @Override
        public List<Servicios> findByNombreOrDescripcionContaining(String keyword) { return new ArrayList<>(); }
        @Override
        public List<Servicios> findByRequiereAutorizacionTrue() { return new ArrayList<>(); }
        @Override
        public List<Servicios> findByRequiereAutorizacionFalse() { return new ArrayList<>(); }
        @Override
        public List<Servicios> findServiciosDisponiblesDirectamente() { return new ArrayList<>(); }
    }

    private static class TecnicoDentalRepositoryMock implements TecnicoDentalRepository {
        @Override
        public List<TecnicoDental> findAll() { return new ArrayList<>(); }
        @Override
        public Optional<TecnicoDental> findById(Long id) { return Optional.empty(); }
        @Override
        public TecnicoDental save(TecnicoDental entity) { return entity; }
        @Override
        public void deleteById(Long id) {}
        @Override
        public boolean existsById(Long id) { return false; }
        @Override
        public long count() { return 0; }
        @Override
        public void delete(TecnicoDental entity) {}
        @Override
        public void deleteAll() {}
        @Override
        public <S extends TecnicoDental> List<S> saveAll(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public List<TecnicoDental> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override
        public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override
        public void deleteAll(Iterable<? extends TecnicoDental> entities) {}
        @Override
        public Page<TecnicoDental> findAll(Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public List<TecnicoDental> findAll(Sort sort) { return new ArrayList<>(); }
        @Override
        public <S extends TecnicoDental> S saveAndFlush(S entity) { return entity; }
        @Override
        public void flush() {}
        @Override
        public void deleteInBatch(Iterable<TecnicoDental> entities) {}
        @Override
        public void deleteAllInBatch(Iterable<TecnicoDental> entities) {}
        @Override
        public void deleteAllInBatch() {}
        @Override
        public TecnicoDental getOne(Long id) { return null; }
        @Override
        public TecnicoDental getById(Long id) { return null; }
        @Override
        public TecnicoDental getReferenceById(Long id) { return null; }
        @Override
        public <S extends TecnicoDental> List<S> saveAllAndFlush(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        
        // QueryByExampleExecutor methods
        @Override
        public <S extends TecnicoDental> Optional<S> findOne(Example<S> example) { return Optional.empty(); }
        
        @Override
        public <S extends TecnicoDental> List<S> findAll(Example<S> example) { return new ArrayList<>(); }
        
        @Override
        public <S extends TecnicoDental> List<S> findAll(Example<S> example, Sort sort) { return new ArrayList<>(); }
        
        @Override
        public <S extends TecnicoDental> Page<S> findAll(Example<S> example, Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        
        @Override
        public <S extends TecnicoDental> long count(Example<S> example) { return 0; }
        
        @Override
        public <S extends TecnicoDental> boolean exists(Example<S> example) { return false; }
        
        @Override
        public <S extends TecnicoDental, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return null; }
        
        // Custom TecnicoDentalRepository methods
        @Override
        public Optional<TecnicoDental> findByDocumento(String documento) { return Optional.empty(); }
        
        @Override
        public List<TecnicoDental> findByNombresContainingIgnoreCase(String nombres) { return new ArrayList<>(); }
        
        @Override
        public List<TecnicoDental> findByApellidosContainingIgnoreCase(String apellidos) { return new ArrayList<>(); }
        
        @Override
        public List<TecnicoDental> findByEspecializacionContainingIgnoreCase(String especializacion) { return new ArrayList<>(); }
        
        @Override
        public List<TecnicoDental> findByEstadoTrue() { return new ArrayList<>(); }
        
        @Override
        public List<TecnicoDental> findByEstadoFalse() { return new ArrayList<>(); }
        
        @Override
        public List<TecnicoDental> findActivosOrderByNombres() { return new ArrayList<>(); }
        
        @Override
        public Long countTecnicosActivos() { return 0L; }
        
        @Override
        public List<TecnicoDental> findByNumeroCertificadoContainingIgnoreCase(String numeroCertificado) { return new ArrayList<>(); }
        
        @Override
        public Optional<TecnicoDental> findByUsuarioEmail(String email) { return Optional.empty(); }
        
        @Override
        public List<TecnicoDental> findByExperienciaYearsGreaterThan(Integer years) { return new ArrayList<>(); }
        
        @Override
        public List<TecnicoDental> findTecnicosDisponibles() { return new ArrayList<>(); }
    }

    private static class UsuarioRepositoryMock implements UsuarioRepository {
        @Override
        public List<Usuario> findAll() { return new ArrayList<>(); }
        @Override
        public Optional<Usuario> findById(Long id) { return Optional.empty(); }
        @Override
        public Usuario save(Usuario entity) { return entity; }
        @Override
        public void deleteById(Long id) {}
        @Override
        public boolean existsById(Long id) { return false; }
        @Override
        public long count() { return 0; }
        @Override
        public void delete(Usuario entity) {}
        @Override
        public void deleteAll() {}
        @Override
        public <S extends Usuario> List<S> saveAll(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public List<Usuario> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override
        public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override
        public void deleteAll(Iterable<? extends Usuario> entities) {}
        @Override
        public Page<Usuario> findAll(Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        @Override
        public <S extends Usuario> S saveAndFlush(S entity) { return entity; }
        @Override
        public void flush() {}
        @Override
        public void deleteInBatch(Iterable<Usuario> entities) {}
        @Override
        public void deleteAllInBatch(Iterable<Usuario> entities) {}
        @Override
        public void deleteAllInBatch() {}
        @Override
        public Usuario getOne(Long id) { return null; }
        @Override
        public Usuario getById(Long id) { return null; }
        @Override
        public Usuario getReferenceById(Long id) { return null; }
        @Override
        public <S extends Usuario> List<S> saveAllAndFlush(Iterable<S> entities) { return new ArrayList<>(); }
        @Override
        public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        
        // Missing JpaRepository methods
        @Override
        public List<Usuario> findAll(Sort sort) { return new ArrayList<>(); }
        
        // QueryByExampleExecutor methods
        @Override
        public <S extends Usuario> Optional<S> findOne(Example<S> example) { return Optional.empty(); }
        
        @Override
        public <S extends Usuario> List<S> findAll(Example<S> example) { return new ArrayList<>(); }
        
        @Override
        public <S extends Usuario> List<S> findAll(Example<S> example, Sort sort) { return new ArrayList<>(); }
        
        @Override
        public <S extends Usuario> Page<S> findAll(Example<S> example, Pageable pageable) { return new PageImpl<>(new ArrayList<>()); }
        
        @Override
        public <S extends Usuario> long count(Example<S> example) { return 0; }
        
        @Override
        public <S extends Usuario> boolean exists(Example<S> example) { return false; }
        
        @Override
        public <S extends Usuario, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return null; }
        
        // Custom UsuarioRepository methods
        @Override
        public Optional<Usuario> findByEmail(String email) { return Optional.empty(); }
        
        @Override
        public List<Usuario> findByNombresContainingIgnoreCase(String nombres) { return new ArrayList<>(); }
        
        @Override
        public List<Usuario> findByApellidosContainingIgnoreCase(String apellidos) { return new ArrayList<>(); }
        
        @Override
        public List<Usuario> findByEstadoTrue() { return new ArrayList<>(); }
        
        @Override
        public List<Usuario> findByEstadoFalse() { return new ArrayList<>(); }
        
        @Override
        public List<Usuario> findActivosOrderByNombres() { return new ArrayList<>(); }
        
        @Override
        public Long countUsuariosActivos() { return 0L; }
        
        @Override
        public List<Usuario> findByRolAndEstado(TipoRol rol) { return new ArrayList<>(); }
        
        @Override
        public List<Usuario> findByRol(TipoRol rol) { return new ArrayList<>(); }
        
        @Override
        public List<Usuario> findByFechaRegistro(java.time.LocalDate fecha) { return new ArrayList<>(); }
        
        @Override
        public List<Usuario> findByFechaRegistroBetween(java.time.LocalDate inicio, java.time.LocalDate fin) { return new ArrayList<>(); }
        
        @Override
        public List<Usuario> findByEmailDomain(String domain) { return new ArrayList<>(); }
        
        @Override
        public List<Usuario> findAdminsActivos() { return new ArrayList<>(); }
        
        @Override
        public List<Usuario> findPersonalClinicoActivo() { return new ArrayList<>(); }
    }
}
