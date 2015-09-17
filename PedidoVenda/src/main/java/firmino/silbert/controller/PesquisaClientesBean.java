package firmino.silbert.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import firmino.silbert.model.Cliente;
import firmino.silbert.repository.Clientes;
import firmino.silbert.repository.filter.ClienteFilter;
import firmino.silbert.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaClientesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ClienteFilter filtro;
	private List<Cliente> clientesFiltrados;
	private Cliente clienteSelecionado;

	@Inject
	private Clientes clientes;


	public PesquisaClientesBean() {
		filtro = new ClienteFilter();
	}

	public void pesquisar() {
		clientesFiltrados = clientes.filtrar(filtro);
	}
	
	public void excluir(){
		clientes.remover(clienteSelecionado);
		clientesFiltrados.remove(clienteSelecionado);
		
		FacesUtil.addInfoMessage("Cliente " + clienteSelecionado.getNome()+ " excluído com sucesso.");
	}

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public ClienteFilter getFiltro() {
		return filtro;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	
	
}
