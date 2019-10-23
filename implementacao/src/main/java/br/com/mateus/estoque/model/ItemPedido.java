package br.com.mateus.estoque.model;

import br.com.mateus.estoque.model.enumerator.StatusItemPedido;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade de item do pedido.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido extends BasicModel {

  @ManyToOne
  private PedidoEstoque pedidoEstoque;

  @ManyToOne
  private Produto produto;

  private StatusItemPedido status;

  @Min(value = 1, message = "Quantidade mínima deve ser 1")
  private Integer quantidade;

  private BigDecimal valorUnitario;

  private BigDecimal valorTotal;

  /**
   * Sobrescrita do método getter para tratar valor unitário nulo
   *
   * @return valor unitário
   */
  public BigDecimal getValorUnitario() {
    if (this.valorUnitario == null) {
      this.valorUnitario = BigDecimal.ZERO;
    }

    return this.valorUnitario;
  }


  @PrePersist
  @PreUpdate
  private void calcValorTotal() {
    if (getQuantidade() > 0) {
      this.valorTotal = this.getValorUnitario().multiply(BigDecimal.valueOf(getQuantidade()));
    }
  }


}
