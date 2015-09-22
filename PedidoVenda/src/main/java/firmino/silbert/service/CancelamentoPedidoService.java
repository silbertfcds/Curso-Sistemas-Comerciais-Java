package firmino.silbert.service;

import java.io.Serializable;

import javax.inject.Inject;

import firmino.silbert.model.Pedido;
import firmino.silbert.model.StatusPedido;
import firmino.silbert.repository.Pedidos;
import firmino.silbert.util.jpa.transactional;

public class CancelamentoPedidoService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Pedidos pedidos;
	
	@Inject
	private EstoqueService estoqueService;
	
	@transactional
	public Pedido cancelar(Pedido pedido) {
		pedido = pedidos.porId(pedido.getId());
		
		if(pedido.isNaoCancelavel()){
			throw new NegocioException("O pedido não pode ser cancelado no status "+pedido.getStatus().getDescricao()
					+ ".");
		}
		
		if(pedido.isEmitido()){
			estoqueService.retornarItensEstoque(pedido);
		}
		
		pedido.setStatus(StatusPedido.CANCELADO);
		pedido = pedidos.guardar(pedido);
		
		return pedido;
	}

	
}
