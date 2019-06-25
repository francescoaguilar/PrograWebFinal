package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Vehiculo;

@Repository
public interface VehiculoRepository  extends JpaRepository<Vehiculo, Integer>{
	@Query("from Vehiculo c where c.modelo like %:modelo%")
	List<Vehiculo> findByModeloVehiculo(String modelo);

	@Query("from Vehiculo c where c.marca like %:marca%")
	List<Vehiculo> findByMarcaVehiculo(String marca);

	@Query("from Vehiculo c where c.placa like %:placa%")
	List<Vehiculo> findByPlaca(String placa);
	
	@Query("from Vehiculo c where c.proveedor like %:proveedor%")
	List<Vehiculo> findByProveedor(String proveedor);

	@Query("select count(c.placa) from Vehiculo c where c.placa =:placa")
	public int buscarPlaca(@Param("placa") String placa);

	@Query("select c from Vehiculo c join fetch c.evaluacion e join e.persona p where p.nombrePersona like %?1%")
	public List<Vehiculo> buscarpersona(String nombrePersona);
	
	public List<Vehiculo> findBymodeloLikeIgnoreCase(String term);

	
	
}