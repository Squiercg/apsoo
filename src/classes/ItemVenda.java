package classes;

public class ItemVenda {
	private Produto itemVendaProduto;
	private int itemVendaQuantidade;
	
	
	
	public ItemVenda(
			Produto itemVendaProduto, 
			int itemVendaQuantidade) {
		
		this.itemVendaProduto = itemVendaProduto;
		this.itemVendaQuantidade = itemVendaQuantidade;
	}
	public Produto getItemVendaProduto() {
		return itemVendaProduto;
	}
	public int getItemVendaQuantidade() {
		return itemVendaQuantidade;
	}	
	//definir como é o desconto do produto
	public double getItemVendaCusto() {
		return this.itemVendaQuantidade * 
				this.itemVendaProduto.getProdutoPreco();
	}
	

}
