package classes;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Lote")
public class Lote {
	
	@DatabaseField(generatedId = true, columnName = "id_lote")
	private int loteId;
	@DatabaseField(canBeNull = false, columnName = "lote_data")
	private Date loteData;
	@DatabaseField(canBeNull = false, foreign = false, columnName = "lote_fornecedor")
	private int loteFornecedor;
	@DatabaseField(canBeNull = false, columnName = "lote_valor")
	private double loteValor;
	
	public Lote() {
		
	}
	
	public Lote(int loteId, Date date, int loteFornecedor, double loteValor) {
		this.loteId = loteId;
		this.loteData = date;
		this.loteFornecedor = loteFornecedor;
		this.loteValor = loteValor;
	}
	
	public Lote(Date date, int loteFornecedor, double loteValor) {
		this.loteData = date;
		this.loteFornecedor = loteFornecedor;
		this.loteValor = loteValor;
	}

	public int getLoteId() {
		return loteId;
	}
	
	public Date getLoteData() {
		return loteData;
	}
	
	public int getLoteFornecedor() {
		return loteFornecedor;
	}
	
	public double getLoteValor() {
		return loteValor;
	}
}
