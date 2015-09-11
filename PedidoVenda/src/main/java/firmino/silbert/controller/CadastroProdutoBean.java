package firmino.silbert.controller;

import javax.inject.Named;

import firmino.silbert.model.Produto;

@Named
@javax.faces.view.ViewScoped
public class CadastroProdutoBean {

	private Produto produto;
	
	public CadastroProdutoBean(){
	
		produto = new Produto();
	}
	
	public void salvar(){
		
	}

	public Produto getProduto() {
		return produto;
	}
	
}
