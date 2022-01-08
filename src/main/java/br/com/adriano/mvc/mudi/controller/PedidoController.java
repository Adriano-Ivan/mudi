package br.com.adriano.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.adriano.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.adriano.mvc.mudi.model.Pedido;
import br.com.adriano.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping("formulario")
	public String formulario(RequisicaoNovoPedido requisicaoNovoPedido) {
		return "pedido/formulario";
	}
	@PostMapping("novo")
	public String novo(@Valid RequisicaoNovoPedido requisicaoNovoPedido,
			BindingResult result) {
		if(result.hasErrors()) {
			return "pedido/formulario";
		}
		Pedido pedido = requisicaoNovoPedido.toPedido();
		pedidoRepository.save(pedido);
		
		return "redirect:/home";
	}
}
