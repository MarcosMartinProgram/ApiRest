package com.informatoriospring.infogamedevtaskmanager.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Desarrollador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "UUID")
    @GenericGenerator(name="UUID",strategy="org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36,columnDefinition = "varchar(36)",updatable = false,nullable = false)
    private UUID uuid;

    @Column(length = 100,columnDefinition = "varchar(100)",updatable = true,nullable = false)
    private String nombre;

    private String correo;

    private String rol;

    // Relación con juegos
    @ManyToMany
    @JoinTable(name = "juego_desarrollador",
            joinColumns = @JoinColumn(name = "desarrollador_id"),
            inverseJoinColumns = @JoinColumn(name = "juego_id"))
    private List<Juego> juegos;

    // Relación con tareas
    @OneToMany(mappedBy = "desarrollador")
    private List<Tarea> tareas;

    @Override
    public String toString() {
        return "Desarrollador{" +
                "uuid=" + uuid +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", rol='" + rol + '\'' +
                ", juegos=" + juegos +
                ", tareas=" + tareas +
                '}';
    }
}
