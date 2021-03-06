package br.com.adriano.mvc.mudi.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.adriano.mvc.mudi.dto.RequisicaoNovaOferta;
import br.com.adriano.mvc.mudi.model.Oferta;
import br.com.adriano.mvc.mudi.model.Pedido;
import br.com.adriano.mvc.mudi.repository.PedidoRepository;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@PostMapping
	public Oferta criaOferta(@RequestBody RequisicaoNovaOferta requisicao) {
		Optional<Pedido> pedidoBuscado= pedidoRepository.findById(requisicao.getPedidoId());
		if(!pedidoBuscado.isPresent()) {
			return null;
		}
		Pedido pedido = pedidoBuscado.get();
		
		Oferta nova = requisicao.toOferta();
		nova.setPedido(pedido);
		pedido.adicionaOferta(nova);
		pedidoRepository.save(pedido);
		
		return nova;
	}
}
