package br.com.mateus.estoque.model;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade de produto.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto extends BasicModel {

  private String nome;

  private String descricao;

  private String codigoBarras;

  private String sequencial;

}
