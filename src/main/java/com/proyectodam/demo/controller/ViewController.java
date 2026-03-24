package com.proyectodam.demo.controller;

import com.proyectodam.demo.model.Cita;
import com.proyectodam.demo.model.Cliente;
import com.proyectodam.demo.model.Servicio;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String listar(Model model) {
        model.addAttribute("cliente", clienteRepository.findAll());
        return "cliente";
    }

    @GetMapping("/servicio-web")
    public String listarServicio(Model model) {
        model.addAttribute("servicio", servicioRepository.findAll());
        return "servicio";
    }

    @GetMapping("/cita-web")
    public String listarCita(Model model) {
        model.addAttribute("cita", citaRepository.findAll());
        return "cita";
    }

    @GetMapping("/cliente-nuevo")
    public String formularioNuevoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "nuevo-cliente";
    }

    @PostMapping("/cliente-guardar")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente) {
        try {
            System.out.println("Inyectando");
            clienteRepository.save(cliente); //guardamos el cliente en la base de datos
            return "redirect:/cliente-web";
        } catch (Exception e) {
            e.printStackTrace();
            return "index"; // redirigimos al a tabla para comprobar
        }

    }

    @GetMapping("/cliente/eliminar/{id}")
    public String eliminarCliente(@PathVariable("id") long id, RedirectAttributes flash) {
        try {
            clienteRepository.deleteById(id);
            clienteRepository.flush();
            flash.addFlashAttribute("mensajeSucces", "✅ Servicio eliminado correctamente.");
            return "redirect:/cliente-web";
        } catch (Exception e) {
            flash.addFlashAttribute("mensajeError", "No puedes eliminar un cliente asociado a una cita");
        }


        return "redirect:/cliente-web";
    }

    @GetMapping("/servicio/eliminar/{id}")
    public String eliminarServicio(@PathVariable("id") long id, RedirectAttributes flash) {
        try {
            servicioRepository.deleteById(id);
            servicioRepository.flush();
            flash.addFlashAttribute("mensajeSuccess", "✅ Servicio eliminado correctamente.");
            return "redirect:/servicio-web";
        } catch (Exception e) {
            flash.addFlashAttribute("mensajeError", "No puedes eliminar un servicio asociado a una cita");

        }
        return "redirect:/servicio-web";
    }

    @GetMapping("/servicio/nuevo")
    public String formularioNuevoServicio(Model model) {
        model.addAttribute("servicio", new Servicio());
        return "servicio-nuevo";

    }

    @PostMapping("/servicio/guardar")
    public String guardarServicio(@ModelAttribute("servicio") Servicio servicio) {
        servicioRepository.save(servicio);
        return "redirect:/servicio-web";
    }

    @GetMapping("/cita/nuevo")
    public String formularioNuevaCita(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("listaClientes", clienteRepository.findAll());
        model.addAttribute("listaServicios", servicioRepository.findAll());
        return "cita-nueva";

    }

    @PostMapping("/cita/guardar")
    public String guardarCita(@ModelAttribute("cita") Cita cita, RedirectAttributes flash) {
        boolean existeChoque;
        if (cita.getId() == 0) {//cita nueva
            existeChoque = citaRepository.existsByFechaHora(cita.getFechaHora());

        } else { // cita editada
            existeChoque = citaRepository.existsByFechaHoraAndIdNot(cita.getFechaHora(), cita.getId());
        }

        if (existeChoque) {
            flash.addFlashAttribute("mensajeError", "⚠️ ¡Error! Ya existe una cita agendada para esa fecha y hora.");
            return "redirect:/cita/nuevo";
        }
        flash.addFlashAttribute("mensajeExito", "✅ Cita guardada correctamente");
        citaRepository.save(cita);
        return "redirect:/cita-calendario";
    }

    @Transactional
    @GetMapping("/cita/eliminar/{id}")
    public String eliminarCita(@PathVariable("id") long id) {
        citaRepository.deleteById(id);
        return "redirect:/cita-web";
    }

    @GetMapping("/cliente/editar/{id}")
    public String formularioEditarCliente(@PathVariable("id") long id, Model model, RedirectAttributes flash) {
        try {
            Cliente cliente = clienteRepository.findById(id).orElseThrow(() ->
                    new Exception("El cliente con ID" + id + "no se encuentra"));
            model.addAttribute("cliente", cliente);
            model.addAttribute("titulo", "Editar cliente");

            return "nuevo-cliente";
        } catch (Exception e) {
            flash.addFlashAttribute("mensajeError", "❌ Error al buscar cliente: ");
            return "redirect:/cliente-web";
        }
    }

    @GetMapping("/servicio/editar/{id}")
    public String formularioEditarServicio(@PathVariable("id") long id, Model model, RedirectAttributes flash) {
        try {
            Servicio servicio = servicioRepository.findById(id).orElseThrow(() ->
                    new Exception("El servicio con ID" + id + "no se encuentra"));
            model.addAttribute("servicio", servicio);
            model.addAttribute("titulo", "Editar servicio");

            return "servicio-nuevo";
        } catch (Exception e) {
            flash.addFlashAttribute("mensajeError", "❌ Error al buscar cliente");
            return "redirect:/servicio-web";
        }
    }

    @GetMapping("/cita/editar/{id}")
    public String formularioEditarCita(@PathVariable("id") long id, Model model, RedirectAttributes flash) {
        try {
            model.addAttribute("listaClientes", clienteRepository.findAll());
            model.addAttribute("listaServicios", servicioRepository.findAll());
            Cita cita = citaRepository.findById(id).orElseThrow(() ->
                    new Exception("La cita con ID " + id + " no se encuenttra"));
            model.addAttribute("cita", cita);
            model.addAttribute("titulo", "Editar titulo");

            return "cita-nueva";
        } catch (Exception e) {
            flash.addFlashAttribute("mensajeError", "❌ Error al buscar cita");
            return "redirect:/cita-web";
        }
    }

    @GetMapping("/cita-calendario")
    public String mostrarCalendario(Model model) {
        model.addAttribute("listaCitas", citaRepository.findAll());
        return "cita-calendario";
    }
}

