package com.example.televet.Repository;

import com.example.televet.Entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio,Integer> {

    @Query(value="Select s.idservicio,m.idmascota as mascota_idmascota,c.idcuenta as cunta_idcuenta,s.hora_inicio,s.duracion,s.entrega,r.idresponsable as responsable_idresponsable,p.descripcion from mascota m \n" +
            "left join servicio s on (s.mascota_idmascota=m.idmascota)\n" +
            "left join responsable r on (s.responsable_idresponsable=r.idresponsable)\n" +
            "left join opcion_servicio o on (o.servicio_idservicio=s.idservicio)\n" +
            "left join opcion p on (p.idopcion=o.opcion_idopcion)\n" +
            "left join cuenta c on (c.idcuenta=m.cuenta_idcuenta)\n" +
            "where m.idmascota=?1",nativeQuery = true)
    List<Servicio> listaMascota(int id);

    @Query(value="Select year(now())-m.anho as edad from mascota m \n" +
            "left join servicio s on (s.mascota_idmascota=m.idmascota)\n" +
            "left join responsable r on (s.responsable_idresponsable=r.idresponsable)\n" +
            "left join opcion_servicio o on (o.servicio_idservicio=s.idservicio)\n" +
            "left join opcion p on (p.idopcion=o.opcion_idopcion)\n" +
            "where m.idmascota=?1 group by m.idmascota",nativeQuery = true)
    Integer edad(int id);

}
