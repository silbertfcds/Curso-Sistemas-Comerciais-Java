package firmino.silbert.controller;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import firmino.silbert.model.Cliente;
import firmino.silbert.model.Endereco;
import firmino.silbert.model.TipoPessoa;
import firmino.silbert.service.CadastroClienteService;
import firmino.silbert.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroClienteService cadastroClienteService;
	
	@Produces
	@ClienteEdicao
	private Cliente cliente;
	
	private Endereco endereco;
	
	public CadastroClienteBean() {
		limpar();
	}

	public void salvar(){
		cliente = cadastroClienteService.salvar(cliente);
		limpar();
		FacesUtil.addInfoMessage("Cliente salvo com sucesso!");
	}
	
	public void adicionarEndereco(){	
		if(endereco!=null){
			cliente.getEnderecos().add(endereco);
			endereco.setCliente(cliente);
			endereco = new Endereco();
		}
	}
	
	public void excluirEndereco(){
		if(endereco!=null){
			cliente.getEnderecos().remove(endereco);
			endereco = new Endereco();
		}
		
	}
	public Cliente getCliente() {
		if(cliente == null){
			cliente = new Cliente();
		}
		return cliente;
	}
	private void limpar() {
		cliente = new Cliente();
		endereco = new Endereco();
	}
	
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		if(cliente!=null){
			cliente.getEnderecos();
		}
	}

	public TipoPessoa[] getTiposPessoas(){
		return TipoPessoa.values();
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public boolean isEditando() {
        boolean resultado = false;
        if (this.cliente != null) {
            resultado = this.cliente.getId() != null;
        }
        return resultado;
    }
}
