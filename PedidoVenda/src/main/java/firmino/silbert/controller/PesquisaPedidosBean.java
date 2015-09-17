package firmino.silbert.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import firmino.silbert.model.Pedido;
import firmino.silbert.model.StatusPedido;
import firmino.silbert.repository.Pedidos;
import firmino.silbert.repository.filter.PedidoFilter;

@Named
@ViewScoped
public class PesquisaPedidosBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Pedido> pedidosFiltrados;
	
	@Inject
	private Pedidos pedidos;
	
	private PedidoFilter filtro;

	public PesquisaPedidosBean() {
		filtro = new PedidoFilter();
		pedidosFiltrados = new ArrayList<>();
	}

	public void pesquisar(){
		pedidosFiltrados = pedidos.filtrados(filtro);
	}
	public List<Pedido> getPedidosFiltrados() {
		return pedidosFiltrados;
	}
	public StatusPedido[] getStatus(){
		return StatusPedido.values();
	}

	public PedidoFilter getFiltro() {
		return filtro;
	}
	
}