package firmino.silbert.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import firmino.silbert.model.Grupo;
import firmino.silbert.model.Usuario;
import firmino.silbert.repository.Grupos;
import firmino.silbert.service.CadastroUsuarioService;
import firmino.silbert.service.NegocioException;
import firmino.silbert.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {


	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Inject
	private Grupos grupos;
	
	private Usuario usuario;
	private Grupo novoGrupo;
	private Grupo removeGrupo;

	private List<Grupo> gruposRaizes;
	
	public CadastroUsuarioBean(){
		limpar();
		
	}

	public void salvar(){
		 try {
	            this.usuario = cadastroUsuarioService.salvar(usuario);
	            limpar();
	            FacesUtil.addInfoMessage("Usuário salvo com sucesso!");
	        } catch (NegocioException e) {
	            FacesUtil.addInfoMessage(e.getMessage());
	        }
		
	}
	public void adicionarGrupo(){
		
		if(usuario.getGrupos().contains(novoGrupo)){
			FacesUtil.addErrorMessage("Grupo já adicionado.");
		}else{
			usuario.getGrupos().add(novoGrupo);
		}
		
		
		
		/*
		if(usuario.getGrupos().indexOf(novoGrupo)<0){
			usuario.getGrupos().add(novoGrupo);
		}else{
			 FacesUtil.addErrorMessage("Grupo já adicionado.");
		}*/
		
	
		System.out.println(novoGrupo.getNome());
	
	}
	public void removerGrupo() { 
        this.usuario.getGrupos().remove(removeGrupo);
    }
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			gruposRaizes = grupos.listar();
		}
	}
	private void limpar() {
		usuario = new Usuario();
	}
	public Usuario getUsuario() {
		if(usuario == null){
			usuario = new Usuario();
		}
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;		
		if(usuario!=null){
			usuario.getGrupos();
		}
	}
	@NotNull
	public List<Grupo> getGruposRaizes() {
		return gruposRaizes;
	}	

	public void setGruposRaizes(List<Grupo> gruposRaizes) {
		this.gruposRaizes = gruposRaizes;
	}
	
	public boolean isEditando() {
        boolean resultado = false;
        if (this.usuario != null) {
            resultado = this.usuario.getId() != null;
        }
        return resultado;
    }

	public Grupo getNovoGrupo() {
		return novoGrupo;
	}

	public void setNovoGrupo(Grupo novoGrupo) {
		this.novoGrupo = novoGrupo;
	}

	public Grupo getRemoveGrupo() {
		return removeGrupo;
	}

	public void setRemoveGrupo(Grupo removeGrupo) {
		this.removeGrupo = removeGrupo;
	}
	
}
