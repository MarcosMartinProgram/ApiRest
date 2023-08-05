package com.informatoriospring.infogamedevtaskmanager.controller;

import com.informatoriospring.infogamedevtaskmanager.domain.Desarrollador;
import com.informatoriospring.infogamedevtaskmanager.domain.EstadoJuego;
import com.informatoriospring.infogamedevtaskmanager.domain.Juego;
import com.informatoriospring.infogamedevtaskmanager.repository.JuegoRepository;
import com.informatoriospring.infogamedevtaskmanager.service.juego.JuegoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/juegos")
@Slf4j
public class JuegoController {
    JuegoService juegoService;

    @Autowired
    public JuegoController(JuegoService juegoService) {
        this.juegoService = juegoService;
    }

    @GetMapping()
    public List<Juego> getAllJuegos(){
        return juegoService.getAllJuegos();
    }


    @PostMapping()
    public ResponseEntity registrarJuego(@RequestBody Juego juego){
        log.info("registro de un juego");

        Juego juegoRegistro = juegoService.registrarJuego(juego);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/juegos"+juegoRegistro.getUuid());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{juegoId}/estado")
    public ResponseEntity<Juego> cambiarEstadoJuego(
            @PathVariable UUID juegoId,
            @RequestParam EstadoJuego nuevoEstado
    ) {
        Juego juego = juegoService.cambiarEstadoJuego(juegoId, nuevoEstado);
        return ResponseEntity.ok(juego);
    }

    @GetMapping("/finalizados")
    public ResponseEntity<List<Juego>> listarJuegosFinalizados() {
        List<Juego> juegosFinalizados = juegoService.listarJuegosFinalizados();
        return ResponseEntity.ok(juegosFinalizados);
    }

    @GetMapping("/{juegoId}/desarrolladores")
    public ResponseEntity<List<Desarrollador>> listarDesarrolladoresDeJuego(@PathVariable UUID juegoId) {
        List<Desarrollador> desarrolladores = juegoService.listarDesarrolladoresDeJuego(juegoId);
        return ResponseEntity.ok(desarrolladores);
    }

}
