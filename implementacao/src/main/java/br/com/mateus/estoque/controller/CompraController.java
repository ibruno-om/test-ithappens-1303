package br.com.mateus.estoque.controller;

import br.com.mateus.estoque.data.CompraData;
import br.com.mateus.estoque.model.Compra;
import br.com.mateus.estoque.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller referentes as ações de {@link Compra}.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Controller
@RequestMapping("compras")
public class CompraController extends CrudController {

  @Autowired
  private CompraService compraService;

  @Override
  @RequestMapping("")
  public String index(Model model) {
    model.addAttribute("compras", compraService.findAll());
    return "compras/index";
  }

  @PostMapping("create")
  public String create(CompraData compraData, Model model) {
    Compra compra = compraService.create(compraData);
    return "redirect:/pedidos/" + compra.getPedidoEstoque().getId() + "/edit";
  }

  @Override
  @RequestMapping(value = "{id}/finalizar")
  public String destroy(Model model, @PathVariable Long id) {
    compraService.finalizarCompra(id);
    return "redirect:/compras";
  }

  @Override
  @RequestMapping(value = "{id}/edit")
  public String edit(Model model, @PathVariable Long id) {
    Compra compra = compraService.findById(id);

    // TODO: Adicionar um mapper
    CompraData compraData = new CompraData();
    compraData.setFormaPagamento(compra.getFormaPagamento());
    compraData.setIdCliente(compra.getCliente().getId());
    compraData.setIdFifial((compra.getFilial().getId()));
    compraData.setIdUsuario(compra.getUsuario().getId());

    model.addAttribute("compra", compraData);
    return "compras/edit";
  }

  @Override
  @RequestMapping(value = "new")
  public String newModel(Model model) {
    model.addAttribute("compra", new CompraData());
    return "compras/new";
  }

}
