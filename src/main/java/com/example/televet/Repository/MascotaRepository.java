package com.example.televet.Repository;

import com.example.televet.Entity.Mascota;
import com.example.televet.Entity.Raza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota,Integer> {


    List<Mascota> findBySexoContainingIgnoreCase(String sexo);

    List<Mascota> findByRazaDescripcionOrOtrosContainingIgnoreCase(String raza, String otros);

    /*
    @Query(value=query+"where lower(m.sexo) like lower('%1%')\n" +
            "group by m.nombre", nativeQuery = true)
    List<MascotaTotalServiciosDTO> filtrarPorContacto(String contacto);
    */
    List<Mascota> findBySexo(String sexo);
    //List<Mascota> encontrarPorRaza(String raza);
    //List<Mascota> findByContacto();

    @Query(value = "SELECT * FROM mascota where cuenta_idcuenta = ?1", nativeQuery = true)
    List<Mascota> mascotaList(int cuenta_idcuenta);
}
