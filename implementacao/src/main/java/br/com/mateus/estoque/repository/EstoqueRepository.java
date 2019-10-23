package br.com.mateus.estoque.repository;

import br.com.mateus.estoque.model.Estoque;
import br.com.mateus.estoque.model.Filial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório de dados de {@link Estoque}.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

  Estoque findByFilial(Filial filial);
}
