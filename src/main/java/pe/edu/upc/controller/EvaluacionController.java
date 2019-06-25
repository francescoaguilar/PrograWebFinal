package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entity.Evaluacion;
import pe.edu.upc.service.IEvaluacionService;
import pe.edu.upc.service.IPersonaService;

@Controller
@RequestMapping("/evaluaciones")
public class EvaluacionController {
	@Autowired
	private IEvaluacionService pService;
	@Autowired
	private IPersonaService cService;

	@GetMapping("/bienvenido")
	public String bienvenido(Model model) {
		return "pago/paymentselector";
	}

	@GetMapping("/nuevo")
	public String nuevoEvaluacion(Model model) {
		model.addAttribute("evaluacion", new Evaluacion());
		model.addAttribute("listaPersonas", cService.listar());
		return "evaluacion/evaluacion";
	}

	@PostMapping("/guardar")
	public String guardarEvaluacion(@Valid Evaluacion evaluacion, BindingResult result, Model model,
			 SessionStatus status) throws Exception {

		if (result.hasErrors()) {
			model.addAttribute("listaPersonas", cService.listar());
			return "evaluacion/evaluacion";
		} else {
			}
			int rpta = pService.insertar(evaluacion);//
			if (rpta == 0) {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
		}
		//model.addAttribute("listaEvaluaciones", pService.listar());

		return "/evaluacion/listaEvaluacion";

	}

	@GetMapping("/listar")
	public String listarEvaluaciones(Model model) {
		try {
			model.addAttribute("evaluacion", new Evaluacion());

			model.addAttribute("listaEvaluaciones", pService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/evaluacion/listaEvaluacion";
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				pService.eliminar(id);

				model.put("mensaje", "Se eliminÃ³ correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar un evaluacion");
		}
		model.put("listaEvaluaciones", pService.listar());

		return "redirect:/evaluaciones/listar";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Evaluacion evaluacion) throws ParseException {

		List<Evaluacion> listaEvaluaciones;

		listaEvaluaciones = pService.buscarporfecha(evaluacion.getFechaEvaluacion());

		if (listaEvaluaciones.isEmpty()) {
			listaEvaluaciones = pService.buscarporfinanciamiento(evaluacion.getFinanciamiento());
		}
		if (listaEvaluaciones.isEmpty()) {
			listaEvaluaciones = pService.buscarportipopersona(evaluacion.getPersona().getDniPersona());
		}
		if(listaEvaluaciones.isEmpty())
		{
			model.put("mensaje", "No se encuentra lo que se solicito :)");
	
		}
		model.put("listaEvaluaciones", listaEvaluaciones);
		return "evaluacion/listaEvaluacion";

	}
}
