package com.example.televet.Repository;

import com.example.televet.Dto.ServiciosMascotasDto;
import com.example.televet.Entity.Mascota;
import com.example.televet.Entity.Raza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota,Integer> {

    @Query(value = "SELECT * FROM mascota where cuenta_idcuenta = ?1", nativeQuery = true)
    List<Mascota> mascotaList(int cuenta_idcuenta);

    @Query(value="Select m.idmascota,m.nombre,m.anho,m.sexo,r.idraza,m.raza_otros as razaotros,r.descripcion as raza, count(s.idservicio) as cantidadservicios from mascota m \n" +
            "left join servicio s on (s.mascota_idmascota=m.idmascota)\n" +
            "left join raza_especie r on (r.idraza=m.raza_especie_idraza)\n" +
            "group by m.idmascota", nativeQuery = true)
    List<ServiciosMascotasDto> listaContadorServicios();

    @Query(value="Select m.idmascota,m.nombre,m.anho,m.sexo,r.idraza,m.raza_otros as razaotros,r.descripcion as raza, count(s.idservicio) as cantidadservicios from mascota m \n" +
            "left join servicio s on (s.mascota_idmascota=m.idmascota)\n" +
            "left join raza_especie r on (r.idraza=m.raza_especie_idraza)\n" +
            "where m.nombre like %?1% " +
            "group by m.idmascota ", nativeQuery = true)
    List<ServiciosMascotasDto> listaContadorServicios(String search);

    @Query(value="Select m.idmascota,m.nombre,m.anho,m.sexo,r.idraza,m.raza_otros as razaotros,r.descripcion as raza, count(s.idservicio) as cantidadservicios from mascota m \n" +
            "left join servicio s on (s.mascota_idmascota=m.idmascota)\n" +
            "left join raza_especie r on (r.idraza=m.raza_especie_idraza)\n" +
            "where CONCAT(r.descripcion,m.raza_otros) like %?1% " +
            "group by m.idmascota ", nativeQuery = true)
    List<ServiciosMascotasDto> listaRaza(String search);

    @Query(value="Select m.idmascota,m.nombre,m.anho,m.sexo,r.idraza,m.raza_otros as razaotros,r.descripcion as raza, count(s.idservicio) as cantidadservicios from mascota m \n" +
            "left join servicio s on (s.mascota_idmascota=m.idmascota)\n" +
            "left join raza_especie r on (r.idraza=m.raza_especie_idraza)\n" +
            " where m.sexo like %?1% " +
            "group by m.idmascota", nativeQuery = true)
    List<ServiciosMascotasDto> listaSexo(String search);

    @Query(value="Select m.idmascota,m.nombre,m.anho,m.sexo,r.idraza,m.raza_otros as razaotros,r.descripcion as raza, count(s.idservicio) as cantidadservicios from mascota m \n" +
            "left join servicio s on (s.mascota_idmascota=m.idmascota)\n" +
            "left join raza_especie r on (r.idraza=m.raza_especie_idraza)\n" +
            "left join cuenta c on (m.cuenta_idcuenta=c.idcuenta)\n" +
            "where CONCAT(c.correo, c.telefono) like %?1% " +
            "group by m.idmascota ", nativeQuery = true)
    List<ServiciosMascotasDto> listaCuenta(String search);


}
