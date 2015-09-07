package firmino.silbert.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

@Named
@javax.enterprise.context.RequestScoped
public class CadastroPedidoBean {

	private List<Integer> itens;

	public CadastroPedidoBean() {
		itens = new ArrayList<>();
		itens.add(1);
	}

	public List<Integer> getItens() {
		return itens;
	}
	
}
