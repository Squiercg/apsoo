package classes;

public class Produto {
	private int produtoId;
	private String produtoDesc;
	private Categoria produtoCategoria;
	private double produtoCusto;
	private double produtoPreco;
	private double produtoLucro;
	
	public Produto(int produtoId, String produtoDesc,
			Categoria produtoCategoria, double produtoCusto,
			double produtoPreco, double produtoLucro) {
		this.produtoId = produtoId;
		this.produtoDesc = produtoDesc;
		this.produtoCategoria = produtoCategoria;
		this.produtoCusto = produtoCusto;
		this.produtoPreco = produtoPreco;
		this.produtoLucro = produtoLucro;
	}

	public double getProdutoPreco() {
		return produtoPreco;
	}

	public void setProdutoPreco(double produtoPreco) {
		if(produtoPreco>0){
			this.produtoPreco = produtoPreco;
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
		Produto produto = new Produto(1, "Calça Jeans",	calca, 30.00, 50.00, 20.00);
	
		System.out.println(produto);

	}

}
