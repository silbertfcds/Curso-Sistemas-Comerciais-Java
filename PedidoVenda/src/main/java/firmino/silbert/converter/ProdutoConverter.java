package firmino.silbert.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import firmino.silbert.model.Produto;
import firmino.silbert.repository.Produtos;

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {

	@Inject
	private Produtos produtos;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Produto retorno = null;

		if (arg2 != null && !"".equals(arg2)) {
			retorno = this.produtos.porId(new Long(arg2));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 != null) {
			Produto produto = (Produto) arg2;
			return produto.getId() == null ? null : produto.getId().toString();
		}
		
		return "";
	}

}
