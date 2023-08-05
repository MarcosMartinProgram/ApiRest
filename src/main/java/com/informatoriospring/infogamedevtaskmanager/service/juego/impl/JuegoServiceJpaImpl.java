package com.informatoriospring.infogamedevtaskmanager.service.juego.impl;


import com.informatoriospring.infogamedevtaskmanager.domain.EstadoJuego;
import com.informatoriospring.infogamedevtaskmanager.domain.Desarrollador;
import com.informatoriospring.infogamedevtaskmanager.domain.EstadoTarea;
import com.informatoriospring.infogamedevtaskmanager.domain.Juego;


import com.informatoriospring.infogamedevtaskmanager.exceptions.JuegoNotFoundException;
import com.informatoriospring.infogamedevtaskmanager.repository.JuegoRepository;
import com.informatoriospring.infogamedevtaskmanager.service.juego.JuegoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class JuegoServiceJpaImpl implements JuegoService {

    private final JuegoRepository juegoRepository;

    @Override
    public List<Juego> getAllJuegos() {
        return juegoRepository.findAll();
    }

    @Override
    public Juego registrarJuego(Juego juego) {

        boolean todasTareasCompletadas = juego.getTareas().stream()
                .allMatch(tarea -> tarea.getEstado() == EstadoTarea.COMPLETADA);

        juego.setEstado(todasTareasCompletadas ? EstadoJuego.TERMINADO : EstadoJuego.EN_DESARROLLO);
        return juegoRepository.save(juego);
    }

    @Override
    public List<Juego> listarJuegosEnDesarrollo() {
        return juegoRepository.findByEstado(EstadoJuego.EN_DESARROLLO);
    }

    @Override
    public List<Juego> listarJuegosFinalizados() {
        return juegoRepository.findByEstado(EstadoJuego.TERMINADO);
    }


    @Override
    public List<Desarrollador> listarDesarrolladoresDeJuego(UUID juegoId) {
        Juego juego = juegoRepository.findById(juegoId)
                .orElseThrow(() -> new JuegoNotFoundException("Juego no encontrado con ID: " + juegoId));

        return juego.getDesarrolladores();
    }
    @Override
    public Juego cambiarEstadoJuego(UUID juegoId, EstadoJuego nuevoEstado) {
        Juego juego = juegoRepository.findById(juegoId)
                .orElseThrow(() -> new JuegoNotFoundException("Juego no encontrado con ID: " + juegoId));

        juego.setEstado(nuevoEstado);
        return juegoRepository.save(juego);
    }
}
