package com.example.televet.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "responsable")
public class Responsable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idresponsable", nullable = false)
    private Integer idresponsable;
    @Column(name = "nombre", nullable = false)
    private String nombre;

}
