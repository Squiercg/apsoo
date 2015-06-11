package report;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import repositorio.CategoriaDao;
import repositorio.ClienteDao;
import repositorio.EstoqueDao;
import repositorio.ItemVendaDao;
import repositorio.ProdutoDao;
import repositorio.VendaDao;
import classes.Cliente;
import classes.ItemVenda;
import classes.Venda;
import classes.Categoria;
import classes.Estoque;
import classes.Produto;

public class testeRelatorios {

	public static void main(String[] args) throws SQLException, IOException, JRException {
		
		//Estoque();
		Venda();
	}
	
	@SuppressWarnings("unused")
	private static void Estoque() throws SQLException, IOException, JRException	{
		
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
	
	private static void Venda() throws IOException, SQLException, JRException {
		
		List<Operacao> vendas = new ArrayList<Operacao>();
		
		String databaseURL = "jdbc:sqlite:" + new File("lib/.").getCanonicalPath() + "/" + "CDT_database.sqlite";
		List<Venda> listaVendas = new VendaDao(databaseURL).getAll();
		
		for(Venda venda : listaVendas) {
			Cliente cliente = new ClienteDao(databaseURL).getById(venda.getVendaCliente());
			Operacao vendaOperacao = 
					new Operacao(
							venda.getVendaId(),
							cliente.getClienteNome(), 
							venda.getVendaData(), 
							venda.getVendaValor()
					);
			
			// Lista para preencher os produtos de cada venda
			List<ProdutoOperacao> vendaProdutos = new ArrayList<ProdutoOperacao>();
			
			List<ItemVenda> itens = new ItemVendaDao(databaseURL).getForValue("venda", String.valueOf(venda.getVendaId()));
			
			for(ItemVenda item : itens) {
				Produto produto = new ProdutoDao(databaseURL).getById(item.getProduto());
				
				vendaProdutos.add(
						new ProdutoOperacao(
								produto.getProdutoId(), 
								produto.getProdutoDesc(),
								item.getQuantidade(),
								produto.getProdutoPreco()
						)
				);
			}
			
			vendaOperacao.setProdutosOperacao(vendaProdutos);
			vendas.add(vendaOperacao);
		}
		
		Relatorios.gerarRelatorioVendas(vendas);
	}
	
}
