package pe.edu.upc.controller;

import java.text.ParseException;
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

import pe.edu.upc.entity.Persona;
import pe.edu.upc.service.IPersonaService;
import pe.edu.upc.service.ITipoPersonaService;
 
@Controller
@RequestMapping("/personas")
public class PersonaController {
	@Autowired
	private IPersonaService pService;
	@Autowired
	private ITipoPersonaService cService;

	@GetMapping("/bienvenido")
	public String bienvenido(Model model) {
		return "bienvenido";
	}

	@GetMapping("/nuevo")
	public String nuevoPersona(Model model) {
		model.addAttribute("persona", new Persona());
		model.addAttribute("listaTipoPersonas", cService.listar());
		return "persona/persona";
	}

	@PostMapping("/guardar")
	public String guardarPersona(@Valid Persona persona, BindingResult result, Model model,
			 SessionStatus status) throws Exception {

		if (result.hasErrors()) {
			model.addAttribute("listaTipoPersonas", cService.listar());
			return "persona/persona";
		} else {
			}
			int rpta = pService.insertar(persona);//
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/persona/persona";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
		}
		model.addAttribute("listaPersonas", pService.listar());

		return "/persona/listaPersona";

	}

	@GetMapping("/listar")
	public String listarPersonas(Model model) {
		try {
			model.addAttribute("persona", new Persona());

			model.addAttribute("listaPersonas", pService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/persona/listaPersona";
	}

	@GetMapping("/detalle/{id}")
	public String detailsPersona(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Persona> persona = pService.listarId(id);

			if (!persona.isPresent()) {
				model.addAttribute("info", "Persona no existe");
				return "redirect:/personas/listar";
			} else {
				model.addAttribute("persona", persona.get());

			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "/persona/persona";
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
			model.put("mensaje", "No se puede eliminar un persona");
		}
		model.put("listaPersonas", pService.listar());

		return "redirect:/personas/listar";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Persona persona) throws ParseException {

		List<Persona> listaPersonas;

		persona.setNombrePersona(persona.getNombrePersona());
		listaPersonas = pService.buscar(persona.getNombrePersona());

		if (listaPersonas.isEmpty()) {
			listaPersonas = pService.buscarTipodePersona(persona.getNombrePersona());
		}

		if (listaPersonas.isEmpty()) {
			listaPersonas = pService.buscarOtros(persona.getNombrePersona());

		}

		if (listaPersonas.isEmpty()) {
			model.put("mensaje", "No se encuentra lo que se solicito :)");
		}

		model.put("listaPersonas", listaPersonas);
		return "persona/listaPersona";

	}

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Persona> persona = pService.listarId(id);
		if (persona == null) {
			flash.addFlashAttribute("error", "El Persona no existe en la base de datos");
			return "redirect:/personas/listar";
		}

		model.put("persona", persona.get());

		return "persona/ver";
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Persona> objPro = pService.listarId(id);

		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³Â³ un error");
			return "redirect:/personas/listar";
		} else {
			model.addAttribute("listaTipoPersonas", cService.listar());

			model.addAttribute("persona", objPro.get());
			return "persona/persona";
		}
	}

}
