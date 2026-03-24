package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Mensajes;
import com.sena.creyese.dentvision_spring.repository.MensajesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensajesService {

    @Autowired
    private final MensajesRepository mensajesRepository;

    public MensajesService(MensajesRepository mensajesRepository) {
        this.mensajesRepository = mensajesRepository;
    }

    public List<Mensajes> listarTodos() {
        return mensajesRepository.findAll();
    }

    public Optional<Mensajes> obtenerPorId(Long id) {
        return mensajesRepository.findById(id);
    }

    public Mensajes guardar(Mensajes mensajes) {
        return mensajesRepository.save(mensajes);
    }

    public void eliminar(Long id) {
        mensajesRepository.deleteById(id);
    }
}
