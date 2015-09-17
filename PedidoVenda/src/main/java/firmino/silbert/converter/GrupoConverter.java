package firmino.silbert.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import firmino.silbert.model.Grupo;
import firmino.silbert.repository.Grupos;
@FacesConverter(forClass = Grupo.class)
public class GrupoConverter implements Converter{

	@Inject
	private Grupos grupos;
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Grupo retorno = null;
		if(arg2!=null ){
			retorno = grupos.porId(new Long(arg2));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if(arg2!=null){
			return ((Grupo)arg2).getId().toString();
		}
		return null;
	}

}
