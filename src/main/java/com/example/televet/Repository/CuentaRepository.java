package com.example.televet.Repository;

import com.example.televet.Dto.MascotasPorDuenoDto;
import com.example.televet.Entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta,Integer> {
    @Query(value="SELECT c.idcuenta as idcuenta,c.correo as correo, c.direccion as direccion,c.telefono as telefono, count(m.idmascota) as cantidadmascotas FROM cuenta c\n" +
            "left join mascota m on (m.cuenta_idcuenta = c.idcuenta)\n" +
            "where c.admin = 0 group by c.idcuenta", nativeQuery = true)
    public List<MascotasPorDuenoDto> listaDuenos();

    List<Cuenta> findByAdmin(int admin);

}
