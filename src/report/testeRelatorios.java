package report;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import repositorio.CategoriaDao;
import repositorio.EstoqueDao;
import repositorio.ProdutoDao;
import classes.Categoria;
import classes.Estoque;
import classes.Produto;

public class testeRelatorios {

	public static void main(String[] args) throws SQLException, IOException, JRException {
		
		String databaseURL = "jdbc:sqlite:" + new File("lib/.").getCanonicalPath() + "/" + "CDT_database.sqlite";
		List<Estoque> listaEstoque = new EstoqueDao(databaseURL).getAll();
		List<EstoqueReport> lista = new ArrayList<EstoqueReport>();
		
		for(Estoque estoque: listaEstoque)
		{
			Produto prod = new ProdutoDao(databaseURL).getById(estoque.getEstoque_produto_id());
			Categoria categ = new CategoriaDao(databaseURL).getById(prod.getProdutoCategoria());			
			
			String ativo = prod.getProdutoAtivo() == 1 ? "Sim": "Não";
			
			Double total = estoque.getEstoque_produto_quantidade() * prod.getProdutoPreco();
			DecimalFormat decimal = new DecimalFormat("0.00"); 
			String valorTotal = "R$ " + decimal.format(total);
			String valor = "R$ " + decimal.format(prod.getProdutoPreco());
			
			lista.add(
				new EstoqueReport(
						prod.getProdutoDesc(),
						categ.getCategoriaDesc(),
						estoque.getEstoque_produto_quantidade(),
						valor,
						valorTotal,
						ativo
				)
			);
		}
		
		Relatorios.gerarRelatorioEstoque(lista);
	}

}
