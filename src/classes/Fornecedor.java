package classes;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Fornecedor")
//Coloquei telefone como no cliente, como string
public class Fornecedor {
	@DatabaseField(generatedId = true)
	private int fornecedorId;
	@DatabaseField(canBeNull = false)
	private String fornecedorNome;
	@DatabaseField(canBeNull = true)
	private String fornecedorTelefone;
	@DatabaseField(canBeNull = true)
	private String fornecedorEmail;
	
	public Fornecedor() {}
	
	public Fornecedor(
			int fornecedorId, 
			String fornecedorNome,
			String fornecedorTelefone, 
			String fornecedorEmail) {

		this.fornecedorId = fornecedorId;
		this.fornecedorNome = fornecedorNome;
		this.fornecedorTelefone = fornecedorTelefone;
		this.fornecedorEmail = fornecedorEmail;
		
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
	
	
	

}
