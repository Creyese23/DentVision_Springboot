package com.sena.creyese.dentvision_spring.service;


import com.sena.creyese.dentvision_spring.modelo.DetalleFactura;
import com.sena.creyese.dentvision_spring.repository.DetalleFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleFacturaService {

    @Autowired
    private final DetalleFacturaRepository detalleFacturaRepository;

    public DetalleFacturaService(DetalleFacturaRepository detalleFacturaRepository) {
        this.detalleFacturaRepository = detalleFacturaRepository;
    }

    public List<DetalleFactura> listarTodos() {
        return detalleFacturaRepository.findAll();
    }

    public Optional<DetalleFactura> obtenerPorId(Long id) {
        return detalleFacturaRepository.findById(id);
    }

    public DetalleFactura guardar(DetalleFactura detalleFactura) {
        return detalleFacturaRepository.save(detalleFactura);
    }

    public void eliminar(Long id) {
        detalleFacturaRepository.deleteById(id);
    }
}
