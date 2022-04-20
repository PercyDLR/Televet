package com.example.televet.Repository;

import com.example.televet.Dto.OpcionResponsableDto;
import com.example.televet.Entity.Opcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpcionRepository extends JpaRepository<Opcion, Integer> {
    @Query(value = "select o.idopcion as idopcion, IFNULL(s.idservicio,0) as idservicio, o.descripcion as descripcion, o.tiempo_minutos as tiempominutos, o.precio as precio, r.nombre as responsable from opcion o\n" +
            "left join opcion_servicio os on os.opcion_idopcion = o.idopcion\n" +
            "left join servicio s on os.servicio_idservicio = s.idservicio\n" +
            "left join responsable r on r.idresponsable = s.responsable_idresponsable\n" +
            "order by o.idopcion;", nativeQuery = true)
    List<OpcionResponsableDto> obtenerOpciones();
}
