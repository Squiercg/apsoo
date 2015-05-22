package classes;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Categoria")
public class Categoria {
	@DatabaseField(generatedId = true)
	private int categoriaId;
	@DatabaseField(canBeNull = false)
	private String categoriaDesc;	
	
	public Categoria(
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
