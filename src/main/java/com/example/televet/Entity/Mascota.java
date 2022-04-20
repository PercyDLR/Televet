package com.example.televet.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity

@Getter
@Setter
@Table(name = "mascota")
public class Mascota{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmascota")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "anho")
    private int anho;

    @Column(name = "historia")
    private String historia;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "sexo")
    private String sexo;

    @ManyToOne
    @JoinColumn(name = "raza_especie_idraza")
    private Raza raza;

    @Column(name = "raza_otros")
    private String otros;

    @ManyToOne
    @JoinColumn(name = "cuenta_idcuenta")
    private Cuenta cuenta;
}
