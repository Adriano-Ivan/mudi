package br.com.adriano.mvc.mudi.controller;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.adriano.mvc.mudi.model.Pedido;
import br.com.adriano.mvc.mudi.repository.PedidoRepository;

@Controller
public class HomeController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping("/home")
	public String home(Model model) {
		List<Pedido> pedidos = pedidoRepository.findAll();
		System.out.println(pedidos.size()+" pedidos");
		model.addAttribute("pedidos",pedidos);
		
		return "home";
	}
}
