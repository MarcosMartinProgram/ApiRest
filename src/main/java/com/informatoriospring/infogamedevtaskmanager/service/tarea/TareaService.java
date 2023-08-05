package com.informatoriospring.infogamedevtaskmanager.service.tarea;

import com.informatoriospring.infogamedevtaskmanager.domain.Tarea;

import java.util.List;
import java.util.UUID;

public interface TareaService {
    Tarea asignarTarea(UUID desarrolladorId, UUID juegoId, Tarea tarea);
    Tarea marcarTareaComoCompletada(UUID tareaId);
    List<Tarea> listarTareasDeDesarrollador(UUID desarrolladorId);
    Tarea actualizarEstadoTarea(UUID tareaId, String estado);
    List<Tarea> buscarTareasPorEstado(String estado);
    List<Tarea> buscarTareasPorJuego(UUID juegoId);
    List<Tarea> buscarTareasFueraPlazo();
}
