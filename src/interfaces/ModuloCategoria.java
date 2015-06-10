package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import main.Main;
import repositorio.CategoriaDao;
import classes.Categoria;

//@SuppressWarnings("rawtypes")
public class ModuloCategoria {
	
	private static Dimension preferredSize;
	private static Border defaultBorder;
	private static List<Categoria> categorias;
	private static CategoriaDao categoriaDao;
	private static Categoria categoria;
//	private static JComboBox comboBoxCategorias;
	private static JTextField textFieldCategoria;
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
		
		textFieldCategoria = new JTextField();
		float[] hsbColor = Color.RGBtoHSB(184, 207, 229, null);
		Color innerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		hsbColor = Color.RGBtoHSB(122, 138, 153, null);
		Color outerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		Border compound = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(outerBorderColor), BorderFactory.createLineBorder(innerBorderColor));
		textFieldCategoria.setBorder(compound);
		textFieldCategoria.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3), (int) (preferredSize.getHeight() / 32)));
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
		
		JButton button = new JButton("Voltar");
		button.setBackground(Color.white);
		button.setForeground(Color.black);
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
		button.addMouseListener(new HandlerAddCategoria(systemInterface));
		button.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 16) - 
			(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(button, BorderLayout.WEST);
		
		return panelLevel0;
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
	
	private static class HandlerAddCategoria implements MouseListener {
		
		private SystemInterface systemInterface;
		
		public HandlerAddCategoria(SystemInterface systemInterface) {
			this.systemInterface = systemInterface;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(!textFieldCategoria.getText().trim().equalsIgnoreCase("") && !verificaCategoria(textFieldCategoria.getText().trim())) {
				conf.requestConfirmation(1);
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
}
