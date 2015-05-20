package classes;

public class Cliente {
	private int clienteId;
	private String clienteNome;
	private String clienteEndereco;
	private String clienteEstado;
	private String clienteTelefone;
	private String clienteDocumento;
	private String clienteEmail;	
	
	public Cliente(
			int clienteId, 
			String clienteNome, 
			String clienteEndereco,
			String clienteEstado, 
			String clienteTelefone,
			String clienteDocumento, 
			String clienteEmail) {

		this.clienteId = clienteId;
		this.clienteNome = clienteNome;
		this.clienteEndereco = clienteEndereco;
		this.clienteEstado = clienteEstado;
		this.clienteTelefone = clienteTelefone;
		this.clienteDocumento = clienteDocumento;
		this.clienteEmail = clienteEmail;
	}
	
	public int getClienteId() {
		return clienteId;
	}
	public String getClienteNome() {
		return clienteNome;
	}
	public String getClienteEndereco() {
		return clienteEndereco;
	}
	public String getClienteEstado() {
		return clienteEstado;
	}
	public String getClienteTelefone() {
		return clienteTelefone;
	}
	public String getClienteDocumento() {
		return clienteDocumento;
	}
	public String getClienteEmail() {
		return clienteEmail;
	}
	
	

}
