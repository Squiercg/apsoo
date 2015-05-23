package classes;
import java.sql.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Venda")
public class Venda {
	
	@DatabaseField(generatedId = true, columnName = "id_venda")
	private int vendaId;
	@DatabaseField(canBeNull = false, columnName = "venda_data")
	private Date vendaData;
	@DatabaseField(canBeNull = false, foreign = true, columnName = "venda_cliente")
	private int vendaCliente;
	@DatabaseField(columnName = "venda_valor")
	private double vendaValor;

	public Venda() {}

	public Venda(int vendaId, Date vendaData, int vendaCliente,
			double vendaValor) {
		this.vendaId = vendaId;
		this.vendaData = vendaData;
		this.vendaCliente = vendaCliente;
		this.vendaValor = vendaValor;
	}

	public int getVendaId() {
		return vendaId;
	}

	public void setVendaId(int vendaId) {
		this.vendaId = vendaId;
	}

	public Date getVendaData() {
		return vendaData;
	}

	public void setVendaData(Date vendaData) {
		this.vendaData = vendaData;
	}

	public int getVendaCliente() {
		return vendaCliente;
	}

	public void setVendaCliente(int vendaCliente) {
		this.vendaCliente = vendaCliente;
	}

	public double getVendaValor() {
		return vendaValor;
	}

	public void setVendaValor(double vendaValor) {
		this.vendaValor = vendaValor;
	}
	
}
