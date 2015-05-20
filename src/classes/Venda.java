package classes;
import java.sql.Date;
import java.util.ArrayList;

//Confirmar imports
//Usar arraylist para vendaItem?

public class Venda {
	
	private int vendaId;
	private Date vendaData;
	private Cliente vendaCliente;
	private ArrayList<ItemVenda> vendaItem;
	private double vendaValor;
	
	
	
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
	
	//Certeza que é setvalor aqui, não é adicionar um desconto, algo assim?
	public void setVendaValor(double vendaValor) {
		this.vendaValor = vendaValor;
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
	
	//verificar esse getter
	public double getVendaValor() {
		double soma=0;
		for(int i=0;i<this.vendaItem.size();i++){
			soma+=vendaItem.get(i).getItemVendaCusto();
		}
		return soma;
	}	

}
