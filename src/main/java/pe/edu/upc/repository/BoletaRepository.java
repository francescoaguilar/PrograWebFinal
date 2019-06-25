package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Boleta;

@Repository
public interface BoletaRepository  extends JpaRepository<Boleta, Integer>{

}

