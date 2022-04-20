package com.example.televet.Repository;

import com.example.televet.Entity.Mascota;
import com.example.televet.Entity.Raza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota,Integer> {

    List<Mascota> findBySexo(String sexo);
    //List<Mascota> encontrarPorRaza(String raza);
    //List<Mascota> findByContacto();
}
