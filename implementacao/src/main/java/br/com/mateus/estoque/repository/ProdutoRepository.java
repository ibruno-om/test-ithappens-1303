package br.com.mateus.estoque.repository;

import br.com.mateus.estoque.model.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório de dados de {@link Produto}.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

  List<Produto> findBySequencialContainingOrCodigoBarrasContainingOrDescricaoContainingAllIgnoreCase(
      String sequencial,
      String codigoBarras, String descricao);

}
