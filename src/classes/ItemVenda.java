package classes;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ItemVenda")
public class ItemVenda {
	@DatabaseField(generatedId = true)
	private Produto itemVendaProduto;
	@DatabaseField(canBeNull = false)
	private int itemVendaQuantidade;
	
	public ItemVenda() {}
	
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
