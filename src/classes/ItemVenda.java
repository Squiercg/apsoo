package classes;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ItemVenda")
public class ItemVenda {
	
	@DatabaseField(foreign = false, canBeNull = false)
	private int produto;
	@DatabaseField(canBeNull = false)
	private int quantidade;
	@DatabaseField(foreign = false, canBeNull = false)
	private int venda;
	
	public ItemVenda() {
		
	}
	
	public ItemVenda(int produto, int quantidade, int venda) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.venda = venda;
	}
	
	public int getProduto() {
		return produto;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public int getVenda() {
		return venda;
	}	
	
	public void setVenda(int venda) {
		this.venda = venda;
	}
}
