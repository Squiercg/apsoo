package classes;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Produto")
public class Produto {
	@DatabaseField(generatedId = true)
	private int produtoId;
	@DatabaseField(canBeNull = false)
	private String produtoDesc;
	@DatabaseField(canBeNull = false, foreign = true)
	private Categoria produtoCategoria;
	@DatabaseField(canBeNull = true)
	private double produtoCusto;
	/*Lucro esta implementado  como um valor maior que zero, que eh a porcentagem de lucro*/	
	@DatabaseField(columnName = "prod_preco")
	private double produtoPreco;
	@DatabaseField(columnName = "prod_lucro")
	private double produtoLucro;
	
	/**/
	public Produto(
//			int produtoId, 
			String produtoDesc,
			Categoria produtoCategoria, 
			double produtoCusto,
			double produtoPreco) {
//		this.produtoId = produtoId;
		this.produtoDesc = produtoDesc;
		this.produtoCategoria = produtoCategoria;
		this.produtoCusto = produtoCusto;
		this.setProdutoPreco(produtoPreco);
	}	
	
	public Produto(
//			int produtoId, 
			String produtoDesc,
			Categoria produtoCategoria, 
			double produtoCusto, 
			double produtoLucro) {
//		this.produtoId = produtoId;
		this.produtoDesc = produtoDesc;
		this.produtoCategoria = produtoCategoria;
		this.produtoCusto = produtoCusto;
		this.setProdutoLucro(produtoLucro);
	}
	


	public double getProdutoPreco() {
		return produtoPreco;
	}

	public void setProdutoPreco(double produtoPreco) {
		if(produtoPreco>0){
			this.produtoPreco = produtoPreco;
			this.produtoLucro = (this.produtoCusto-this.produtoPreco)/this.produtoCusto;
		} else {
			this.produtoPreco = 0;
		}
		
	}

	//Esse valor sera porcentagem, de 0 a 1?
	public double getProdutoLucro() {
		return produtoLucro;
	}

	public void setProdutoLucro(double produtoLucro) {
		this.produtoLucro = produtoLucro;
		this.produtoPreco = this.produtoPreco + this.produtoLucro * this.produtoPreco;
		
	}

	public int getProdutoId() {
		return produtoId;
	}

	public String getProdutoDesc() {
		return produtoDesc;
	}

	public Categoria getProdutoCategoria() {
		return produtoCategoria;
	}

	public double getProdutoCusto() {
		return produtoCusto;
	}

	public static void main(String[] args) {
		System.out.println("Criando um objeto categoria.");
		Categoria calca = new Categoria("Calças");
		
		System.out.println("Criando um objeto Produto.");
		Produto produto = new Produto( "Calça Jeans",	calca, 30.00, 0.2);
	
		System.out.println(produto);

	}

}
