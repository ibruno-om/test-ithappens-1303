package br.com.mateus.estoque.service;

import br.com.mateus.estoque.model.Cliente;
import br.com.mateus.estoque.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe responsável  pelas operações de {@link Cliente}.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Service
public class ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;


  /**
   * Retorna todos os registros de {@link Cliente}
   *
   * @return lista de clientes
   */
  public List<Cliente> findAll() {
    return clienteRepository.findAll();
  }

  public List<Cliente> pesquisarClientes(String term) {
    return clienteRepository
        .findByNomeContainingIgnoreCaseOrderByNome(term);
  }
}
