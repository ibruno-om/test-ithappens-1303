package br.com.mateus.estoque.model;

import br.com.mateus.estoque.model.enumerator.TipoPedidoEstoque;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade de pedido de movimentação do estoque.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEstoque extends BasicModel {

  private TipoPedidoEstoque tipo;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoEstoque")
  private List<ItemPedido> itensPedido;

  @ManyToOne
  @NotNull(message = "Usuário deve ser informado")
  private Usuario usuario;

  @ManyToOne
  @NotNull(message = "Cliente deve ser informado")
  private Cliente cliente;

  @ManyToOne
  @NotNull(message = "Filial deve ser informada")
  private Filial filial;

  /**
   * Sobrescrita do método getter parar tratar lista vazia
   *
   * @return itens do pedidos
   */
  public List<ItemPedido> getItensPedido() {
    if (this.itensPedido == null) {
      this.itensPedido = new ArrayList<ItemPedido>();
    }

    return itensPedido;
  }


}
