package br.com.mateus.estoque.repository;

import br.com.mateus.estoque.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório de dados de {@link Cliente}.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

  List<Cliente> findByNomeContainingIgnoreCaseOrderByNome(String nome);
}
