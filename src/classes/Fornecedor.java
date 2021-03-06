package classes;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Fornecedor")
public class Fornecedor {
	
	@DatabaseField(generatedId = true, columnName = "id_fornecedor")
	private int fornecedorId;
	@DatabaseField(canBeNull = false, columnName = "fornecedor_nome", index = true, indexName = "Nome_Fornecedor")
	private String fornecedorNome;
	@DatabaseField(columnName = "fornecedor_fone")
	private String fornecedorTelefone;
	@DatabaseField(columnName = "fornecedor_email")
	private String fornecedorEmail;
	@DatabaseField(canBeNull = false, columnName = "ativo", defaultValue = "1")
	private int fornecedorAtivo;
	
	public Fornecedor() {
		
	}
	
	public Fornecedor(String fornecedorNome) {
		this.fornecedorNome = fornecedorNome;
	}
	
	public Fornecedor(String fornecedorNome, String fornecedorTelefone, String fornecedorEmail) {
		this.fornecedorNome = fornecedorNome;
		this.fornecedorTelefone = fornecedorTelefone;
		this.fornecedorEmail = fornecedorEmail;
		fornecedorAtivo = 1;
	}
	
	public Fornecedor(int fornecedorId, String fornecedorNome, String fornecedorTelefone, String fornecedorEmail, int fornecedorAtivo) {
		this.fornecedorId = fornecedorId;
		this.fornecedorNome = fornecedorNome;
		this.fornecedorTelefone = fornecedorTelefone;
		this.fornecedorEmail = fornecedorEmail;
		this.fornecedorAtivo = fornecedorAtivo;
	}
	
	public int getFornecedorId() {
		return fornecedorId;
	}
	
	public String getFornecedorNome() {
		return fornecedorNome;
	}
	
	public String getFornecedorTelefone() {
		return fornecedorTelefone;
	}
	
	public String getFornecedorEmail() {
		return fornecedorEmail;
	}
	
	public int getFornecedorAtivo() {
		return fornecedorAtivo;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Fornecedor))
			return false;
		return this.getFornecedorId() == ((Fornecedor) obj).getFornecedorId();
	}
}
