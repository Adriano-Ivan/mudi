package br.com.adriano.mvc.mudi.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.adriano.mvc.mudi.model.Pedido;
import br.com.adriano.mvc.mudi.model.StatusPedido;

@Repository
public interface PedidoRepository extends
	JpaRepository<Pedido, Long>{

	List<Pedido> findByStatus(StatusPedido aguardando);
}
