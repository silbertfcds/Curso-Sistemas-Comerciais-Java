package firmino.silbert.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import firmino.silbert.model.Pedido;
import firmino.silbert.repository.Pedidos;

@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter {

	@Inject
	private Pedidos pedidos;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Pedido retorno = null;

		if (arg2 != null && !"".equals(arg2)) {
			retorno = this.pedidos.porId(new Long(arg2));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 != null) {
			Pedido pedido = (Pedido) arg2;
			return pedido.getId() == null ? null : pedido.getId().toString();
		}
		
		return "";
	}

}
