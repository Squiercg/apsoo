package classes;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ItemVenda")
public class ItemVenda {
	@DatabaseField(foreign = true, canBeNull = false)
	private int produto;
	@DatabaseField(canBeNull = false)
	private int quantidade;
	@DatabaseField(foreign = true, canBeNull = false)
	private int venda;
	
	public ItemVenda() {}
	
	public ItemVenda(
			int produto, 
			int quantidade) {
		
		this.produto = produto;
		this.produto = produto;
	}
}
