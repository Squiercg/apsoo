package repositorio;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Categoria")
public class CategoriaEntity {
	
	@DatabaseField(generatedId = true)
	private int categoriaId;
	
	@DatabaseField(columnName = "categoriaDesc", canBeNull = false)
	private String categoriaDesc;	
	
	public CategoriaEntity() {
		// Construtor Vazio
	};
	
	public CategoriaEntity(
			int categoriaId, 
			String categoriaDesc) {
		this.categoriaId = categoriaId;
		this.categoriaDesc = categoriaDesc;
	}

	public String getCategoriaDesc() {
		return categoriaDesc;
	}

	public int getCategoriaId() {
		return categoriaId;
	}
	
}
