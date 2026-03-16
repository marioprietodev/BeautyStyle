package com.proyectodam.demo.controller;

import com.proyectodam.demo.model.Cliente;
import com.proyectodam.demo.repository.CitaRepository;
import com.proyectodam.demo.repository.ClienteRepository;
import com.proyectodam.demo.repository.ServicioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    @GetMapping("/cliente-nuevo")
    public String formularioNuevoCliente(Model model){
        model.addAttribute("cliente",new Cliente());
        return "nuevo-cliente";
    }
    @PostMapping("/cliente-guardar")
    public String guardarCliente(@ModelAttribute("cliente")Cliente cliente){
        try {
            System.out.println("Inyectando");
            clienteRepository.save(cliente); //guardamos el cliente en la base de datos
            return "redirect:/cliente-web";
        } catch (Exception e){
            e.printStackTrace();
            return "index"; // redirigimos al a tabla para comprobar
        }

    }
    @Transactional
    @GetMapping("/cliente/eliminar/{id}")
    public String eliminarCliente (@PathVariable("id")long id){
        try {
            clienteRepository.deleteById(id);
            System.out.println("Cliente borrado con exito");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("No se pudo borrar al cliente");
        }


        return "redirect:/cliente-web";
    }
}
