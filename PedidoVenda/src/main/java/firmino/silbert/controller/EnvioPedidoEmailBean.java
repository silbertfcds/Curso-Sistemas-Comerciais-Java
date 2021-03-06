package firmino.silbert.controller;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.velocity.tools.generic.NumberTool;

import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;

import firmino.silbert.model.Pedido;
import firmino.silbert.util.jsf.FacesUtil;
import firmino.silbert.util.mail.Mailer;

@Named
@RequestScoped
public class EnvioPedidoEmailBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	@Inject
	private Mailer mailer;
	
	public void enviarPedido(){
		MailMessage message = mailer.novaMensagem();
	
		message.to(this.pedido.getCliente().getEmail())
		.subject("Pedido " + this.pedido.getId())
		.bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/pedido.template")))
		.put("pedido", this.pedido)
		.put("numberTool", new NumberTool())
		.put("locale", new Locale("pt", "BR"))
		.charset("utf-8")

		.send();
		
		FacesUtil.addInfoMessage("Pedido enviado por e-mail com sucesso.");
	}

}
