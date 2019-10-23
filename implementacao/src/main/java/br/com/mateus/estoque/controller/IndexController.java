package br.com.mateus.estoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller referente ao mapeamento da rota inicial.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Controller
public class IndexController {

  @RequestMapping({"index", ""})
  public ModelAndView home() {
    return new ModelAndView("index");
  }

}
