package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Vehiculo;

public interface IVehiculoService {
	
	public Integer insertar(Vehiculo vehiculo);

	public void modificar(Vehiculo vehiculo);

	public void eliminar(int idVehiculo);

	Optional<Vehiculo> listarId(int idVehiculo);

	List<Vehiculo> listar();

	List<Vehiculo> buscarModelo(String modelo);

	List<Vehiculo> buscarMarca(String marca);

	List<Vehiculo> buscarPlaca(String placa);
	
	List<Vehiculo> buscarProveedor(String proveedor);
	
	List<Vehiculo> buscarPersona(String nombrePersona);
	
	List<Vehiculo> buscarOtros(String modelo);
}
