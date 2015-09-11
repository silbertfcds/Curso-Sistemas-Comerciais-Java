package firmino.silbert.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import firmino.silbert.model.Usuario;

@Named
@ViewScoped
public class CadastroUsuarioBean {

	private Usuario usuario;
	
	public CadastroUsuarioBean() {
		usuario = new Usuario();
	}
	public void salvar(){
		
	}
	public Usuario getUsuario() {
		return usuario;
	}
	
}
