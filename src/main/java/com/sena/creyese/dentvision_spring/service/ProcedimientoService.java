package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Procedimiento;
import com.sena.creyese.dentvision_spring.repository.ProcedimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcedimientoService {

    @Autowired
    private final ProcedimientoRepository procedimientoRepository;

    public ProcedimientoService(ProcedimientoRepository procedimientoRepository) {
        this.procedimientoRepository = procedimientoRepository;
    }

    public List<Procedimiento> listarTodos() {
        return procedimientoRepository.findAll();
    }

    public Optional<Procedimiento> obtenerPorId(Long id) {
        return procedimientoRepository.findById(id);
    }

    public Procedimiento guardar(Procedimiento procedimiento) {
        return procedimientoRepository.save(procedimiento);
    }

    public void eliminar(Long id) {
        procedimientoRepository.deleteById(id);
    }
}
