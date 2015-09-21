package firmino.silbert.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import firmino.silbert.model.Pedido;
import firmino.silbert.model.StatusPedido;
import firmino.silbert.repository.Pedidos;
import firmino.silbert.util.jpa.transactional;

public class CadastroPedidoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pedidos pedidos;
	
	@transactional
	public Pedido salvar(Pedido pedido){
		if(pedido.isNovo()){
			pedido.setDataCriacao(new Date());
			pedido.setStatus(StatusPedido.ORCAMENTO);
		}
		
		pedido.recalcularValorTotal();
		
		if(pedido.getItens().isEmpty()){
			throw new NegocioException("O pedido deve possuir pelo menos um item.");
		}
		
		if(pedido.valorTotalNegativo()){
			throw new NegocioException("O valor total do pedido não pode ser negativo.");
		}
		return pedidos.guardar(pedido);
		
	}

}
