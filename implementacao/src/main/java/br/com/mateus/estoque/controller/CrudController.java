package br.com.mateus.estoque.controller;

import org.springframework.ui.Model;

/**
 * Controller base para cadastros.
 *
 * @author Iago Bruno
 * @since 1.0
 */
public abstract class CrudController {

  public abstract String index(Model model);

  public abstract String destroy(Model model, Long id);

  public abstract String edit(Model model, Long id);

  public abstract String newModel(Model model);


}
