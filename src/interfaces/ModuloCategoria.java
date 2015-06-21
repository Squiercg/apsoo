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
import classes.Categoria;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ModuloCategoria {
	
	private static Dimension preferredSize;
	private static Border defaultBorder;
	private static List<Categoria> categorias;
	private static CategoriaDao categoriaDao;
	private static Categoria categoria;
	private static JTextField textFieldCategoria;
	private static JComboBox comboBoxCategorias;
	private static JComboBox comboBoxCategoriaAtiva;
	private static JButton alterButton;
	private static JButton cancelButton;
	private static Confirmation conf;
	private static SystemInterface systemInterface;
	
	public ModuloCategoria(SystemInterface systemInterface) {
		ModuloCategoria.systemInterface = systemInterface;
		preferredSize = systemInterface.getSystemInterfaceDimension();
		defaultBorder = Main.isBrunoTesting ? BorderFactory.createRaisedBevelBorder() : BorderFactory.createEmptyBorder();
		conf = new Confirmation(systemInterface, defaultBorder, this);
	}
	
	public JPanel cadastraCategoria() {
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
		
		placeHolder = new JLabel("Cadastro de Categorias");
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
		
		placeHolder = new JLabel("Categoria*");
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
		
		textFieldCategoria = new JTextField();
		float[] hsbColor = Color.RGBtoHSB(184, 207, 229, null);
		Color innerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		hsbColor = Color.RGBtoHSB(122, 138, 153, null);
		Color outerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		Border compound = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(outerBorderColor), BorderFactory.createLineBorder(innerBorderColor));
		textFieldCategoria.setBorder(compound);
		textFieldCategoria.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel4.add(textFieldCategoria, BorderLayout.WEST);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				textFieldCategoria.requestFocusInWindow();
		    }
		});
		
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.SOUTH);
		panelLevel4 = new JPanel(new BorderLayout());
		panelLevel3.add(panelLevel4, BorderLayout.SOUTH);
		JPanel panelLevel5 = new JPanel(new BorderLayout());
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
		alterButton.addMouseListener(new HandlerAddCategoria(systemInterface));
		alterButton.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 16) - 
			(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(alterButton, BorderLayout.WEST);
		
		return panelLevel0;
	}
	
	public JPanel consultaCategoria(Categoria categoriaSelecionada) {
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
		
		placeHolder = new JLabel("Consulta de Categorias");
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
		
		placeHolder = new JLabel("Categoria*");
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
			lista = new String[categorias.size()];
			
			for(Categoria c : categorias)
				lista[categorias.indexOf(c)] = c.getCategoriaDesc();
		}
		comboBoxCategorias = new JComboBox(lista);
		comboBoxCategorias.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		comboBoxCategorias.setBackground(Color.white);
		comboBoxCategorias.setEditable(false);
		comboBoxCategorias.setSelectedIndex(categoriaSelecionada == null ? 0 : categorias.indexOf(categoriaSelecionada));
		comboBoxCategorias.addActionListener(new HandlerComboBox(this));
		panelLevel4.add(comboBoxCategorias, BorderLayout.WEST);
		
		JPanel panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + (int) (preferredSize.getWidth() / 64) - 
			(int) (preferredSize.getWidth() / 4) - 5, (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		completaCampos(categorias.get(comboBoxCategorias.getSelectedIndex()));
		comboBoxCategoriaAtiva.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(comboBoxCategoriaAtiva, BorderLayout.CENTER);
		
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
		float[] hsbColor = Color.RGBtoHSB(51, 122, 183, null); 
		alterButton.setBackground(Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]));
		alterButton.setForeground(Color.white);
		alterButton.addMouseListener(new HandlerAlterCategoria(systemInterface));
		alterButton.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 16) - 
			(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(alterButton, BorderLayout.WEST);
		
		return panelLevel0;
	}
	
	private JComboBox getComboBoxCategorias() {
		return comboBoxCategorias;
	}
	
	public void incluiCategoria(Boolean choice) {
		if(choice) {
			categoria = new Categoria(textFieldCategoria.getText().trim());
			try {
				categoriaDao = new CategoriaDao(systemInterface.getSystemInterfaceDatabaseURL());
				categoriaDao.insert(categoria);
				
				systemInterface.clearSystemInterface(!Main.isBrunoTesting);
				systemInterface.getSystemInterfacePanelMain().add(systemInterface.getSystemInterfaceCategorias().cadastraCategoria());
				
				systemInterface.getSystemInterfaceLabelStatus().setText("Categoria cadastrada com sucesso!");
			} catch (SQLException e1) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Houve uma falha ao inserir os dados da categoria no banco!");
			}
		}
	}
	
	public void atualizaCategoria(Boolean choice) {
		if(choice) {
				try {
				categoriaDao = new CategoriaDao(systemInterface.getSystemInterfaceDatabaseURL());
				categoriaDao.update(new Categoria(categoria.getCategoriaId(), comboBoxCategorias.getSelectedItem().toString(), 
						comboBoxCategoriaAtiva.getSelectedItem().toString().equalsIgnoreCase("Sim") ? 1 : 0));
				
				systemInterface.clearSystemInterface(!Main.isBrunoTesting);
				systemInterface.getSystemInterfacePanelMain().add(systemInterface.getSystemInterfaceCategorias().consultaCategoria(categoria));
				categoria = null;
				
				systemInterface.getSystemInterfaceLabelStatus().setText("Categoria atualizada com sucesso!");
			} catch (SQLException e1) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Houve uma falha ao inserir os dados da categoria no banco!");
			}
		}
	}
	
	private static boolean verificaCategoria(String novaCategoria) {
		try {
			categoriaDao = new CategoriaDao(systemInterface.getSystemInterfaceDatabaseURL());
			categorias = categoriaDao.getAll();
			
			for(Categoria c : categorias) {
				if(c.getCategoriaDesc().equalsIgnoreCase(novaCategoria))
					return true;
			}
		} catch (SQLException e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve uma falha ao buscar os dados das categorias no banco!");
		}
		return false;
	}
	
	private static boolean verificaCategoria(String novaCategoria, Categoria categoria) {
		try {
			categoriaDao = new CategoriaDao(systemInterface.getSystemInterfaceDatabaseURL());
			categorias = categoriaDao.getAll();
			categorias.remove(categoria);
			
			for(Categoria c : categorias) {
				if(c.getCategoriaDesc().equalsIgnoreCase(novaCategoria))
					return true;
			}
		} catch (SQLException e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve uma falha ao buscar os dados das categorias no banco!");
		}
		return false;
	}
	
	private void completaCampos(Categoria categoria) {
		completaAtivo(categoria);
	}
	
	private static String[] completaAtivo(Categoria categoria) {
		String lista[] = {"Não", "Sim"};
		boolean isNull = categoria.getCategoriaDesc().equalsIgnoreCase("Nenhum valor encontrado"); 
		
		if(isNull) {
			lista = new String[1];
			lista[0] = "N/A";
		}
		if(comboBoxCategoriaAtiva == null) {
			comboBoxCategoriaAtiva = new JComboBox<>(lista);
		} else {
			comboBoxCategoriaAtiva.removeAllItems();
		    for(String s : lista){
		    	comboBoxCategoriaAtiva.addItem(s);
		    }
		}
		comboBoxCategoriaAtiva.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		comboBoxCategoriaAtiva.setBackground(Color.white);
		comboBoxCategoriaAtiva.setEditable(false);
		comboBoxCategoriaAtiva.setEnabled(false);
		UIManager.put("ComboBox.disabledBackground", Color.white);
		UIManager.put("ComboBox.disabledForeground", Color.black);
		comboBoxCategoriaAtiva.setSelectedIndex(isNull ? 0 : categoria.getCategoriaAtiva());
		
		return lista;
	}
	
	private static class HandlerComboBox implements ActionListener {
		
		private ModuloCategoria source;
		
		public HandlerComboBox(ModuloCategoria source) {
			this.source = source;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			source.completaCampos(categorias.get(source.getComboBoxCategorias().getSelectedIndex()));
		}
	}
	
	protected static class HandlerAddCategoria implements MouseListener {
		
		private SystemInterface systemInterface;
		
		public HandlerAddCategoria(SystemInterface systemInterface) {
			this.systemInterface = systemInterface;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			textFieldCategoria.setBackground(Color.white);
			if(!textFieldCategoria.getText().trim().equalsIgnoreCase("") && !verificaCategoria(textFieldCategoria.getText().trim())) {
				conf.requestConfirmation(1, this);
			} else if(textFieldCategoria.getText().trim().equalsIgnoreCase("")) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Não há registro a inserir no banco!");
				textFieldCategoria.setBackground(Color.yellow);
				textFieldCategoria.requestFocus();
			} else {
				systemInterface.getSystemInterfaceLabelStatus().setText("Categoria já existente!");
				textFieldCategoria.setBackground(Color.yellow);
				textFieldCategoria.requestFocus();
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Adiciona a categoria informada ao banco");
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
	
	private static class HandlerAlterCategoria implements MouseListener {
		
		private SystemInterface systemInterface;
		
		public HandlerAlterCategoria(SystemInterface systemInterface) {
			this.systemInterface = systemInterface;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(!comboBoxCategorias.getSelectedItem().toString().equalsIgnoreCase("Nenhum valor encontrado")) {
				categoria = categorias.get(comboBoxCategorias.getSelectedIndex());
				
				alterButton.setText("Salvar");
				alterButton.removeMouseListener(alterButton.getMouseListeners()[1]);
				alterButton.addMouseListener(new HandlerConfirmAlterCategoria(systemInterface, categorias.get(comboBoxCategorias.getSelectedIndex())));
				
				comboBoxCategorias.setEditable(true);
				comboBoxCategorias.removeActionListener(comboBoxCategorias.getActionListeners()[0]);
				comboBoxCategoriaAtiva.setEnabled(true);
				
				cancelButton.setText("Cancelar");
				cancelButton.removeMouseListener(cancelButton.getMouseListeners()[1]);
				cancelButton.addMouseListener(new HandlerCancelCategoria(systemInterface, categoria));
			} else {
				systemInterface.getSystemInterfaceLabelStatus().setText("Não há categoria válida a ser alterada!");				
			}
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
	
	private static class HandlerCancelCategoria implements MouseListener {
		
		private SystemInterface systemInterface;
		private Categoria categoria;
		
		public HandlerCancelCategoria(SystemInterface systemInterface, Categoria categoria) {
			this.systemInterface = systemInterface;
			this.categoria = categoria;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			systemInterface.clearSystemInterface(!Main.isBrunoTesting);
			systemInterface.getSystemInterfacePanelMain().add(systemInterface.getSystemInterfaceCategorias().consultaCategoria(categoria));
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
	
	protected static class HandlerConfirmAlterCategoria implements MouseListener {
		
		private SystemInterface systemInterface;
		private Categoria categoria;
		
		public HandlerConfirmAlterCategoria(SystemInterface systemInterface, Categoria categoria) {
			this.systemInterface = systemInterface;
			this.categoria = categoria;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(!comboBoxCategorias.getSelectedItem().toString().trim().equalsIgnoreCase("") && !verificaCategoria(comboBoxCategorias.getSelectedItem().toString().trim(), categoria)) {
				conf.requestConfirmation(1, this);
			} else if(comboBoxCategorias.getSelectedItem().toString().trim().equalsIgnoreCase("")) {
				systemInterface.getSystemInterfaceLabelStatus().setText("O nome da categoria não é válido!");
				comboBoxCategorias.setBackground(Color.yellow);
				comboBoxCategorias.requestFocus();
			} else {
				systemInterface.getSystemInterfaceLabelStatus().setText("Categoria já existente!");
				comboBoxCategorias.setBackground(Color.yellow);
				comboBoxCategorias.requestFocus();
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setForeground(Color.black);
			systemInterface.getSystemInterfaceLabelStatus().setText("Aplica as alterações à categoria selecionada");
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
