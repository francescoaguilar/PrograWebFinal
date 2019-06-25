package pe.edu.upc.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entity.Boleta;
import pe.edu.upc.entity.Contrato;
import pe.edu.upc.service.IBoletaService;
import pe.edu.upc.service.IContratoService;

@Controller
@RequestMapping("/boletas")
public class BoletaController {
	
	@Autowired
	private IBoletaService bService;
	
	@Autowired
	private IContratoService cService;
	
	@GetMapping("/retornar")
	public String index(Model model){
	return "pago/bienvenido";
	}
	
	@GetMapping("/nuevo")
	public String nuevoBoleta(Model model){
		model.addAttribute("boleta", new Boleta());
		model.addAttribute("listaContratos", cService.listar());
		return "pago/seleccionpago";
	}
	
	
	@GetMapping("/nuevo2")
	public String nuevoBoleta2(Model model){
		model.addAttribute("boleta", new Boleta());
		model.addAttribute("listaContratos", cService.listar());
		return "pago/paymentselector";
	}
	
	@PostMapping("/guardar")
	public String guardarBoleta(@Valid Boleta boleta, BindingResult result , Model model,
			SessionStatus status) throws Exception{
		if(result.hasErrors()) {
			model.addAttribute("listaContratos", cService.listar());
			return "pago/seleccionpago";
		} else {
			}
			
			bService.insertar(boleta);
			model.addAttribute("mensaje","Se guardó correctamente");
			status.setComplete();
		
		model.addAttribute("listaBoletas", cService.listar());
		return "/pago/exitoso";
	}
	
	@PostMapping("/guardar2")
	public String guardarBoleta2(@Valid Boleta boleta, BindingResult result , Model model,
			SessionStatus status) throws Exception{
		if(result.hasErrors()) {
			model.addAttribute("listaContratos", cService.listar());
			return "pago/paymentselector";
		} else {
			}
			
			bService.insertar2(boleta);
			model.addAttribute("mensaje","Se guardó correctamente");
			status.setComplete();
		
		model.addAttribute("listaBoletas", cService.listar());
		return "/pago/exitoso2";
	}
	
	
	@GetMapping("/listar")
	public String listarBoletas(Model model) {
		try {
			model.addAttribute("boleta", new Contrato());

			model.addAttribute("listaBoletas", bService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/boleta/listaBoletas";
	}
	
	@GetMapping("/detalle/{id}")
	public String detailsBoleta(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Boleta> boleta = bService.listarId(id);
	

			if (!boleta.isPresent()) {
				model.addAttribute("info", "Boleta no existe");
				return "redirect:/boletas/listar";
			} else {
				model.addAttribute("boleta", boleta.get());

			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "/boleta/boleta";
	}

}
