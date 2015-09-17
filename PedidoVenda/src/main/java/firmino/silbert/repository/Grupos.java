package firmino.silbert.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import firmino.silbert.model.Grupo;
import firmino.silbert.util.jpa.transactional;

public class Grupos implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	@transactional
	public Grupo guardar(Grupo grupo) {
		
		return manager.merge(grupo);
	}
	
	public Grupo porNome(String nome) {
		try {
			return manager
					.createQuery("from Grupo where upper(nome) = :nome",
							Grupo.class)
					.setParameter("nome", nome.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}
	
	public List<Grupo> listar() {
		return manager.createQuery("from Grupo",
				Grupo.class).getResultList();
	}

	public Grupo porId(Long id) {
		return manager.find(Grupo.class, id);
	}
}
