package br.com.adriano.mvc.mudi.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.adriano.mvc.mudi.model.Pedido;
import br.com.adriano.mvc.mudi.model.StatusPedido;

@Repository
public interface PedidoRepository extends
	JpaRepository<Pedido, Long> {
	
	List<Pedido> findByStatus(StatusPedido aguardando);

	@Query("select p from Pedido p join p.user u where u.username = :username")
	List<Pedido> findAllByUsuario(@Param("username")String username);
}
