package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import repositorio.FornecedorDao;
import classes.Fornecedor;

public class CadastraLotes {
	
	private static Boolean isBrunoTesting;
	private static Dimension preferredSize;
	private static Border defaultBorder;
	private static String[] lista;
	private static List<Fornecedor> fornecedores;
	private static FornecedorDao fornecedorDao;
	private static PopUps popUp;
	private static String columnNames[];
	private static JTable table;
	private SystemInterface systemInterface;
	private JLabel labelValorTotal;
	private Double valorTotal;
	
	public CadastraLotes(SystemInterface systemInterface) {
		isBrunoTesting = false;
		this.systemInterface = systemInterface;
		preferredSize = systemInterface.getSystemInterfaceDimension();
		defaultBorder = isBrunoTesting ? BorderFactory.createRaisedBevelBorder() : BorderFactory.createEmptyBorder();
		popUp = new PopUps(systemInterface, defaultBorder);
		String[] newColumnNames = {"Código", "Nome", "Categoria", "Quantidade", "Preço"};
		columnNames = newColumnNames;
	}
	
	public JTable getTable() {
		return table;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JPanel cadastraLote() {
		JPanel panelLevel0 = new JPanel(new BorderLayout());
		makeLateralBorders(panelLevel0, preferredSize, defaultBorder);
		
		JPanel panelLevel1 = new JPanel(new BorderLayout());
		panelLevel0.add(panelLevel1, BorderLayout.CENTER);
		
		JPanel panelLevel2 = new JPanel(new BorderLayout());
		panelLevel1.add(panelLevel2, BorderLayout.NORTH);
		
		JLabel placeHolder = new JLabel("");
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		placeHolder.setBorder(defaultBorder);
		panelLevel2.add(placeHolder, BorderLayout.NORTH);
		
		placeHolder = new JLabel("Cadastro de Lotes");
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
		
		placeHolder = new JLabel("Data da inclusão");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		panelLevel3.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.CENTER);
		JPanel panelLevel4 = new JPanel(new BorderLayout());
		panelLevel3.add(panelLevel4, BorderLayout.NORTH);
		
		lista = null;
		fornecedores = null;
		try {
			fornecedorDao = new FornecedorDao(systemInterface.getSystemInterfaceDatabaseURL());
			fornecedores = fornecedorDao.getAll();
		} catch (SQLException e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de fornecedores!");
		} finally {
			if(fornecedores == null) {
				lista = new String[1];
				lista[0] = "Nenhum valor encontrado";
				systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de fornecedores!");
			}
			else {
				lista = new String[fornecedores.size()];
				for(Fornecedor f : fornecedores)
					lista[fornecedores.indexOf(f)] = f.getFornecedorNome();
			}
		}
		JComboBox comboBox = new JComboBox(lista);
		comboBox.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		comboBox.setBackground(Color.white);
		comboBox.setEditable(false);
		comboBox.setSelectedIndex(0);
		panelLevel4.add(comboBox, BorderLayout.WEST);
		
		JPanel panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + (int) (preferredSize.getWidth() / 64) - 
			(int) (preferredSize.getWidth() / 4) - 5, (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		JTextField textField = new JTextField();
		textField.setText((new SimpleDateFormat("dd/MM/yyyy")).format(new Date()));
		float[] hsbColor = Color.RGBtoHSB(184, 207, 229, null);
		Color innerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		hsbColor = Color.RGBtoHSB(122, 138, 153, null);
		Color outerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		Border compound = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(outerBorderColor), BorderFactory.createLineBorder(innerBorderColor));
		textField.setBorder(compound);
		textField.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textField, BorderLayout.CENTER);
		
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
		
