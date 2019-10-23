package br.com.mateus.estoque.controller;

import br.com.mateus.estoque.data.ProdutoSearchData;
import br.com.mateus.estoque.model.Produto;
import br.com.mateus.estoque.repository.ProdutoRepository;
import br.com.mateus.estoque.service.ProdutoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller referentes as ações de {@link Produto}.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Controller
@RequestMapping("produtos")
public class ProdutoController {

  @Autowired
  private ProdutoRepository produtoRepository;

  @Autowired
  private ProdutoService produtoService;

  /**
   * Retorna uma lista de {@link ProdutoSearchData} conforme o termo pesquisado.
   *
   * @param term termo da pesquisa
   * @return lista de produtos
   */
  @RequestMapping("pesquisa")
  @ResponseBody
  public List<ProdutoSearchData> pesquisar(String term) {
    List<ProdutoSearchData> dataProdutos = new ArrayList<ProdutoSearchData>();

    if (!StringUtils.isEmpty(term)) {
      try {
        List<Produto> produtos = produtoService.pesquisarProdutos(term);

        produtos.forEach(produto -> {
          dataProdutos.add(new ProdutoSearchData(produto.getId(), produto.getNome()));
        });

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return dataProdutos;
  }

}
