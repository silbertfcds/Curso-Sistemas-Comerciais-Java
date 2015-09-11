package firmino.silbert.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import firmino.silbert.model.Cliente;

@Named
@ViewScoped
public class CadastroClienteBean {

	private Cliente cliente;
	
	
	public CadastroClienteBean() {
		cliente = new Cliente();
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public void salvar(){
		
	}
}
