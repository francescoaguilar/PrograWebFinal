package pe.edu.upc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Contrato;
import pe.edu.upc.service.IContratoService;
import pe.edu.upc.service.IEvaluacionService;

@Controller
@RequestMapping("/contratos")
public class ContratoController {

	@Autowired
	private IContratoService cService;
	
	@Autowired 
	private IEvaluacionService eService;
	
	@GetMapping("/bienvenido")
	public String bienvenido(Model model)
	{
		return "index/tabla";
	}
	
	@GetMapping("/nuevo")
	public String nuevoContrato(Model model) {
		model.addAttribute("contrato", new Contrato());
		model.addAttribute("listaEvaluaciones", eService.listar());
		return "contrato/contrato";
	}

	
	@PostMapping("/guardar")
	public String guardarContrato(@Valid Contrato contrato, BindingResult result , Model model,
			SessionStatus status) throws Exception{
		if(result.hasErrors()) {
			model.addAttribute("listaEvaluaciones", eService.listar());
			return "contrato/contrato";
		} else {
		}
			model.addAttribute("mensaje","Se guardó correctamente");
			status.setComplete();
		
		model.addAttribute("listaContratos", cService.listar());
		return "/contrato/listaContrato";
	}
		
	@GetMapping("/listar")
	public String listarContratos(Model model) {
		try {
			model.addAttribute("contrato", new Contrato());

			model.addAttribute("listaContratos", cService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/contrato/listaContrato";
	}
	
	@GetMapping("/detalle/{id}")
	public String detailsContrato(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Contrato> contrato = cService.listarId(id);

			if (!contrato.isPresent()) {
				model.addAttribute("info", "Contrato no existe");
				return "redirect:/contratos/listar";
			} else {
				model.addAttribute("contrato", contrato.get());

			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "/contrato/contrato";
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Contrato> objPro = cService.listarId(id);

		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/contratos/listar";
		} else {
			model.addAttribute("listaEvaluaciones", eService.listar());

			model.addAttribute("contrato", objPro.get());
			return "contrato/contrato";
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				cService.eliminar(id);

				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar un contrato");
		}
		model.put("listaEvaluaciones", cService.listar());

		return "redirect:/contratos/listar";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Contrato contrato) throws ParseException {

		List<Contrato> listaContratos;

		contrato.setFechaContrato(contrato.getFechaContrato());
		listaContratos = cService.buscarporfecha(contrato.getFechaContrato());

		if(listaContratos.isEmpty())
		{
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			contrato.setFechaContrato(sdf.parse(contrato.getEvaluacion().getPersona().getNombrePersona()));
			listaContratos = cService.buscarporfecha(contrato.getFechaContrato());
		} catch (Exception e) {
			model.put("mensaje", "Formato incorrecto");
		}
		}
		if (listaContratos.isEmpty()) {
			model.put("mensaje", "No se encuentra lo que se solicito :)");
		}

		model.put("listaContratos", listaContratos);
		return "contrato/listaContrato";

	}

}
