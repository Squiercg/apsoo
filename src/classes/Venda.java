package classes;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Venda")
public class Venda {
	
	@DatabaseField(generatedId = true, columnName = "id_venda")
	private int vendaId;
	@DatabaseField(canBeNull = false, columnName = "venda_data")
	private Date vendaData;
	@DatabaseField(canBeNull = false, foreign = false, columnName = "venda_cliente")
	private int vendaCliente;
	@DatabaseField(columnName = "venda_valor")
	private double vendaValor;
	
	public Venda() {
		
	}
	
	public Venda(int vendaId, Date vendaData, int vendaCliente, double vendaValor) {
		this.vendaId = vendaId;
		this.vendaData = vendaData;
		this.vendaCliente = vendaCliente;
		this.vendaValor = vendaValor;
	}
	
	public Venda(Date vendaData, int vendaCliente, double vendaValor) {
		this.vendaData = vendaData;
		this.vendaCliente = vendaCliente;
		this.vendaValor = vendaValor;
	}
	
	public int getVendaId() {
		return vendaId;
	}
	
	public Date getVendaData() {
		return vendaData;
	}
	
	public int getVendaCliente() {
		return vendaCliente;
	}
	
	public double getVendaValor() {
		return vendaValor;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Venda))
			return false;
		return this.getVendaId() == ((Venda) obj).getVendaId();
	}
}
