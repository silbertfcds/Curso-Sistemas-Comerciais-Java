package firmino.silbert.service;

import java.io.Serializable;

import javax.inject.Inject;

import firmino.silbert.model.ItemPedido;
import firmino.silbert.model.Pedido;
import firmino.silbert.repository.Pedidos;
import firmino.silbert.util.jpa.transactional;

public class EstoqueService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pedidos pedidos;
	
	@transactional
	public void baixarItensEstoque(Pedido pedido) {
		pedido = this.pedidos.porId(pedido.getId());
		
		for (ItemPedido item : pedido.getItens()) {
			item.getProduto().baixarEstoque(item.getQuantidade());
		}
	}

	public void retornarItensEstoque(Pedido pedido) {
		pedido = pedidos.porId(pedido.getId());
		
		for(ItemPedido item: pedido.getItens()){
			item.getProduto().adicionarEstoque(item.getQuantidade());
		}
		
	}
}
