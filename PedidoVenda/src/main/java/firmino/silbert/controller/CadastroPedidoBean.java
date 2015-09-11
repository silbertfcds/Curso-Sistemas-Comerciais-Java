package firmino.silbert.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import firmino.silbert.model.EnderecoEntrega;
import firmino.silbert.model.Pedido;
import firmino.silbert.service.NegocioException;

@Named
@ViewScoped
public class CadastroPedidoBean {

	private Pedido pedido;
	private List<Integer> itens;

	public CadastroPedidoBean() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
		itens = new ArrayList<>();
		itens.add(1);
	}

	public List<Integer> getItens() {
		return itens;
	}
	
	public void salvar(){
		throw new NegocioException("Pedido não pode ser salvo, pois ainda não foi implementado.");
	}

	public Pedido getPedido() {
		return pedido;
	}
	
}
