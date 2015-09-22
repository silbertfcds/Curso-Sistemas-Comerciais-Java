package firmino.silbert.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import firmino.silbert.model.Pedido;
import firmino.silbert.service.CancelamentoPedidoService;
import firmino.silbert.util.jsf.FacesUtil;

@Named
@RequestScoped
public class CancelamentoPedidoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private CancelamentoPedidoService cancelamentoPedidoService;
	
	@Inject
	private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	public void cancelarPedido(){
		pedido = cancelamentoPedidoService.cancelar(pedido);
		pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(pedido));
		
		FacesUtil.addInfoMessage("Pedido cancelado com sucesso.");
	}
}
