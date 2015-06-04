package classes;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Estoque")
public class Estoque {
	
	@DatabaseField(generatedId = true, columnName = "estoque_id")
	private int estoqueId;
	@DatabaseField(canBeNull = false, columnName = "estoque_produto_id")
	private int estoqueProdutoId;
	@DatabaseField(canBeNull = false, columnName = "estoque_produto_quantidade")
	private int estoqueProdutoQtde;
		
	public Estoque() {}
	
	public Estoque(
			int estoqueId, 
			int estoqueProdutoId,
			int estoqueProdutoQtde) {

		this.estoqueId = estoqueId;
		this.estoqueProdutoId = estoqueProdutoId;
		this.estoqueProdutoQtde = estoqueProdutoQtde;
	}
	
	public int getEstoque_id() {
		return estoqueId;
	}
	
	public void setEstoque_id(int estoqueId) {
		this.estoqueId = estoqueId;
	}
	
	public int getEstoque_produto_id() {
		return estoqueProdutoId;
	}
	
	public void setEstoque_produto_id(int estoqueProdutoId) {
		this.estoqueProdutoId = estoqueProdutoId;
	}
	
	public int getEstoque_produto_quantidade() {
		return estoqueProdutoQtde;
	}
	
	public void setEstoque_produto_quantidade(int estoqueProdutoQtde) {
		this.estoqueProdutoQtde = estoqueProdutoQtde;
	}
	
}
