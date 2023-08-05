package com.informatoriospring.infogamedevtaskmanager.controller;

import com.informatoriospring.infogamedevtaskmanager.domain.Tarea;
import com.informatoriospring.infogamedevtaskmanager.repository.TareaRepository;
import com.informatoriospring.infogamedevtaskmanager.service.tarea.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tareas")
public class TareaController {
    TareaRepository tareaRepository;
    TareaService tareaService;

    @Autowired
    public TareaController(TareaRepository tareaRepository, TareaService tareaService) {
        this.tareaRepository = tareaRepository;
        this.tareaService = tareaService;
    }

    @PostMapping("/{tareaId}/completar")
    public ResponseEntity completarTarea(@PathVariable UUID tareaId) {
        Tarea tareaCompletada = tareaService.marcarTareaComoCompletada(tareaId);
        return ResponseEntity.ok(tareaCompletada);
    }

    @PostMapping("/{desarrolladorId}/{juegoId}/asignar")
    public ResponseEntity<Tarea> asignarTarea(
            @PathVariable UUID desarrolladorId,
            @PathVariable UUID juegoId,
            @RequestBody Tarea tarea
    ) {
        Tarea tareaAsignada = tareaService.asignarTarea(desarrolladorId, juegoId, tarea);
        return ResponseEntity.ok(tareaAsignada);
    }

    @GetMapping("/desarrollador/{desarrolladorId}")
    public ResponseEntity<List<Tarea>> listarTareasDeDesarrollador(@PathVariable UUID desarrolladorId) {
        List<Tarea> tareas = tareaService.listarTareasDeDesarrollador(desarrolladorId);
        return ResponseEntity.ok(tareas);
    }

    @PutMapping("/{tareaId}/actualizarEstado")
    public ResponseEntity<Tarea> actualizarEstadoTarea(
            @PathVariable UUID tareaId,
            @RequestParam String estado
    ) {
        Tarea tareaActualizada = tareaService.actualizarEstadoTarea(tareaId, estado);
        return ResponseEntity.ok(tareaActualizada);
    }

    @GetMapping("/buscarPorEstado")
    public ResponseEntity<List<Tarea>> buscarTareasPorEstado(@RequestParam String estado) {
        List<Tarea> tareas = tareaService.buscarTareasPorEstado(estado);
        return ResponseEntity.ok(tareas);
    }

    @GetMapping("/buscarPorJuego/{juegoId}")
    public ResponseEntity<List<Tarea>> buscarTareasPorJuego(@PathVariable UUID juegoId) {
        List<Tarea> tareas = tareaService.buscarTareasPorJuego(juegoId);
        return ResponseEntity.ok(tareas);
    }

    @GetMapping("/buscarFueraPlazo")
    public ResponseEntity<List<Tarea>> buscarTareasFueraPlazo() {
        List<Tarea> tareas = tareaService.buscarTareasFueraPlazo();
        return ResponseEntity.ok(tareas);
    }

}
