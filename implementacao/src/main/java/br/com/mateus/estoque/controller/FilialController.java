package br.com.mateus.estoque.controller;

import br.com.mateus.estoque.data.FilialSearchData;
import br.com.mateus.estoque.model.Filial;
import br.com.mateus.estoque.repository.FilialRepository;
import br.com.mateus.estoque.service.FilialService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller referentes as ações de {@link Filial}.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Controller
@RequestMapping("filiais")
public class FilialController {

  @Autowired
  private FilialService filialService;

  @RequestMapping("")
  public String index(Model model) {
    model.addAttribute("filiais", filialService.findAll());
    return "filiais/index";
  }

  /**
   * Retorna uma lista de {@link FilialSearchData} conforme o termo pesquisado.
   *
   * @param term termo da pesquisa
   * @return lista de filiais
   */
  @RequestMapping("pesquisa")
  @ResponseBody
  public List<FilialSearchData> pesquisar(String term) {
    List<FilialSearchData> dataFiliais = new ArrayList<FilialSearchData>();

    if (!StringUtils.isEmpty(term)) {
      try {
        List<Filial> filiais = filialService.pesquisarFiliais(term);

        filiais.forEach(filial -> {
          dataFiliais.add(new FilialSearchData(filial.getId(), filial.getNome()));
        });

      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return dataFiliais;
  }

}
