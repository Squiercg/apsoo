package classes;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Produto")
public class Produto {
	
	@DatabaseField(generatedId = true, columnName = "id_produto")
	private int produtoId;
	@DatabaseField(canBeNull = false, columnName = "prod_descricao", index = true, indexName = "Nome_Produto")
	private String produtoDesc;
	@DatabaseField(canBeNull = false, columnName = "prod_categoria")
	private int produtoCategoria;
	@DatabaseField(columnName = "prod_custo")
	private double produtoCusto;
	@DatabaseField(columnName = "prod_preco")
	private double produtoPreco;
	@DatabaseField(columnName = "prod_lucro")
	private double produtoLucro;
	@DatabaseField(canBeNull = false, columnName = "ativo", defaultValue = "1")
	private int produtoAtivo;
	
	public Produto() {
		
	}
	
	public Produto(String produtoDesc, int produtoCategoria, double produtoCusto, double produtoPreco, double produtoLucro) {
		this.produtoDesc = produtoDesc;
		this.produtoCategoria = produtoCategoria;
		this.produtoCusto = produtoCusto;
		this.produtoPreco = produtoPreco;
		this.produtoLucro = produtoLucro;
		produtoAtivo = 1;
	}
	
	public Produto(int produtoId, String produtoDesc, int produtoCategoria, double produtoCusto, double produtoPreco, double produtoLucro, int produtoAtivo) {
		this.produtoId = produtoId;
		this.produtoDesc = produtoDesc;
		this.produtoCategoria = produtoCategoria;
		this.produtoCusto = produtoCusto;
		this.produtoPreco = produtoPreco;
		this.produtoLucro = produtoLucro;
		this.produtoAtivo = produtoAtivo;
	}
	
	public Produto(String produtoDesc) {
		this.produtoDesc = produtoDesc;
	}

	public int getProdutoId() {
		return produtoId;
	}
	
	public String getProdutoDesc() {
		return produtoDesc;
	}
	
	public int getProdutoCategoria() {
		return produtoCategoria;
	}
	
	public double getProdutoCusto() {
		return produtoCusto;
	}
	
	public double getProdutoPreco() {
		return produtoPreco;
	}
	
	public double getProdutoLucro() {
		return produtoLucro;
	}
	
	public int getProdutoAtivo() {
		return produtoAtivo;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Produto))
			return false;
		return this.getProdutoId() == ((Produto) obj).getProdutoId();
	}
}
