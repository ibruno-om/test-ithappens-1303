package br.com.mateus.estoque.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entidade de Item de Estoque.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"estoque"})
public class ItemEstoque extends BasicModel {

  @ManyToOne(cascade = CascadeType.ALL)
  private Estoque estoque;

  @ManyToOne
  private Produto produto;

  private Integer quantidade;

}
