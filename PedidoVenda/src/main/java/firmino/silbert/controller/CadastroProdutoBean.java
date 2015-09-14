package firmino.silbert.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import firmino.silbert.model.Categoria;
import firmino.silbert.model.Produto;
import firmino.silbert.repository.Categorias;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto produto;

	@Inject
	private Categorias categorias;
	
	private Categoria categoriaPai;
	
	private List<Categoria> categoriasRaizes;
	
	public CadastroProdutoBean(){
		
		produto = new Produto();
	}
	
	public void salvar(){
		System.out.println("Categoria Pai selecionada: "+categoriaPai.getDescricao());
	}

	public Produto getProduto() {
		return produto;
	}

	public void inicializar() {
		System.out.println("Inicializando...");
		
		categoriasRaizes = categorias.raizes();
	}
	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}
	@NotNull
	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}
	
	
}
