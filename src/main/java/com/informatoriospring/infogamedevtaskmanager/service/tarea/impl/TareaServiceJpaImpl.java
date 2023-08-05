package com.informatoriospring.infogamedevtaskmanager.service.tarea.impl;

import com.informatoriospring.infogamedevtaskmanager.domain.Desarrollador;
import com.informatoriospring.infogamedevtaskmanager.domain.Juego;
import com.informatoriospring.infogamedevtaskmanager.exceptions.DesarrolladorNotFoundException;
import com.informatoriospring.infogamedevtaskmanager.exceptions.JuegoNotFoundException;
import com.informatoriospring.infogamedevtaskmanager.exceptions.TareaNotFoundException;
import com.informatoriospring.infogamedevtaskmanager.domain.EstadoTarea;
import com.informatoriospring.infogamedevtaskmanager.domain.Tarea;
import com.informatoriospring.infogamedevtaskmanager.repository.DesarrolladorRepository;
import com.informatoriospring.infogamedevtaskmanager.repository.JuegoRepository;
import com.informatoriospring.infogamedevtaskmanager.repository.TareaRepository;
import com.informatoriospring.infogamedevtaskmanager.service.tarea.TareaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TareaServiceJpaImpl implements TareaService {

    TareaRepository tareaRepository;
    DesarrolladorRepository desarrolladorRepository;
    JuegoRepository juegoRepository;

    @Override
    public Tarea marcarTareaComoCompletada(UUID tareaId) {
        // Obtener la tarea por su ID desde la base de datos
        Optional<Tarea> tareaOptional = tareaRepository.findById(tareaId);
        if (tareaOptional.isPresent()) {
            Tarea tarea = tareaOptional.get();
            // Establecer el estado de la tarea como "COMPLETADA"
            tarea.setEstado(EstadoTarea.COMPLETADA);
            // Guardar los cambios en la base de datos
            return tareaRepository.save(tarea);
        } else {
            // Manejar el caso en que no se encuentre la tarea
            throw new TareaNotFoundException("Tarea no encontrada con ID: " + tareaId);
        }
    }

    @Override
    public Tarea asignarTarea(UUID desarrolladorId, UUID juegoId, Tarea tarea) {
        Desarrollador desarrollador = desarrolladorRepository.findById(desarrolladorId)
                .orElseThrow(() -> new DesarrolladorNotFoundException("Desarrollador no encontrado con ID: " + desarrolladorId));

        Juego juego = juegoRepository.findById(juegoId)
                .orElseThrow(() -> new JuegoNotFoundException("Juego no encontrado con ID: " + juegoId));

        tarea.setDesarrollador(desarrollador);
        tarea.setJuego(juego);
        tarea.setEstado(EstadoTarea.PENDIENTE);
        return tareaRepository.save(tarea);
    }

    @Override
    public List<Tarea> listarTareasDeDesarrollador(UUID desarrolladorId) {
        Desarrollador desarrollador = desarrolladorRepository.findById(desarrolladorId)
                .orElseThrow(() -> new DesarrolladorNotFoundException("Desarrollador no encontrado con ID: " + desarrolladorId));

        return tareaRepository.findByDesarrollador(desarrollador);
    }

    @Override
    public Tarea actualizarEstadoTarea(UUID tareaId, String estado) {
        Tarea tarea = tareaRepository.findById(tareaId)
                .orElseThrow(() -> new TareaNotFoundException("Tarea no encontrada con ID: " + tareaId));

        try {
            EstadoTarea estadoTarea = EstadoTarea.valueOf(estado.toUpperCase());
            tarea.setEstado(estadoTarea);
            return tareaRepository.save(tarea);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Estado de tarea inválido: " + estado);
        }
    }

    @Override
    public List<Tarea> buscarTareasPorEstado(String estado) {
        try {
            EstadoTarea estadoTarea = EstadoTarea.valueOf(estado.toUpperCase());
            return tareaRepository.findByEstado(estadoTarea);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Estado de tarea inválido: " + estado);
        }
    }

    @Override
    public List<Tarea> buscarTareasPorJuego(UUID juegoId) {
        Juego juego = juegoRepository.findById(juegoId)
                .orElseThrow(() -> new JuegoNotFoundException("Juego no encontrado con ID: " + juegoId));

        return tareaRepository.findByJuego(juego);
    }

    @Override
    public List<Tarea> buscarTareasFueraPlazo() {
        Date currentDate = new Date();
        return tareaRepository.findByFechaLimiteBeforeAndEstadoNot(currentDate, EstadoTarea.COMPLETADA);
    }


}
