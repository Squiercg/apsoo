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
	@DatabaseField(canBeNull = false, columnName = "estoque_categoria_id")
	private int estoqueCategoriaId;
		
	public Estoque() {
		
	}
	
	public Estoque(int estoqueProdutoId, int estoqueProdutoQtde, int estoqueCategoriaId) {
		this.estoqueProdutoId = estoqueProdutoId;
		this.estoqueProdutoQtde = estoqueProdutoQtde;
		this.estoqueCategoriaId = estoqueCategoriaId;
	}
	
	public int getEstoque_id() {
		return estoqueId;
	}
	
	public int getEstoque_produto_id() {
		return estoqueProdutoId;
	}
	
	public int getEstoque_produto_quantidade() {
		return estoqueProdutoQtde;
	}
	
	public int getEstoque_categoria_id() {
		return estoqueId;
	}
}
