package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Boleta;

public interface IBoletaService {

public void insertar(Boleta boleta);
	
	public void modificar(Boleta boleta);
	
	public void eliminar(int idBoleta);
	
	List<Boleta> listar();
	
	Optional<Boleta> listarId(int idBoleta);




}
