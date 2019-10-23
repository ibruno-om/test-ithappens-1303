package br.com.mateus.estoque.data;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe de dados de inserção/edição de {@link br.com.mateus.estoque.model.ItemPedido}
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoData {

  private Long idPedido;

  private Long idProduto;

  private Integer quantidade;

  private BigDecimal valorUnitario;

  public Integer getQuatidade() {
    if (this.quantidade == null) {
      this.quantidade = 0;
    }
    return this.quantidade;
  }

}
