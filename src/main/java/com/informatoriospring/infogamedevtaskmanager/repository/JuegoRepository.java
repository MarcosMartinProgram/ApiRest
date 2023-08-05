package com.informatoriospring.infogamedevtaskmanager.repository;

import com.informatoriospring.infogamedevtaskmanager.domain.EstadoJuego;
import com.informatoriospring.infogamedevtaskmanager.domain.Juego;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JuegoRepository extends JpaRepository<Juego, UUID> {

    List<Juego> findByEstado(EstadoJuego estado);
}
