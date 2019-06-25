package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.TipoPersona;

@Repository
public interface TipoPersonaRepository  extends JpaRepository<TipoPersona, Integer>{
	
	@Query("select count(c.nombre) from TipoPersona c where c.nombre =:nombre")
	public int buscarTipodePersona(@Param("nombre") String nombre);
}
