package br.com.mateus.estoque.data;

import br.com.mateus.estoque.model.enumerator.FormaPagamento;
import lombok.Data;

/**
 * Classe de dados de inserção/edição de {@link br.com.mateus.estoque.model.Compra}.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Data
public class CompraData {

  private Long id;

  private FormaPagamento formaPagamento;

  private Long idCliente;

  private Long idFifial;

  private Long idUsuario;

  private String observacao;

}
