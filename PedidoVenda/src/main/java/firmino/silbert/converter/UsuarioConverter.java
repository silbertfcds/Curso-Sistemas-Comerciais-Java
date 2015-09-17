package firmino.silbert.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import firmino.silbert.model.Usuario;
import firmino.silbert.repository.Usuarios;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	@Inject
	private Usuarios usuarios;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Usuario retorno = null;
		if(arg2!=null && !"".equals(arg2)){
			
			retorno = usuarios.porId(new Long(arg2));
			
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if(arg2!=null){
			Usuario usuario = (Usuario) arg2;
			return usuario.getId() == null ? null : usuario.getId().toString();
		}
		return "";
	}

	
	

}
