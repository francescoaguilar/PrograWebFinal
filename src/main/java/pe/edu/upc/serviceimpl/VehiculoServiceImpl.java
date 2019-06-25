package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Vehiculo;
import pe.edu.upc.repository.VehiculoRepository;
import pe.edu.upc.service.IVehiculoService;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

	@Autowired
	private VehiculoRepository vR;
	@Override
	@Transactional
	public Integer insertar(Vehiculo vehiculo) {
		int rpta = vR.buscarPlaca(vehiculo.getPlaca());
		if(rpta == 0)
		{
			vR.save(vehiculo);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void modificar(Vehiculo vehiculo) {
		vR.save(vehiculo);
	}

	@Override
	@Transactional
	public void eliminar(int idVehiculo) {
		vR.deleteById(idVehiculo);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Vehiculo> listarId(int idVehiculo) {
		return vR.findById(idVehiculo);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Vehiculo> listar() {
		return vR.findAll(Sort.by(Direction.ASC,"idVehiculo"));
	}

	@Override
	@Transactional (readOnly=true)
	public List<Vehiculo> buscarModelo(String modelo) {
		return vR.findByModeloVehiculo(modelo);
	}

	@Override
	@Transactional
	public List<Vehiculo> buscarMarca(String marca) {
		return vR.findByMarcaVehiculo(marca);
	}

	@Override
	@Transactional
	public List<Vehiculo> buscarPlaca(String placa) {
		return vR.findByPlaca(placa);
	}

	@Override
	@Transactional
	public List<Vehiculo> buscarProveedor(String proveedor) {
		return vR.findByProveedor(proveedor);
	}

	@Override
	@Transactional
	public List<Vehiculo> buscarPersona(String nombrePersona) {
		return vR.buscarpersona(nombrePersona);
	}

	@Override
	@Transactional
	public List<Vehiculo> buscarOtros(String modelo) {
		return vR.findBymodeloLikeIgnoreCase(modelo);
	}

}
