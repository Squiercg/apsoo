package report;

public class EstoqueReport {
	private String estoqueProdutoDescricao;
	private String estoqueProdutoCategoria;
	private String estoqueProdutoValor;
	private String estoqueProdutoValorTotal;
	private String estoqueProdutoAtivoFlag;
	private int estoqueProdutoQtde;
	
	public EstoqueReport() {}
	
	public EstoqueReport(
			String estoqueProdutoDescricao, 
			String estoqueProdutoCategoria, 
			int estoqueProdutoQtde,
			String estoqueProdutoValor,
			String estoqueProdutoValorTotal,
			String estoqueProdutoAtivoFlag) 
	{
		this.estoqueProdutoDescricao = estoqueProdutoDescricao;
		this.estoqueProdutoCategoria = estoqueProdutoCategoria;
		this.estoqueProdutoQtde = estoqueProdutoQtde;
		this.estoqueProdutoValor = estoqueProdutoValor;
		this.estoqueProdutoAtivoFlag = estoqueProdutoAtivoFlag;
		this.estoqueProdutoValorTotal = estoqueProdutoValorTotal;
	}
	
	public String getEstoqueProdutoDescricao() {
		return estoqueProdutoDescricao;
	}
	
	public void setEstoqueProdutoDescricao(String estoqueProdutoDescricao) {
		this.estoqueProdutoDescricao = estoqueProdutoDescricao;
	}
	
	public String getEstoqueProdutoCategoria() {
		return estoqueProdutoCategoria;
	}
	
	public void setEstoqueProdutoCategoria(String estoqueProdutoCategoria) {
		this.estoqueProdutoCategoria = estoqueProdutoCategoria;
	}
	
	public int getEstoqueProdutoQtde() {
		return estoqueProdutoQtde;
	}
	
	public void setEstoqueProdutoQtde(int estoqueProdutoQtde) {
		this.estoqueProdutoQtde = estoqueProdutoQtde;
	}

	public String getEstoqueProdutoValor() {
		return estoqueProdutoValor;
	}

	public void setEstoqueProdutoValor(String estoqueProdutoValor) {
		this.estoqueProdutoValor = estoqueProdutoValor;
	}

	public String getEstoqueProdutoAtivoFlag() {
		return estoqueProdutoAtivoFlag;
	}

	public void setEstoqueProdutoAtivoFlag(String estoqueProdutoAtivoFlag) {
		this.estoqueProdutoAtivoFlag = estoqueProdutoAtivoFlag;
	}

	public String getEstoqueProdutoValorTotal() {
		return estoqueProdutoValorTotal;
	}

	public void setEstoqueProdutoValorTotal(String estoqueProdutoValorTotal) {
		this.estoqueProdutoValorTotal = estoqueProdutoValorTotal;
	}
}
