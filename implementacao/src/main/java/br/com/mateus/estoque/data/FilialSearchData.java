package br.com.mateus.estoque.data;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Classe de dados de pesquisa de {@link br.com.mateus.estoque.model.Filial}
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Data
@AllArgsConstructor
public class FilialSearchData {

  private Long id;

  private String text;

}
