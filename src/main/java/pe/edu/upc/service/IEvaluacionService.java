package pe.edu.upc.service;

import java.util.Date;
import java.util.List;

import pe.edu.upc.entity.Evaluacion;

public interface IEvaluacionService {
	public Integer insertar(Evaluacion evaluacion);

	public void modificar(Evaluacion evaluacion);

	public void eliminar(int idEvaluacion);

	List<Evaluacion> listar();

	List<Evaluacion> buscarporfinanciamiento(double financiamiento);

	List<Evaluacion> buscarporfecha(Date fechaEvaluacion);
	
	List<Evaluacion> buscarportipopersona(String nombre);
}
