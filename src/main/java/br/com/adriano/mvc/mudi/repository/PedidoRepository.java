package br.com.adriano.mvc.mudi.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.adriano.mvc.mudi.model.Pedido;
import br.com.adriano.mvc.mudi.model.StatusPedido;

@Repository
public interface PedidoRepository extends
	JpaRepository<Pedido, Long> {
	
	@Cacheable("books")
	List<Pedido> findByStatus(StatusPedido aguardando,Pageable sort);

	@Query("select p from Pedido p join p.user u where u.username = :username")
	List<Pedido> findAllByUser(@Param("username")String username);
	
	@Query("select p from Pedido p join p.user u where u.username = :username and p.status = :status")
	List<Pedido> findByStatusAndUser(@Param("status")StatusPedido status, @Param("username")String username);
}
