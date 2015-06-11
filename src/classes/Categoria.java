package classes;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Categoria")
public class Categoria {
	
	@DatabaseField(generatedId = true, columnName = "id_categoria")
	private int categoriaId;
	@DatabaseField(canBeNull = false, columnName = "categoria_descricao")
	private String categoriaDesc;	
	@DatabaseField(canBeNull = false, columnName = "ativo", defaultValue = "1")
	private int categoriaAtiva;
	
	public Categoria() {
		
	}
	
	public Categoria(String categoriaDesc) {
		this.categoriaDesc = categoriaDesc;
		categoriaAtiva = 1;
	}
	
	public String getCategoriaDesc() {
		return categoriaDesc;
	}
	
	public int getCategoriaId() {
		return categoriaId;
	}
	
	public int getCategoriaAtiva() {
		return categoriaAtiva;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Categoria))
			return false;
		return this.getCategoriaId() == ((Categoria) obj).getCategoriaId();
	}
}
