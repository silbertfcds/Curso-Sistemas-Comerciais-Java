package firmino.silbert.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import firmino.silbert.model.Cliente;
import firmino.silbert.model.EnderecoEntrega;
import firmino.silbert.model.FormaPagamento;
import firmino.silbert.model.ItemPedido;
import firmino.silbert.model.Pedido;
import firmino.silbert.model.Produto;
import firmino.silbert.model.Usuario;
import firmino.silbert.repository.Clientes;
import firmino.silbert.repository.Produtos;
import firmino.silbert.repository.Usuarios;
import firmino.silbert.service.CadastroPedidoService;
import firmino.silbert.util.jsf.FacesUtil;
import firmino.silbert.validation.SKU;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Produces
	@PedidoEdicao
	private Pedido pedido;
	
	private Produto produtoLinhaEditavel;
	private String sku;

	@Inject
	private Usuarios usuarios;
	@Inject
	private Produtos produtos;
	@Inject
	private Clientes clientes;
	@Inject
	private CadastroPedidoService cadastroPedidoService;

	private List<Usuario> vendedores;

	public CadastroPedidoBean() {
		limpar();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			if(this.pedido==null){
				this.pedido = new Pedido();
				this.pedido.setEnderecoEntrega(new EnderecoEntrega());
			}
			vendedores = usuarios.vendedores();
			this.pedido.adicionarItemVazio();
			this.recalcularPedido();
			
			
		}
		
		
	}

	public void limpar() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
	}

	public void salvar() {
		pedido.removerItemVazio();
		
		try {
			pedido = cadastroPedidoService.salvar(pedido);

			FacesUtil.addInfoMessage("Pedido salvo com sucesso.");
		} finally {
			pedido.adicionarItemVazio();
		}
	}

	public void carregarProdutoLinhaEditavel() {
		ItemPedido item = this.pedido.getItens().get(0);

		if (this.produtoLinhaEditavel != null) {
			if (this.existeItemComProduto(this.produtoLinhaEditavel)) {
				FacesUtil.addErrorMessage("Já existe um item no pedido com o produto informado.");
			} else {
				item.setProduto(this.produtoLinhaEditavel);
				item.setValorUnitario(this.produtoLinhaEditavel.getValorUnitario());

				this.pedido.adicionarItemVazio();
				this.produtoLinhaEditavel = null;
				this.sku = null;

				this.pedido.recalcularValorTotal();
			}
		}
	}

	public void pedidoAlterado(@Observes PedidoAlteradoEvent event) {
		this.pedido = event.getPedido();
	}
	
	private boolean existeItemComProduto(Produto produto) {
		boolean existeItem = false;

		for (ItemPedido item : this.getPedido().getItens()) {
			if (produto.equals(item.getProduto())) {
				existeItem = true;
				break;
			}
		}

		return existeItem;
	}

	public void carregarProdutoPorSku() {
		if (StringUtils.isNotEmpty(this.sku)) {
			this.produtoLinhaEditavel = this.produtos.porSku(this.sku);
			this.carregarProdutoLinhaEditavel();
		}
	}

	public void recalcularPedido() {
		if (this.pedido != null) {
			this.pedido.recalcularValorTotal();
		}
	}

	public List<Produto> completarProduto(String nome) {
		return produtos.porNome(nome);
	}

	public List<Cliente> completarCliente(String nome) {
		return clientes.porNome(nome);
	}

	public void atualizarQuantidade(ItemPedido item, int linha) {
		if (item.getQuantidade() < 1) {
			if (linha == 0) {
				item.setQuantidade(1);
			} else {
				getPedido().getItens().remove(linha);
			}
		}
		this.pedido.recalcularValorTotal();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Usuario> getVendedores() {
		return vendedores;
	}

	public FormaPagamento[] getFormasPagamento() {
		return FormaPagamento.values();
	}

	public boolean isEditando() {
		boolean resultado = false;
		if (this.pedido != null) {
			resultado = this.pedido.getId() != null;
		}
		return resultado;
	}

	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}

	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}

	@SKU
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

}
