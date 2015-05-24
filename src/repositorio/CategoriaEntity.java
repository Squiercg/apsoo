package repositorio;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Categoria")
public class CategoriaEntity {
	
	@DatabaseField(generatedId = true, columnName = "id_categoria")
	private int categoriaId;
	@DatabaseField(canBeNull = false, columnName = "categoria_descricao")
	private String categoriaDesc;	
	
	public CategoriaEntity() {
		// Construtor Vazio
	};
	
	public CategoriaEntity(String categoriaDesc) {
		this.categoriaDesc = categoriaDesc;
	}

	public String getCategoriaDesc() {
		return categoriaDesc;
	}

	public int getCategoriaId() {
		return categoriaId;
	}
	
}
