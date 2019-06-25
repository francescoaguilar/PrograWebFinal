package pe.edu.upc.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Evaluacion;
import pe.edu.upc.repository.EvaluacionRepository;
import pe.edu.upc.service.IEvaluacionService;

@Service
public class EvaluacionServiceImpl implements IEvaluacionService {
	@Autowired
	private EvaluacionRepository pR;
	
	@Override
	@Transactional
	public Integer insertar(Evaluacion evaluacion) {
	{
		int rpta = 0;
		if (rpta == 0) {
			pR.save(evaluacion);
		}
		return rpta;
	}
}

	@Override
	@Transactional
	public void modificar(Evaluacion evaluacion) {
		pR.save(evaluacion);

	}

	@Override
	@Transactional
	public void eliminar(int idEvaluacion) {
		pR.deleteById(idEvaluacion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Evaluacion> listar() {
		return pR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Evaluacion> buscarporfecha(Date fechaEvaluacion) {
		return pR.findByFechaEvaluacion(fechaEvaluacion);
	}

	@Override
	public List<Evaluacion> buscarporfinanciamiento(double financiamiento) {
		return pR.buscarFinanciamientoCliente(financiamiento);
	}

	@Override
	public List<Evaluacion> buscarportipopersona(String nombre) {
		return pR.buscarTipoCliente(nombre);
	}
	

}
