package firmino.silbert.controller;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import firmino.silbert.model.Produto;
import firmino.silbert.repository.Produtos;
import firmino.silbert.repository.filter.ProdutoFilter;

@Named
@ViewScoped
public class PesquisaProdutosBean {

	private ProdutoFilter filtro;
	private List<Produto> produtosFiltrados;
	@Inject
	private Produtos produtos;
	

	public PesquisaProdutosBean() {
		filtro = new ProdutoFilter();
	}
	public void pesquisar(){
		produtosFiltrados = produtos.filtrar(filtro);
	}
	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public ProdutoFilter getFiltro() {
		return filtro;
	}
	
	
}
