package com.proyectodam.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

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

    @Column(nullable = false, length = 300)
    private String nombre;
    @Column(nullable = false, length = 400)
    private String descripcion;
    @Column(nullable = false)
    private double precio;
    @Column(nullable = false)
    private int duracionMin;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getDuracionMin() {
        return duracionMin;
    }

    public void setDuracionMin(int duracionMin) {
        this.duracionMin = duracionMin;
    }
}
