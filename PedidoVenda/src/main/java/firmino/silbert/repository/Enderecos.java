package firmino.silbert.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import firmino.silbert.model.Endereco;
import firmino.silbert.service.NegocioException;
import firmino.silbert.util.jpa.transactional;

public class Enderecos implements Serializable {


	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	@transactional
	public Endereco guardar(Endereco endereco){
		return manager.merge(endereco);
	}
	
	public Endereco porId(Long id){
		return manager.find(Endereco.class, id);
	}
	
	@transactional
	public void remover(Endereco endereco){
		try{
			endereco = porId(endereco.getId());
			manager.remove(endereco);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("Endereco não pode ser excluído.");
		}
	}
}
