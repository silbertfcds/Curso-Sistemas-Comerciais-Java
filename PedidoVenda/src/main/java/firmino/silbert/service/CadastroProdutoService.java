package firmino.silbert.service;

import java.io.Serializable;

import javax.inject.Inject;

import firmino.silbert.model.Produto;
import firmino.silbert.repository.Produtos;
import firmino.silbert.util.jpa.transactional;

public class CadastroProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Produtos produtos;
	
	@transactional
	public Produto salvar(Produto produto){
		Produto produtoExistente = produtos.porSku(produto.getSku());
		
		if(produtoExistente!=null && !produtoExistente.equals(produto)){
			throw new NegocioException("Já existe um produto com o sku informado.");
		}
		return produtos.guardar(produto);
	}
}
