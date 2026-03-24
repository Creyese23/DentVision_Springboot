package com.sena.creyese.dentvision_spring.service;

import com.sena.creyese.dentvision_spring.modelo.ImagenesDiseno;
import com.sena.creyese.dentvision_spring.repository.ImagenesDisenoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagenesDisenoService {

    @Autowired
    private final ImagenesDisenoRepository imagenesDisenoRepository;

    public ImagenesDisenoService(ImagenesDisenoRepository imagenesDisenoRepository) {
        this.imagenesDisenoRepository = imagenesDisenoRepository;
    }

    public List<ImagenesDiseno> listarTodos() {
        return imagenesDisenoRepository.findAll();
    }

    public Optional<ImagenesDiseno> obtenerPorId(Long id) {
        return imagenesDisenoRepository.findById(id);
    }

    public ImagenesDiseno guardar(ImagenesDiseno imagenesDiseno) {
        return imagenesDisenoRepository.save(imagenesDiseno);
    }

    public void eliminar(Long id) {
        imagenesDisenoRepository.deleteById(id);
    }
}
