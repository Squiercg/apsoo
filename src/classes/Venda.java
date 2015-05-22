package classes;
import java.sql.Date;
import java.util.ArrayList;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

//Confirmar imports
//Usar arraylist para vendaItem?

@DatabaseTable(tableName = "Venda")
public class Venda {
	
	@DatabaseField(generatedId = true)
	private int vendaId;
	@DatabaseField(canBeNull = false)
	private Date vendaData;
	@DatabaseField(canBeNull = false, foreign = true)
	private Cliente vendaCliente;
	private ArrayList<ItemVenda> vendaItem;
	@DatabaseField(canBeNull = true)
	private double vendaValor;
	
	/*
	 * Aqui também, dois construtores, caso não queira setar o valor da venda, podemos calcular 
	 * ele a partir do vendaItem
	 */
	
	
	public Venda(
			int vendaId, 
			Date vendaData, 
			Cliente vendaCliente,
			ArrayList<ItemVenda> vendaItem, 
			double vendaValor) {

		this.vendaId = vendaId;
		this.vendaData = vendaData;
		this.vendaCliente = vendaCliente;
		this.vendaItem = vendaItem;
		this.vendaValor = vendaValor;
	}
	
	public Venda(
			int vendaId, 
			Date vendaData, 
			Cliente vendaCliente,
			ArrayList<ItemVenda> vendaItem) {

		this.vendaId = vendaId;
		this.vendaData = vendaData;
		this.vendaCliente = vendaCliente;
		this.vendaItem = vendaItem;
		this.setVendaValor();
	}
	
	/*
	 * Fiz dois set valor, um ele le do proprio objeto o valor a ser usado, o outro deixa
	 * você colocar qualquer valor, caso queria dar um desconto por exemplo.
	 */
	public void setVendaValor(double vendaValor) {
		if(vendaValor>=0){
			this.vendaValor = vendaValor;			
		}
	}
	
	public void setVendaValor() {
		double soma=0;
		for(int i=0;i<this.vendaItem.size();i++){
			soma+=vendaItem.get(i).getItemVendaCusto();
		}
		this.vendaValor = soma;
	}
	
	
	public int getVendaId() {
		return vendaId;
	}
	public Date getVendaData() {
		return vendaData;
	}
	public Cliente getVendaCliente() {
		return vendaCliente;
	}

	public double getVendaValor() {
		return this.vendaValor;
	}	

}
