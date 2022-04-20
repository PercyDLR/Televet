package com.example.televet.Dto;

import com.example.televet.Entity.Responsable;

import java.util.List;

public interface OpcionResponsableDto {
    int getIdopcion();
    int getIdservicio();
    String getDescripcion();
    int getTiempominutos();
    float getPrecio();
    String getResponsable();
}
