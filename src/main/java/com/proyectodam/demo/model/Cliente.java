package com.proyectodam.demo.model;

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
    @Column(nullable = false, length = 200)
    private String nombre;
    @Column(nullable = false, length = 100)
    private String telefono;
    @Column
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
