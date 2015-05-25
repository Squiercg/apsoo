package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import repositorio.CategoriaDao;
import repositorio.ProdutoDao;
import classes.Categoria;
import classes.Produto;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class PopUps {
	
	public static void selecionaProduto(SystemInterface systemInterface, Border defaultBorder) {
		Dimension preferredSize = systemInterface.getSystemInterfaceDimension();
		JFrame frame = setSystemInterfaceFrame(preferredSize);
		JPanel panelLevel0 = new JPanel(new BorderLayout());
		frame.add(panelLevel0, BorderLayout.CENTER);
		
		Methods.makeLateralBorders(panelLevel0, preferredSize, defaultBorder);
		
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
		
		String[] lista = null;
		List<Categoria> categorias = null;
		try {
			CategoriaDao categoriaDao = new CategoriaDao(systemInterface.getSystemInterfaceDatabaseURL());
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
		JComboBox comboBoxCategorias = new JComboBox(lista);
		comboBoxCategorias.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		comboBoxCategorias.setBackground(Color.white);
		comboBoxCategorias.setEditable(false);
		comboBoxCategorias.setSelectedIndex(0);
		comboBoxCategorias.addActionListener(new HandlerComboBox(comboBoxCategorias, systemInterface));
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
		
		JComboBox comboBoxProdutos = new JComboBox(completaListaPedidos(systemInterface, comboBoxCategorias));
		comboBoxProdutos.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		comboBoxProdutos.setBackground(Color.white);
		comboBoxProdutos.setEditable(false);
		comboBoxProdutos.setSelectedIndex(0);
		panelLevel3.add(comboBoxProdutos, BorderLayout.NORTH);
	}
	
	private static JFrame setSystemInterfaceFrame(Dimension preferredSize) {
		JFrame frame = new JFrame("Seleção de Produtos");
		try {
			String systemIconImagePath = new File("lib/.").getCanonicalPath() + "\\" + "CDT_icon.png";
			frame.setIconImage((new ImageIcon(systemIconImagePath)).getImage());
		} catch (IOException exPathNotFound) {
			
		}
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.setSize(new Dimension((int) (preferredSize.getWidth() / 2), (int) (preferredSize.getHeight() / 3)));
		frame.setLocationRelativeTo(null);
	
		frame.setVisible(true);
		frame.repaint();
		
		return frame;
	}
	
	private static String[] completaListaPedidos(SystemInterface systemInterface, JComboBox source) {
		String[] lista;
		List<Produto> produtos = null;
		try {
			ProdutoDao produtoDao = new ProdutoDao(systemInterface.getSystemInterfaceDatabaseURL());
			produtos = produtoDao.getAll();
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
	
	private static class HandlerComboBox implements ActionListener {
		
		private JComboBox source;
		private SystemInterface systemInterface;
		
		public HandlerComboBox(JComboBox source, SystemInterface systemInterface) {
			this.source = source;
			this.systemInterface = systemInterface;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			completaListaPedidos(systemInterface, source);			
		}
	}
}
