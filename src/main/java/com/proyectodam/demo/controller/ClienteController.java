package com.proyectodam.demo.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import com.proyectodam.demo.model.Cliente;
import com.proyectodam.demo.repository.ClienteRepository;

import java.util.List;

@RestController
public class ClienteController {
    private final ClienteRepository clienteRepository;

    public ClienteController (ClienteRepository clienteRepository){
        this.clienteRepository= clienteRepository;
    }
    @GetMapping("/cliente")
    public List<Cliente>obtenerClientes(){
       return clienteRepository.findAll();
    }
}
