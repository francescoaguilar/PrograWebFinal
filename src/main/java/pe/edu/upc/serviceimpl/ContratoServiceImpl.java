package pe.edu.upc.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Contrato;
import pe.edu.upc.repository.ContratoRepository;
import pe.edu.upc.service.IContratoService;

@Service
public class ContratoServiceImpl implements IContratoService{

	@Autowired
	private ContratoRepository cR;

	@Override
	@Transactional
	public void insertar(Contrato contrato) {
		cR.save(contrato);
	}

	@Override
	@Transactional
	public void modificar(Contrato contrato) {
		cR.save(contrato);
		
	}

	@Override
	@Transactional
	public void eliminar(int idContrato) {
		cR.deleteById(idContrato);
	}

	@Override
	@Transactional (readOnly = true)
	public List<Contrato> listar() {
		return cR.findAll();
	}

	@Override
	@Transactional 
	public List<Contrato> buscarporfecha(Date fechaContrato) {
		return cR.findByFechaContrato(fechaContrato);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Contrato> buscarporCliente(String nombreContrato) {
		return cR.buscarCliente(nombreContrato);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Contrato> listarId(int idContrato) {
		return cR.findById(idContrato);
	}
}
