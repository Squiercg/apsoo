package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
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

import main.Main;
import repositorio.ClienteDao;
import repositorio.ItemVendaDao;
import repositorio.VendaDao;
import classes.Cliente;
import classes.ItemVenda;
import classes.Venda;

@SuppressWarnings({"rawtypes", "unchecked"})
public class CadastraVendas {
	
	private static Dimension preferredSize;
	private static Border defaultBorder;
	private static String[] lista;
	private static List<Cliente> clientes;
	private static ClienteDao clienteDao;
	private static JComboBox comboBoxClientes;
	private static PopUpVenda popUp;
	private static Confirmation conf;
	private static String columnNames[];
	private static JTable table;
	private static JLabel labelValorTotal;
	private static Double valorTotal;
	private static JTextField textField;
	private static Venda venda;
	private SystemInterface systemInterface;
	
	public CadastraVendas(SystemInterface systemInterface) {
		this.systemInterface = systemInterface;
		preferredSize = systemInterface.getSystemInterfaceDimension();
		defaultBorder = Main.isBrunoTesting ? BorderFactory.createRaisedBevelBorder() : BorderFactory.createEmptyBorder();
		popUp = new PopUpVenda(systemInterface, defaultBorder);
		conf = new Confirmation(systemInterface, defaultBorder, this);
		String[] newColumnNames = {"Código", "Nome", "Categoria", "Quantidade", "Preço"};
		columnNames = newColumnNames;
	}
	
	public JTable getTable() {
		return table;
	}
	
	public PopUpVenda getPopUps() {
		return popUp;
	}
	
	public JPanel cadastraVenda() {
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
		
		placeHolder = new JLabel("Cadastro de Vendas");
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
		
		placeHolder = new JLabel("Data da inclusão");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		panelLevel3.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.CENTER);
		JPanel panelLevel4 = new JPanel(new BorderLayout());
		panelLevel3.add(panelLevel4, BorderLayout.NORTH);
		
		lista = null;
		clientes = null;
		
		try {
			clienteDao = new ClienteDao(systemInterface.getSystemInterfaceDatabaseURL());
			clientes = clienteDao.getForValue("ativo", "1");
		} catch (SQLException e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de clientes!");
		} finally {
			if(clientes == null || clientes.size() == 0) {
				lista = new String[1];
				lista[0] = "Nenhum valor encontrado";
				String message = clientes == null ? "Houve um erro ao recuperar a lista de clientes!" : "Nenhum cliente ativo encontrado!";
				systemInterface.getSystemInterfaceLabelStatus().setText(message);
			}
			else {
				lista = new String[clientes.size()];
				for(Cliente f : clientes)
					lista[clientes.indexOf(f)] = f.getClienteNome();
			}
		}
		comboBoxClientes = new JComboBox(lista);
		comboBoxClientes.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		comboBoxClientes.setBackground(Color.white);
		comboBoxClientes.setEditable(false);
		comboBoxClientes.setSelectedIndex(0);
		panelLevel4.add(comboBoxClientes, BorderLayout.WEST);
		
