package br.com.mateus.estoque.repository;

import br.com.mateus.estoque.model.PedidoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório de dados de {@link PedidoEstoque}.
 *
 * @author Iago Bruno
 * @since 1.0
 */
public interface PedidoEstoqueRepository extends JpaRepository<PedidoEstoque, Long> {

}
