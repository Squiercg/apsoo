package classes;

//Coloquei telefone como no cliente, como string
public class Fornecedor {
	
	private int fornecedorId;
	private String fornecedorNome;
	private String fornecedorTelefone;
	private String fornecedorEmail;
	
	
	
	public Fornecedor(
//			int fornecedorId, 
			String fornecedorNome,
			String fornecedorTelefone, 
			String fornecedorEmail) {

//		this.fornecedorId = fornecedorId;
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
