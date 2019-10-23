package br.com.mateus.estoque.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade de Estoque.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estoque extends BasicModel {

  @ManyToOne
  private Filial filial;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "estoque")
  private List<ItemEstoque> itensEstoque;


}
