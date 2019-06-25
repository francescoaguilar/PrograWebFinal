package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
	@Query("select p from Persona p where p.nombrePersona like %?1%")
	public List<Persona> findByNombrePersona(String term);

	@Query("select p from Persona p where p.tipoPersona.nombre like %?1%")
	public List<Persona> buscarTipoPersona(String nombre);

	public List<Persona> findByNombrePersonaLikeIgnoreCase(String term);

	@Query("select count(p.nombrePersona) from Persona p where p.nombrePersona =:nombrePersona")
	public int buscarNombrePersona(@Param("nombrePersona") String nombrePersona);
	
	@Query("select count(p.dniPersona) from Persona p where p.dniPersona =:dniPersona")
	public int buscarDniPersona(@Param("dniPersona") String dniPersona);
}
