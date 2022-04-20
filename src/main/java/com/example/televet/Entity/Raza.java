package com.example.televet.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "raza_especie")
public class Raza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idraza")
    private int id;

    @Column(name = "descripcion")
    private String descripcion;
}
