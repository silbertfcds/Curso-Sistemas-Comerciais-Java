package firmino.silbert.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

@Named
@javax.enterprise.context.RequestScoped
public class PesquisaUsuariosBean {

private List<Integer> usuariosFiltrados;
	
	public PesquisaUsuariosBean() {
		usuariosFiltrados = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			usuariosFiltrados.add(i);
		}
	}

	public List<Integer> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}
}
