package firmino.silbert.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import firmino.silbert.model.Cliente;
import firmino.silbert.repository.Clientes;

@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter {

	@Inject
	private Clientes clientes;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Cliente retorno = null;
		if(arg2!=null && !"".equals(arg2)){
			
			retorno = clientes.porId(new Long(arg2));
			
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if(arg2!=null){
			Cliente cliente = (Cliente) arg2;
			return cliente.getId() == null ? null : cliente.getId().toString();
		}
		return "";
	}

	
	

}
