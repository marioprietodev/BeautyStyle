package com.proyectodam.demo.controller;

import com.proyectodam.demo.repository.CitaRepository;
import com.proyectodam.demo.repository.ClienteRepository;
import com.proyectodam.demo.repository.ServicioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    private final ClienteRepository clienteRepository;
    private final ServicioRepository servicioRepository;
    private final CitaRepository citaRepository;

    public ViewController(ClienteRepository clienteRepository, ServicioRepository servicioRepository, CitaRepository citaRepository) {
        this.clienteRepository = clienteRepository;
        this.servicioRepository = servicioRepository;
        this.citaRepository = citaRepository;
    }


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/cliente-web")
    public String listar(Model model){
    model.addAttribute("cliente",clienteRepository.findAll());
    return "cliente";
    }
    @GetMapping("/servicio-web")
    public String listarServicio(Model model){
        model.addAttribute("servicio",servicioRepository.findAll());
        return "servicio";
    }
    @GetMapping("/cita-web")
    public String listarCita(Model model){
        model.addAttribute("cita", citaRepository.findAll());
        return "cita";
    }
}
