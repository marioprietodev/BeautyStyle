package com.proyectodam.demo;

import com.proyectodam.demo.model.Cliente;
import com.proyectodam.demo.model.Cita;
import com.proyectodam.demo.model.Servicio;
import com.proyectodam.demo.repository.CitaRepository;
import com.proyectodam.demo.repository.ClienteRepository;
import com.proyectodam.demo.repository.ServicioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final ClienteRepository clienteRepository;
    private final CitaRepository citaRepository;
    private final ServicioRepository servicioRepository;

    // Creamos un objeto con todos los repositorios como parámetros
    public DataLoader(ClienteRepository clienteRepository, CitaRepository citaRepository, ServicioRepository servicioRepository) {
        this.clienteRepository = clienteRepository;
        this.citaRepository = citaRepository;
        this.servicioRepository = servicioRepository;

    }

    @Override
    public void run(String... args) throws Exception {
        if (clienteRepository.count() == 0) {
            Cliente c1 = new Cliente();
            c1.setNombre("Mario");
            c1.setEmail("marioieslacaleta@gmail.com");
            c1.setTelefono("601173177");
            clienteRepository.save(c1);

            Cliente c2 = new Cliente();
            c2.setNombre("Tiffany");
            c2.setEmail("tiffanyunnuk@gmail.com");
            c2.setTelefono("647532487");
            clienteRepository.save(c2);
        }
        if (servicioRepository.count() == 0) {
            Servicio s1 = new Servicio();
            s1.setNombre("Laser");
            s1.setDescripcion("Eliminacion de pelos");
            s1.setPrecio(7.5);
            s1.setDuracionMin(30);
            servicioRepository.save(s1);

            Servicio s2 = new Servicio();
            s2.setNombre("Cejas");
            s2.setDescripcion("Elimina pelos");
            s2.setDuracionMin(10);
            s2.setPrecio(5);
            servicioRepository.save(s2);
        }
        if (citaRepository.count() == 0){
            Cliente juan = clienteRepository.findAll().get(0);
            Servicio laser = servicioRepository.findAll().get(0);
            Cita ci1 = new Cita();
            ci1.setCliente(juan);
            ci1.setServicio(laser);
            ci1.setFechaHora(java.time.LocalDateTime.now().plusDays(1));
            citaRepository.save(ci1);
            System.out.println("Cita vinculada perfectamente");
        }
    }
}
