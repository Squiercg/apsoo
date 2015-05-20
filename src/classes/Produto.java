package classes;

public class Produto {
	private int produtoId;
	private String produtoDesc;
	private Categoria produtoCategoria;
	private double produtoCusto;
	/*Lucro está implementado  como um valor maior que zero, que é a porcentagem de lucro*/	
	private double produtoPreco;
	private double produtoLucro;
	
	/**/
	public Produto(
			int produtoId, 
			String produtoDesc,
			Categoria produtoCategoria, 
			double produtoCusto,
			double produtoPreco) {
		this.produtoId = produtoId;
		this.produtoDesc = produtoDesc;
		this.produtoCategoria = produtoCategoria;
		this.produtoCusto = produtoCusto;
		this.setProdutoPreco(produtoPreco);
	}	

	/**Dois construtores estão falhando
	 * mas eu queria deixar 2 construtores porque ou você constroi a classe com o lucro
	 * ou você poe o preço, mas uma informação é devirada da outra.

	public Produto(
			int produtoId, 
			String produtoDesc,
			Categoria produtoCategoria, 
			double produtoCusto, 
			double produtoLucro) {
		this.produtoId = produtoId;
		this.produtoDesc = produtoDesc;
		this.produtoCategoria = produtoCategoria;
		this.produtoCusto = produtoCusto;
		this.setProdutoLucro(produtoLucro);
	}
	 */


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
		Categoria calca = new Categoria(1,"Calças");
		
		System.out.println("Criando um objeto Produto.");
		Produto produto = new Produto(1, "Calça Jeans",	calca, 30.00, 0.2);
	
		System.out.println(produto);

	}

}
