package com.proyectodam.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

// Importamos Entity para asociar tabla en la db
@Entity
//Importamos Table para darle un nombre dentro de nuestro MySQL
@Table(name = "servicio")
//Importamos Data para darle getters y setters a nuestra clase
@Data
@Getter
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank ( message = "El nombre no puede estar en blanco")
    @Size (min = 2, max = 100, message = "Entre 2 y 100 caracteres")
    private String nombre;
  @NotBlank (message = "Debe contener una descripción")
    private String descripcion;
    @NotNull (message = "No puede estar vacio")
    @Min(value = 1, message = "El precio debe ser de al menos un euro")
    private double precio;
    @NotNull (message = "Indica una duración en minutos")
    @Min(value = 5, message = "La duracion debe ser de al menos 5 minutos")
    private int duracionMin;


}
