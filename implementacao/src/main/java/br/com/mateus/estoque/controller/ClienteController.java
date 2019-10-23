package br.com.mateus.estoque.controller;

import br.com.mateus.estoque.data.ClienteSearchData;
import br.com.mateus.estoque.model.Cliente;
import br.com.mateus.estoque.service.ClienteService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller referentes as ações de {@link Cliente}.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Controller
@RequestMapping("clientes")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;

  @RequestMapping("")
  private String index(Model model) {
    model.addAttribute("clientes", clienteService.findAll());
    return "clientes/index";
  }

  /**
   * Retorna uma lista de {@link ClienteSearchData} conforme o termo pesquisado.
   *
   * @param term termo da pesquisa
   * @return lista de clientes
   */
  @RequestMapping("pesquisa")
  @ResponseBody
  public List<ClienteSearchData> pesquisar(String term, Model model) {
    List<ClienteSearchData> dataClientes = new ArrayList<ClienteSearchData>();

    if (!StringUtils.isEmpty(term)) {
      try {
        List<Cliente> clientes = clienteService.pesquisarClientes(term);

        clientes.forEach(cliente -> {
          dataClientes.add(new ClienteSearchData(cliente.getId(), cliente.getNome()));
        });

      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return dataClientes;
  }

}