		JPanel panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + (int) (preferredSize.getWidth() / 64) - 
			(int) (preferredSize.getWidth() / 4) - 5, (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		textField = new JTextField();
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
		
		button = new JButton("Remover produtos");
		hsbColor = Color.RGBtoHSB(51, 122, 183, null); 
		button.setBackground(Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]));
		button.setForeground(Color.white);
		button.addMouseListener(new HandlerRemoveProducts(systemInterface));
		button.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 6), (int) (preferredSize.getHeight() / 16) - 
			(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(button, BorderLayout.EAST);
		
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
		button.addMouseListener(new HandlerAddVenda(systemInterface));
		button.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 16) - 
			(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(button, BorderLayout.WEST);
		
		return panelLevel0;
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
	
	public void incluiVenda(Boolean choice) {
		if(choice) {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			try {
				venda = new Venda(format.parse(textField.getText()), clientes.get(comboBoxClientes.getSelectedIndex()).getClienteId(), valorTotal);
			} catch (ParseException e1) {
				venda = new Venda(new Date(), clientes.get(comboBoxClientes.getSelectedIndex()).getClienteId(), valorTotal);
				systemInterface.getSystemInterfaceLabelStatus().setText("Data informada é inválida, utilizando dia atual!");
			} finally {
				try {
					VendaDao vendaDao = new VendaDao(systemInterface.getSystemInterfaceDatabaseURL());
					ItemVendaDao itemVendaDao = new ItemVendaDao(systemInterface.getSystemInterfaceDatabaseURL());
					
					int id = vendaDao.insertVenda(venda);
					for(ItemVenda i : popUp.getItensVenda()) {
						i.setVenda(id);
						itemVendaDao.insert(i);
					}
					popUp.encerraVenda();
					venda = null;
					
					systemInterface.clearSystemInterface(!Main.isBrunoTesting);
					systemInterface.getSystemInterfacePanelMain().add(systemInterface.getSystemInterfaceCadastraVendas().cadastraVenda());
					
					systemInterface.getSystemInterfaceLabelStatus().setText("Venda cadastrada com sucesso!");
				} catch (SQLException e1) {
					systemInterface.getSystemInterfaceLabelStatus().setText("Houve uma falha ao inserir os dados da venda no banco!");
				}
			}
		}
	}
	
	private static class HandlerAddProducts implements MouseListener {
		
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
			systemInterface.getSystemInterfaceLabelStatus().setText("Adiciona produtos à venda");
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
	
	private static class HandlerRemoveProducts implements MouseListener {
		
		private SystemInterface systemInterface;
		
		public HandlerRemoveProducts(SystemInterface systemInterface) {
			this.systemInterface = systemInterface;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				int rowToDelete = table.getSelectedRow(); 
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				DecimalFormat df = new DecimalFormat("R$ #,##0.00");
				
				valorTotal -= popUp.getItensVendaPrecos().get(rowToDelete);
				labelValorTotal.setText("Total: " + df.format(valorTotal));
				
				model.removeRow(rowToDelete);
				popUp.getItensVenda().remove(rowToDelete);
				popUp.getItensVendaPrecos().remove(rowToDelete);
				popUp.getProdutosAdicionados().remove(rowToDelete);
				
				systemInterface.getSystemInterfaceLabelStatus().setText("Registro removido com sucesso!");
			} catch (ArrayIndexOutOfBoundsException ex) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Não há registros selecionados para remover!");
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Remove os produtos selecionados da venda");
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
	
	private static class HandlerAddVenda implements MouseListener {
		
		private SystemInterface systemInterface;
		
		public HandlerAddVenda(SystemInterface systemInterface) {
			this.systemInterface = systemInterface;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(!comboBoxClientes.getSelectedItem().toString().equalsIgnoreCase("Nenhum valor encontrado")) {
				if(Common.isValidDate(textField.getText()) && table.getRowCount() > 0) {
					conf.requestConfirmation(1, null);
				} else if(!Common.isValidDate(textField.getText())) {
					systemInterface.getSystemInterfaceLabelStatus().setText("Data informada é inválida!");
					systemInterface.getSystemInterfaceLabelStatus().setForeground(Color.red);
					textField.setBackground(Color.yellow);
					textField.requestFocus();
				} else {
					systemInterface.getSystemInterfaceLabelStatus().setText("Não há registros a inserir no banco!");
					systemInterface.getSystemInterfaceLabelStatus().setForeground(Color.red);
				}
			} else {
					systemInterface.getSystemInterfaceLabelStatus().setText("Não há cliente ativo informado!");
					systemInterface.getSystemInterfaceLabelStatus().setForeground(Color.red);
					comboBoxClientes.requestFocus();
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setForeground(Color.black);
			systemInterface.getSystemInterfaceLabelStatus().setText("Encerra a venda e adiciona ao banco");
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
}
