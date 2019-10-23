package br.com.mateus.estoque.service;

import br.com.mateus.estoque.model.Usuario;
import br.com.mateus.estoque.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe responsável  pelas operações de {@link Usuario}.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Service
public class UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  public List<Usuario> findAll() {
    return usuarioRepository.findAll();
  }

  public List<Usuario> pesquisarUsuarios(String term) {
    return usuarioRepository
        .findByNomeContainingIgnoreCaseOrderByNome(term);
  }
}
