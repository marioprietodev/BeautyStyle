package com.proyectodam.demo.repository;

import com.proyectodam.demo.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    boolean existsByFechaHoraAndIdNot(LocalDateTime fechaHora, Long id);

    boolean existsByFechaHora(LocalDateTime fechaHora);
}
