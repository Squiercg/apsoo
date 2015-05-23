package classes;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ItemLoteProduto")
public class ItemLote {
	@DatabaseField(foreign = true, canBeNull = false)
	private int lote;
	@DatabaseField(canBeNull = false)
	private int quantidade;
	@DatabaseField(foreign = true, canBeNull = false)
	private int produto;
	
	public ItemLote() {}
	
	public ItemLote(
			int produto, 
			int quantidade,
			int lote) {

		this.produto = produto;
		this.quantidade = quantidade;
		this.lote = lote;
	}
	
	public int getLote() {
		return lote;
	}

	public void setLote(int lote) {
		this.lote = lote;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getProduto() {
		return produto;
	}

	public void setProduto(int produto) {
		this.produto = produto;
	}
}
