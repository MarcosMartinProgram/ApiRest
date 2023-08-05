package com.informatoriospring.infogamedevtaskmanager.service.desarrollador;

import com.informatoriospring.infogamedevtaskmanager.domain.Desarrollador;

import java.util.List;
import java.util.UUID;

public interface DesarrolladorService {

    Desarrollador registrarDesarrollador(Desarrollador desarrollador);
    Desarrollador asignarDesarrolladorAJuego(UUID desarrolladorId, UUID juegoId);
    List<Desarrollador> listarDesarrolladores();

}
