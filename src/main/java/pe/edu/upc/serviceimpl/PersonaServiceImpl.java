package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Persona;
import pe.edu.upc.repository.PersonaRepository;
import pe.edu.upc.service.IPersonaService;

@Service
public class PersonaServiceImpl implements IPersonaService {
	@Autowired
	private PersonaRepository pR;

	@Override
	@Transactional
	public Integer insertar(Persona producto) {
		int rpta = pR.buscarDniPersona(producto.getDniPersona());
		if (rpta == 0) {
			pR.save(producto);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void modificar(Persona producto) {
		pR.save(producto);

	}

	@Override
	@Transactional
	public void eliminar(int idPersona) {
		pR.deleteById(idPersona);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Persona> listarId(int idPersona) {
		return pR.findById(idPersona);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Persona> listar() {
		return pR.findAll(Sort.by(Direction.ASC, "nombrePersona"));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Persona> buscar(String nombrePersona) {
		return pR.findByNombrePersona(nombrePersona);
	}

	@Override
	public List<Persona> buscarTipodePersona(String nombre) {
		return pR.buscarTipoPersona(nombre);
	}

	@Override
	public List<Persona> buscarOtros(String nombrePersona) {
		return pR.findByNombrePersonaLikeIgnoreCase(nombrePersona);
	}


}
