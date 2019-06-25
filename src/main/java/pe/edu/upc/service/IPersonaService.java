package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Persona;

public interface IPersonaService {
	public Integer insertar(Persona persona);

	public void modificar(Persona persona);

	public void eliminar(int idPersona);

	Optional<Persona> listarId(int idPersona);

	List<Persona> listar();

	List<Persona> buscar(String nombrePersona);

	List<Persona> buscarTipodePersona(String nombre);
	
	List<Persona> buscarOtros(String nombrePersona);
	
}
