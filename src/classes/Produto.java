package classes;

import java.sql.SQLException;
import java.util.ArrayList;

import repositorio.ProdutoDao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Produto")
public class Produto {
	@DatabaseField(generatedId = true, columnName = "id_produto")
	private int produtoId;
	@DatabaseField(canBeNull = false, columnName = "prod_descricao", 
			index = true, indexName = "Nome_Produto")
	private String produtoDesc;
	@DatabaseField(canBeNull = false, columnName = "prod_categoria")
	private int produtoCategoria;
	@DatabaseField(columnName = "prod_custo")
	private double produtoCusto;
	@DatabaseField(columnName = "prod_preco")
	private double produtoPreco;
	@DatabaseField(columnName = "prod_lucro")
	private double produtoLucro;
	
	public Produto() {}

	public Produto(int produtoId, String produtoDesc, int produtoCategoria,
			double produtoCusto, double produtoPreco, double produtoLucro) {
		this.produtoId = produtoId;
		this.produtoDesc = produtoDesc;
		this.produtoCategoria = produtoCategoria;
		this.produtoCusto = produtoCusto;
		this.produtoPreco = produtoPreco;
		this.produtoLucro = produtoLucro;
	}

	public int getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(int produtoId) {
		this.produtoId = produtoId;
	}

	public String getProdutoDesc() {
		return produtoDesc;
	}

	public void setProdutoDesc(String produtoDesc) {
		this.produtoDesc = produtoDesc;
	}

	public int getProdutoCategoria() {
		return produtoCategoria;
	}

	public void setProdutoCategoria(int produtoCategoria) {
		this.produtoCategoria = produtoCategoria;
	}

	public double getProdutoCusto() {
		return produtoCusto;
	}

	public void setProdutoCusto(double produtoCusto) {
		this.produtoCusto = produtoCusto;
	}

	public double getProdutoPreco() {
		return produtoPreco;
	}

	public void setProdutoPreco(double produtoPreco) {
		this.produtoPreco = produtoPreco;
	}

	public double getProdutoLucro() {
		return produtoLucro;
	}

	public void setProdutoLucro(double produtoLucro) {
		this.produtoLucro = produtoLucro;
	}
	
	public ArrayList<Produto> produtoPorCategoria(String idCategoria, String databaseUrl) throws SQLException{
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		ProdutoDao buscador = new ProdutoDao(databaseUrl);
		produtos = (ArrayList<Produto>) buscador.getForValue("prod_categoria", idCategoria);
		return produtos;
	}
}
