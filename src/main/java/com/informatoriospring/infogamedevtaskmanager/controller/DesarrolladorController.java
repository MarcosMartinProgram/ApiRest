package com.informatoriospring.infogamedevtaskmanager.controller;

import com.informatoriospring.infogamedevtaskmanager.domain.Desarrollador;
import com.informatoriospring.infogamedevtaskmanager.repository.DesarrolladorRepository;
import com.informatoriospring.infogamedevtaskmanager.service.desarrollador.DesarrolladorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/desarrolladores")
public class DesarrolladorController {
    DesarrolladorRepository desarrolladorRepository;
    DesarrolladorService desarrolladorService;

    @Autowired
    public DesarrolladorController(DesarrolladorRepository desarrolladorRepository, DesarrolladorService desarrolladorService) {
        this.desarrolladorRepository = desarrolladorRepository;
        this.desarrolladorService = desarrolladorService;
    }

    @PostMapping
    public ResponseEntity<Desarrollador> registrarDesarrollador(@RequestBody Desarrollador desarrollador) {
        Desarrollador nuevoDesarrollador = desarrolladorService.registrarDesarrollador(desarrollador);
        return ResponseEntity.ok(nuevoDesarrollador);
    }

    @PostMapping("/{desarrolladorId}/asignar-juego/{juegoId}")
    public ResponseEntity<Desarrollador> asignarDesarrolladorAJuego(@PathVariable UUID desarrolladorId, @PathVariable UUID juegoId) {
        Desarrollador desarrolladorAsignado = desarrolladorService.asignarDesarrolladorAJuego(desarrolladorId, juegoId);
        return ResponseEntity.ok(desarrolladorAsignado);
    }

    @GetMapping
    public ResponseEntity<List<Desarrollador>> listarDesarrolladores() {
        List<Desarrollador> listaDesarrolladores = desarrolladorService.listarDesarrolladores();
        return ResponseEntity.ok(listaDesarrolladores);
    }
}
