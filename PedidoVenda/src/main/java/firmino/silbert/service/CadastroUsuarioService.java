package firmino.silbert.service;

import java.io.Serializable;

import javax.inject.Inject;

import firmino.silbert.model.Usuario;
import firmino.silbert.repository.Usuarios;
import firmino.silbert.util.jpa.transactional;

public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;
	
	@transactional
	public Usuario salvar(Usuario usuario){
		Usuario usuarioExistente = usuarios.porEmail(usuario.getEmail());
		
		if(usuarioExistente!=null && !usuarioExistente.equals(usuario)){
			throw new NegocioException("Já existe um Usuário com o email informado.");
		}
		
		
		return usuarios.guardar(usuario);
	}
}
