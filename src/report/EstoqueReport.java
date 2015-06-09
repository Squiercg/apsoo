package report;

public class EstoqueReport {
	private int estoqueId;
	private int estoqueProdutoId;
	private int estoqueProdutoQtde;
	
	public EstoqueReport() {}

	public int getEstoque_id() {
		return estoqueId;
	}
	
	public void setEstoque_id(int estoqueId) {
		this.estoqueId = estoqueId;
	}
	
	public int getEstoque_produto_id() {
		return estoqueProdutoId;
	}
	
	public void setEstoque_produto_id(int estoqueProdutoId) {
		this.estoqueProdutoId = estoqueProdutoId;
	}
	
	public int getEstoque_produto_quantidade() {
		return estoqueProdutoQtde;
	}
	
	public void setEstoque_produto_quantidade(int estoqueProdutoQtde) {
		this.estoqueProdutoQtde = estoqueProdutoQtde;
	}
}
