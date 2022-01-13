package br.com.adriano.mvc.mudi.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.adriano.mvc.mudi.model.Pedido;
import br.com.adriano.mvc.mudi.model.StatusPedido;
import br.com.adriano.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public String montarRespostaEretornar(Model model,Principal principal) {
		Sort sort = Sort.by("dataDaEntrega").descending();
		PageRequest paginacao = PageRequest.of(0, 10,sort);
		
		List<Pedido> pedidos =pedidoRepository.findByStatus(StatusPedido.ENTREGUE,paginacao);
		model.addAttribute("pedidos",pedidos);
		
		return "home";
	}
	@GetMapping
	public String root(Model model, Principal principal) {
		return montarRespostaEretornar(model,principal);
	}
	
	@GetMapping("/home")
	public String home(Model model, Principal principal) {
		return montarRespostaEretornar(model,principal);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}
}
