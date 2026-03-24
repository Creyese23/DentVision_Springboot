package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Pagos;
import com.sena.creyese.dentvision_spring.repository.PagosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagosService {

    @Autowired
    private final PagosRepository pagosRepository;

    public PagosService(PagosRepository pagosRepository) {
        this.pagosRepository = pagosRepository;
    }

    public List<Pagos> listarTodos() {
        return pagosRepository.findAll();
    }

    public Optional<Pagos> obtenerPorId(Long id) {
        return pagosRepository.findById(id);
    }

    public Pagos guardar(Pagos pagos) {
        return pagosRepository.save(pagos);
    }

    public void eliminar(Long id) {
        pagosRepository.deleteById(id);
    }
}
