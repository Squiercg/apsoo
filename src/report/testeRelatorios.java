package report;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import repositorio.CategoriaDao;
import repositorio.ClienteDao;
import repositorio.EstoqueDao;
import repositorio.FornecedorDao;
import repositorio.ItemLoteProdutoDao;
import repositorio.ItemVendaDao;
import repositorio.LoteDao;
import repositorio.ProdutoDao;
import repositorio.VendaDao;
import classes.Categoria;
import classes.Cliente;
import classes.Estoque;
import classes.Fornecedor;
import classes.ItemLote;
import classes.ItemVenda;
import classes.Lote;
import classes.Produto;
import classes.Venda;

public class testeRelatorios {

	static DecimalFormat decimal = new DecimalFormat("0.00");
	static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void main(String[] args) throws SQLException, IOException, JRException {
		
		Estoque();
		Venda();
		Lote();
	}
	
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
			
			lista.add(
				new EstoqueReport(
						prod.getProdutoDesc(),
						categ.getCategoriaDesc(),
						estoque.getEstoque_produto_quantidade(),
						"R$ " + decimal.format(prod.getProdutoPreco()),
						"R$ " + decimal.format(total),
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
		
		for(Venda venda : listaVendas) 
		{
			Cliente cliente = new ClienteDao(databaseURL).getById(venda.getVendaCliente());		
			
			Operacao vendaOperacao = 
					new Operacao(
							venda.getVendaId(),
							cliente.getClienteNome(), 
							df.format(venda.getVendaData()), 
							"R$ " + decimal.format(venda.getVendaValor())
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
								"R$ " + decimal.format(produto.getProdutoPreco())
						)
				);
			}
			
			vendaOperacao.setProdutosOperacao(vendaProdutos);
			vendas.add(vendaOperacao);
		}
		
		Relatorios.gerarRelatorioVendas(vendas);
		
		// Comprovante de Venda
		Relatorios.gerarComprovanteVenda(vendas.get(0));
	}
	
	private static void Lote() throws IOException, SQLException, JRException {
		
		List<Operacao> lotes = new ArrayList<Operacao>();
		
		String databaseURL = "jdbc:sqlite:" + new File("lib/.").getCanonicalPath() + "/" + "CDT_database.sqlite";
		List<Lote> listaLotes = new LoteDao(databaseURL).getAll();
		
		for(Lote lote : listaLotes) 
		{
			Fornecedor fornecedor = new FornecedorDao(databaseURL).getById(lote.getLoteFornecedor());
			Operacao loteOperacao = 
					new Operacao(
							lote.getLoteId(),
							fornecedor.getFornecedorNome(), 
							df.format(lote.getLoteData()), 
							"R$ " + decimal.format(lote.getLoteValor())
					);
			
			// Lista para preencher os produtos de cada lote
			List<ProdutoOperacao> loteProdutos = new ArrayList<ProdutoOperacao>();
			
			List<ItemLote> itens = new ItemLoteProdutoDao(databaseURL).getForValue("lote", String.valueOf(lote.getLoteId()));
			
			for(ItemLote item : itens) 
			{
				Produto produto = new ProdutoDao(databaseURL).getById(item.getProduto());
				
				loteProdutos.add(
						new ProdutoOperacao(
								produto.getProdutoId(), 
								produto.getProdutoDesc(),
								item.getQuantidade(),
								"R$ " + decimal.format(produto.getProdutoCusto())
						)
				);
			}
			
			loteOperacao.setProdutosOperacao(loteProdutos);
			lotes.add(loteOperacao);
		}
		
		Relatorios.gerarRelatorioLotes(lotes);
	}
	
}
