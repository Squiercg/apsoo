package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import repositorio.CategoriaDao;
import repositorio.ProdutoDao;
import classes.Categoria;
import classes.ItemVenda;
import classes.Produto;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class PopUpVenda {
	
	private static SystemInterface systemInterface;
	private Dimension preferredSize;
	private static Border defaultBorder;
	private String[] lista;
	private List<Categoria> categorias;
	private CategoriaDao categoriaDao;
	private List<Produto> produtos;
	private ProdutoDao produtoDao;
	private List<ItemVenda> itensvenda;
	private List<Double> itensvendaPrecos;
	private JTextField textField;
	private JComboBox comboBoxProdutos;
	private JComboBox comboBoxCategorias;
	private JFrame frame;
	
	public PopUpVenda(SystemInterface systemInterface, Border defaultBorder) {
		PopUpVenda.systemInterface = systemInterface;
		preferredSize = systemInterface.getSystemInterfaceDimension();
		PopUpVenda.defaultBorder = defaultBorder;
		itensvenda = new ArrayList<ItemVenda>();
		itensvendaPrecos = new ArrayList<Double>();
	}
	
	public void selecionaProduto() {
		frame = setSystemInterfaceFrame(preferredSize);
		JPanel panelLevel0 = new JPanel(new BorderLayout());
		frame.add(panelLevel0, BorderLayout.CENTER);
		
		CadastraVendas.makeLateralBorders(panelLevel0, preferredSize, defaultBorder);
		
		JPanel panelLevel1 = new JPanel(new BorderLayout());
		panelLevel0.add(panelLevel1, BorderLayout.CENTER);
		JPanel panelLevel2 = new JPanel(new BorderLayout());
		panelLevel1.add(panelLevel2, BorderLayout.NORTH);
		JPanel panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.NORTH);
		
		JLabel placeHolder = new JLabel("");
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 2), (int) (preferredSize.getHeight() / 64)));
		placeHolder.setBorder(defaultBorder);
		panelLevel3.add(placeHolder, BorderLayout.NORTH);

		placeHolder = new JLabel("Categoria");
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 5));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 2), (int) (preferredSize.getHeight() / 32)));
		placeHolder.setBorder(defaultBorder);
		panelLevel3.add(placeHolder, BorderLayout.SOUTH);
		
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.CENTER);
		
		lista = null;
		categorias = null;
		try {
			categoriaDao = new CategoriaDao(systemInterface.getSystemInterfaceDatabaseURL());
			categorias = categoriaDao.getAll();
		} catch (SQLException e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de categorias!");
		} finally {
			if(categorias == null) {
				lista = new String[1];
				lista[0] = "Nenhum valor encontrado";
				systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de categorias!");
			}
			else {
				lista = new String[categorias.size()];
				for(Categoria c : categorias)
					lista[categorias.indexOf(c)] = c.getCategoriaDesc();
			}
		}
		comboBoxCategorias = new JComboBox(lista);
		comboBoxCategorias.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		comboBoxCategorias.setBackground(Color.white);
		comboBoxCategorias.setEditable(false);
		comboBoxCategorias.setSelectedIndex(0);
		comboBoxCategorias.addActionListener(new HandlerComboBox(this));
		panelLevel3.add(comboBoxCategorias, BorderLayout.NORTH);
		
		placeHolder = new JLabel("");
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 2), (int) (preferredSize.getHeight() / 64)));
		placeHolder.setBorder(defaultBorder);
		panelLevel3.add(placeHolder, BorderLayout.CENTER);
		
		placeHolder = new JLabel("Produto");
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 5));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 2), (int) (preferredSize.getHeight() / 32)));
		placeHolder.setBorder(defaultBorder);
		panelLevel3.add(placeHolder, BorderLayout.SOUTH);
		
		panelLevel2 = new JPanel(new BorderLayout());
		panelLevel1.add(panelLevel2, BorderLayout.CENTER);
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.NORTH);
		
		criaListaProdutos();
		panelLevel3.add(comboBoxProdutos, BorderLayout.NORTH);
		
		placeHolder = new JLabel("");
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 2), (int) (preferredSize.getHeight() / 32)));
		placeHolder.setBorder(defaultBorder);
		panelLevel3.add(placeHolder, BorderLayout.SOUTH);
		
		panelLevel2 = new JPanel(new BorderLayout());
		panelLevel2.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 2), (int) (preferredSize.getHeight() / 10) + 2));
		panelLevel1.add(panelLevel2, BorderLayout.SOUTH);
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.NORTH);
		
		placeHolder = new JLabel("Quantidade");
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 5));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 6), (int) (preferredSize.getHeight() / 32)));
		placeHolder.setBorder(defaultBorder);
		panelLevel3.add(placeHolder, BorderLayout.WEST);
		
		JButton button = new JButton("Adicionar");
		float[] hsbColor = Color.RGBtoHSB(51, 122, 183, null); 
		button.setBackground(Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]));
		button.setForeground(Color.white);
		button.addMouseListener(new HandlerAcceptButton(systemInterface, frame));
		button.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 32) - 
			(int) (preferredSize.getHeight() / 64)));
		panelLevel3.add(button, BorderLayout.EAST);
		
		placeHolder = new JLabel("");
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 2), (int) (preferredSize.getHeight() / 64)));
		placeHolder.setBorder(defaultBorder);
		panelLevel2.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel3.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 2), (int) (preferredSize.getHeight() / 18)));
		panelLevel2.add(panelLevel3, BorderLayout.SOUTH);
		
		panelLevel2 = panelLevel3;
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.NORTH);
		
		textField = new JTextField();
		hsbColor = Color.RGBtoHSB(184, 207, 229, null);
		Color innerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		hsbColor = Color.RGBtoHSB(122, 138, 153, null);
		Color outerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		Border compound = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(outerBorderColor), BorderFactory.createLineBorder(innerBorderColor));
		textField.setBorder(compound);
		textField.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 6), (int) (preferredSize.getHeight() / 32)));
		panelLevel3.add(textField, BorderLayout.WEST);
		
		button = new JButton("Voltar");
		button.setBackground(Color.white);
		button.setForeground(Color.black);
		button.addMouseListener(new HandlerBackButton(systemInterface, frame));
		button.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 32) - 
				(int) (preferredSize.getHeight() / 64)));
		panelLevel3.add(button, BorderLayout.EAST);
	}
	
	private static JFrame setSystemInterfaceFrame(Dimension preferredSize) {
		JFrame frame = new JFrame("Sele��o de Produtos");
		try {
			String systemIconImagePath = new File("lib/.").getCanonicalPath() + "\\" + "CDT_icon.png";
			frame.setIconImage((new ImageIcon(systemIconImagePath)).getImage());
		} catch (IOException exPathNotFound) {
			
		}
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.setSize(new Dimension((int) (preferredSize.getWidth() / 2), (int) (preferredSize.getHeight() / 3)));
		frame.setLocationRelativeTo(null);
	
		frame.setVisible(true);
		frame.repaint();
		
		return frame;
	}
	
	public List<ItemVenda> getItensVenda() {
		return itensvenda;
	}
	
	public List<Double> getItensVendaPrecos() {
		return itensvendaPrecos;
	}
	
	private void criaListaProdutos() {
		String[] novaLista = completaListaProdutos(systemInterface, comboBoxCategorias);
		
		if(comboBoxProdutos == null) {
			comboBoxProdutos = new JComboBox<>(novaLista);
		} else {
			comboBoxProdutos.removeAllItems();
		    for(String s : novaLista){
		    	comboBoxProdutos.addItem(s);
		    }
		}
	    
		comboBoxProdutos.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		comboBoxProdutos.setBackground(Color.white);
		comboBoxProdutos.setEditable(false);
		comboBoxProdutos.setSelectedIndex(0);
	}
	
	public void encerraVenda() {
		itensvenda.clear();
		itensvendaPrecos.clear();
	}
	
	private String[] completaListaProdutos(SystemInterface systemInterface, JComboBox source) {
		lista = null;
		produtos = null;
		try {
			produtoDao = new ProdutoDao(systemInterface.getSystemInterfaceDatabaseURL());
			produtos = produtoDao.getForValue("prod_categoria", String.valueOf(categorias.get(source.getSelectedIndex()).getCategoriaId()));
		} catch (SQLException e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de produtos!");
		} finally {
			if(produtos == null) {
				lista = new String[1];
				lista[0] = "Nenhum valor encontrado";
				systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de produtos!");
			}
			else {
				lista = new String[produtos.size()];
				for(Produto p : produtos)
					lista[produtos.indexOf(p)] = p.getProdutoDesc();
			}
		}
		return lista;
	}
	
	public static boolean isInteger(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}
		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c <= '/' || c >= ':') {
				return false;
			}
		}
		return true;
	}
	
	private static class HandlerComboBox implements ActionListener {
		
		private PopUpVenda source;
		
		public HandlerComboBox(PopUpVenda popUpVenda) {
			this.source = popUpVenda;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			source.criaListaProdutos();
		}
	}
	
	private class HandlerAcceptButton implements MouseListener {
		
		private SystemInterface systemInterface;
		private JFrame source;
		
		public HandlerAcceptButton(SystemInterface systemInterface, JFrame source) {
			this.systemInterface = systemInterface;
			this.source = source;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(isInteger(textField.getText())) {
				int quantidade = Integer.parseInt(textField.getText());
				if(quantidade > 0) {
					DecimalFormat df = new DecimalFormat("#,###.00");
					Object[] newRow = new Object[systemInterface.getSystemInterfaceCadastraVendas().getTable().getColumnCount()];
					
					newRow[0] = produtos.get(comboBoxProdutos.getSelectedIndex()).getProdutoId();
					newRow[1] = produtos.get(comboBoxProdutos.getSelectedIndex()).getProdutoDesc();
					newRow[2] = categorias.get(comboBoxCategorias.getSelectedIndex()).getCategoriaDesc();
					newRow[3] = textField.getText();
					newRow[4] = "R$ " + String.valueOf(df.format(produtos
						.get(comboBoxProdutos.getSelectedIndex()).getProdutoPreco() * quantidade));
					
					itensvenda.add(new ItemVenda(produtos.get(comboBoxProdutos.getSelectedIndex()).getProdutoId(), quantidade, 0));
					itensvendaPrecos.add(produtos.get(comboBoxProdutos.getSelectedIndex()).getProdutoPreco() * quantidade);
					
					systemInterface.getSystemInterfaceCadastraVendas().updateTable(newRow);
					systemInterface.getSystemInterfaceCadastraVendas().updateValorTotal(produtos
						.get(comboBoxProdutos.getSelectedIndex()).getProdutoPreco() * quantidade);
					
					source.dispose();
				} else {
					systemInterface.getSystemInterfaceLabelStatus().setText("Valor informado para a quantidade deve ser maior que zero!");
					textField.setBackground(Color.yellow);
				}
			} else {
				systemInterface.getSystemInterfaceLabelStatus().setText("Valor informado para a quantidade de itens � inv�lido!");
				textField.setBackground(Color.yellow);
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Adiciona os dados informados � tabela de itens da venda");
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setText(systemInterface.getSystemInterfaceStatusMessage());
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
	}
	
	private class HandlerBackButton implements MouseListener {
		
		private SystemInterface systemInterface;
		private JFrame source;
		
		public HandlerBackButton(SystemInterface systemInterface, JFrame source) {
			this.systemInterface = systemInterface;
			this.source = source;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
		    source.dispose();
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Retorna para o cadastro de vendas");
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setText(systemInterface.getSystemInterfaceStatusMessage());
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
	}
}
