package br.com.mateus.estoque.repository;

import br.com.mateus.estoque.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório de dados de {@link Usuario}.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  List<Usuario> findByNomeContainingIgnoreCaseOrderByNome(String nome);

}