		JButton button = new JButton("Incluir produtos");
		hsbColor = Color.RGBtoHSB(51, 122, 183, null); 
		button.setBackground(Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]));
		button.setForeground(Color.white);
		button.addMouseListener(new HandlerAddProducts(systemInterface));
		button.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 6), (int) (preferredSize.getHeight() / 16) - 
			(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(button, BorderLayout.WEST);
		
		panelLevel2 = new JPanel(new BorderLayout());
		panelLevel2.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 2) + 
			(int) (preferredSize.getHeight() / 11) + 3));
		panelLevel1.add(panelLevel2, BorderLayout.SOUTH);
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.NORTH);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		panelLevel3.add(placeHolder, BorderLayout.NORTH);
		
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.CENTER);
		placeHolder = new JLabel("Produtos");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 15));
		panelLevel3.add(placeHolder, BorderLayout.NORTH);
		
		panelLevel4 = new JPanel(new BorderLayout());
		panelLevel3.add(panelLevel4, BorderLayout.CENTER);
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.NORTH);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.NORTH);
		
		DefaultTableModel model = new DefaultTableModel(0, columnNames.length) ;
		model.setColumnIdentifiers(columnNames);
		JScrollPane pane = new JScrollPane(makeTable(model, preferredSize, compound));
		pane.setBorder(defaultBorder);
		pane.setOpaque(false);
		pane.getViewport().setOpaque(false);
		pane.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 3) - (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(pane, BorderLayout.CENTER);
		
		panelLevel4 = panelLevel5;
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.SOUTH);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.NORTH);
		
		DecimalFormat df = new DecimalFormat("R$ #,##0.00");
		valorTotal = 0.0;
		labelValorTotal = new JLabel("Total: " + df.format(valorTotal));
		labelValorTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		labelValorTotal.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 5));
		labelValorTotal.setBorder(defaultBorder);
		labelValorTotal.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4) + 16, (int) (preferredSize.getHeight() / 32) + 7));
		
		panelLevel4 = panelLevel5;
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.SOUTH);
		panelLevel5.add(labelValorTotal, BorderLayout.EAST);
		
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
		
		button = new JButton("Voltar");
		button.setBackground(Color.white);
		button.setForeground(Color.black);
		button.addMouseListener(systemInterface.new HandlerHomeButton());
		button.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 16) - 
			(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(button, BorderLayout.EAST);

		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 64),  (int) (preferredSize.getHeight() / 16) - 
				(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);

		button = new JButton("Salvar");
		hsbColor = Color.RGBtoHSB(51, 122, 183, null); 
		button.setBackground(Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]));
		button.setForeground(Color.white);
		button.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 16) - 
			(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(button, BorderLayout.WEST);
		
		return panelLevel0;
	}
	
	public JPanel underConstruction() {
		JPanel panel = new JPanel(new BorderLayout());
		try {
			String systemImagePath = new File("lib/.").getCanonicalPath() + "\\" + "CDT_underconstruction.png";
			JLabel systemInterfaceLabelImage = new JLabel(new ImageIcon(systemImagePath));
			panel.add(systemInterfaceLabelImage, BorderLayout.CENTER);
		} catch(IOException exPathNotFound) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Imagem da tela de alerta nao encontrada!");
		}
		return panel;
	}
	
	public static void makeLateralBorders(JPanel panel, Dimension reference, Border style) {
		JLabel placeHolder = new JLabel("");
		placeHolder.setBorder(style);
		placeHolder.setPreferredSize(new Dimension((int) (reference.getWidth() / 16), (int) (reference.getHeight())));
		panel.add(placeHolder, BorderLayout.WEST);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(style);
		placeHolder.setPreferredSize(new Dimension((int) (reference.getWidth() / 32), (int) (reference.getHeight())));
		panel.add(placeHolder, BorderLayout.EAST);
	}
	
	private static JTable makeTable(DefaultTableModel model, Dimension reference, Border style) {
		table = new JTable(model);
		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		for(int i = 0; i < table.getColumnCount(); i++)
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
		
		ListSelectionModel listSelection = 	table.getSelectionModel();
		listSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.setShowGrid(false);
		table.setBorder(style);
		
		table.getTableHeader().setBorder(BorderFactory.createEmptyBorder());
		table.getTableHeader().setFont(new Font(null, Font.PLAIN + Font.BOLD, table.getTableHeader().getFont().getSize() + 2));
		
		return table;
	}
	
	public void updateTable(Object[] newRow) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(newRow);
	}
	
	public void updateValorTotal(Double valor) {
		valorTotal += valor;
		DecimalFormat df = new DecimalFormat("R$ #,##0.00");
		labelValorTotal.setText("Total: " + df.format(valorTotal));
	}
	
	protected static class HandlerAddProducts implements MouseListener {
		
		private SystemInterface systemInterface;
		
		public HandlerAddProducts(SystemInterface systemInterface) {
			this.systemInterface = systemInterface;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			popUp.selecionaProduto();
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Adiciona produtos ao lote");
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
