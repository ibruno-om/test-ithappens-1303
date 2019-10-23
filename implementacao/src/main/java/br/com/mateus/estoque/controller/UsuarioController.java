package br.com.mateus.estoque.controller;

import br.com.mateus.estoque.data.UsuarioSearchData;
import br.com.mateus.estoque.model.Usuario;
import br.com.mateus.estoque.service.UsuarioService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller referentes as ações de {@link Usuario}.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Controller
@RequestMapping("usuarios")
public class UsuarioController {

  @Autowired
  private UsuarioService usuarioService;

  @RequestMapping("")
  private String index(Model model) {
    model.addAttribute("usuarios", usuarioService.findAll());
    return "usuarios/index";
  }

  /**
   * Retorna uma lista de {@link UsuarioSearchData} conforme o termo pesquisado.
   *
   * @param term termo da pesquisa
   * @return lista de usuários
   */
  @RequestMapping("pesquisa")
  @ResponseBody
  public List<UsuarioSearchData> pesquisar(String term) {
    List<UsuarioSearchData> dataUsuarios = new ArrayList<UsuarioSearchData>();

    if (!StringUtils.isEmpty(term)) {
      try {
        List<Usuario> usuarios = usuarioService.pesquisarUsuarios(term);

        usuarios.forEach(usuario -> {
          dataUsuarios.add(new UsuarioSearchData(usuario.getId(), usuario.getNome()));
        });

      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return dataUsuarios;
  }

}
