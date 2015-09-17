package firmino.silbert.service;

import java.io.Serializable;

import javax.inject.Inject;

import firmino.silbert.model.Cliente;
import firmino.silbert.repository.Clientes;
import firmino.silbert.util.jpa.transactional;

public class CadastroClienteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;
	
	@transactional
	public Cliente salvar(Cliente cliente){
		Cliente clienteExistente = clientes.porCpfCnpj(cliente.getDocumentoReceitaFederal());
		
		if(clienteExistente!=null && !clienteExistente.equals(cliente)){
			throw new NegocioException("Já existe um Cliente com o CPF/CNPJ informado.");
		}
		
		
		return clientes.guardar(cliente);
	}
}
