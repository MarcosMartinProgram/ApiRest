package com.informatoriospring.infogamedevtaskmanager.service.desarrollador.impl;

import com.informatoriospring.infogamedevtaskmanager.domain.Desarrollador;
import com.informatoriospring.infogamedevtaskmanager.domain.Juego;
import com.informatoriospring.infogamedevtaskmanager.repository.DesarrolladorRepository;
import com.informatoriospring.infogamedevtaskmanager.repository.JuegoRepository;
import com.informatoriospring.infogamedevtaskmanager.service.desarrollador.DesarrolladorService;
import com.informatoriospring.infogamedevtaskmanager.exceptions.DesarrolladorNotFoundException;
import com.informatoriospring.infogamedevtaskmanager.exceptions.JuegoNotFoundException;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DesarrolladorServiceJpaImpl implements DesarrolladorService {


    DesarrolladorRepository desarrolladorRepository;
    JuegoRepository juegoRepository;
    @Override
    public Desarrollador registrarDesarrollador(Desarrollador desarrollador) {
        return desarrolladorRepository.save(desarrollador);
    }

    @Override
    public Desarrollador asignarDesarrolladorAJuego(UUID desarrolladorId, UUID juegoId) {
        Desarrollador desarrollador = desarrolladorRepository.findById(desarrolladorId)
                .orElseThrow(() -> new DesarrolladorNotFoundException("Desarrollador no encontrado con ID: " + desarrolladorId));

        Juego juego = juegoRepository.findById(juegoId)
                .orElseThrow(() -> new JuegoNotFoundException("Juego no encontrado con ID: " + juegoId));

        desarrollador.getJuegos().add(juego);
        return desarrolladorRepository.save(desarrollador);
    }

    @Override
    public List<Desarrollador> listarDesarrolladores() {
        return desarrolladorRepository.findAll();
    }
}
