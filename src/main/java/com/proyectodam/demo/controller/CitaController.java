package com.proyectodam.demo.controller;

import com.proyectodam.demo.model.Cita;
import com.proyectodam.demo.repository.CitaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CitaController {
    private final CitaRepository citaRepository;

    public CitaController(CitaRepository citaRepository){
        this.citaRepository = citaRepository;
    }
    @GetMapping("/cita")
    public List <Cita>obtenerCita(){
        return citaRepository.findAll();
    }
}
