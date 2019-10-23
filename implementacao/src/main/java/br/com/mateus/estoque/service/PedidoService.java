package br.com.mateus.estoque.service;

import br.com.mateus.estoque.data.ItemPedidoData;
import br.com.mateus.estoque.model.Estoque;
import br.com.mateus.estoque.model.ItemEstoque;
import br.com.mateus.estoque.model.ItemPedido;
import br.com.mateus.estoque.model.PedidoEstoque;
import br.com.mateus.estoque.model.Produto;
import br.com.mateus.estoque.model.enumerator.StatusItemPedido;
import br.com.mateus.estoque.repository.EstoqueRepository;
import br.com.mateus.estoque.repository.PedidoEstoqueRepository;
import br.com.mateus.estoque.repository.ProdutoRepository;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe responsável  pelas operações de {@link PedidoEstoque}.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Service
public class PedidoService {

  @Autowired
  private PedidoEstoqueRepository pedidoEstoqueRepository;

  @Autowired
  private ProdutoRepository produtoRepository;

  @Autowired
  private EstoqueRepository estoqueRepository;

  /**
   * Adiciona um {@link ItemPedido} conforme dados do item informado. Este método altera a
   * quantidade de {@link Produto} no estoque da {@link br.com.mateus.estoque.model.Filial}
   * informada.
   *
   * @param itemPedidoData dados do item do pedido
   * @return retorna Boolean.TRUE caso a operção seja realizada com sucesso, caso contrário, false.
   */
  @Transactional
  public Boolean create(ItemPedidoData itemPedidoData) {
    PedidoEstoque pedidoEstoque = pedidoEstoqueRepository.findById(itemPedidoData.getIdPedido())
        .get();

    Estoque estoque = estoqueRepository.findByFilial(pedidoEstoque.getFilial());

    Produto produto = produtoRepository.findById(itemPedidoData.getIdProduto()).get();

    Integer totalSolicitado = pedidoEstoque
        .getItensPedido().stream()
        .filter(itemEstoque -> itemEstoque.getProduto().equals(produto)).map(
            ItemPedido::getQuantidade).reduce(0, (a, b) -> a + b);

    Stream<ItemEstoque> itemEstoqueStream = estoque.getItensEstoque().stream()
        .filter(itemEstoque -> itemEstoque.getProduto().equals(produto));

    Integer totalEstoque = itemEstoqueStream.map(
        ItemEstoque::getQuantidade).reduce(0, (a, b) -> a + b);

    if (totalEstoque >= itemPedidoData.getQuantidade()) {

      Stream<ItemPedido> itemPedidoStream = pedidoEstoque.getItensPedido().stream()
          .filter(item -> item.getProduto().equals(produto));

      if (!itemPedidoStream.findAny().isPresent()) {
        pedidoEstoque.getItensPedido().add(
            new ItemPedido(pedidoEstoque, produto, StatusItemPedido.ATIVO,
                itemPedidoData.getQuantidade(), itemPedidoData.getValorUnitario(), null));
      } else {
        pedidoEstoque.getItensPedido().stream()
            .filter(item -> item.getProduto().equals(produto)).forEach(item -> {
          item.setQuantidade(itemPedidoData.getQuantidade());
          item.setValorUnitario(itemPedidoData.getValorUnitario());
        });
      }

      if (estoque.getItensEstoque().stream()
          .filter(itemEstoque -> itemEstoque.getProduto().equals(produto)).findAny().isPresent()) {
        estoque.getItensEstoque().stream()
            .filter(itemEstoque -> itemEstoque.getProduto().equals(produto)).forEach(item -> {
          item.setQuantidade(
              item.getQuantidade() + totalSolicitado - itemPedidoData.getQuantidade());
        });
      }

      pedidoEstoqueRepository.save(pedidoEstoque);
      estoqueRepository.save(estoque);

      return Boolean.TRUE;
    } else {
      return Boolean.FALSE;
    }
  }

  /**
   * Retorna o registro de {@link PedidoEstoque} conforme o ID informado.
   * @param id chave do registo de {@link PedidoEstoque}
   * @return pedido do estoque caso exista para o ID informado, caso contrário, null.
   */
  public PedidoEstoque findById(Long id) {
    return pedidoEstoqueRepository.findById(id).get();
  }
}
