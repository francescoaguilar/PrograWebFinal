package pe.edu.upc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.entity.Cronograma;
import pe.edu.upc.service.ICronogramaService;
@Controller
@RequestMapping("/cronogramas")
public class CronogramaController {
	@Autowired
	private ICronogramaService cService;

	@RequestMapping("/bienvenido")
	public String irBienvenido() {
		return "bienvenido";
	}
	
	@GetMapping("/nuevo")
	public String nuevoCronograma(Model model) {
		model.addAttribute("cronograma", new Cronograma());
		
		return "cronograma/cronograma";
	}

	@GetMapping("/listar")
	public String listarCronogramas(Model model) {
		try {
			model.addAttribute("listaCronogramas", cService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/cronograma/listaCronograma";
	}
}
