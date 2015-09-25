package firmino.silbert.controller;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.velocity.tools.generic.NumberTool;

import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;

import firmino.silbert.model.Cliente;
import firmino.silbert.util.jsf.FacesUtil;
import firmino.silbert.util.mail.Mailer;

@Named
@RequestScoped
public class EnvioClienteEmailBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	@ClienteEdicao
	private Cliente cliente;
	
	@Inject
	private Mailer mailer;
	
	public void enviarDadosCliente(){
		MailMessage message = mailer.novaMensagem();
		
		message.to(this.cliente.getEmail())
		.subject("Cliente " + this.cliente.getId())
		.bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/cliente.template")))
		.put("cliente", this.cliente)
		.put("numberTool", new NumberTool())
		.put("locale", new Locale("pt", "BR"))
		.charset("utf-8")

		.send();
		
		FacesUtil.addInfoMessage("Cliente enviado por e-mail com sucesso.");
	}
}
