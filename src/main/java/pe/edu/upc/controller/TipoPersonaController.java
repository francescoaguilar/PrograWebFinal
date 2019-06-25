package pe.edu.upc.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entity.TipoPersona;
import pe.edu.upc.service.ITipoPersonaService;

@Controller
@RequestMapping("/tipopersonas")
public class TipoPersonaController {
	@Autowired
	private ITipoPersonaService cService;

	@RequestMapping("/listReserva")
	public String irBienvenido() {
		return "listReserva";
	}

	@GetMapping("/nuevo")
	public String nuevoTipoPersona(Model model) {
		model.addAttribute("tipopersona", new TipoPersona());
		return "tipopersona/tipopersona";
	}


	@PostMapping("/guardar")
	public String guardarTipoPersona(@Valid TipoPersona tipopersona, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "tipopersona/tipopersona";
		} else {
			int rpta = cService.insertar(tipopersona);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/tipopersona/tipopersona";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}

		}
		model.addAttribute("listaTipoPersonas", cService.listar());

		return "/tipopersona/listaTipoPersona";
	}

	@GetMapping("/listar")
	public String listarTipoPersonas(Model model) {
		try {
			model.addAttribute("tipopersona", new TipoPersona());
			model.addAttribute("listaTipoPersonas", cService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/tipopersona/listaTipoPersona";
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
			model.put("mensaje", "No se puede eliminar un tipopersona");
		}
		model.put("listaTipoPersonas", cService.listar());

		return "redirect:/tipopersonas/listar";
	}

	
}
