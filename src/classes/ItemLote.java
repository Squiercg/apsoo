package classes;

public class ItemLote {
	private Produto itemLoteProduto;
	private int itemLoteQuantidade;
	
	
	public ItemLote(
			Produto itemLoteProduto, 
			int itemLoteQuantidade) {

		this.itemLoteProduto = itemLoteProduto;
		this.itemLoteQuantidade = itemLoteQuantidade;
	}
	public Produto getItemLoteProduto() {
		return itemLoteProduto;
	}
	public int getItemLoteQuantidade() {
		return itemLoteQuantidade;
	}
	
	public double getItemLoteCusto() {
		return this.itemLoteQuantidade*
				this.itemLoteProduto.getProdutoPreco();
	}	

}
