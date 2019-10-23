package br.com.mateus.estoque.service;

import br.com.mateus.estoque.data.CompraData;
import br.com.mateus.estoque.model.Cliente;
import br.com.mateus.estoque.model.Compra;
import br.com.mateus.estoque.model.Filial;
import br.com.mateus.estoque.model.Usuario;
import br.com.mateus.estoque.model.enumerator.StatusItemPedido;
import br.com.mateus.estoque.repository.ClienteRepository;
import br.com.mateus.estoque.repository.CompraRepository;
import br.com.mateus.estoque.repository.FilialRepository;
import br.com.mateus.estoque.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe responsável  pelas operações de {@link Compra}.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Service
public class CompraService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private FilialRepository filialRepository;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private CompraRepository compraRepository;

  /**
   * Cria uma compra a partir dos dados de compra.
   *
   * @param compraData dados de compra
   * @return Compra persistida
   */
  public Compra create(CompraData compraData) {

    Cliente cliente = clienteRepository.findById(compraData.getIdCliente()).get();
    Filial filial = filialRepository.findById(compraData.getIdFifial()).get();
    Usuario usuario = usuarioRepository.findById(compraData.getIdUsuario()).get();

    Compra compra = new Compra(cliente, null, compraData.getFormaPagamento(), usuario, filial);

    return compraRepository.save(compra);
  }

  /**
   * Fecha a compra, alterando o status do item do pedido para processado.
   *
   * @param id chave da {@link Compra} que será fechada.
   */
  public void finalizarCompra(Long id) {

    Compra compra = compraRepository.findById(id).get();

    compra.getPedidoEstoque().getItensPedido().forEach(item -> {
      item.setStatus(StatusItemPedido.PROCESSADO);
    });

    compraRepository.save(compra);

  }

  /**
   * Retorna todos os registros de compra
   *
   * @return lista de compras
   */
  public List<Compra> findAll() {
    return compraRepository.findAll();
  }

  /**
   * Retorna uma {@link Compra} pelo id
   *
   * @param id chave da {@link Compra}
   * @return caso localize o id, retorna o item de compra referente ao mesmo, caso contrário null.
   */
  public Compra findById(Long id) {
    return compraRepository.findById(id).get();
  }
}
