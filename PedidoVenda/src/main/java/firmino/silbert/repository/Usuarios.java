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

import firmino.silbert.model.Usuario;
import firmino.silbert.repository.filter.UsuarioFilter;
import firmino.silbert.service.NegocioException;
import firmino.silbert.util.jpa.transactional;

public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	
	public Usuario porId(Long id){
		return manager.find(Usuario.class, id);
	}
	
	public Usuario guardar(Usuario usuario){
		usuario = manager.merge(usuario);
		return usuario;
	}
	
	@transactional
	public void remover(Usuario usuario){
		try{
			usuario = porId(usuario.getId());
			manager.remove(usuario);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("Usuario não pode ser excluído, pois tem grupos adicionados.");
		}
	}
	public Usuario porEmail(String email) {
		try{
			return manager.createQuery("from Usuario where lower(email) = :email", Usuario.class).
					setParameter("email", email.toLowerCase()).
					getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<Usuario> vendedores() {
		// TODO filtrar apenas vendedores (por um grupo específico)
		return this.manager.createQuery("from Usuario", Usuario.class)
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> filtrados(UsuarioFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Usuario.class);
		
		if(StringUtils.isNotBlank(filtro.getNome())){
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}
}
