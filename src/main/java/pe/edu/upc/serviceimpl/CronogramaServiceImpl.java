package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Cronograma;
import pe.edu.upc.repository.CronogramaRepository;
import pe.edu.upc.service.ICronogramaService;;


@Service
public class CronogramaServiceImpl implements ICronogramaService {
	
	@Autowired
	private CronogramaRepository cR;

	@Override
	@Transactional(readOnly = true)
	public List<Cronograma> listar() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}
}
