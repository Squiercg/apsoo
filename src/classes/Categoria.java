package classes;

public class Categoria {
	private int categoriaId;
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
