package com.example.televet.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "servicio")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idservicio", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "mascota_idmascota")
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "cuenta_idcuenta", nullable = false)
    private Cuenta cuenta;

    @Column(name = "hora_inicio", nullable = false)
    private Instant horaInicio;

    @Column(name = "duracion", nullable = false)
    private Integer duracion;

    @Lob
    @Column(name = "entrega", nullable = false)
    private String entrega;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "responsable_idresponsable", nullable = false)
    private Responsable responsable;


    @ManyToMany
    @JoinTable(name="opcion_servicio",
            joinColumns = @JoinColumn(name = "servicio_idservicio"),
            inverseJoinColumns = @JoinColumn(name = "opcion_idopcion"))
    Set<Opcion> opciones;


}