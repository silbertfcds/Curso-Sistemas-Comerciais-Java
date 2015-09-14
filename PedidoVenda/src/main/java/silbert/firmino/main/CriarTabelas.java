package silbert.firmino.main;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import firmino.silbert.model.Categoria;
import firmino.silbert.model.Cliente;
import firmino.silbert.model.Endereco;
import firmino.silbert.model.Grupo;
import firmino.silbert.model.Produto;
import firmino.silbert.model.TipoPessoa;
import firmino.silbert.model.Usuario;

public class CriarTabelas {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("sistemaPedidoVenda");
		EntityManager manager = factory.createEntityManager();

		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		Cliente cliente = new Cliente();
		cliente.setNome("João das Couves2");
		cliente.setEmail("joao@dascouves.com");
		cliente.setDocumentoReceitaFederal("123.123.123-12");
		cliente.setTipoPessoa(TipoPessoa.FISICA);

		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua das Aboboras Vermelhas");
		endereco.setNumero("111");
		endereco.setCidade("Uberlândia");
		endereco.setUf("MG");
		endereco.setCep("38400-000");
		endereco.setCliente(cliente);
		
		Grupo grupo = new Grupo();
		grupo.setNome("Vendedor");
		grupo.setDescricao("Responsavel pela venda");
		
		Usuario usuario = new Usuario();
		usuario.setNome("eu");
		usuario.setEmail("silbert.fcds@hotmail.com");
		usuario.setSenha("123");
		usuario.getGrupos().add(grupo);

		cliente.getEnderecos().add(endereco);

		// instanciamos a categoria pai (Bebidas)
		Categoria categoriaPai = new Categoria();
		categoriaPai.setDescricao("Bebidas");
				
		// instanciamos a categoria filha (Refrigerantes)
		Categoria categoriaFilha = new Categoria();
		categoriaFilha.setDescricao("Refrigerantes");
		categoriaFilha.setCategoriaPai(categoriaPai);
				
		// adicionamos a categoria Refrigerantes como filha de Bebidas
		categoriaPai.getSubcategorias().add(categoriaFilha);
				
		// ao persistir a categoria pai (Refrigerantes), a filha (Bebidas) 
		// deve ser persistida também
		manager.persist(categoriaPai);
				
		// instanciamos e persistimos um produto
		Produto produto = new Produto();
		produto.setCategoria(categoriaFilha);
		produto.setNome("Guaraná 2L");
		produto.setQuantidadeEstoque(10);
		produto.setSku("GU2345");
		produto.setValorUnitario(new BigDecimal(2.21));
		
		manager.persist(cliente);
		manager.persist(usuario);
		manager.persist(produto);

		trx.commit();
	}
}
