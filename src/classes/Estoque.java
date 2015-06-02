package classes;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Estoque")
public class Estoque {
	
	@DatabaseField(generatedId = true, columnName = "estoque_id")
	private int estoque_id;
	@DatabaseField(canBeNull = false, columnName = "estoque_produto_id")
	private int estoque_produto_id;
	@DatabaseField(canBeNull = false, columnName = "estoque_produto_quantidade")
	private int estoque_produto_quantidade;
		
	public Estoque() {}
	
	public Estoque(
			int estoque_id, 
			int estoque_produto_id,
			int estoque_produto_quantidade) {

		this.estoque_id = estoque_id;
		this.estoque_produto_id = estoque_produto_id;
		this.estoque_produto_quantidade = estoque_produto_quantidade;
	}
	
	public int getEstoque_id() {
		return estoque_id;
	}
	
	public void setEstoque_id(int estoque_id) {
		this.estoque_id = estoque_id;
	}
	
	public int getEstoque_produto_id() {
		return estoque_produto_id;
	}
	
	public void setEstoque_produto_id(int estoque_produto_id) {
		this.estoque_produto_id = estoque_produto_id;
	}
	
	public int getEstoque_produto_quantidade() {
		return estoque_produto_quantidade;
	}
	
	public void setEstoque_produto_quantidade(int estoque_produto_quantidade) {
		this.estoque_produto_quantidade = estoque_produto_quantidade;
	}
	
}
