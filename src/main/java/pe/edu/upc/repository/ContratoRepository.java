package pe.edu.upc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Contrato;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato,Integer> {
	
	@Query("select c from Contrato c join fetch c.evaluacion e join e.persona p "
			+ "where p.nombrePersona like %?1%")
	public List<Contrato> buscarCliente(String nombrePersona);
	
	List<Contrato> findByFechaContrato(Date fechaContrato);
	
	
}
