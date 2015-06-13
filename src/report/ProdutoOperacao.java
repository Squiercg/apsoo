package report;

public class ProdutoOperacao {
	private int produtoId;
	private String produtoDescricao;
	private int quantidade;
	private String valor;
	
	public ProdutoOperacao(int produtoId, String produtoDescricao, int quantidade, String valor){
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
	
	public String getValor() {
		return this.valor;
	}

}
