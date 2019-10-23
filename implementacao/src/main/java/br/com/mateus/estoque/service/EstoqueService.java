package br.com.mateus.estoque.service;

import br.com.mateus.estoque.model.Estoque;
import br.com.mateus.estoque.repository.EstoqueRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe responsável pelas operações de {@link Estoque}
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Service
public class EstoqueService {

  @Autowired
  private EstoqueRepository estoqueRepository;

  /**
   * Lista todos os registros de {@link Estoque}
   *
   * @return lista de estoque
   */
  public List<Estoque> findAll() {
    return estoqueRepository.findAll();
  }


  /**
   * Retorna um registro de estoque conforme o ID informado.
   *
   * @param id chave primária do {@link Estoque}
   * @return registro de estoque caso encontrado, caso contrário, null.
   */
  public Estoque findById(Long id) {
    return estoqueRepository.findById(id).get();
  }
}
