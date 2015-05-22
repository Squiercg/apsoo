package classes;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Cliente")
public class Cliente {
	@DatabaseField(generatedId = true)
	private int clienteId;
	@DatabaseField(canBeNull = false)
	private String clienteNome;
	@DatabaseField(canBeNull = true)
	private String clienteEndereco;
	@DatabaseField(canBeNull = true)
	private String clienteEstado;
	@DatabaseField(canBeNull = true)
	private String clienteTelefone;
	@DatabaseField(canBeNull = true)
	private String clienteDocumento;
	@DatabaseField(canBeNull = true)
	private String clienteEmail;	
	
	public Cliente() {}
	
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
