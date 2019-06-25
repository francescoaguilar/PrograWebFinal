package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.TipoPersona;
import pe.edu.upc.repository.TipoPersonaRepository;
import pe.edu.upc.service.ITipoPersonaService;

@Service
public class TipoPersonaServiceImpl implements ITipoPersonaService{

	@Autowired
	private TipoPersonaRepository PeR;
	
	@Override
	@Transactional
	public Integer insertar(TipoPersona tipopersona) {
		int rpta = PeR.buscarTipodePersona(tipopersona.getNombre());
		if (rpta == 0) {
			PeR.save(tipopersona);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void modificar(TipoPersona tipopersona) {
		PeR.save(tipopersona);
	}

	@Override
	@Transactional
	public void eliminar(int idTipoPersona) {
		PeR.deleteById(idTipoPersona);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoPersona> listar() {
		return PeR.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
	}

}
