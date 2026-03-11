package com.proyectodam.demo.controller;
import com.proyectodam.demo.repository.ServicioRepository;
import com.proyectodam.demo.model.Servicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServicioController {
    private final ServicioRepository servicioRepository;

    public ServicioController (ServicioRepository servicioRepository){
        this.servicioRepository = servicioRepository;
    }
    @GetMapping("/servicio")
    public List<Servicio>obtenerServicio(){
        return servicioRepository.findAll();
    }
}
