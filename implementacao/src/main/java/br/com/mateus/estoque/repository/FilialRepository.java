package br.com.mateus.estoque.repository;

import br.com.mateus.estoque.model.Filial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório de dados de {@link Filial}.
 *
 * @author Iago Bruno
 * @since 1.0
 */
public interface FilialRepository extends JpaRepository<Filial, Long> {

  List<Filial> findByNomeContainingIgnoreCaseOrderByNome(String term);
}
