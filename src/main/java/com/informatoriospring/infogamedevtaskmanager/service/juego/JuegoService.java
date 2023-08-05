package com.informatoriospring.infogamedevtaskmanager.service.juego;

import com.informatoriospring.infogamedevtaskmanager.domain.EstadoJuego;
import com.informatoriospring.infogamedevtaskmanager.domain.Juego;
import com.informatoriospring.infogamedevtaskmanager.domain.Desarrollador;
import java.util.List;
import java.util.UUID;

public interface JuegoService {
    List<Juego> getAllJuegos();
    Juego registrarJuego(Juego juego);
    List<Juego> listarJuegosEnDesarrollo();
    List<Juego> listarJuegosFinalizados();
    List<Desarrollador> listarDesarrolladoresDeJuego(UUID juegoId);
    Juego cambiarEstadoJuego(UUID juegoId, EstadoJuego nuevoEstado);


}
