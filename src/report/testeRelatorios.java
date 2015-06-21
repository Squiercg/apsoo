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
import repositorio.ClienteDao;
import repositorio.ItemVendaDao;
import repositorio.ProdutoDao;
import repositorio.VendaDao;
import classes.Cliente;
import classes.ItemVenda;
import classes.Produto;
import classes.Venda;

public class testeRelatorios {

	static DecimalFormat decimal = new DecimalFormat("0.00");
	static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void main(String[] args) throws SQLException, IOException, JRException {
		
		Venda();
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
}
