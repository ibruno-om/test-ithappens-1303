package br.com.mateus.estoque.model;

import br.com.mateus.estoque.model.enumerator.FormaPagamento;
import br.com.mateus.estoque.model.enumerator.StatusItemPedido;
import br.com.mateus.estoque.model.enumerator.TipoPedidoEstoque;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade de compra.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compra extends BasicModel {

  @ManyToOne
  private Cliente cliente;

  @ManyToOne(cascade = CascadeType.ALL)
  @NotNull(message = "Necessário realizar pedidos de Saída do estoque")
  private PedidoEstoque pedidoEstoque;

  private FormaPagamento formaPagamento;

  @Transient
  private Usuario usuario;

  @Transient
  private Filial filial;

  @PrePersist
  private void realizaPedido() {
    this.pedidoEstoque = new PedidoEstoque(TipoPedidoEstoque.SAIDA, null, this.usuario,
        this.cliente, this.filial);
  }

  @PostLoad
  private void seTransients() {
    this.filial = this.pedidoEstoque.getFilial();
    this.usuario = this.pedidoEstoque.getUsuario();
  }

  /**
   * Retorna Boolean.TRUE caso os itens do pedido estejam preenchidos.
   *
   * @return true caso tenha itens do pedido preenchidos, caso contrário, false.
   */
  @Transient
  public boolean isItensPresentes() {
    if (this.pedidoEstoque != null) {
      return this.getPedidoEstoque().getItensPedido().stream()
          .filter(item -> StatusItemPedido.ATIVO.equals(item.getStatus())).findAny().isPresent();
    }
    return false;
  }

}
