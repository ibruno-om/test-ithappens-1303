package br.com.mateus.estoque.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe de dados de pesquisa de {@link br.com.mateus.estoque.model.Produto}
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoSearchData {

  private Long id;

  private String text;

}
