package firmino.silbert.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import firmino.silbert.model.Categoria;
import firmino.silbert.repository.Categorias;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

	@Inject
	private Categorias categorias;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Categoria retorno = null;

		if (arg2 != null && !"".equals(arg2)) {
			retorno = this.categorias.porId(new Long(arg2));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 != null) {
			Categoria categoria = ((Categoria) arg2);
			return categoria.getId() == null ? null : categoria.getId().toString();
		}
		return null;
	}

}
