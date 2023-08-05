package com.informatoriospring.infogamedevtaskmanager.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.informatoriospring.infogamedevtaskmanager.domain.EstadoJuego;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Juego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "UUID")
    @GenericGenerator(name="UUID",strategy="org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID uuid;

    @Column(length = 100, columnDefinition = "varchar(100)", updatable = true, nullable = false )
    private String titulo;

    @Column(length = 150, columnDefinition = "varchar(150)", updatable = true, nullable = false )
    private String descripcion;

    private Date fechalanzamiento;

    @Enumerated(EnumType.STRING)
    private EstadoJuego estado;

    // Relación con desarrolladores
    @ManyToMany(mappedBy = "juegos")
    @JsonIgnore
    private List<Desarrollador> desarrolladores;

    // Relación con tareas
    @OneToMany(mappedBy = "juego")
    private List<Tarea> tareas = new ArrayList<>();

    @Override
    public String toString() {
        return "Juego{" +
                "uuid=" + uuid +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaLanzamiento=" + fechalanzamiento +
                ", desarrolladores=" + desarrolladores +
                ", tareas=" + tareas +
                '}';
    }
}
