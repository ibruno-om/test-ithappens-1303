package br.com.mateus.estoque.service;

import br.com.mateus.estoque.model.Filial;
import br.com.mateus.estoque.repository.FilialRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe responsável  pelas operações de {@link Filial}.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Service
public class FilialService {

  @Autowired
  private FilialRepository filialRepository;


  public List<Filial> findAll() {
    return filialRepository.findAll();
  }

  public List<Filial> pesquisarFiliais(String term) {
    return filialRepository
        .findByNomeContainingIgnoreCaseOrderByNome(term);
  }
}
