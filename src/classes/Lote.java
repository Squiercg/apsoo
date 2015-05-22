package classes;

import java.sql.Date;
import java.util.ArrayList;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName = "Lote")
public class Lote {
	@DatabaseField(generatedId = true)
	private int loteId;
	@DatabaseField(canBeNull = false)
	private Date loteData;
	@DatabaseField(canBeNull = false, foreign = true)
	private Fornecedor loteFornecedor;
	private ArrayList<ItemLote> itemLote;
	@DatabaseField(canBeNull = false)
	private double loteValor;
	
	public Lote() {}
	
	public Lote(
			int loteId, 
			Date loteData, 
			Fornecedor loteFornecedor,
			ArrayList<ItemLote> itemLote) {

		this.loteId = loteId;
		this.loteData = loteData;
		this.loteFornecedor = loteFornecedor;
		this.itemLote = itemLote;
		this.setLoteValor();
	}

	//Confirmar esse getter e setter
	public void setLoteValor() {
		double soma = 0.0;
		for(int i=0;i<this.itemLote.size();i++) {
			soma+= itemLote.get(i).getItemLoteCusto();
		}
		this.loteValor = soma;
	}
	
	public double getLoteValor() {
		return loteValor;
	}

	public int getLoteId() {
		return loteId;
	}
	public Date getLoteData() {
		return loteData;
	}
	public Fornecedor getLoteFornecedor() {
		return loteFornecedor;
	}	

}
