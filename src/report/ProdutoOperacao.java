package report;

public class ProdutoOperacao {
	private int produtoId;
	private String produtoDescricao;
	private int quantidade;
	private double valor;
	
	public ProdutoOperacao(int produtoId, String produtoDescricao, int quantidade, double valor){
		this.produtoId = produtoId;
		this.produtoDescricao = produtoDescricao;
		this.quantidade = quantidade;
		this.valor = valor;
	}
	
	public int getProdutoId() {
		return this.produtoId;
	}
	
	public String getProdutoDescricao() {
		return this.produtoDescricao;
	}
	
	public int getQuantidade() {
		return this.quantidade;
	}
	
	public double getValor() {
		return this.valor;
	}

}
