package com.example.televet.Repository;

import com.example.televet.Entity.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsableRepository extends JpaRepository<Responsable,Integer>{
}
