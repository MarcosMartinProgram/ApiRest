package com.informatoriospring.infogamedevtaskmanager.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "UUID")
    @GenericGenerator(name="UUID",strategy="org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)

    @Column(length = 36,columnDefinition = "varchar(36)",updatable = false,nullable = false)
    private UUID uuid;

    @Column(length = 150,columnDefinition = "varchar(150)",updatable = true, nullable = false)
    private String descripcion;
    @Column(length = 36,columnDefinition = "varchar(36)",updatable = true)
    private Date fechaLimite;
    @Column(length = 100,columnDefinition = "varchar(100)",updatable = true)
    @Enumerated(EnumType.STRING)
    private EstadoTarea estado;


    // Relación con juego
    @ManyToOne
    @JoinColumn(name = "juego_id")
    @JsonIgnore
    private Juego juego;

    // Relación con desarrollador
    @ManyToOne
    @JoinColumn(name = "desarrollador_id")
    @JsonIgnore
    private Desarrollador desarrollador;

}
