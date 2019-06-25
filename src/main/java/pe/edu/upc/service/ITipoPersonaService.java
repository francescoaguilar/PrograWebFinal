package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.TipoPersona;


public interface ITipoPersonaService {

	public Integer insertar(TipoPersona tipopersona);

	public void modificar(TipoPersona tipopersona);

	public void eliminar(int idTipoPersona);
	
	List<TipoPersona> listar();
	
}
