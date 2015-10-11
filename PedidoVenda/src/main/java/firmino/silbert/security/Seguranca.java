package firmino.silbert.security;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * A classe retorna o nome do usuário logado através do metodo getNomeUsuario()
 */
@Named
@RequestScoped
public class Seguranca {
	
	@Inject
	private ExternalContext externalContext;
	
	
	/** Retorna o nome do usuário logado */
	public String getNomeUsuario() {
		String nome = null;

		UsuarioSistema usuarioLogado = getUsuarioLogado();

		if (usuarioLogado != null) {
			nome = usuarioLogado.getUsuario().getNome();
		}
		return nome;
	}

	@Produces
	@UsuarioLogado
	public UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;

		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();

		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}

		return usuario;

	}

	public boolean isEmitirPedidoPermitido() {
		return externalContext.isUserInRole("ADMINISTRADORES") || externalContext.isUserInRole("VENDEDORES");
	}

	public boolean isCancelarPedidoPermitido() {
		return externalContext.isUserInRole("ADMINISTRADORES") || externalContext.isUserInRole("VENDEDORES");
	}
	
	public boolean isNotSalvarCliente(){
		return externalContext.isUserInRole("AUXILIARES");
	}
	
	public boolean isNotRemoverCliente(){
		return externalContext.isUserInRole("AUXILIARES");
	}
}
