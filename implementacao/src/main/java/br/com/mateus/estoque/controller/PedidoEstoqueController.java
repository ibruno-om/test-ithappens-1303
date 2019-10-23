package br.com.mateus.estoque.controller;

import br.com.mateus.estoque.data.ItemPedidoData;
import br.com.mateus.estoque.model.PedidoEstoque;
import br.com.mateus.estoque.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller referentes as ações de {@link PedidoEstoque}.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Controller
@RequestMapping("pedidos")
public class PedidoEstoqueController {

  @Autowired
  private PedidoService pedidoService;

  @RequestMapping(value = "{id}/edit")
  public String edit(Model model, @PathVariable Long id) {
    try {
      PedidoEstoque pedido = pedidoService.findById(id);

      model.addAttribute("pedido", pedido);

      return "pedidos/edit";
    } catch (Exception e) {
      return redirectCompras();
    }
  }

  @RequestMapping(value = "{id}/add")
  public String add(Model model, @PathVariable Long id) {
    try {
      PedidoEstoque pedido = pedidoService.findById(id);

      ItemPedidoData itemPedidoData = new ItemPedidoData();
      itemPedidoData.setIdPedido(pedido.getId());

      model.addAttribute("pedido", itemPedidoData);

      return "pedidos/add";
    } catch (Exception e) {
      return redirectCompras();
    }

  }

  @PostMapping("create")
  public String create(Model model, ItemPedidoData itemPedidoData) {
    try {
      if (pedidoService.create(itemPedidoData)) {
        return "redirect:/pedidos/" + itemPedidoData.getIdPedido() + "/edit";
      } else {
        model.addAttribute("erro", "Quantidades de itens insuficientes no estoque");
        model.addAttribute("pedido", itemPedidoData);
        return "pedidos/add";
      }
    } catch (Exception e) {
      return redirectCompras();
    }
  }

  /**
   * Retorna um redirecionamento para compras em caso de falha
   */
  private String redirectCompras() {
    return "redirect:/compras";
  }

}
