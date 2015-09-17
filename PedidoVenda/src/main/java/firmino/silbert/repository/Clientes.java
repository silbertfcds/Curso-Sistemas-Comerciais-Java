package firmino.silbert.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import firmino.silbert.model.Cliente;
import firmino.silbert.repository.filter.ClienteFilter;
import firmino.silbert.service.NegocioException;
import firmino.silbert.util.jpa.transactional;

public class Clientes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public Cliente guardar(Cliente cliente){
		return manager.merge(cliente);
	}
	
	public Cliente porId(Long id){
		return manager.find(Cliente.class, id);
	}
	
	@transactional
	public void remover(Cliente cliente){
		try{
			cliente = porId(cliente.getId());
			manager.remove(cliente);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("Cliente não pode ser excluído.");
		}
	}
	
	public Cliente porCpfCnpj(String documentoReceitaFederal) {
		try{
			return manager.createQuery("from Cliente where documentoReceitaFederal = :documentoReceitaFederal", Cliente.class).
					setParameter("documentoReceitaFederal", documentoReceitaFederal).
					getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> filtrar(ClienteFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cliente.class);
		
		if(StringUtils.isNotBlank(filtro.getDocumentoReceitaFederal())){
			criteria.add(Restrictions.ilike("documentoReceitaFederal", filtro.getDocumentoReceitaFederal(), 
					MatchMode.ANYWHERE));
		}
		
		if(StringUtils.isNotBlank(filtro.getNome())){
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}
}
