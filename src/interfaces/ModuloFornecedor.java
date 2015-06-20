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
import repositorio.FornecedorDao;
import classes.Fornecedor;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ModuloFornecedor {
	
	private static Dimension preferredSize;
	private static Border defaultBorder;
	private static List<Fornecedor> fornecedores;
	private static FornecedorDao fornecedorDao;
	private static Fornecedor fornecedor;
	private static JTextField textFieldFornecedorNome;
	private static JTextField textFieldFornecedorTelefone;
	private static JTextField textFieldFornecedorEmail;
	private static JComboBox comboBoxFornecedorNome;
	private static JComboBox comboBoxFornecedorAtivo;
	private static JButton alterButton;
	private static JButton cancelButton;
	private static Confirmation conf;
	private static SystemInterface systemInterface;
	
	public ModuloFornecedor(SystemInterface systemInterface) {
		ModuloFornecedor.systemInterface = systemInterface;
		preferredSize = systemInterface.getSystemInterfaceDimension();
		defaultBorder = Main.isBrunoTesting ? BorderFactory.createRaisedBevelBorder() : BorderFactory.createEmptyBorder();
		conf = new Confirmation(systemInterface, defaultBorder, this);
	}
	
	public JPanel cadastraFornecedor() {
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
		
		placeHolder = new JLabel("Cadastro de Fornecedores");
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
		
		placeHolder = new JLabel("Fornecedor*");
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
		
		textFieldFornecedorNome = new JTextField();
		float[] hsbColor = Color.RGBtoHSB(184, 207, 229, null);
		Color innerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		hsbColor = Color.RGBtoHSB(122, 138, 153, null);
		Color outerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		Border compound = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(outerBorderColor), BorderFactory.createLineBorder(innerBorderColor));
		textFieldFornecedorNome.setBorder(compound);
		textFieldFornecedorNome.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel4.add(textFieldFornecedorNome, BorderLayout.WEST);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				textFieldFornecedorNome.requestFocusInWindow();
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
		
		placeHolder = new JLabel("Telefone");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getHeight() / 64 + (int) (preferredSize.getWidth() / 3)), 
			(int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		placeHolder = new JLabel("E-mail");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel3 = panelLevel4;
		panelLevel4 = new JPanel(new BorderLayout());
		panelLevel3.add(panelLevel4, BorderLayout.CENTER);
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.NORTH);
		
		textFieldFornecedorTelefone = new JTextField();
		textFieldFornecedorTelefone.setBorder(compound);
		textFieldFornecedorTelefone.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldFornecedorTelefone, BorderLayout.WEST);
		
		panelLevel4 = panelLevel5;
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + (int) (preferredSize.getWidth() / 64) - 
			(int) (preferredSize.getWidth() / 4) - 5, (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		textFieldFornecedorEmail = new JTextField();
		textFieldFornecedorEmail.setBorder(compound);
		textFieldFornecedorEmail.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldFornecedorEmail, BorderLayout.CENTER);
		
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
		alterButton.addMouseListener(new HandlerAddFornecedor(systemInterface));
		alterButton.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 16) - 
			(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(alterButton, BorderLayout.WEST);
		
		return panelLevel0;
	}
	
	public JPanel consultaFornecedor(Fornecedor fornecedorSelecionado) {
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
		
		placeHolder = new JLabel("Consulta de Fornecedores");
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
		
		placeHolder = new JLabel("Fornecedor*");
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
		fornecedores = null;
		
		try {
			fornecedorDao = new FornecedorDao(systemInterface.getSystemInterfaceDatabaseURL());
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
			lista = new String[fornecedores.size()];
			
			for(Fornecedor f : fornecedores)
				lista[fornecedores.indexOf(f)] = f.getFornecedorNome();
		}
		comboBoxFornecedorNome = new JComboBox(lista);
		comboBoxFornecedorNome.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		comboBoxFornecedorNome.setBackground(Color.white);
		comboBoxFornecedorNome.setEditable(false);
		comboBoxFornecedorNome.setSelectedIndex(fornecedorSelecionado == null ? 0 : buscaFornecedor(fornecedores, fornecedorSelecionado.getFornecedorId()));
		comboBoxFornecedorNome.addActionListener(new HandlerComboBox(this));
		panelLevel4.add(comboBoxFornecedorNome, BorderLayout.WEST);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				comboBoxFornecedorNome.requestFocusInWindow();
		    }
		});
		
		JPanel panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + (int) (preferredSize.getWidth() / 64) - 
			(int) (preferredSize.getWidth() / 4) - 5, (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		completaCampos(fornecedores.get(comboBoxFornecedorNome.getSelectedIndex()));
		comboBoxFornecedorAtivo.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(comboBoxFornecedorAtivo, BorderLayout.CENTER);
		
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
		
		placeHolder = new JLabel("Telefone");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getHeight() / 64 + (int) (preferredSize.getWidth() / 3)), 
			(int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		placeHolder = new JLabel("E-mail");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel3 = panelLevel4;
		panelLevel4 = new JPanel(new BorderLayout());
		panelLevel3.add(panelLevel4, BorderLayout.CENTER);
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.NORTH);
		
		float[] hsbColor = Color.RGBtoHSB(184, 207, 229, null);
		Color innerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		hsbColor = Color.RGBtoHSB(122, 138, 153, null);
		Color outerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		Border compound = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(outerBorderColor), BorderFactory.createLineBorder(innerBorderColor));
		
		textFieldFornecedorTelefone.setBorder(compound);
		textFieldFornecedorTelefone.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldFornecedorTelefone, BorderLayout.WEST);
		
		panelLevel4 = panelLevel5;
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + (int) (preferredSize.getWidth() / 64) - 
			(int) (preferredSize.getWidth() / 4) - 5, (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		textFieldFornecedorEmail.setBorder(compound);
		textFieldFornecedorEmail.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldFornecedorEmail, BorderLayout.CENTER);
		
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
		alterButton.addMouseListener(new HandlerAlterFornecedor(systemInterface));
		alterButton.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 16) - 
			(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(alterButton, BorderLayout.WEST);
		
		return panelLevel0;
	}
	
	public void incluiFornecedor(Boolean choice) {
		if(choice) {
			fornecedor = new Fornecedor(textFieldFornecedorNome.getText().trim(), textFieldFornecedorTelefone.getText().trim(), textFieldFornecedorEmail.getText().trim());
			try {
				fornecedorDao = new FornecedorDao(systemInterface.getSystemInterfaceDatabaseURL());
				fornecedorDao.insert(fornecedor);
				
				systemInterface.clearSystemInterface(!Main.isBrunoTesting);
				systemInterface.getSystemInterfacePanelMain().add(systemInterface.getSystemInterfaceFornecedores().cadastraFornecedor());
				
				systemInterface.getSystemInterfaceLabelStatus().setText("Fornecedor cadastrado com sucesso!");
			} catch (SQLException e1) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Houve uma falha ao inserir os dados do fornecedor no banco!");
			}
		}
	}
	
	public void atualizaFornecedor(Boolean choice) {
		if(choice) {
			try {
				fornecedorDao = new FornecedorDao(systemInterface.getSystemInterfaceDatabaseURL());
				fornecedor = new Fornecedor(fornecedor.getFornecedorId(), comboBoxFornecedorNome.getSelectedItem().toString().trim(), 
						textFieldFornecedorTelefone.getText().trim(), textFieldFornecedorEmail.getText().trim(), 
						comboBoxFornecedorAtivo.getSelectedItem().toString().equalsIgnoreCase("Sim") ? 1 : 0);
				fornecedorDao.update(fornecedor);
				
				systemInterface.clearSystemInterface(!Main.isBrunoTesting);
				systemInterface.getSystemInterfacePanelMain().add(systemInterface.getSystemInterfaceFornecedores().consultaFornecedor(fornecedor));
				fornecedor = null;
				
				systemInterface.getSystemInterfaceLabelStatus().setText("Fornecedor atualizado com sucesso!");
			} catch (SQLException e1) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Houve uma falha ao inserir os dados do fornecedor no banco!");
			}
		}
	}
	
	private static int buscaFornecedor(List<Fornecedor> fornecedores, int fornecedorId) {
		for(Fornecedor f : fornecedores) {
			if(f.getFornecedorId() == fornecedorId)
				return fornecedores.indexOf(f);
		}
		return 0;
	}
	
	private void completaCampos(Fornecedor fornecedor) {
		completaAtivo(fornecedor);
		completaTelefone(fornecedor);
		completaEmail(fornecedor);
	}
	
	private static String[] completaAtivo(Fornecedor fornecedor) {
		String lista[] = {"Não", "Sim"};
		boolean isNull = fornecedor.getFornecedorNome().equalsIgnoreCase("Nenhum valor encontrado");
		
		if(isNull) {
			lista = new String[1];
			lista[0] = "N/A";
		}
		if(comboBoxFornecedorAtivo == null) {
			comboBoxFornecedorAtivo = new JComboBox<>(lista);
		} else {
			comboBoxFornecedorAtivo.removeAllItems();
		    for(String s : lista){
		    	comboBoxFornecedorAtivo.addItem(s);
		    }
		}
		comboBoxFornecedorAtivo.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		comboBoxFornecedorAtivo.setBackground(Color.white);
		comboBoxFornecedorAtivo.setEditable(false);
		comboBoxFornecedorAtivo.setEnabled(false);
		UIManager.put("ComboBox.disabledBackground", Color.white);
		UIManager.put("ComboBox.disabledForeground", Color.black);
		comboBoxFornecedorAtivo.setSelectedIndex(isNull ? 0 : fornecedor.getFornecedorAtivo());
		
		return lista;
	}
	
	private static void completaTelefone(Fornecedor fornecedor) {
		String telefoneFornecedor = String.valueOf(fornecedor.getFornecedorTelefone());
		boolean isNull = fornecedor.getFornecedorNome().equalsIgnoreCase("Nenhum valor encontrado");
		
		if(isNull) {
			telefoneFornecedor = "";
		}
		if(textFieldFornecedorTelefone == null)
			textFieldFornecedorTelefone = new JTextField();
		textFieldFornecedorTelefone.setText(telefoneFornecedor);
		textFieldFornecedorTelefone.setBackground(Color.white);
		textFieldFornecedorTelefone.setEditable(false);
		textFieldFornecedorTelefone.setEnabled(false);
		textFieldFornecedorTelefone.setDisabledTextColor(Color.black);
	}
	
	private static void completaEmail(Fornecedor fornecedor) {
		String emailFornecedor = String.valueOf(fornecedor.getFornecedorEmail());
		boolean isNull = fornecedor.getFornecedorNome().equalsIgnoreCase("Nenhum valor encontrado");
		
		if(isNull) {
			emailFornecedor = "";
		}
		if(textFieldFornecedorEmail == null)
			textFieldFornecedorEmail = new JTextField();
		textFieldFornecedorEmail.setText(emailFornecedor);
		textFieldFornecedorEmail.setBackground(Color.white);
		textFieldFornecedorEmail.setEditable(false);
		textFieldFornecedorEmail.setEnabled(false);
		textFieldFornecedorEmail.setDisabledTextColor(Color.black);
	}
	
	private static boolean verificaFornecedor(String novoFornecedor) {
		try {
			fornecedorDao = new FornecedorDao(systemInterface.getSystemInterfaceDatabaseURL());
			fornecedores = fornecedorDao.getAll();
			
			for(Fornecedor f : fornecedores) {
				if(f.getFornecedorNome().equalsIgnoreCase(novoFornecedor))
					return true;
			}
		} catch (SQLException e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve uma falha ao buscar os dados dos fornecedores no banco!");
		}
		return false;
	}
	
	private static boolean verificaFornecedor(String novoFornecedor, Fornecedor fornecedor) {
		try {
			fornecedorDao = new FornecedorDao(systemInterface.getSystemInterfaceDatabaseURL());
			fornecedores = fornecedorDao.getAll();
			fornecedores.remove(fornecedor);
			
			for(Fornecedor f : fornecedores) {
				if(f.getFornecedorNome().equalsIgnoreCase(novoFornecedor))
					return true;
			}
		} catch (SQLException e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve uma falha ao buscar os dados dos fornecedores no banco!");
		}
		return false;
	}
	
	private JComboBox getComboBoxFornecedorNome() {
		return comboBoxFornecedorNome;
	}
	
	private static class HandlerComboBox implements ActionListener {
		
		private ModuloFornecedor source;
		
		public HandlerComboBox(ModuloFornecedor source) {
			this.source = source;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			source.completaCampos(fornecedores.get(source.getComboBoxFornecedorNome().getSelectedIndex()));
		}
	}
	
	protected static class HandlerAddFornecedor implements MouseListener {
		
		private SystemInterface systemInterface;
		
		public HandlerAddFornecedor(SystemInterface systemInterface) {
			this.systemInterface = systemInterface;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			textFieldFornecedorNome.setBackground(Color.white);
			textFieldFornecedorTelefone.setBackground(Color.white);
			textFieldFornecedorEmail.setBackground(Color.white);
			if(!textFieldFornecedorNome.getText().trim().equalsIgnoreCase("") && 
					!verificaFornecedor(textFieldFornecedorNome.getText().trim())) {
				conf.requestConfirmation(1, this);
			} else if(textFieldFornecedorNome.getText().trim().equalsIgnoreCase("")) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Não há nome informado para o novo fornecedor!");
				textFieldFornecedorNome.setBackground(Color.yellow);
				textFieldFornecedorNome.requestFocus();
			} else {
				systemInterface.getSystemInterfaceLabelStatus().setText("Fornecedor já existente!");
				textFieldFornecedorNome.setBackground(Color.yellow);
				textFieldFornecedorNome.requestFocus();
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Adiciona o fornecedor informado ao banco");
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
	
	private static class HandlerAlterFornecedor implements MouseListener {
		
		private SystemInterface systemInterface;
		
		public HandlerAlterFornecedor(SystemInterface systemInterface) {
			this.systemInterface = systemInterface;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			fornecedor = fornecedores.get(comboBoxFornecedorNome.getSelectedIndex());
			
			alterButton.setText("Salvar");
			alterButton.removeMouseListener(alterButton.getMouseListeners()[1]);
			alterButton.addMouseListener(new HandlerConfirmAlterFornecedor(systemInterface));
			
			textFieldFornecedorTelefone.setEnabled(true);
			textFieldFornecedorTelefone.setEditable(true);
			textFieldFornecedorEmail.setEnabled(true);
			textFieldFornecedorEmail.setEditable(true);
			
			comboBoxFornecedorNome.setEditable(true);
			comboBoxFornecedorNome.removeActionListener(comboBoxFornecedorNome.getActionListeners()[0]);
			comboBoxFornecedorAtivo.setEnabled(true);
			
			cancelButton.setText("Cancelar");
			cancelButton.removeMouseListener(cancelButton.getMouseListeners()[1]);
			cancelButton.addMouseListener(new HandlerCancelFornecedor(systemInterface, fornecedor));
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setForeground(Color.black);
			systemInterface.getSystemInterfaceLabelStatus().setText("Altera as informações ddo fornecedor selecionado");
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
	
	private static class HandlerCancelFornecedor implements MouseListener {
		
		private SystemInterface systemInterface;
		private Fornecedor fornecedor;
		
		public HandlerCancelFornecedor(SystemInterface systemInterface, Fornecedor fornecedor) {
			this.systemInterface = systemInterface;
			this.fornecedor = fornecedor;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			systemInterface.clearSystemInterface(!Main.isBrunoTesting);
			systemInterface.getSystemInterfacePanelMain().add(systemInterface.getSystemInterfaceFornecedores().consultaFornecedor(fornecedor));
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
	protected static class HandlerConfirmAlterFornecedor implements MouseListener {
		
		private SystemInterface systemInterface;
		
		public HandlerConfirmAlterFornecedor(SystemInterface systemInterface) {
			this.systemInterface = systemInterface;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			comboBoxFornecedorNome.setBackground(Color.white);
			textFieldFornecedorTelefone.setBackground(Color.white);
			textFieldFornecedorEmail.setBackground(Color.white);
			if(!comboBoxFornecedorNome.getSelectedItem().toString().trim().equalsIgnoreCase("") && 
					!verificaFornecedor(comboBoxFornecedorNome.getSelectedItem().toString().trim(), fornecedor)) {
				conf.requestConfirmation(1, this);
			} else if(comboBoxFornecedorNome.getSelectedItem().toString().trim().equalsIgnoreCase("")) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Não há nome informado para o novo fornecedor!");
				comboBoxFornecedorNome.setBackground(Color.yellow);
				comboBoxFornecedorNome.requestFocus();
			} else {
				systemInterface.getSystemInterfaceLabelStatus().setText("Fornecedor já existente!");
				comboBoxFornecedorNome.setBackground(Color.yellow);
				comboBoxFornecedorNome.requestFocus();
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Adiciona o fornecedor informado ao banco");
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
