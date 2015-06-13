package report;

import java.util.List;

public class Operacao {
	private int operacaoId;
	private String nomeSolicitante;
	private String dataOperacao;
	private String valorOperacao;
	private List<ProdutoOperacao> produtosOperacao;
	
	public Operacao(int operacaoId, String nomeSolicitante, String dataOperacao, String valorOperacao) {
		this.operacaoId = operacaoId;
		this.nomeSolicitante = nomeSolicitante;
		this.dataOperacao = dataOperacao;
		this.valorOperacao = valorOperacao;
	}
	
	public Operacao(int operacaoId, String nomeSolicitante, String dataOperacao, String valorOperacao, List<ProdutoOperacao> produtosOperacao) {
		this.operacaoId = operacaoId;
		this.nomeSolicitante = nomeSolicitante;
		this.dataOperacao = dataOperacao;
		this.valorOperacao = valorOperacao;
		this.produtosOperacao = produtosOperacao;
	}
	
	public int getOperacaoId() {
		return this.operacaoId;
	}
	
	public String getNomeSolicitante() {
		return this.nomeSolicitante;
	}
	
	public String getDataOperacao() {
		return this.dataOperacao;
	}
	
	public String getValorOperacao() {
		return this.valorOperacao;
	}
	
	public List<ProdutoOperacao> getProdutosOperacao() {
		return this.produtosOperacao;
	}

	public void setProdutosOperacao(List<ProdutoOperacao> produtosOperacao) {
		this.produtosOperacao = produtosOperacao;
	}
	
}
