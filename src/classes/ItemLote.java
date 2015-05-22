package classes;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ItemLote")
public class ItemLote {
	@DatabaseField(generatedId = true)
	private Produto itemLoteProduto;
	@DatabaseField(canBeNull = false)
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
