package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;

import main.Main;
import repositorio.CategoriaDao;
import repositorio.ProdutoDao;
import classes.Categoria;
import classes.Produto;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ModuloProduto {
	
	private static Dimension preferredSize;
	private static Border defaultBorder;
	private static List<Categoria> categorias;
	private static List<Produto> produtos;
	private static CategoriaDao categoriaDao;
	private static ProdutoDao produtoDao;
	private static Produto produto;
	private static JTextField textFieldProdutoNome;
	private static JTextField textFieldProdutoCusto;
	private static JTextField textFieldProdutoLucro;
	private static JTextField textFieldProdutoPreco;
	private static JComboBox comboBoxCategorias;
	private static JComboBox comboBoxProdutos;
	private static JComboBox comboBoxProdutoAtivo;
	private static JButton alterButton;
	private static JButton cancelButton;
	private static Confirmation conf;
	private static SystemInterface systemInterface;
	
	public ModuloProduto(SystemInterface systemInterface) {
		ModuloProduto.systemInterface = systemInterface;
		preferredSize = systemInterface.getSystemInterfaceDimension();
		defaultBorder = Main.isBrunoTesting ? BorderFactory.createRaisedBevelBorder() : BorderFactory.createEmptyBorder();
		conf = new Confirmation(systemInterface, defaultBorder, this);
	}
	
	public JPanel cadastraProduto() {
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
		
		placeHolder = new JLabel("Cadastro de Produtos");
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
		
		placeHolder = new JLabel("Produto*");
		placeHolder.setToolTipText("O preenchimento deste campo é obrigatório");
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
		
		textFieldProdutoNome = new JTextField();
		float[] hsbColor = Color.RGBtoHSB(184, 207, 229, null);
		Color innerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		hsbColor = Color.RGBtoHSB(122, 138, 153, null);
		Color outerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		Border compound = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(outerBorderColor), BorderFactory.createLineBorder(innerBorderColor));
		textFieldProdutoNome.setBorder(compound);
		textFieldProdutoNome.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel4.add(textFieldProdutoNome, BorderLayout.WEST);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				textFieldProdutoNome.requestFocusInWindow();
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
		
		placeHolder = new JLabel("Categoria*");
		placeHolder.setToolTipText("O preenchimento deste campo é obrigatório");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getHeight() / 64 + (int) (preferredSize.getWidth() / 3)), 
			(int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		placeHolder = new JLabel("Custo* (R$)");
		placeHolder.setToolTipText("O preenchimento deste campo é obrigatório");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel3 = panelLevel4;
		panelLevel4 = new JPanel(new BorderLayout());
		panelLevel3.add(panelLevel4, BorderLayout.CENTER);
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.NORTH);
		
		String lista[] = null;
		categorias = null;
		
		try {
			categoriaDao = new CategoriaDao(systemInterface.getSystemInterfaceDatabaseURL());
			categorias = categoriaDao.getForValue("ativo", "1");
		} catch (SQLException e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de categorias!");
		} finally {
			if(categorias == null || categorias.size() == 0) {
				categorias = new ArrayList<Categoria>();
				categorias.add(new Categoria("Nenhum valor encontrado"));
				String message = categorias == null ? "Houve um erro ao recuperar a lista de categorias!" : "Nenhuma categoria encontrada!";
				systemInterface.getSystemInterfaceLabelStatus().setText(message);
			}
			lista = new String[categorias.size()];
			for(Categoria c : categorias)
				lista[categorias.indexOf(c)] = c.getCategoriaDesc();
		}
		comboBoxCategorias = new JComboBox(lista);
		comboBoxCategorias.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		comboBoxCategorias.setBackground(Color.white);
		comboBoxCategorias.setEditable(false);
		comboBoxCategorias.setSelectedIndex(0);
		panelLevel5.add(comboBoxCategorias, BorderLayout.WEST);
		
		panelLevel4 = panelLevel5;
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + (int) (preferredSize.getWidth() / 64) - 
			(int) (preferredSize.getWidth() / 4) - 5, (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		textFieldProdutoCusto = new JTextField();
		textFieldProdutoCusto.setBorder(compound);
		textFieldProdutoCusto.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldProdutoCusto, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4) + 
			(int) (preferredSize.getWidth() / 64), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.EAST);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.SOUTH);
		panelLevel5.add(placeHolder, BorderLayout.NORTH);
		
		panelLevel4 = panelLevel5;
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.SOUTH);
		panelLevel4 = panelLevel5;
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		panelLevel4 = panelLevel5;
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.NORTH);
		
		placeHolder = new JLabel("Lucro Desejado* (%)");
		placeHolder.setToolTipText("O preenchimento deste campo é obrigatório se o preço de revenda não for informado");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getHeight() / 64 + (int) (preferredSize.getWidth() / 3)), 
			(int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		placeHolder = new JLabel("Preço de Revenda* (R$)");
		placeHolder.setToolTipText("O preenchimento deste campo é obrigatório se o lucro desejado não for informado");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.SOUTH);
		
		textFieldProdutoLucro = new JTextField();
		textFieldProdutoLucro.setBorder(compound);
		textFieldProdutoLucro.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldProdutoLucro, BorderLayout.WEST);
		
		panelLevel4 = panelLevel5;
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + (int) (preferredSize.getWidth() / 64) - 
			(int) (preferredSize.getWidth() / 4) - 5, (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		textFieldProdutoPreco = new JTextField();
		textFieldProdutoPreco.setBorder(compound);
		textFieldProdutoPreco.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldProdutoPreco, BorderLayout.CENTER);
		
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
		
		alterButton = new JButton("Salvar");
		hsbColor = Color.RGBtoHSB(51, 122, 183, null); 
		alterButton.setBackground(Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]));
		alterButton.setForeground(Color.white);
		alterButton.addMouseListener(new HandlerAddProduto(systemInterface));
		alterButton.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 16) - 
			(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(alterButton, BorderLayout.WEST);
		
		return panelLevel0;
	}
	
	public JPanel consultaProduto(Produto produtoSelecionado) {
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
		
		placeHolder = new JLabel("Consulta de Produtos");
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
		
		placeHolder = new JLabel("Produto*");
		placeHolder.setToolTipText("O preenchimento deste campo é obrigatório");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getHeight() / 64 + (int) (preferredSize.getWidth() / 3)), 
			(int) (preferredSize.getHeight() / 32)));
		panelLevel3.add(placeHolder, BorderLayout.WEST);
		
		placeHolder = new JLabel("Ativo");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		panelLevel3.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.CENTER);
		JPanel panelLevel4 = new JPanel(new BorderLayout());
		panelLevel3.add(panelLevel4, BorderLayout.NORTH);
		
		String lista[] = null;
		categorias = null;
		
		try {
			categoriaDao = new CategoriaDao(systemInterface.getSystemInterfaceDatabaseURL());
			categorias = categoriaDao.getForValue("ativo", "1");
		} catch (SQLException e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de categorias!");
		} finally {
			if(categorias == null || categorias.size() == 0) {
				categorias = new ArrayList<Categoria>();
				categorias.add(new Categoria("Nenhum valor encontrado"));
				String message = categorias == null ? "Houve um erro ao recuperar a lista de categorias!" : "Nenhuma categoria encontrada!";
				systemInterface.getSystemInterfaceLabelStatus().setText(message);
			}
			lista = new String[categorias.size()];
			
			for(Categoria c : categorias)
				lista[categorias.indexOf(c)] = c.getCategoriaDesc();
		}
		comboBoxCategorias = new JComboBox(lista);
		comboBoxCategorias.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		comboBoxCategorias.setBackground(Color.white);
		comboBoxCategorias.setEditable(false);
		comboBoxCategorias.setSelectedIndex(produtoSelecionado == null ? 0 : buscaCategoria(categorias, produto.getProdutoCategoria()));
		comboBoxCategorias.addActionListener(new HandlerComboBox(this, produtoSelecionado));
		
		criaListaProdutos(produtoSelecionado);
		panelLevel4.add(comboBoxProdutos, BorderLayout.WEST);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				comboBoxProdutos.requestFocusInWindow();
		    }
		});
		
		JPanel panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + (int) (preferredSize.getWidth() / 64) - 
			(int) (preferredSize.getWidth() / 4) - 5, (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		completaCampos(produtos.get(comboBoxProdutos.getSelectedIndex()));
		comboBoxProdutoAtivo.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(comboBoxProdutoAtivo, BorderLayout.CENTER);
		
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
		
		placeHolder = new JLabel("Categoria*");
		placeHolder.setToolTipText("O preenchimento deste campo é obrigatório");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getHeight() / 64 + (int) (preferredSize.getWidth() / 3)), 
			(int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		placeHolder = new JLabel("Custo* (R$)");
		placeHolder.setToolTipText("O preenchimento deste campo é obrigatório");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel3 = panelLevel4;
		panelLevel4 = new JPanel(new BorderLayout());
		panelLevel3.add(panelLevel4, BorderLayout.CENTER);
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.NORTH);
		panelLevel5.add(comboBoxCategorias, BorderLayout.WEST);
		
		panelLevel4 = panelLevel5;
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + (int) (preferredSize.getWidth() / 64) - 
			(int) (preferredSize.getWidth() / 4) - 5, (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		float[] hsbColor = Color.RGBtoHSB(184, 207, 229, null);
		Color innerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		hsbColor = Color.RGBtoHSB(122, 138, 153, null);
		Color outerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		Border compound = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(outerBorderColor), BorderFactory.createLineBorder(innerBorderColor));
		textFieldProdutoCusto.setBorder(compound);
		textFieldProdutoCusto.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldProdutoCusto, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4) + 
			(int) (preferredSize.getWidth() / 64), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.EAST);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.SOUTH);
		panelLevel5.add(placeHolder, BorderLayout.NORTH);
		
		panelLevel4 = panelLevel5;
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.SOUTH);
		panelLevel4 = panelLevel5;
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		panelLevel4 = panelLevel5;
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.NORTH);
		
		placeHolder = new JLabel("Lucro Desejado* (%)");
		placeHolder.setToolTipText("O preenchimento deste campo é obrigatório se o preço de revenda não for informado");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getHeight() / 64 + (int) (preferredSize.getWidth() / 3)), 
			(int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		placeHolder = new JLabel("Preço de Revenda* (R$)");
		placeHolder.setToolTipText("O preenchimento deste campo é obrigatório se o lucro desejado não for informado");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.SOUTH);
		
		textFieldProdutoLucro.setBorder(compound);
		textFieldProdutoLucro.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldProdutoLucro, BorderLayout.WEST);
		
		panelLevel4 = panelLevel5;
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + (int) (preferredSize.getWidth() / 64) - 
			(int) (preferredSize.getWidth() / 4) - 5, (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		textFieldProdutoPreco.setBorder(compound);
		textFieldProdutoPreco.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldProdutoPreco, BorderLayout.CENTER);
		
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
		
		alterButton = new JButton("Editar");
		hsbColor = Color.RGBtoHSB(51, 122, 183, null); 
		alterButton.setBackground(Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]));
		alterButton.setForeground(Color.white);
		alterButton.addMouseListener(new HandlerAlterProduto(systemInterface));
		alterButton.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 16) - 
			(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(alterButton, BorderLayout.WEST);
		
		return panelLevel0;
	}
	
	private static int buscaCategoria(List<Categoria> categorias2, int categoriaId) {
		for(Categoria c : categorias2) {
			if(c.getCategoriaId() == categoriaId)
				return categorias2.indexOf(c);
		}
		return 0;
	}
	
	private static boolean verificaProduto(String novoProduto) {
		try {
			produtoDao = new ProdutoDao(systemInterface.getSystemInterfaceDatabaseURL());
			produtos = produtoDao.getAll();
			
			for(Produto p : produtos) {
				if(p.getProdutoDesc().equalsIgnoreCase(novoProduto))
					return true;
			}
		} catch (SQLException e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve uma falha ao buscar os dados dos produtos no banco!");
		}
		return false;
	}
	
	private static boolean verificaProduto(String novoProduto, Produto produto) {
		try {
			produtoDao = new ProdutoDao(systemInterface.getSystemInterfaceDatabaseURL());
			produtos = produtoDao.getAll();
			produtos.remove(produto);
			
			for(Produto p : produtos) {
				if(p.getProdutoDesc().equalsIgnoreCase(novoProduto))
					return true;
			}
		} catch (SQLException e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve uma falha ao buscar os dados dos produtos no banco!");
		}
		return false;
	}
	
	static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
	
	static boolean checkValues(String custo, String lucro, String preco) {
		if(!isDouble(custo))
			return false;
		if(lucro.equalsIgnoreCase("") && preco.equalsIgnoreCase(""))
			return false;
		if(!lucro.equalsIgnoreCase("") && !isDouble(lucro))
			return false;
		if(!preco.equalsIgnoreCase("") && !isDouble(preco))
			return false;
		if(isDouble(custo) && isDouble(lucro) && isDouble(preco))
			return (Double.parseDouble(custo) + (Double.parseDouble(custo) * Double.parseDouble(lucro) / 100)) == Double.parseDouble(preco);
		return true;
	}
	
	public void incluiProduto(Boolean choice) {
		if(choice) {
			produto = new Produto(textFieldProdutoNome.getText().trim(), categorias.get(comboBoxCategorias.getSelectedIndex()).getCategoriaId(), 
					Double.parseDouble(textFieldProdutoCusto.getText().trim().replace(",", ".")), Double.parseDouble(textFieldProdutoPreco.getText().trim().replace(",", ".")), 
					Double.parseDouble(textFieldProdutoLucro.getText().trim().replace(",", ".")) / 100);
			try {
				produtoDao = new ProdutoDao(systemInterface.getSystemInterfaceDatabaseURL());
				produtoDao.insert(produto);
				
				systemInterface.clearSystemInterface(!Main.isBrunoTesting);
				systemInterface.getSystemInterfacePanelMain().add(systemInterface.getSystemInterfaceProdutos().cadastraProduto());
				
				systemInterface.getSystemInterfaceLabelStatus().setText("Produto cadastrado com sucesso!");
			} catch (SQLException e1) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Houve uma falha ao inserir os dados do produto no banco!");
			}
		}
	}
	
	public void atualizaProduto(Boolean choice) {
		if(choice) {
				try {
				produtoDao = new ProdutoDao(systemInterface.getSystemInterfaceDatabaseURL());
				produtoDao.update(new Produto(produto.getProdutoId(), comboBoxProdutos.getSelectedItem().toString(), categorias.get(comboBoxCategorias.getSelectedIndex()).getCategoriaId(), 
						Double.parseDouble(textFieldProdutoCusto.getText().trim().replace(",", ".")), Double.parseDouble(textFieldProdutoPreco.getText().trim().replace(",", ".")), 
						Double.parseDouble(textFieldProdutoLucro.getText().trim().replace(",", ".")) / 100, comboBoxProdutoAtivo.getSelectedItem().toString().equalsIgnoreCase("Sim") ? 1 : 0));
				
				systemInterface.clearSystemInterface(!Main.isBrunoTesting);
				systemInterface.getSystemInterfacePanelMain().add(systemInterface.getSystemInterfaceProdutos().consultaProduto(produto));
				produto = null;
				
				systemInterface.getSystemInterfaceLabelStatus().setText("Produto atualizado com sucesso!");
			} catch (SQLException e1) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Houve uma falha ao inserir os dados do produto no banco!");
			}
		}
	}
	
	private void completaCampos(Produto produto) {
		completaAtivo(produto);
		completaCusto(produto);
		completaLucro(produto);
		completaPreco(produto);
	}
	
	private static String[] completaAtivo(Produto produto) {
		String lista[] = {"Não", "Sim"};
		boolean isNull = produto.getProdutoDesc().equalsIgnoreCase("Nenhum valor encontrado");
		
		if(isNull) {
			lista = new String[1];
			lista[0] = "N/A";
		}
		if(comboBoxProdutoAtivo == null) {
			comboBoxProdutoAtivo = new JComboBox<>(lista);
		} else {
			comboBoxProdutoAtivo.removeAllItems();
		    for(String s : lista){
		    	comboBoxProdutoAtivo.addItem(s);
		    }
		}
		comboBoxProdutoAtivo.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		comboBoxProdutoAtivo.setBackground(Color.white);
		comboBoxProdutoAtivo.setEditable(false);
		comboBoxProdutoAtivo.setEnabled(false);
		UIManager.put("ComboBox.disabledBackground", Color.white);
		UIManager.put("ComboBox.disabledForeground", Color.black);
		comboBoxProdutoAtivo.setSelectedIndex(isNull ? 0 : produto.getProdutoAtivo());
		
		return lista;
	}
	
	private static void completaCusto(Produto produto) {
		DecimalFormat df = new DecimalFormat("0.00");
		String custoProduto = String.valueOf(df.format(produto.getProdutoCusto()).replace(".", ","));
		boolean isNull = produto.getProdutoDesc().equalsIgnoreCase("Nenhum valor encontrado");
		
		if(isNull) {
			custoProduto = "";
		}
		if(textFieldProdutoCusto == null)
			textFieldProdutoCusto = new JTextField();
		textFieldProdutoCusto.setText(custoProduto);
		textFieldProdutoCusto.setBackground(Color.white);
		textFieldProdutoCusto.setEditable(false);
		textFieldProdutoCusto.setEnabled(false);
		textFieldProdutoCusto.setDisabledTextColor(Color.black);
	}
	
	private static void completaLucro(Produto produto) {
		DecimalFormat df = new DecimalFormat("0.00");
		String lucroProduto = String.valueOf(df.format(produto.getProdutoLucro() * 100).replace(".", ","));
		boolean isNull = produto.getProdutoDesc().equalsIgnoreCase("Nenhum valor encontrado");
		
		if(isNull) {
			lucroProduto = "";
		}
		if(textFieldProdutoLucro == null)
			textFieldProdutoLucro = new JTextField();
		textFieldProdutoLucro.setText(lucroProduto);
		textFieldProdutoLucro.setBackground(Color.white);
		textFieldProdutoLucro.setEditable(false);
		textFieldProdutoLucro.setEnabled(false);
		textFieldProdutoLucro.setDisabledTextColor(Color.black);
	}
	
	private static void completaPreco(Produto produto) {
		DecimalFormat df = new DecimalFormat("0.00");
		String precoProduto = String.valueOf(df.format(produto.getProdutoPreco()).replace(".", ","));
		boolean isNull = produto.getProdutoDesc().equalsIgnoreCase("Nenhum valor encontrado");
		
		if(isNull) {
			precoProduto = "";
		}
		if(textFieldProdutoPreco == null)
			textFieldProdutoPreco = new JTextField();
		textFieldProdutoPreco.setText(precoProduto);
		textFieldProdutoPreco.setBackground(Color.white);
		textFieldProdutoPreco.setEditable(false);
		textFieldProdutoPreco.setEnabled(false);
		textFieldProdutoPreco.setDisabledTextColor(Color.black);
	}
	
	private void criaListaProdutos(Produto produtoSelecionado) {
		String[] novaLista = completaListaProdutos(systemInterface, comboBoxCategorias);
		
		if(comboBoxProdutos == null) {
			comboBoxProdutos = new JComboBox<>(novaLista);
		} else {
			comboBoxProdutos.removeAllItems();
		    for(String s : novaLista) {
		    	comboBoxProdutos.addItem(s);
		    }
		}
		comboBoxProdutos.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		if(comboBoxProdutos.getActionListeners().length > 0)
			comboBoxProdutos.removeActionListener(comboBoxProdutos.getActionListeners()[0]);
		comboBoxProdutos.addActionListener(new HandlerCompletaProdutos(this));
		comboBoxProdutos.setBackground(Color.white);
		comboBoxProdutos.setEditable(false);
		comboBoxProdutos.setSelectedIndex(produtoSelecionado == null ? 0 : produtos.indexOf(produtoSelecionado));
	}
	
	private static String[] completaListaProdutos(SystemInterface systemInterface, JComboBox source) {
		String[] lista = null;
		produtos = null;
		
		try {
			produtoDao = new ProdutoDao(systemInterface.getSystemInterfaceDatabaseURL());
			produtos = produtoDao.getForValue("prod_categoria", source.getSelectedItem().toString().equalsIgnoreCase("Nenhum valor encontrado") ? "0" : 
				String.valueOf(categorias.get(source.getSelectedIndex()).getCategoriaId()));
		} catch (SQLException e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de produtos!");
		} finally {
			if(produtos == null || produtos.size() == 0) {
				lista = new String[1];
				lista[0] = "Nenhum valor encontrado";
				String message = produtos == null ? "Houve um erro ao recuperar a lista de produtos!" : "Nenhum produto ativo encontrado!";
				systemInterface.getSystemInterfaceLabelStatus().setText(message);
			}
			else {
				lista = new String[produtos.size()];
				for(Produto p : produtos)
					lista[produtos.indexOf(p)] = p.getProdutoDesc();
			}
		}
		return lista;
	}
	
	private static class HandlerComboBox implements ActionListener {
		
		private ModuloProduto source;
		private Produto produto;
		
		public HandlerComboBox(ModuloProduto source, Produto produto) {
			this.source = source;
			this.produto = produto;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			source.criaListaProdutos(produto);
		}
	}
	
	private static class HandlerCompletaProdutos implements ActionListener {
		
		private ModuloProduto source;
		
		public HandlerCompletaProdutos(ModuloProduto source) {
			this.source = source;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setForeground(Color.black);
			try {
				source.completaCampos(produtos.get(comboBoxProdutos.getSelectedIndex()));
			} catch(ArrayIndexOutOfBoundsException ex) {
				
			}
		}
	}
	
	protected static class HandlerAddProduto implements MouseListener {
		
		private SystemInterface systemInterface;
		
		public HandlerAddProduto(SystemInterface systemInterface) {
			this.systemInterface = systemInterface;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			textFieldProdutoNome.setBackground(Color.white);
			textFieldProdutoCusto.setBackground(Color.white);
			textFieldProdutoLucro.setBackground(Color.white);
			textFieldProdutoPreco.setBackground(Color.white);
			comboBoxCategorias.setBackground(Color.white);
			if(!textFieldProdutoNome.getText().trim().equalsIgnoreCase("") && 
					!comboBoxCategorias.getSelectedItem().toString().equalsIgnoreCase("Nenhum valor encontrado") && 
					checkValues(textFieldProdutoCusto.getText().trim().replace(",", "."), textFieldProdutoLucro.getText().trim().replace(",", "."), 
							textFieldProdutoPreco.getText().trim().replace(",", ".")) && 
					!verificaProduto(textFieldProdutoNome.getText().trim())) {
				DecimalFormat df = new DecimalFormat("0.00");
				if(textFieldProdutoLucro.getText().trim().equalsIgnoreCase("")) {
					textFieldProdutoLucro.setText(String.valueOf(df.format((Double.parseDouble(textFieldProdutoPreco.getText().trim().replace(",", ".")) / 
							Double.parseDouble(textFieldProdutoCusto.getText().trim().replace(",", ".")) - 1) * 100).replace(".", ",")));
				} else if(textFieldProdutoPreco.getText().trim().equalsIgnoreCase("")) {
					textFieldProdutoPreco.setText(String.valueOf(df.format(Double.parseDouble(textFieldProdutoCusto.getText().trim().replace(",", ".")) * 
							(Double.parseDouble(textFieldProdutoLucro.getText().trim().replace(",", ".")) / 100 + 1)).replace(".", ",")));
				}
				conf.requestConfirmation(1, this);
			} else if(textFieldProdutoNome.getText().trim().equalsIgnoreCase("")) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Não há nome informado para o novo produto!");
				textFieldProdutoNome.setBackground(Color.yellow);
				textFieldProdutoNome.requestFocus();
			} else if(comboBoxCategorias.getSelectedItem().toString().equalsIgnoreCase("Nenhum valor encontrado")) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Não categoria ativa selecionada!");
				comboBoxCategorias.setBackground(Color.yellow);
				comboBoxCategorias.requestFocus();
			} else if(textFieldProdutoCusto.getText().trim().equalsIgnoreCase("")) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Não há custo informado para o novo produto!");
				textFieldProdutoCusto.setBackground(Color.yellow);
				textFieldProdutoCusto.requestFocus();
			} else if(!isDouble(textFieldProdutoCusto.getText().trim().replace(",", "."))) {
				systemInterface.getSystemInterfaceLabelStatus().setText("O valor informado para o custo do produto é inválido!");
				textFieldProdutoCusto.setBackground(Color.yellow);
				textFieldProdutoCusto.requestFocus();
			} else if((textFieldProdutoLucro.getText().trim().equalsIgnoreCase("") && textFieldProdutoPreco.getText().trim().equalsIgnoreCase(""))) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Não há lucro desejado ou preço de revenda informado para o novo produto!");
				textFieldProdutoLucro.setBackground(Color.yellow);
				textFieldProdutoPreco.setBackground(Color.yellow);
				textFieldProdutoLucro.requestFocus();
			} else if(!textFieldProdutoLucro.getText().trim().equalsIgnoreCase("") && !isDouble(textFieldProdutoLucro.getText().trim().replace(",", "."))) {
				systemInterface.getSystemInterfaceLabelStatus().setText("O valor informado para o lucro desejado do produto é inválido!");
				textFieldProdutoLucro.setBackground(Color.yellow);
				textFieldProdutoLucro.requestFocus();
			} else if(!textFieldProdutoPreco.getText().trim().equalsIgnoreCase("") && !isDouble(textFieldProdutoPreco.getText().trim().replace(",", "."))) {
				systemInterface.getSystemInterfaceLabelStatus().setText("O valor informado para o preço de revenda do produto é inválido!");
				textFieldProdutoPreco.setBackground(Color.yellow);
				textFieldProdutoPreco.requestFocus();
			} else if(isDouble(textFieldProdutoCusto.getText().trim().replace(",", ".")) && isDouble(textFieldProdutoLucro.getText().trim().replace(",", ".")) && 
					isDouble(textFieldProdutoPreco.getText().trim().replace(",", ".")) && 
					!((Double.parseDouble(textFieldProdutoCusto.getText().trim().replace(",", ".")) + (Double.parseDouble(textFieldProdutoCusto.getText().trim().replace(",", ".")) * 
					Double.parseDouble(textFieldProdutoLucro.getText().trim().replace(",", ".")) / 100)) == Double.parseDouble(textFieldProdutoPreco.getText().trim().replace(",", ".")))) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Os valores informados para o lucro e o preço não correspondem!");
				textFieldProdutoLucro.setBackground(Color.yellow);
				textFieldProdutoPreco.setBackground(Color.yellow);
				textFieldProdutoLucro.requestFocus();
			} else {
				systemInterface.getSystemInterfaceLabelStatus().setText("Produto já existente!");
				textFieldProdutoNome.setBackground(Color.yellow);
				textFieldProdutoNome.requestFocus();
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Adiciona o produto informado ao banco");
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
	
	private static class HandlerAlterProduto implements MouseListener {
		
		private SystemInterface systemInterface;
		
		public HandlerAlterProduto(SystemInterface systemInterface) {
			this.systemInterface = systemInterface;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			produto = produtos.get(comboBoxProdutos.getSelectedIndex());
			
			alterButton.setText("Salvar");
			alterButton.removeMouseListener(alterButton.getMouseListeners()[1]);
			alterButton.addMouseListener(new HandlerConfirmAlterProduto(systemInterface));
			
			textFieldProdutoCusto.setEnabled(true);
			textFieldProdutoCusto.setEditable(true);
			textFieldProdutoLucro.setEnabled(true);
			textFieldProdutoLucro.setEditable(true);
			textFieldProdutoPreco.setEnabled(true);
			textFieldProdutoPreco.setEditable(true);
			
			comboBoxProdutos.setEditable(true);
			comboBoxProdutos.removeActionListener(comboBoxProdutos.getActionListeners()[0]);
			comboBoxProdutoAtivo.setEnabled(true);
			comboBoxCategorias.removeActionListener(comboBoxCategorias.getActionListeners()[0]);
			
			cancelButton.setText("Cancelar");
			cancelButton.removeMouseListener(cancelButton.getMouseListeners()[1]);
			cancelButton.addMouseListener(new HandlerCancelProduto(systemInterface, produto));
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setForeground(Color.black);
			systemInterface.getSystemInterfaceLabelStatus().setText("Altera as informações da categoria selecionada");
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setForeground(Color.black);
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
	
	private static class HandlerCancelProduto implements MouseListener {
		
		private SystemInterface systemInterface;
		private Produto produto;
		
		public HandlerCancelProduto(SystemInterface systemInterface, Produto produto) {
			this.systemInterface = systemInterface;
			this.produto = produto;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			systemInterface.clearSystemInterface(!Main.isBrunoTesting);
			systemInterface.getSystemInterfacePanelMain().add(systemInterface.getSystemInterfaceProdutos().consultaProduto(produto));
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setForeground(Color.black);
			systemInterface.getSystemInterfaceLabelStatus().setText("Descarta as alterações realizadas");
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setForeground(Color.black);
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
	
	protected static class HandlerConfirmAlterProduto implements MouseListener {
		
		private SystemInterface systemInterface;
		
		public HandlerConfirmAlterProduto(SystemInterface systemInterface) {
			this.systemInterface = systemInterface;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			comboBoxProdutos.setBackground(Color.white);
			textFieldProdutoCusto.setBackground(Color.white);
			textFieldProdutoLucro.setBackground(Color.white);
			textFieldProdutoPreco.setBackground(Color.white);
			comboBoxCategorias.setBackground(Color.white);
			if(!comboBoxProdutos.getSelectedItem().toString().trim().equalsIgnoreCase("") && 
					!comboBoxCategorias.getSelectedItem().toString().equalsIgnoreCase("Nenhum valor encontrado") && 
					checkValues(textFieldProdutoCusto.getText().trim().replace(",", "."), textFieldProdutoLucro.getText().trim().replace(",", "."), 
							textFieldProdutoPreco.getText().trim().replace(",", ".")) && 
					!verificaProduto(comboBoxProdutos.getSelectedItem().toString().trim(), produto)) {
				DecimalFormat df = new DecimalFormat("0.00");
				if(textFieldProdutoLucro.getText().trim().equalsIgnoreCase("")) {
					textFieldProdutoLucro.setText(String.valueOf(df.format((Double.parseDouble(textFieldProdutoPreco.getText().trim().replace(",", ".")) / 
							Double.parseDouble(textFieldProdutoCusto.getText().trim().replace(",", ".")) - 1) * 100).replace(".", ",")));
				} else if(textFieldProdutoPreco.getText().trim().equalsIgnoreCase("")) {
					textFieldProdutoPreco.setText(String.valueOf(df.format(Double.parseDouble(textFieldProdutoCusto.getText().trim().replace(",", ".")) * 
							(Double.parseDouble(textFieldProdutoLucro.getText().trim().replace(",", ".")) / 100 + 1)).replace(".", ",")));
				}
				conf.requestConfirmation(1, this);
			} else if(comboBoxProdutos.getSelectedItem().toString().trim().equalsIgnoreCase("")) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Não há nome informado para o novo produto!");
				comboBoxProdutos.setBackground(Color.yellow);
				comboBoxProdutos.requestFocus();
			} else if(comboBoxCategorias.getSelectedItem().toString().equalsIgnoreCase("Nenhum valor encontrado")) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Não categoria ativa selecionada!");
				comboBoxCategorias.setBackground(Color.yellow);
				comboBoxCategorias.requestFocus();
			} else if(textFieldProdutoCusto.getText().trim().equalsIgnoreCase("")) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Não há custo informado para o novo produto!");
				textFieldProdutoCusto.setBackground(Color.yellow);
				textFieldProdutoCusto.requestFocus();
			} else if(!isDouble(textFieldProdutoCusto.getText().trim().replace(",", "."))) {
				systemInterface.getSystemInterfaceLabelStatus().setText("O valor informado para o custo do produto é inválido!");
				textFieldProdutoCusto.setBackground(Color.yellow);
				textFieldProdutoCusto.requestFocus();
			} else if((textFieldProdutoLucro.getText().trim().equalsIgnoreCase("") && textFieldProdutoPreco.getText().trim().equalsIgnoreCase(""))) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Não há lucro desejado ou preço de revenda informado para o novo produto!");
				textFieldProdutoLucro.setBackground(Color.yellow);
				textFieldProdutoPreco.setBackground(Color.yellow);
				textFieldProdutoLucro.requestFocus();
			} else if(!textFieldProdutoLucro.getText().trim().equalsIgnoreCase("") && !isDouble(textFieldProdutoLucro.getText().trim().replace(",", "."))) {
				systemInterface.getSystemInterfaceLabelStatus().setText("O valor informado para o lucro desejado do produto é inválido!");
				textFieldProdutoLucro.setBackground(Color.yellow);
				textFieldProdutoLucro.requestFocus();
			} else if(!textFieldProdutoPreco.getText().trim().equalsIgnoreCase("") && !isDouble(textFieldProdutoPreco.getText().trim().replace(",", "."))) {
				systemInterface.getSystemInterfaceLabelStatus().setText("O valor informado para o preço de revenda do produto é inválido!");
				textFieldProdutoPreco.setBackground(Color.yellow);
				textFieldProdutoPreco.requestFocus();
			} else if(isDouble(textFieldProdutoCusto.getText().trim().replace(",", ".")) && isDouble(textFieldProdutoLucro.getText().trim().replace(",", ".")) && 
					isDouble(textFieldProdutoPreco.getText().trim().replace(",", ".")) && 
					!((Double.parseDouble(textFieldProdutoCusto.getText().trim().replace(",", ".")) + (Double.parseDouble(textFieldProdutoCusto.getText().trim().replace(",", ".")) * 
					Double.parseDouble(textFieldProdutoLucro.getText().trim().replace(",", ".")) / 100)) == Double.parseDouble(textFieldProdutoPreco.getText().trim().replace(",", ".")))) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Os valores informados para o lucro e o preço não correspondem!");
				textFieldProdutoLucro.setBackground(Color.yellow);
				textFieldProdutoPreco.setBackground(Color.yellow);
				textFieldProdutoLucro.requestFocus();
			} else {
				systemInterface.getSystemInterfaceLabelStatus().setText("Produto já existente!");
				comboBoxProdutos.setBackground(Color.yellow);
				comboBoxProdutos.requestFocus();
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Adiciona o produto informado ao banco");
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
