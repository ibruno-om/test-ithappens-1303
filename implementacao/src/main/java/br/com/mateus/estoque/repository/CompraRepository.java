package br.com.mateus.estoque.repository;

import br.com.mateus.estoque.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório de dados de {@link Compra}.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

}
