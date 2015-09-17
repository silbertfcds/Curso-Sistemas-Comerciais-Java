package firmino.silbert.controller;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import firmino.silbert.model.Usuario;
import firmino.silbert.repository.Usuarios;
import firmino.silbert.repository.filter.UsuarioFilter;
import firmino.silbert.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuariosBean {

	private UsuarioFilter filtro;
	private List<Usuario> usuariosFiltrados;
	private Usuario usuarioSelecionado;
	
	@Inject
	private Usuarios usuarios;

	public PesquisaUsuariosBean() {
		filtro = new UsuarioFilter();
	}

	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}

	public void excluir(){
		usuarios.remover(usuarioSelecionado);
		usuariosFiltrados.remove(usuarioSelecionado);
		
		FacesUtil.addInfoMessage("Usuário " + usuarioSelecionado.getNome()+ " excluído com sucesso.");
	}
	
	public void setUsuariosFiltrados(List<Usuario> usuariosFiltrados) {
		this.usuariosFiltrados = usuariosFiltrados;
	}

	public void pesquisar() {
		usuariosFiltrados = usuarios.filtrados(filtro);
	}

	public UsuarioFilter getFiltro() {
		return filtro;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

}
