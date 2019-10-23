package br.com.mateus.estoque.controller;

import br.com.mateus.estoque.model.Estoque;
import br.com.mateus.estoque.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller referentes as ações de {@link Estoque}.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Controller
@RequestMapping("estoques")
public class EstoqueController {

  @Autowired
  private EstoqueService estoqueService;

  @RequestMapping("")
  public String index(Model model) {
    model.addAttribute("estoques", estoqueService.findAll());
    return "estoques/index";
  }

  @RequestMapping(value = "{id}/itens")
  public String itens(Model model, @PathVariable Long id) {
    Estoque estoque = estoqueService.findById(id);
    if (estoque != null) {
      model.addAttribute("itensEstoque", estoque.getItensEstoque());
    }
    return "estoques/itens";
  }

}
