package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import main.Main;
import net.sf.jasperreports.engine.JRException;
import report.EstoqueReport;
import report.Operacao;
import report.ProdutoOperacao;
import report.Relatorios;
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

@SuppressWarnings({"rawtypes", "unchecked"})
public class ModuloRelatorios {
	
	private static Dimension preferredSize;
	private static Border defaultBorder;
	private static JButton alterButton;
	private static JButton cancelButton;
	private static SystemInterface systemInterface;
	
	public ModuloRelatorios(SystemInterface systemInterface) {
		ModuloRelatorios.systemInterface = systemInterface;
		preferredSize = systemInterface.getSystemInterfaceDimension();
		defaultBorder = Main.isBrunoTesting ? BorderFactory.createRaisedBevelBorder() : BorderFactory.createEmptyBorder();
	}
	
	public JPanel relatorioConferenciaEstoque() {
		JPanel panelLevel0 = new JPanel(new BorderLayout());
		Common.makeLateralBorders(panelLevel0, preferredSize, defaultBorder);
		
		JPanel panelLevel1 = new JPanel(new BorderLayout());
		panelLevel0.add(panelLevel1, BorderLayout.CENTER);
		
		JPanel panelLevel2 = new JPanel(new BorderLayout());
		panelLevel1.add(panelLevel2, BorderLayout.NORTH);
		
		JLabel placeHolder = new JLabel("");
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		placeHolder.setBorder(defaultBorder);
		panelLevel2.add(placeHolder, BorderLayout.NORTH);
		
		placeHolder = new JLabel("Conferência de Estoque");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 20));
		panelLevel2.add(placeHolder, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 16)));
		placeHolder.setBorder(defaultBorder);
		panelLevel2.add(placeHolder, BorderLayout.SOUTH);
		
		panelLevel2 = new JPanel(new BorderLayout());
		panelLevel1.add(panelLevel2, BorderLayout.CENTER);
		
		JPanel panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.NORTH);
		
		placeHolder = new JLabel("Categoria");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getHeight() / 64 + (int) (preferredSize.getWidth() / 3)), 
			(int) (preferredSize.getHeight() / 32)));
		panelLevel3.add(placeHolder, BorderLayout.WEST);
		
		placeHolder = new JLabel("Data de Inclusão"); 
		placeHolder.setBorder(defaultBorder);
		placeHolder.setForeground(placeHolder.getBackground());
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		panelLevel3.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.CENTER);
		JPanel panelLevel4 = new JPanel(new BorderLayout());
		panelLevel3.add(panelLevel4, BorderLayout.NORTH);
		
		String lista[] = null;
		List<Categoria> categorias = null;
		
		try {
			CategoriaDao categoriaDao = new CategoriaDao(systemInterface.getSystemInterfaceDatabaseURL());
			categorias = categoriaDao.getAll();
		} catch (SQLException e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de categorias!");
		} finally {
			if(categorias == null || categorias.size() == 0) {
				categorias = new ArrayList<Categoria>();
				categorias.add(new Categoria("Nenhum valor encontrado"));
				String message = categorias == null ? "Houve um erro ao recuperar a lista de categorias!" : "Nenhuma categoria encontrada!";
				systemInterface.getSystemInterfaceLabelStatus().setText(message);
			}
			boolean isNull = categorias.get(0).getCategoriaDesc().equalsIgnoreCase("Nenhum valor encontrado");
			lista = new String[isNull ? categorias.size() : categorias.size() + 1];
			
			if(!isNull) {
				lista[0] = "Todas as categorias";
			}
			for(Categoria c : categorias)
				lista[categorias.indexOf(c) + (isNull ? 0 : 1)] = c.getCategoriaDesc();
		}
		JComboBox comboBoxCategorias = new JComboBox(lista);
		comboBoxCategorias.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		comboBoxCategorias.setBackground(Color.white);
		comboBoxCategorias.setEditable(false);
		comboBoxCategorias.setSelectedIndex(0);
		panelLevel4.add(comboBoxCategorias, BorderLayout.WEST);
		
		JPanel panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + (int) (preferredSize.getWidth() / 64) - 
			(int) (preferredSize.getWidth() / 4) - 5, (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.SOUTH);
		panelLevel4 = new JPanel(new BorderLayout());
		panelLevel3.add(panelLevel4, BorderLayout.SOUTH);
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.NORTH);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.SOUTH);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.EAST);
		
		cancelButton = new JButton("Voltar");
		cancelButton.setBackground(Color.white);
		cancelButton.setForeground(Color.black);
		cancelButton.addMouseListener(systemInterface.new HandlerHomeButton());
		cancelButton.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 16) - 
			(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(cancelButton, BorderLayout.EAST);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 64),  (int) (preferredSize.getHeight() / 16) - 
				(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		alterButton = new JButton("Gerar");
		float[] hsbColor = Color.RGBtoHSB(51, 122, 183, null); 
		alterButton.setBackground(Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]));
		alterButton.setForeground(Color.white);
		alterButton.addMouseListener(new HandlerConferenciaEstoque(systemInterface, comboBoxCategorias, categorias));
		alterButton.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 16) - 
			(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(alterButton, BorderLayout.WEST);
		
		return panelLevel0;
	}
	
	public JPanel relatorioHistoricoLotes() {
		JPanel panelLevel0 = new JPanel(new BorderLayout());
		Common.makeLateralBorders(panelLevel0, preferredSize, defaultBorder);
		
		JPanel panelLevel1 = new JPanel(new BorderLayout());
		panelLevel0.add(panelLevel1, BorderLayout.CENTER);
		
		JPanel panelLevel2 = new JPanel(new BorderLayout());
		panelLevel1.add(panelLevel2, BorderLayout.NORTH);
		
		JLabel placeHolder = new JLabel("");
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		placeHolder.setBorder(defaultBorder);
		panelLevel2.add(placeHolder, BorderLayout.NORTH);
		
		placeHolder = new JLabel("Histórico de Lotes");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 20));
		panelLevel2.add(placeHolder, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 16)));
		placeHolder.setBorder(defaultBorder);
		panelLevel2.add(placeHolder, BorderLayout.SOUTH);
		
		panelLevel2 = new JPanel(new BorderLayout());
		panelLevel1.add(panelLevel2, BorderLayout.CENTER);
		
		JPanel panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.NORTH);
		
		placeHolder = new JLabel("Fornecedor");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getHeight() / 64 + (int) (preferredSize.getWidth() / 3)), 
			(int) (preferredSize.getHeight() / 32)));
		panelLevel3.add(placeHolder, BorderLayout.WEST);
		
		placeHolder = new JLabel("Data de Inclusão"); 
		placeHolder.setBorder(defaultBorder);
		placeHolder.setForeground(placeHolder.getBackground());
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		panelLevel3.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.CENTER);
		JPanel panelLevel4 = new JPanel(new BorderLayout());
		panelLevel3.add(panelLevel4, BorderLayout.NORTH);
		
		String lista[] = null;
		List<Fornecedor> fornecedores = null;
		
		try {
			FornecedorDao fornecedorDao = new FornecedorDao(systemInterface.getSystemInterfaceDatabaseURL());
			fornecedores = fornecedorDao.getAll();
		} catch (SQLException e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de fornecedores!");
		} finally {
			if(fornecedores == null || fornecedores.size() == 0) {
				fornecedores = new ArrayList<Fornecedor>();
				fornecedores.add(new Fornecedor("Nenhum valor encontrado"));
				String message = fornecedores == null ? "Houve um erro ao recuperar a lista de fornecedores!" : "Nenhum fornecedor encontrado!";
				systemInterface.getSystemInterfaceLabelStatus().setText(message);
			}
			boolean isNull = fornecedores.get(0).getFornecedorNome().equalsIgnoreCase("Nenhum valor encontrado");
			lista = new String[isNull ? fornecedores.size() : fornecedores.size() + 1];
			
			if(!isNull) {
				lista[0] = "Todos os fornecedores";
			}
			for(Fornecedor f : fornecedores)
				lista[fornecedores.indexOf(f) + (isNull ? 0 : 1)] = f.getFornecedorNome();
		}
		JComboBox comboBoxFornecedores = new JComboBox(lista);
		comboBoxFornecedores.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		comboBoxFornecedores.setBackground(Color.white);
		comboBoxFornecedores.setEditable(false);
		comboBoxFornecedores.setSelectedIndex(0);
		panelLevel4.add(comboBoxFornecedores, BorderLayout.WEST);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				comboBoxFornecedores.requestFocusInWindow();
		    }
		});
		
		JPanel panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4) + 
			(int) (preferredSize.getWidth() / 64), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.EAST);
		
		panelLevel4 = new JPanel(new BorderLayout());
		panelLevel3.add(panelLevel4, BorderLayout.CENTER);
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		panelLevel4.add(placeHolder, BorderLayout.NORTH);
		
		panelLevel3 = panelLevel4;
		panelLevel4 = new JPanel(new BorderLayout());
		panelLevel3.add(panelLevel4, BorderLayout.CENTER);
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.NORTH);
		
		placeHolder = new JLabel("Data início*");
		placeHolder.setToolTipText("O preenchimento deste campo é obrigatório");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getHeight() / 64 + (int) (preferredSize.getWidth() / 3)), 
			(int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		placeHolder = new JLabel("Data final*");
		placeHolder.setToolTipText("O preenchimento deste campo é obrigatório");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel3 = panelLevel4;
		panelLevel4 = new JPanel(new BorderLayout());
		panelLevel3.add(panelLevel4, BorderLayout.CENTER);
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.NORTH);
		
		JTextField textFieldDataIncio = new JTextField();
		textFieldDataIncio.setText((new SimpleDateFormat("dd/MM/yyyy")).format(new Date()));
		float[] hsbColor = Color.RGBtoHSB(184, 207, 229, null);
		Color innerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		hsbColor = Color.RGBtoHSB(122, 138, 153, null);
		Color outerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		Border compound = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(outerBorderColor), BorderFactory.createLineBorder(innerBorderColor));
		textFieldDataIncio.setBorder(compound);
		textFieldDataIncio.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldDataIncio, BorderLayout.WEST);
		
		panelLevel4 = panelLevel5;
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + (int) (preferredSize.getWidth() / 64) - 
			(int) (preferredSize.getWidth() / 4) - 5, (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		JTextField textFieldDataFinal = new JTextField();
		textFieldDataFinal.setText((new SimpleDateFormat("dd/MM/yyyy")).format(new Date()));
		textFieldDataFinal.setBorder(compound);
		textFieldDataFinal.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldDataFinal, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4) + 
			(int) (preferredSize.getWidth() / 64), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.EAST);
		
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.SOUTH);
		panelLevel4 = new JPanel(new BorderLayout());
		panelLevel3.add(panelLevel4, BorderLayout.SOUTH);
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.NORTH);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.SOUTH);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.EAST);
		
		cancelButton = new JButton("Voltar");
		cancelButton.setBackground(Color.white);
		cancelButton.setForeground(Color.black);
		cancelButton.addMouseListener(systemInterface.new HandlerHomeButton());
		cancelButton.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 16) - 
			(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(cancelButton, BorderLayout.EAST);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 64),  (int) (preferredSize.getHeight() / 16) - 
				(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		alterButton = new JButton("Gerar");
		hsbColor = Color.RGBtoHSB(51, 122, 183, null); 
		alterButton.setBackground(Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]));
		alterButton.setForeground(Color.white);
		alterButton.addMouseListener(new HandlerHistoricoLotes(systemInterface, comboBoxFornecedores, fornecedores, textFieldDataIncio, textFieldDataFinal));
		alterButton.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 16) - 
			(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(alterButton, BorderLayout.WEST);
		
		return panelLevel0;
	}
	
	public JPanel relatorioHistoricoVendas() {
		JPanel panelLevel0 = new JPanel(new BorderLayout());
		Common.makeLateralBorders(panelLevel0, preferredSize, defaultBorder);
		
		JPanel panelLevel1 = new JPanel(new BorderLayout());
		panelLevel0.add(panelLevel1, BorderLayout.CENTER);
		
		JPanel panelLevel2 = new JPanel(new BorderLayout());
		panelLevel1.add(panelLevel2, BorderLayout.NORTH);
		
		JLabel placeHolder = new JLabel("");
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		placeHolder.setBorder(defaultBorder);
		panelLevel2.add(placeHolder, BorderLayout.NORTH);
		
		placeHolder = new JLabel("Histórico de Vendas");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 20));
		panelLevel2.add(placeHolder, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 16)));
		placeHolder.setBorder(defaultBorder);
		panelLevel2.add(placeHolder, BorderLayout.SOUTH);
		
		panelLevel2 = new JPanel(new BorderLayout());
		panelLevel1.add(panelLevel2, BorderLayout.CENTER);
		
		JPanel panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.NORTH);
		
		placeHolder = new JLabel("Cliente");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getHeight() / 64 + (int) (preferredSize.getWidth() / 3)), 
			(int) (preferredSize.getHeight() / 32)));
		panelLevel3.add(placeHolder, BorderLayout.WEST);
		
		placeHolder = new JLabel("Data de Inclusão"); 
		placeHolder.setBorder(defaultBorder);
		placeHolder.setForeground(placeHolder.getBackground());
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		panelLevel3.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.CENTER);
		JPanel panelLevel4 = new JPanel(new BorderLayout());
		panelLevel3.add(panelLevel4, BorderLayout.NORTH);
		
		String lista[] = null;
		List<Cliente> clientes = null;
		
		try {
			ClienteDao clienteDao = new ClienteDao(systemInterface.getSystemInterfaceDatabaseURL());
			clientes = clienteDao.getAll();
		} catch (SQLException e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de clientes!");
		} finally {
			if(clientes == null || clientes.size() == 0) {
				clientes = new ArrayList<Cliente>();
				clientes.add(new Cliente("Nenhum valor encontrado"));
				String message = clientes == null ? "Houve um erro ao recuperar a lista de clientes!" : "Nenhum cliente encontrado!";
				systemInterface.getSystemInterfaceLabelStatus().setText(message);
			}
			boolean isNull = clientes.get(0).getClienteNome().equalsIgnoreCase("Nenhum valor encontrado");
			lista = new String[isNull ? clientes.size() : clientes.size() + 1];
			
			if(!isNull) {
				lista[0] = "Todos os clientes";
			}
			for(Cliente c : clientes)
				lista[clientes.indexOf(c) + (isNull ? 0 : 1)] = c.getClienteNome();
		}
		JComboBox comboBoxClientes = new JComboBox(lista);
		comboBoxClientes.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		comboBoxClientes.setBackground(Color.white);
		comboBoxClientes.setEditable(false);
		comboBoxClientes.setSelectedIndex(0);
		panelLevel4.add(comboBoxClientes, BorderLayout.WEST);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				comboBoxClientes.requestFocusInWindow();
		    }
		});
		
		JPanel panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4) + 
			(int) (preferredSize.getWidth() / 64), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.EAST);
		
		panelLevel4 = new JPanel(new BorderLayout());
		panelLevel3.add(panelLevel4, BorderLayout.CENTER);
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		panelLevel4.add(placeHolder, BorderLayout.NORTH);
		
		panelLevel3 = panelLevel4;
		panelLevel4 = new JPanel(new BorderLayout());
		panelLevel3.add(panelLevel4, BorderLayout.CENTER);
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.NORTH);
		
		placeHolder = new JLabel("Data início*");
		placeHolder.setToolTipText("O preenchimento deste campo é obrigatório");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getHeight() / 64 + (int) (preferredSize.getWidth() / 3)), 
			(int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		placeHolder = new JLabel("Data final*");
		placeHolder.setToolTipText("O preenchimento deste campo é obrigatório");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel3 = panelLevel4;
		panelLevel4 = new JPanel(new BorderLayout());
		panelLevel3.add(panelLevel4, BorderLayout.CENTER);
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.NORTH);
		
		JTextField textFieldDataIncio = new JTextField();
		textFieldDataIncio.setText((new SimpleDateFormat("dd/MM/yyyy")).format(new Date()));
		float[] hsbColor = Color.RGBtoHSB(184, 207, 229, null);
		Color innerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		hsbColor = Color.RGBtoHSB(122, 138, 153, null);
		Color outerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		Border compound = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(outerBorderColor), BorderFactory.createLineBorder(innerBorderColor));
		textFieldDataIncio.setBorder(compound);
		textFieldDataIncio.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldDataIncio, BorderLayout.WEST);
		
		panelLevel4 = panelLevel5;
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + (int) (preferredSize.getWidth() / 64) - 
			(int) (preferredSize.getWidth() / 4) - 5, (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		JTextField textFieldDataFinal = new JTextField();
		textFieldDataFinal.setText((new SimpleDateFormat("dd/MM/yyyy")).format(new Date()));
		textFieldDataFinal.setBorder(compound);
		textFieldDataFinal.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldDataFinal, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4) + 
			(int) (preferredSize.getWidth() / 64), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.EAST);
		
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.SOUTH);
		panelLevel4 = new JPanel(new BorderLayout());
		panelLevel3.add(panelLevel4, BorderLayout.SOUTH);
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.NORTH);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.SOUTH);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.EAST);
		
		cancelButton = new JButton("Voltar");
		cancelButton.setBackground(Color.white);
		cancelButton.setForeground(Color.black);
		cancelButton.addMouseListener(systemInterface.new HandlerHomeButton());
		cancelButton.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 16) - 
			(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(cancelButton, BorderLayout.EAST);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 64),  (int) (preferredSize.getHeight() / 16) - 
				(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		alterButton = new JButton("Gerar");
		hsbColor = Color.RGBtoHSB(51, 122, 183, null); 
		alterButton.setBackground(Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]));
		alterButton.setForeground(Color.white);
		alterButton.addMouseListener(new HandlerHistoricoVendas(systemInterface, comboBoxClientes, clientes, textFieldDataIncio, textFieldDataFinal));
		alterButton.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 16) - 
			(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(alterButton, BorderLayout.WEST);
		
		return panelLevel0;
	}
	
	public static void geraComprovanteVenda(int vendaId) {
		
		List<Operacao> vendas = new ArrayList<Operacao>();
		List<Venda> listaVendas = null;
		try {
			Venda vendaTemp = new VendaDao(systemInterface.getSystemInterfaceDatabaseURL()).getById(vendaId);
			ArrayList<Venda> vendasTemp = new ArrayList<Venda>();
			vendasTemp.add(vendaTemp);
			listaVendas = vendasTemp;
		} catch (SQLException e1) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de vendas!");
		}
		DecimalFormat decimal = new DecimalFormat("0.00");
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						
		for(Venda venda : listaVendas) {
			Cliente cliente = null;
			try {
				cliente = new ClienteDao(systemInterface.getSystemInterfaceDatabaseURL()).getById(venda.getVendaCliente());
			} catch (SQLException e1) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de vendas!");
			}
			Operacao vendaOperacao = new Operacao(
					venda.getVendaId(), 
					cliente.getClienteNome(), 
					df.format(venda.getVendaData()), 
					"R$ " + decimal.format(venda.getVendaValor())
			);
			List<ProdutoOperacao> vendaProdutos = new ArrayList<ProdutoOperacao>();
			List<ItemVenda> itens = null;
			try {
				itens = new ItemVendaDao(systemInterface.getSystemInterfaceDatabaseURL()).getForValue("venda", String.valueOf(venda.getVendaId()));
			} catch (SQLException e1) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de vendas!");
			}
			
			for(ItemVenda item : itens) {
				Produto produto = null;
				try {
					produto = new ProdutoDao(systemInterface.getSystemInterfaceDatabaseURL()).getById(item.getProduto());
				} catch (SQLException e1) {
					systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de vendas!");
				}
				
				vendaProdutos.add(new ProdutoOperacao(
						produto.getProdutoId(), 
						produto.getProdutoDesc(), 
						item.getQuantidade(), 
						"R$ " + decimal.format(produto.getProdutoPreco()))
				);
			}
			vendaOperacao.setProdutosOperacao(vendaProdutos);
			vendas.add(vendaOperacao);
		}
		
		try {
			Relatorios.gerarRelatorioVendas(vendas);
		} catch (JRException e1) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de vendas!");
		} catch (IOException e1) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de vendas!");
		}
	}
	
	protected static class HandlerConferenciaEstoque implements MouseListener {
		
		private SystemInterface systemInterface;
		private JComboBox source;
		private List<Categoria> categorias;
		
		public HandlerConferenciaEstoque(SystemInterface systemInterface, JComboBox source, List<Categoria> categorias) {
			this.systemInterface = systemInterface;
			this.source = source;
			this.categorias = categorias;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(!source.getSelectedItem().toString().equalsIgnoreCase("Nenhum valor encontrado")) {
				List<Estoque> listaEstoque = null;
				
				if(source.getSelectedItem().toString().equalsIgnoreCase("Todas as categorias")) {
					try {
						listaEstoque = new EstoqueDao(systemInterface.getSystemInterfaceDatabaseURL()).getAll();
					} catch (SQLException e1) {
						systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de estoque!");
					}
				} else {
					try {
						listaEstoque = new EstoqueDao(systemInterface.getSystemInterfaceDatabaseURL()).getForValue("estoque_categoria_id", 
								String.valueOf(categorias.get(source.getSelectedIndex() - 1).getCategoriaId()));
					} catch (SQLException e1) {
						systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de estoque!");
					}
				}
				List<EstoqueReport> lista = new ArrayList<EstoqueReport>();
				
				for(Estoque estoque : listaEstoque) {
					Produto prod = null;
					try {
						prod = new ProdutoDao(systemInterface.getSystemInterfaceDatabaseURL()).getById(estoque.getEstoque_produto_id());
					} catch (SQLException e1) {
						systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de estoque!");
					}
					Categoria categ = null;
					try {
						categ = new CategoriaDao(systemInterface.getSystemInterfaceDatabaseURL()).getById(prod.getProdutoCategoria());
					} catch (SQLException e1) {
						systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de estoque!");
					}			
					String ativo = prod.getProdutoAtivo() == 1 ? "Sim": "Não";
					Double total = estoque.getEstoque_produto_quantidade() * prod.getProdutoPreco();
					DecimalFormat decimal = new DecimalFormat("0.00");
					
					lista.add(new EstoqueReport(
							prod.getProdutoDesc(), 
							categ.getCategoriaDesc(), 
							estoque.getEstoque_produto_quantidade(), 
							"R$ " + decimal.format(prod.getProdutoPreco()), 
							"R$ " + decimal.format(total), 
							ativo)
					);
				}
				
				try {
					Relatorios.gerarRelatorioEstoque(lista);
				} catch (JRException e1) {
					systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de estoque!");
				} catch (IOException e1) {
					systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de estoque!");
				}
			} else {
				systemInterface.getSystemInterfaceLabelStatus().setText("Não há catagoria válida para a busca!");
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Gera o relatório de conferência de estoque com a categoria selecionada");
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setText(systemInterface.getSystemInterfaceStatusMessage());
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			mouseEntered(e);
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			mouseExited(e);
		}
	}
	
	protected static class HandlerHistoricoLotes implements MouseListener {
		
		private SystemInterface systemInterface;
		private JComboBox source;
		private List<Fornecedor> fornecedores;
		private JTextField dataInicio;
		private JTextField dataFinal;
		
		public HandlerHistoricoLotes(SystemInterface systemInterface, JComboBox source, List<Fornecedor> fornecedores, JTextField dataInicio, JTextField dataFinal) {
			this.systemInterface = systemInterface;
			this.source = source;
			this.fornecedores = fornecedores;
			this.dataInicio = dataInicio;
			this.dataFinal = dataFinal;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			dataInicio.setBackground(Color.white);
			dataFinal.setBackground(Color.white);
			if(!source.getSelectedItem().toString().equalsIgnoreCase("Nenhum valor encontrado") && 
					!dataInicio.getText().trim().equalsIgnoreCase("") && Common.isValidDate(dataInicio.getText()) && 
					!dataFinal.getText().trim().equalsIgnoreCase("") && Common.isValidDate(dataFinal.getText())) {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				try {
					Date dateInicio = df.parse(dataInicio.getText().trim());
					Date dateFinal = df.parse(dataFinal.getText().trim());
					
					if(dateFinal.getTime() >= dateInicio.getTime()) {
						List<Operacao> lotes = new ArrayList<Operacao>();
						List<Lote> listaLotes = null;
						try {
							if(source.getSelectedItem().toString().equalsIgnoreCase("Todos os fornecedores")) {
								listaLotes = new LoteDao(systemInterface.getSystemInterfaceDatabaseURL()).getAll();
							} else {
								listaLotes = new LoteDao(systemInterface.getSystemInterfaceDatabaseURL()).getForValue("lote_fornecedor", 
										String.valueOf(fornecedores.get(source.getSelectedIndex() - 1).getFornecedorId()));
							}
							ArrayList<Lote> lotesMenoresDataInicio = new ArrayList<Lote>();
							ArrayList<Lote> lotesMaioresDataFinal = new ArrayList<Lote>();
							
							for(Lote l : listaLotes) {
								if(l.getLoteData().getTime() < dateInicio.getTime()) {
									lotesMenoresDataInicio.add(l);
								}
								if(l.getLoteData().getTime() > dateFinal.getTime()) {
									lotesMaioresDataFinal.add(l);
								}
							}
							listaLotes.removeAll(lotesMenoresDataInicio);
							listaLotes.removeAll(lotesMaioresDataFinal);
						} catch (SQLException e1) {
							systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de lotes!");
						}
						DecimalFormat decimal = new DecimalFormat("0.00");
						
						for(Lote lote : listaLotes) {
							Fornecedor fornecedor = null;
							try {
								fornecedor = new FornecedorDao(systemInterface.getSystemInterfaceDatabaseURL()).getById(lote.getLoteFornecedor());
							} catch (SQLException e1) {
								systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de lotes!");
							}
							Operacao loteOperacao = new Operacao(
									lote.getLoteId(), 
									fornecedor.getFornecedorNome(), 
									df.format(lote.getLoteData()), 
									"R$ " + decimal.format(lote.getLoteValor())
							);
							List<ProdutoOperacao> loteProdutos = new ArrayList<ProdutoOperacao>();
							List<ItemLote> itens = null;
							try {
								itens = new ItemLoteProdutoDao(systemInterface.getSystemInterfaceDatabaseURL()).getForValue("lote", String.valueOf(lote.getLoteId()));
							} catch (SQLException e1) {
								systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de lotes!");
							}
							
							for(ItemLote item : itens) {
								Produto produto = null;
								try {
									produto = new ProdutoDao(systemInterface.getSystemInterfaceDatabaseURL()).getById(item.getProduto());
								} catch (SQLException e1) {
									systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de lotes!");
								}
								
								loteProdutos.add(new ProdutoOperacao(
										produto.getProdutoId(), 
										produto.getProdutoDesc(), 
										item.getQuantidade(), 
										"R$ " + decimal.format(produto.getProdutoCusto()))
								);
							}
							loteOperacao.setProdutosOperacao(loteProdutos);
							lotes.add(loteOperacao);
						}
						
						try {
							Relatorios.gerarRelatorioLotes(lotes);
						} catch (JRException e1) {
							systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de lotes!");
						} catch (IOException e1) {
							systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de lotes!");
						}
					} else {
						systemInterface.getSystemInterfaceLabelStatus().setText("A data final deve ser maior que a data inicial!");
						dataInicio.setBackground(Color.yellow);
						dataFinal.setBackground(Color.yellow);
					}
				} catch (ParseException e1) {
					systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao converter as datas!");
					dataInicio.setBackground(Color.yellow);
					dataFinal.setBackground(Color.yellow);
				}
			} else if(dataInicio.getText().trim().equalsIgnoreCase("")) {
				systemInterface.getSystemInterfaceLabelStatus().setText("É necessário informar a data de início!");
				dataInicio.setBackground(Color.yellow);
				dataInicio.requestFocus();
			} else if(!Common.isValidDate(dataInicio.getText())) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Data de início informada é inválida!");
				dataInicio.setBackground(Color.yellow);
				dataInicio.requestFocus();
			} else if(dataFinal.getText().trim().equalsIgnoreCase("")) {
				systemInterface.getSystemInterfaceLabelStatus().setText("É necessário informar a data final!");
				dataFinal.setBackground(Color.yellow);
				dataFinal.requestFocus();
			} else if(!Common.isValidDate(dataFinal.getText())) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Data final informada é inválida!");
				dataFinal.setBackground(Color.yellow);
				dataFinal.requestFocus();
			} else {
				systemInterface.getSystemInterfaceLabelStatus().setText("Não há fornecedor válido para a busca!");
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Gera o relatório de histórico de lotes com o fornecedor selecionado");
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setText(systemInterface.getSystemInterfaceStatusMessage());
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			mouseEntered(e);
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			mouseExited(e);
		}
	}
	
	protected static class HandlerHistoricoVendas implements MouseListener {
		
		private SystemInterface systemInterface;
		private JComboBox source;
		private List<Cliente> clientes;
		private JTextField dataInicio;
		private JTextField dataFinal;
		
		public HandlerHistoricoVendas(SystemInterface systemInterface, JComboBox source, List<Cliente> clientes, JTextField dataInicio, JTextField dataFinal) {
			this.systemInterface = systemInterface;
			this.source = source;
			this.clientes = clientes;
			this.dataInicio = dataInicio;
			this.dataFinal = dataFinal;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			dataInicio.setBackground(Color.white);
			dataFinal.setBackground(Color.white);
			if(!source.getSelectedItem().toString().equalsIgnoreCase("Nenhum valor encontrado") && 
					!dataInicio.getText().trim().equalsIgnoreCase("") && Common.isValidDate(dataInicio.getText()) && 
					!dataFinal.getText().trim().equalsIgnoreCase("") && Common.isValidDate(dataFinal.getText())) {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				try {
					Date dateInicio = df.parse(dataInicio.getText().trim());
					Date dateFinal = df.parse(dataFinal.getText().trim());
					
					if(dateFinal.getTime() >= dateInicio.getTime()) {
						List<Operacao> vendas = new ArrayList<Operacao>();
						List<Venda> listaVendas = null;
						try {
							if(source.getSelectedItem().toString().equalsIgnoreCase("Todos os clientes")) {
								listaVendas = new VendaDao(systemInterface.getSystemInterfaceDatabaseURL()).getAll();
							} else {
								listaVendas = new VendaDao(systemInterface.getSystemInterfaceDatabaseURL()).getForValue("venda_cliente", 
										String.valueOf(clientes.get(source.getSelectedIndex() - 1).getClienteId()));
							}
							ArrayList<Venda> vendasMenoresDataInicio = new ArrayList<Venda>();
							ArrayList<Venda> vendasMaioresDataFinal = new ArrayList<Venda>();
							
							for(Venda v : listaVendas) {
								if(v.getVendaData().getTime() < dateInicio.getTime()) {
									vendasMenoresDataInicio.add(v);
								}
								if(v.getVendaData().getTime() > dateFinal.getTime()) {
									vendasMaioresDataFinal.add(v);
								}
							}
							listaVendas.removeAll(vendasMenoresDataInicio);
							listaVendas.removeAll(vendasMaioresDataFinal);
						} catch (SQLException e1) {
							systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de vendas!");
						}
						DecimalFormat decimal = new DecimalFormat("0.00");
						
						for(Venda venda : listaVendas) {
							Cliente cliente = null;
							try {
								cliente = new ClienteDao(systemInterface.getSystemInterfaceDatabaseURL()).getById(venda.getVendaCliente());
							} catch (SQLException e1) {
								systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de vendas!");
							}
							Operacao vendaOperacao = new Operacao(
									venda.getVendaId(), 
									cliente.getClienteNome(), 
									df.format(venda.getVendaData()), 
									"R$ " + decimal.format(venda.getVendaValor())
							);
							List<ProdutoOperacao> vendaProdutos = new ArrayList<ProdutoOperacao>();
							List<ItemVenda> itens = null;
							try {
								itens = new ItemVendaDao(systemInterface.getSystemInterfaceDatabaseURL()).getForValue("venda", String.valueOf(venda.getVendaId()));
							} catch (SQLException e1) {
								systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de vendas!");
							}
							
							for(ItemVenda item : itens) {
								Produto produto = null;
								try {
									produto = new ProdutoDao(systemInterface.getSystemInterfaceDatabaseURL()).getById(item.getProduto());
								} catch (SQLException e1) {
									systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de vendas!");
								}
								
								vendaProdutos.add(new ProdutoOperacao(
										produto.getProdutoId(), 
										produto.getProdutoDesc(), 
										item.getQuantidade(), 
										"R$ " + decimal.format(produto.getProdutoPreco()))
								);
							}
							vendaOperacao.setProdutosOperacao(vendaProdutos);
							vendas.add(vendaOperacao);
						}
						
						try {
							Relatorios.gerarRelatorioVendas(vendas);
						} catch (JRException e1) {
							systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de vendas!");
						} catch (IOException e1) {
							systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de vendas!");
						}
					} else {
						systemInterface.getSystemInterfaceLabelStatus().setText("A data final deve ser maior que a data inicial!");
						dataInicio.setBackground(Color.yellow);
						dataFinal.setBackground(Color.yellow);
					}
				} catch (ParseException e1) {
					systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao converter as datas!");
					dataInicio.setBackground(Color.yellow);
					dataFinal.setBackground(Color.yellow);
				}
			} else if(dataInicio.getText().trim().equalsIgnoreCase("")) {
				systemInterface.getSystemInterfaceLabelStatus().setText("É necessário informar a data de início!");
				dataInicio.setBackground(Color.yellow);
				dataInicio.requestFocus();
			} else if(!Common.isValidDate(dataInicio.getText())) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Data de início informada é inválida!");
				dataInicio.setBackground(Color.yellow);
				dataInicio.requestFocus();
			} else if(dataFinal.getText().trim().equalsIgnoreCase("")) {
				systemInterface.getSystemInterfaceLabelStatus().setText("É necessário informar a data final!");
				dataFinal.setBackground(Color.yellow);
				dataFinal.requestFocus();
			} else if(!Common.isValidDate(dataFinal.getText())) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Data final informada é inválida!");
				dataFinal.setBackground(Color.yellow);
				dataFinal.requestFocus();
			} else {
				systemInterface.getSystemInterfaceLabelStatus().setText("Não há cliente válido para a busca!");
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Gera o relatório de histórico de vendas com o cliente selecionado");
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setText(systemInterface.getSystemInterfaceStatusMessage());
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			mouseEntered(e);
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			mouseExited(e);
		}
	}
}
