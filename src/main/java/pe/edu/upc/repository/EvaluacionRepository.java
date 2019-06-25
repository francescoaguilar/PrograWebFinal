package pe.edu.upc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Evaluacion;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Integer> {

	@Query("select p from Evaluacion p where p.persona.nombrePersona like %?1%")
	public List<Evaluacion> buscarNombreCliente(String nombrePersona);
	
	@Query("select e from Evaluacion e join fetch e.persona p join p.tipoPersona tp "
			+ "where tp.nombre like %?1%")
	public List<Evaluacion> buscarTipoCliente(String nombre);
	
	List<Evaluacion> findByFechaEvaluacion(Date fechaEvaluacion);
	
	@Query("select count(p.dniPersona) from Persona p where p.dniPersona =:dniPersona")
	public List<Evaluacion> buscarDniCliente(String dniPersona);
	
	@Query("select p from Evaluacion p where p.financiamiento =: financiamiento")
	public List<Evaluacion> buscarFinanciamientoCliente(double financiamiento);
	
}
