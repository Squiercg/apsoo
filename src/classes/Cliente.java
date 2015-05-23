package classes;

import java.sql.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Cliente")
public class Cliente {
	@DatabaseField(generatedId = true, columnName = "id_cliente")
	private int clienteId;
	@DatabaseField(canBeNull = false, columnName = "cliente_nome")
	private String clienteNome;
	@DatabaseField(columnName = "cliente_endereco")
	private String clienteEndereco;
	@DatabaseField(columnName = "cliente_cidade", defaultValue = "Campo Grande")
	private String clienteCidade;
	@DatabaseField(columnName = "cliente_estado", defaultValue = "MS")
	private String clienteEstado;
	@DatabaseField(columnName = "cliente_fone")
	private String clienteTelefone;
	@DatabaseField(columnName = "cliente_documento")
	private String clienteDocumento;
	@DatabaseField(columnName = "cliente_nascimento")
	private Date clienteNascimento;
	@DatabaseField(columnName = "cliente_email")
	private String clienteEmail;	
	
	public Cliente() {}
	
	public Cliente(
			int clienteId, 
			String clienteNome, 
			String clienteEndereco,
			String clienteCidade,
			String clienteEstado, 
			String clienteTelefone,
			String clienteDocumento,
			Date clienteNascimento,
			String clienteEmail) {

		this.clienteId = clienteId;
		this.clienteNome = clienteNome;
		this.clienteEndereco = clienteEndereco;
		this.clienteCidade = clienteCidade;
		this.clienteEstado = clienteEstado;
		this.clienteTelefone = clienteTelefone;
		this.clienteDocumento = clienteDocumento;
		this.clienteNascimento = clienteNascimento;
		this.clienteEmail = clienteEmail;
	}
	
	public String getClienteCidade() {
		return clienteCidade;
	}

	public void setClienteCidade(String clienteCidade) {
		this.clienteCidade = clienteCidade;
	}

	public Date getClienteNascimento() {
		return clienteNascimento;
	}

	public void setClienteNascimento(Date clienteNascimento) {
		this.clienteNascimento = clienteNascimento;
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
