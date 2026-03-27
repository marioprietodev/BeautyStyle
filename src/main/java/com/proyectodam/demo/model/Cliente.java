package com.proyectodam.demo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.persistence.*;
import lombok.Getter;

// Importamos Entity para asociar tabla en la db
@Entity
//Importamos Table para darle un nombre dentro de nuestro MySQL
@Table(name = "cliente")
//Importamos Data para darle getters y setters a nuestra clase
@Data
@Getter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
   @NotBlank ( message = "El nombre no puede estar vacío")
   @Size ( min = 3 , max= 100, message = "EL nombre debe tener al menos 3 caracteres")
    private String nombre;
    @Size ( min = 1, max = 20, message = "El telefono debe ser válido")
    private String telefono;
    @Email (message = "Debes introducir un email válido")
    private String email;


}
