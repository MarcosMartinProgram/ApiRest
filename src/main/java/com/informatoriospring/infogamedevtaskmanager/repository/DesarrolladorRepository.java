package com.informatoriospring.infogamedevtaskmanager.repository;

import com.informatoriospring.infogamedevtaskmanager.domain.Desarrollador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DesarrolladorRepository extends JpaRepository<Desarrollador, UUID> {
    List<Desarrollador> findByNombre(String nombre);
}
