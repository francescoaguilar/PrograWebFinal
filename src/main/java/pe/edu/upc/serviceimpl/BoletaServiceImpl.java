package pe.edu.upc.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Boleta;
import pe.edu.upc.repository.BoletaRepository;
import pe.edu.upc.repository.CronogramaRepository;
import pe.edu.upc.service.IBoletaService;

@Service
public class BoletaServiceImpl implements IBoletaService{

	@Autowired
	private BoletaRepository bR;
	
	@Autowired
	private CronogramaRepository cR;

	@Override
	@Transactional
	public void insertar(Boleta boleta) {
		
		String NTransac="";
		Random rnd = new Random();
		boolean a = true;
		for(int i = 0; i< 10 ; i++)
		{
			if(i<5)
			{
				NTransac += rnd.nextInt(10);
			}
			else
			{
				NTransac += (char)(rnd.nextInt(25) + 65);
			}
		}
		Date fechaBoleta = new Date();
	
		boleta.setFechaPago(fechaBoleta);
		boleta.setEstado(a);
		boleta.setMonto(cR.findAll().get(boleta.getVecespagadas()).getCuota());
		boleta.setNroTransaccion(NTransac);
		bR.save(boleta);
	}

	@Override
	@Transactional
	public void modificar(Boleta boleta) {
		bR.save(boleta);
	}

	@Override
	@Transactional
	public void eliminar(int idBoleta) {
		bR.deleteById(idBoleta);
	}

	@Override
	@Transactional
	public List<Boleta> listar() {
		return bR.findAll();
	}

	@Override
	@Transactional
	public Optional<Boleta> listarId(int idBoleta) {
		return bR.findById(idBoleta);
	}



	
}
