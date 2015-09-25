package firmino.silbert.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import firmino.silbert.model.Grupo;
import firmino.silbert.model.Usuario;
import firmino.silbert.repository.Usuarios;
import firmino.silbert.util.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {

	//@Inject
	//private Usuarios usuarios;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UsuarioSistema user = null;
		Usuarios usuarios = CDIServiceLocator.getBean(Usuarios.class); 
		Usuario usuario = usuarios.porEmail(email); 
		
		if(usuario!=null){
			user = new UsuarioSistema(usuario, getGrupos(usuario));
		}
		return user;
	}

	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for (Grupo grupo : usuario.getGrupos()) {
			authorities.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
		}
		
		return authorities;

	}

}
