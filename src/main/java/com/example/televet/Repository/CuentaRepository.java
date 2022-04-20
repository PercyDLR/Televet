package com.example.televet.Repository;

import com.example.televet.Entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta,Integer> {
}
