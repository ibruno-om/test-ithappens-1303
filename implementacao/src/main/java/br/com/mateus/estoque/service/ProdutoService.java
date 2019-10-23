package br.com.mateus.estoque.service;

import br.com.mateus.estoque.model.Produto;
import br.com.mateus.estoque.repository.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe responsável  pelas operações de {@link Produto}.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Service
public class ProdutoService {

  @Autowired
  private ProdutoRepository produtoRepository;

  /**
   *
   * @param term
   * @return
   */
  public List<Produto> pesquisarProdutos(String term) {
    return produtoRepository
        .findBySequencialContainingOrCodigoBarrasContainingOrDescricaoContainingAllIgnoreCase(term,
            term, term);
  }

  /**
   *
   * @param id
   * @return
   */
  public Produto findById(Long id) {
    return produtoRepository.findById(id).get();
  }
}
