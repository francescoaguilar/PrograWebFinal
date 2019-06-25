package pe.edu.upc.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Vehiculo;
import pe.edu.upc.service.IEvaluacionService;
import pe.edu.upc.service.IUploadFileService;
import pe.edu.upc.service.IVehiculoService;

@Controller
@RequestMapping("/vehiculos")
public class VehiculoController {

	@Autowired
	private IVehiculoService vService;
	
	@Autowired
	private IEvaluacionService eService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@RequestMapping("/bienvenido")
	public String irBienvenido(Model model) {
		return "index/tabla";
	}

	@GetMapping("/nuevo")
	public String nuevoVehiculo(Model model) {
		model.addAttribute("vehiculo", new Vehiculo());
		model.addAttribute("listaEvaluaciones", eService.listar());
		return "vehiculo/vehiculo";
	}

	@PostMapping("/guardar")
	public String guardarVehiculo(@Valid Vehiculo vehiculo, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) throws Exception {

		if (result.hasErrors()) {
			model.addAttribute("listaEvaluaciones", eService.listar());
			return "vehiculo/vehiculo";
		} else {
			if (!foto.isEmpty()) {
//si no presenta errores presenta esta funcion
				if (vehiculo.getIdVehiculo() > 0 && vehiculo.getFoto() != null && vehiculo.getFoto().length() > 0) {
//cumple la condicion
					uploadFileService.delete(vehiculo.getFoto());//elimina la imagen ingresada para que si se desea ingresar otra imagen no salga en el mismo registrar
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);//prueba si se copia el archivo que se sube,dentro de tu carpeta uploads
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");//mostrara que has subido correctamente con el nombre del file, en este caso como es un archivo grande el flashatributte te ayuda a eso
				vehiculo.setFoto(uniqueFilename);
			}
			int rpta = vService.insertar(vehiculo);//
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/vehiculo/vehiculo";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}

		}
		model.addAttribute("listaVehiculos", vService.listar());

		return "/vehiculo/listaVehiculo";

	}

	@GetMapping("/listar")
	public String listarVehiculos(Model model) {
		try {
			model.addAttribute("vehiculo", new Vehiculo());
			model.addAttribute("listaVehiculos", vService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/vehiculo/listaVehiculo";
	}

	@GetMapping("/detalle/{id}")
	public String detailsVehiculo(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Vehiculo> vehiculo = vService.listarId(id);
			if (!vehiculo.isPresent()) {
				model.addAttribute("info", "Vehiculo no existe");
				return "redirect:/vehiculos/listar";
			} else {
				model.addAttribute("vehiculo", vehiculo.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/vehiculo/vehiculo";
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				vService.eliminar(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar un vehiculo");
		}
		model.put("listaVehiculos", vService.listar());

		return "redirect:/vehiculos/listar";
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Vehiculo> objPro = vService.listarId(id);

		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³Â³ un error");
			return "redirect:/vehiculos/listar";
		} else {
			model.addAttribute("listaEvaluaciones", eService.listar());

			model.addAttribute("vehiculo", objPro.get());
			return "vehiculo/vehiculo";
		}
	}
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Vehiculo> vehiculo = vService.listarId(id);
		if (vehiculo == null) {
			flash.addFlashAttribute("error", "El Vehiculo no existe en la base de datos");
			return "redirect:/vehiculos/listar";
		}

		model.put("vehiculo", vehiculo.get());

		return "vehiculo/ver";
	}
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Vehiculo vehiculo) throws ParseException {

		List<Vehiculo> listaVehiculos;
		vehiculo.setModelo(vehiculo.getModelo());
		listaVehiculos	= vService.buscarModelo(vehiculo.getModelo());
		if (listaVehiculos.isEmpty()) {
			listaVehiculos = vService.buscarMarca(vehiculo.getModelo());
		}
		if (listaVehiculos.isEmpty()) {
			listaVehiculos = vService.buscarOtros(vehiculo.getModelo());
		}
		if (listaVehiculos.isEmpty()) {
			listaVehiculos = vService.buscarPersona(vehiculo.getModelo());
		}
		if (listaVehiculos.isEmpty()) {
			listaVehiculos = vService.buscarPlaca(vehiculo.getModelo());
		}
		if (listaVehiculos.isEmpty()) {
			listaVehiculos = vService.buscarProveedor(vehiculo.getModelo());
		}
		if(listaVehiculos.isEmpty())
		{
			model.put("mensaje", "No se encuentra lo que se solicito :)");
	
		}
		model.put("listaVehiculos", listaVehiculos);
		return "vehiculo/listaVehiculo";

	}
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
//muestra una clase nueva que tiene como variable principal un recurso
		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);//carga el archivo
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();//si existe error , me mostrara un mensaje
		}
		return ResponseEntity.ok()//retorna un valor en este caso el nombre del archivo, que sera guardado dentro de uploads
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

}
