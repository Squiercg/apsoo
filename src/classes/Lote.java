package classes;

import java.sql.Date;
import java.util.ArrayList;

public class Lote {
	private int loteId;
	private Date loteData;
	private Fornecedor loteFornecedor;
	private ArrayList<ItemLote> itemLote;
	private double loteValor;
	
	
	public Lote(
			int loteId, 
			Date loteData, 
			Fornecedor loteFornecedor,
			ArrayList<ItemLote> itemLote, 
			double loteValor) {

		this.loteId = loteId;
		this.loteData = loteData;
		this.loteFornecedor = loteFornecedor;
		this.itemLote = itemLote;
		this.loteValor = loteValor;
	}

	//Confirmar esse getter e setter
	public void setLoteValor(double loteValor) {
		this.loteValor = loteValor;
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
