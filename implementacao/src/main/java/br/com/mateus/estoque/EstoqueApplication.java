package br.com.mateus.estoque;

import br.com.mateus.estoque.model.Cliente;
import br.com.mateus.estoque.model.Estoque;
import br.com.mateus.estoque.model.Filial;
import br.com.mateus.estoque.model.ItemEstoque;
import br.com.mateus.estoque.model.Produto;
import br.com.mateus.estoque.model.Usuario;
import br.com.mateus.estoque.repository.ClienteRepository;
import br.com.mateus.estoque.repository.EstoqueRepository;
import br.com.mateus.estoque.repository.FilialRepository;
import br.com.mateus.estoque.repository.ProdutoRepository;
import br.com.mateus.estoque.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EstoqueApplication {

  public static void main(String[] args) {
    SpringApplication.run(EstoqueApplication.class, args);
  }

  /**
   * Alimenta os dados inciais do sistema
   *
   * @param produtoRepository injeção de dependência
   * @param clienteRepository injeção de dependência
   * @param usuarioRepository injeção de dependência
   * @param filialRepository injeção de dependência
   * @param estoqueRepository injeção de dependência
   */
  @Bean
  CommandLineRunner runner(ProdutoRepository produtoRepository, ClienteRepository clienteRepository,
      UsuarioRepository usuarioRepository, FilialRepository filialRepository,
      EstoqueRepository estoqueRepository) {

    Set<Produto> produtos = seedProdutos();
    Set<Cliente> clientes = seedClientes();
    Set<Usuario> usuarios = seedUsuarios();
    Set<Filial> filiais = seedFiliais();
    Set<Estoque> estoques = seedEstoques(filiais, produtos);

    return args -> {
      produtoRepository.saveAll(produtos);
      clienteRepository.saveAll(clientes);
      usuarioRepository.saveAll(usuarios);
      filialRepository.saveAll(filiais);
      estoqueRepository.saveAll(estoques);
    };
  }

  /**
   * Dados iniciais de {@link Estoque}.
   *
   * @param filiais lista de filiais que serão alimentados o estoque
   * @param produtos lista de produtos do estoque.
   * @return dados iniciais do estoque.
   */
  private Set<Estoque> seedEstoques(Set<Filial> filiais, Set<Produto> produtos) {
    Set<Estoque> estoques = new HashSet<Estoque>();

    filiais.forEach(filial -> {
      Estoque estoque = new Estoque(filial, new ArrayList<ItemEstoque>());

      produtos.forEach(produto -> {
        estoque.getItensEstoque().add(new ItemEstoque(estoque, produto, 100));
      });

      estoques.add(estoque);
    });

    return estoques;
  }


  /**
   * Dadis iniciais de {@link Filial}.
   *
   * @return lista de dados de filiais.
   */
  private Set<Filial> seedFiliais() {
    Set<Filial> filiais = new HashSet<Filial>();

    filiais.add(new Filial("Sao Luis"));
    filiais.add(new Filial("Imperatriz"));
    filiais.add(new Filial("Acailandia"));

    return filiais;
  }

  /**
   * Dadis iniciais de {@link Usuario}.
   *
   * @return lista de dados de usuários.
   */
  private Set<Usuario> seedUsuarios() {
    Set<Usuario> usuarios = new HashSet<Usuario>();
    usuarios.add(new Usuario("John Snow", "snow", "123456"));
    usuarios.add(new Usuario("Brienne de Tarth", "tarth", "123456"));
    usuarios.add(new Usuario("Ned Stark", "stark", "123456"));
    return usuarios;
  }

  /**
   * Dadis iniciais de {@link Cliente}.
   *
   * @return lista de dados de clientes.
   */
  private Set<Cliente> seedClientes() {
    Set<Cliente> clientes = new HashSet<Cliente>();
    clientes.add(new Cliente("John Snow"));
    clientes.add(new Cliente("Brienne de Tarth"));
    clientes.add(new Cliente("Ned Stark"));
    return clientes;
  }

  /**
   * Dadis iniciais de {@link Produto}.
   *
   * @return lista de dados de produtos.
   */
  private Set<Produto> seedProdutos() {
    Set<Produto> produtos = new HashSet<Produto>();
    produtos.add(new Produto("Nescau", "Achocolatado em pó nescau", "321312332", "000001"));
    produtos.add(new Produto("Leite Ninho", "Leite em pó ninho", "321310000", "000002"));
    produtos.add(new Produto("Toddy", "Achocolatado em pó Toddy", "321319089", "000003"));
    return produtos;
  }

}
