package pe.edu.upc.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Contrato;

public interface IContratoService {
	
	public void insertar(Contrato contrato);
	
	public void modificar(Contrato contrato);
	
	public void eliminar(int idContrato);
	
	List<Contrato> listar();
	
	Optional<Contrato> listarId(int idContrato);
	
	List<Contrato> buscarporfecha(Date fechaContrato);
	
	List<Contrato> buscarporCliente(String nombrePersona);

}
