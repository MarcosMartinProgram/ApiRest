package com.informatoriospring.infogamedevtaskmanager.repository;

import com.informatoriospring.infogamedevtaskmanager.domain.Desarrollador;
import com.informatoriospring.infogamedevtaskmanager.domain.EstadoTarea;
import com.informatoriospring.infogamedevtaskmanager.domain.Juego;
import com.informatoriospring.infogamedevtaskmanager.domain.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TareaRepository extends JpaRepository<Tarea, UUID> {


    List<Tarea> findByEstado(EstadoTarea estado);


    List<Tarea> findByJuego(Juego juego);


    List<Tarea> findByDesarrollador(Desarrollador desarrollador);


    List<Tarea> findByFechaLimiteBeforeAndEstadoNot(Date fechaLimite, EstadoTarea estado);
}
