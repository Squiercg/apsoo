package classes;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Cliente")
public class Cliente {
	
	@DatabaseField(generatedId = true, columnName = "id_cliente")
	private int clienteId;
	@DatabaseField(canBeNull = false, columnName = "cliente_nome", index = true, indexName = "Nome_clientes")
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
	@DatabaseField(canBeNull = false, columnName = "ativo", defaultValue = "1")
	private int clienteAtivo;
	
	public Cliente() {
		
	}
	
	public Cliente(String clienteNome) {
		this.clienteNome = clienteNome;
	}
	
	public Cliente(String clienteNome, String clienteEndereco, String clienteCidade, String clienteEstado, 
			String clienteTelefone, String clienteDocumento, Date clienteNascimento, String clienteEmail) {
		this.clienteNome = clienteNome;
		this.clienteEndereco = clienteEndereco;
		this.clienteCidade = clienteCidade;
		this.clienteEstado = clienteEstado;
		this.clienteTelefone = clienteTelefone;
		this.clienteDocumento = clienteDocumento;
		this.clienteNascimento = clienteNascimento;
		this.clienteEmail = clienteEmail;
		clienteAtivo = 1;
	}
	
	public Cliente(int clienteId, String clienteNome, String clienteEndereco, String clienteCidade, String clienteEstado, 
			String clienteTelefone, String clienteDocumento, Date clienteNascimento, String clienteEmail, int clienteAtivo) {
		this.clienteId = clienteId;
		this.clienteNome = clienteNome;
		this.clienteEndereco = clienteEndereco;
		this.clienteCidade = clienteCidade;
		this.clienteEstado = clienteEstado;
		this.clienteTelefone = clienteTelefone;
		this.clienteDocumento = clienteDocumento;
		this.clienteNascimento = clienteNascimento;
		this.clienteEmail = clienteEmail;
		this.clienteAtivo = clienteAtivo;
	}
	
	public String getClienteCidade() {
		return clienteCidade;
	}
	
	public Date getClienteNascimento() {
		return clienteNascimento;
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
	
	public int getClienteAtivo() {
		return clienteAtivo;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Cliente))
			return false;
		return this.getClienteId() == ((Cliente) obj).getClienteId();
	}
}
