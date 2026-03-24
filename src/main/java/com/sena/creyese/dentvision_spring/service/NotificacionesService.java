package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.Notificaciones;
import com.sena.creyese.dentvision_spring.repository.NotificacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionesService {

    @Autowired
    private final NotificacionesRepository notificacionesRepository;

    public NotificacionesService(NotificacionesRepository notificacionesRepository) {
        this.notificacionesRepository = notificacionesRepository;
    }

    public List<Notificaciones> listarTodos() {
        return notificacionesRepository.findAll();
    }

    public Optional<Notificaciones> obtenerPorId(Long id) {
        return notificacionesRepository.findById(id);
    }

    public Notificaciones guardar(Notificaciones notificaciones) {
        return notificacionesRepository.save(notificaciones);
    }

    public void eliminar(Long id) {
        notificacionesRepository.deleteById(id);
    }
}
