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
import java.text.DateFormat;
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
import javax.swing.UIManager;
import javax.swing.border.Border;

import main.Main;
import repositorio.ClienteDao;
import classes.Cliente;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ModuloCliente {
	
	private static Dimension preferredSize;
	private static Border defaultBorder;
	private static List<Cliente> clientes;
	private static ClienteDao clienteDao;
	private static Cliente cliente;
	private static JTextField textFieldClienteNome;
	private static JTextField textFieldClienteEndereco;
	private static JTextField textFieldClienteCidade;
	private static JTextField textFieldClienteEstado;
	private static JTextField textFieldClienteTelefone;
	private static JTextField textFieldClienteDocumento;
	private static JTextField textFieldClienteDataNasc;
	private static JTextField textFieldClienteEmail;
	private static JComboBox comboBoxClienteNome;
	private static JComboBox comboBoxClienteAtivo;
	private static JButton alterButton;
	private static JButton cancelButton;
	private static Confirmation conf;
	private static SystemInterface systemInterface;
	
	public ModuloCliente(SystemInterface systemInterface) {
		ModuloCliente.systemInterface = systemInterface;
		preferredSize = systemInterface.getSystemInterfaceDimension();
		defaultBorder = Main.isBrunoTesting ? BorderFactory.createRaisedBevelBorder() : BorderFactory.createEmptyBorder();
		conf = new Confirmation(systemInterface, defaultBorder, this);
	}
	
	public JPanel cadastraCliente() {
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
		
		placeHolder = new JLabel("Cadastro de Clientes");
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
		
		placeHolder = new JLabel("Cliente*");
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
		
		textFieldClienteNome = new JTextField();
		float[] hsbColor = Color.RGBtoHSB(184, 207, 229, null);
		Color innerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		hsbColor = Color.RGBtoHSB(122, 138, 153, null);
		Color outerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		Border compound = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(outerBorderColor), BorderFactory.createLineBorder(innerBorderColor));
		textFieldClienteNome.setBorder(compound);
		textFieldClienteNome.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel4.add(textFieldClienteNome, BorderLayout.WEST);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				textFieldClienteNome.requestFocusInWindow();
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
		
		placeHolder = new JLabel("Data de nascimento");
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
		
		textFieldClienteDataNasc = new JTextField();
		textFieldClienteDataNasc.setBorder(compound);
		textFieldClienteDataNasc.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldClienteDataNasc, BorderLayout.WEST);
		
		panelLevel4 = panelLevel5;
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + (int) (preferredSize.getWidth() / 64) - 
			(int) (preferredSize.getWidth() / 4) - 5, (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		textFieldClienteEmail = new JTextField();
		textFieldClienteEmail.setBorder(compound);
		textFieldClienteEmail.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldClienteEmail, BorderLayout.CENTER);
		
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
		
		placeHolder = new JLabel("Endereço");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getHeight() / 64 + (int) (preferredSize.getWidth() / 3)), 
			(int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		placeHolder = new JLabel("Telefone");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.SOUTH);
		
		textFieldClienteEndereco = new JTextField();
		textFieldClienteEndereco.setBorder(compound);
		textFieldClienteEndereco.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldClienteEndereco, BorderLayout.WEST);
		
		panelLevel4 = panelLevel5;
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + (int) (preferredSize.getWidth() / 64) - 
			(int) (preferredSize.getWidth() / 4) - 5, (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		textFieldClienteTelefone = new JTextField();
		textFieldClienteTelefone.setBorder(compound);
		textFieldClienteTelefone.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldClienteTelefone, BorderLayout.CENTER);
		
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
		
		placeHolder = new JLabel("Cidade");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getHeight() / 64 + (int) (preferredSize.getWidth() / 3)), 
			(int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		placeHolder = new JLabel("Estado");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);

		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.SOUTH);
		
		textFieldClienteCidade = new JTextField();
		textFieldClienteCidade.setBorder(compound);
		textFieldClienteCidade.setText("Campo Grande");
		textFieldClienteCidade.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldClienteCidade, BorderLayout.WEST);
		
		panelLevel4 = panelLevel5;
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + (int) (preferredSize.getWidth() / 64) - 
			(int) (preferredSize.getWidth() / 4) - 5, (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		textFieldClienteEstado = new JTextField();
		textFieldClienteEstado.setBorder(compound);
		textFieldClienteEstado.setText("MS");
		textFieldClienteEstado.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldClienteEstado, BorderLayout.CENTER);
		
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
		
		placeHolder = new JLabel("Documento");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getHeight() / 64 + (int) (preferredSize.getWidth() / 3)), 
			(int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		placeHolder = new JLabel("Estado");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setForeground(placeHolder.getBackground());
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.SOUTH);
		
		textFieldClienteDocumento = new JTextField();
		textFieldClienteDocumento.setBorder(compound);
		textFieldClienteDocumento.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldClienteDocumento, BorderLayout.WEST);
		
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
		alterButton.addMouseListener(new HandlerAddCliente(systemInterface));
		alterButton.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 16) - 
			(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(alterButton, BorderLayout.WEST);
		
		return panelLevel0;
	}
	
	public JPanel consultaCliente(Cliente clienteSelecionado) {
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
		
		placeHolder = new JLabel("Consulta de Clientes");
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
		
		placeHolder = new JLabel("Cliente*");
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
		clientes = null;
		
		try {
			clienteDao = new ClienteDao(systemInterface.getSystemInterfaceDatabaseURL());
			clientes = clienteDao.getAll();
		} catch (SQLException e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de clientes!");
		} finally {
			if(clientes == null || clientes.size() == 0) {
				clientes = new ArrayList<Cliente>();
				clientes.add(new Cliente("Nenhum valor encontrado"));
				String message = clientes == null ? "Houve um erro ao recuperar a lista de clientes!" : "Nenhum clientes encontrado!";
				systemInterface.getSystemInterfaceLabelStatus().setText(message);
			}
			lista = new String[clientes.size()];
			
			for(Cliente c : clientes)
				lista[clientes.indexOf(c)] = c.getClienteNome();
		}
		comboBoxClienteNome = new JComboBox(lista);
		comboBoxClienteNome.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		comboBoxClienteNome.setBackground(Color.white);
		comboBoxClienteNome.setEditable(false);
		comboBoxClienteNome.setSelectedIndex(clienteSelecionado == null ? 0 : buscaCliente(clientes, clienteSelecionado.getClienteId()));
		comboBoxClienteNome.addActionListener(new HandlerComboBox(this));
		panelLevel4.add(comboBoxClienteNome, BorderLayout.WEST);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				comboBoxClienteNome.requestFocusInWindow();
		    }
		});
		
		JPanel panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + (int) (preferredSize.getWidth() / 64) - 
			(int) (preferredSize.getWidth() / 4) - 5, (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		completaCampos(clientes.get(comboBoxClienteNome.getSelectedIndex()));
		comboBoxClienteAtivo.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(comboBoxClienteAtivo, BorderLayout.CENTER);
		
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
		
		placeHolder = new JLabel("Data de nascimento");
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
		
		textFieldClienteDataNasc.setBorder(compound);
		textFieldClienteDataNasc.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldClienteDataNasc, BorderLayout.WEST);
		
		panelLevel4 = panelLevel5;
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + (int) (preferredSize.getWidth() / 64) - 
			(int) (preferredSize.getWidth() / 4) - 5, (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		textFieldClienteEmail.setBorder(compound);
		textFieldClienteEmail.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldClienteEmail, BorderLayout.CENTER);
		
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
		
		placeHolder = new JLabel("Endereço");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getHeight() / 64 + (int) (preferredSize.getWidth() / 3)), 
			(int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		placeHolder = new JLabel("Telefone");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.SOUTH);
		
		textFieldClienteEndereco.setBorder(compound);
		textFieldClienteEndereco.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldClienteEndereco, BorderLayout.WEST);
		
		panelLevel4 = panelLevel5;
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + (int) (preferredSize.getWidth() / 64) - 
			(int) (preferredSize.getWidth() / 4) - 5, (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		textFieldClienteTelefone.setBorder(compound);
		textFieldClienteTelefone.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldClienteTelefone, BorderLayout.CENTER);
		
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
		
		placeHolder = new JLabel("Cidade");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getHeight() / 64 + (int) (preferredSize.getWidth() / 3)), 
			(int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		placeHolder = new JLabel("Estado");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);

		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.SOUTH);
		
		textFieldClienteCidade.setBorder(compound);
		textFieldClienteCidade.setText("Campo Grande");
		textFieldClienteCidade.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldClienteCidade, BorderLayout.WEST);
		
		panelLevel4 = panelLevel5;
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + (int) (preferredSize.getWidth() / 64) - 
			(int) (preferredSize.getWidth() / 4) - 5, (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		textFieldClienteEstado.setBorder(compound);
		textFieldClienteEstado.setText("MS");
		textFieldClienteEstado.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldClienteEstado, BorderLayout.CENTER);
		
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
		
		placeHolder = new JLabel("Documento");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getHeight() / 64 + (int) (preferredSize.getWidth() / 3)), 
			(int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		placeHolder = new JLabel("Estado");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setForeground(placeHolder.getBackground());
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.SOUTH);
		
		textFieldClienteDocumento.setBorder(compound);
		textFieldClienteDocumento.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(textFieldClienteDocumento, BorderLayout.WEST);
		
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
		alterButton.addMouseListener(new HandlerAlterCliente(systemInterface));
		alterButton.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 16) - 
			(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(alterButton, BorderLayout.WEST);
		
		return panelLevel0;
	}
	
	private static boolean verificaCliente(String novoCliente) {
		try {
			clienteDao = new ClienteDao(systemInterface.getSystemInterfaceDatabaseURL());
			clientes = clienteDao.getAll();
			
			for(Cliente c : clientes) {
				if(c.getClienteNome().equalsIgnoreCase(novoCliente))
					return true;
			}
		} catch (SQLException e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve uma falha ao buscar os dados dos clientes no banco!");
		}
		return false;
	}
	
	private static boolean verificaCliente(String novoCliente, Cliente cliente) {
		try {
			clienteDao = new ClienteDao(systemInterface.getSystemInterfaceDatabaseURL());
			clientes = clienteDao.getAll();
			clientes.remove(cliente);
			
			for(Cliente c : clientes) {
				if(c.getClienteNome().equalsIgnoreCase(novoCliente))
					return true;
			}
		} catch (SQLException e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve uma falha ao buscar os dados dos clientes no banco!");
		}
		return false;
	}
	
	private static int buscaCliente(List<Cliente> clientes, int clienteId) {
		for(Cliente c : clientes) {
			if(c.getClienteId() == clienteId)
				return clientes.indexOf(c);
		}
		return 0;
	}
	
	private void completaCampos(Cliente cliente) {
		completaAtivo(cliente);
		completaDataNasc(cliente);
		completaEmail(cliente);
		completaEndereco(cliente);
		completaTelefone(cliente);
		completaCidade(cliente);
		completaEstado(cliente);
		completaDocumento(cliente);
	}
	
	private static String[] completaAtivo(Cliente cliente) {
		String lista[] = {"Não", "Sim"};
		boolean isNull = cliente.getClienteNome().equalsIgnoreCase("Nenhum valor encontrado");
		
		if(isNull) {
			lista = new String[1];
			lista[0] = "N/A";
		}
		if(comboBoxClienteAtivo == null) {
			comboBoxClienteAtivo = new JComboBox<>(lista);
		} else {
			comboBoxClienteAtivo.removeAllItems();
		    for(String s : lista){
		    	comboBoxClienteAtivo.addItem(s);
		    }
		}
		comboBoxClienteAtivo.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		comboBoxClienteAtivo.setBackground(Color.white);
		comboBoxClienteAtivo.setEditable(false);
		comboBoxClienteAtivo.setEnabled(false);
		UIManager.put("ComboBox.disabledBackground", Color.white);
		UIManager.put("ComboBox.disabledForeground", Color.black);
		comboBoxClienteAtivo.setSelectedIndex(isNull ? 0 : cliente.getClienteAtivo());
		
		return lista;
	}
	
	private static void completaDataNasc(Cliente cliente) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String dataNascCliente;
		try {
			dataNascCliente = String.valueOf(df.format(cliente.getClienteNascimento()));
		} catch(NullPointerException ex) {
			dataNascCliente = "";
		}
		boolean isNull = cliente.getClienteNome().equalsIgnoreCase("Nenhum valor encontrado");
		
		if(isNull) {
			dataNascCliente = "";
		}
		if(textFieldClienteDataNasc == null)
			textFieldClienteDataNasc = new JTextField();
		textFieldClienteDataNasc.setText(dataNascCliente);
		textFieldClienteDataNasc.setBackground(Color.white);
		textFieldClienteDataNasc.setEditable(false);
		textFieldClienteDataNasc.setEnabled(false);
		textFieldClienteDataNasc.setDisabledTextColor(Color.black);
	}
	
	private static void completaEmail(Cliente cliente) {
		String emailCliente = String.valueOf(cliente.getClienteEmail());
		boolean isNull = cliente.getClienteNome().equalsIgnoreCase("Nenhum valor encontrado");
		
		if(isNull || emailCliente.equalsIgnoreCase("null")) {
			emailCliente = "";
		}
		if(textFieldClienteEmail == null)
			textFieldClienteEmail = new JTextField();
		textFieldClienteEmail.setText(emailCliente);
		textFieldClienteEmail.setBackground(Color.white);
		textFieldClienteEmail.setEditable(false);
		textFieldClienteEmail.setEnabled(false);
		textFieldClienteEmail.setDisabledTextColor(Color.black);
	}
	
	private static void completaEndereco(Cliente cliente) {
		String enderecoCliente = String.valueOf(cliente.getClienteEndereco());
		boolean isNull = cliente.getClienteNome().equalsIgnoreCase("Nenhum valor encontrado");
		
		if(isNull || enderecoCliente.equalsIgnoreCase("null")) {
			enderecoCliente = "";
		}
		if(textFieldClienteEndereco == null)
			textFieldClienteEndereco = new JTextField();
		textFieldClienteEndereco.setText(enderecoCliente);
		textFieldClienteEndereco.setBackground(Color.white);
		textFieldClienteEndereco.setEditable(false);
		textFieldClienteEndereco.setEnabled(false);
		textFieldClienteEndereco.setDisabledTextColor(Color.black);
	}
	
	private static void completaTelefone(Cliente cliente) {
		String telefoneCliente = String.valueOf(cliente.getClienteTelefone());
		boolean isNull = cliente.getClienteNome().equalsIgnoreCase("Nenhum valor encontrado");
		
		if(isNull || telefoneCliente.equalsIgnoreCase("null")) {
			telefoneCliente = "";
		}
		if(textFieldClienteTelefone == null)
			textFieldClienteTelefone = new JTextField();
		textFieldClienteTelefone.setText(telefoneCliente);
		textFieldClienteTelefone.setBackground(Color.white);
		textFieldClienteTelefone.setEditable(false);
		textFieldClienteTelefone.setEnabled(false);
		textFieldClienteTelefone.setDisabledTextColor(Color.black);
	}
	
	private static void completaCidade(Cliente cliente) {
		String cidadeCliente = String.valueOf(cliente.getClienteCidade());
		boolean isNull = cliente.getClienteNome().equalsIgnoreCase("Nenhum valor encontrado");
		
		if(isNull || cidadeCliente.equalsIgnoreCase("null")) {
			cidadeCliente = "";
		}
		if(textFieldClienteCidade == null)
			textFieldClienteCidade = new JTextField();
		textFieldClienteCidade.setText(cidadeCliente);
		textFieldClienteCidade.setBackground(Color.white);
		textFieldClienteCidade.setEditable(false);
		textFieldClienteCidade.setEnabled(false);
		textFieldClienteCidade.setDisabledTextColor(Color.black);
	}
	
	private static void completaEstado(Cliente cliente) {
		String estadoCliente = String.valueOf(cliente.getClienteEstado());
		boolean isNull = cliente.getClienteNome().equalsIgnoreCase("Nenhum valor encontrado");
		
		if(isNull || estadoCliente.equalsIgnoreCase("null")) {
			estadoCliente = "";
		}
		if(textFieldClienteEstado == null)
			textFieldClienteEstado = new JTextField();
		textFieldClienteEstado.setText(estadoCliente);
		textFieldClienteEstado.setBackground(Color.white);
		textFieldClienteEstado.setEditable(false);
		textFieldClienteEstado.setEnabled(false);
		textFieldClienteEstado.setDisabledTextColor(Color.black);
	}
	
	private static void completaDocumento(Cliente cliente) {
		String documentoCliente = String.valueOf(cliente.getClienteDocumento());
		boolean isNull = cliente.getClienteNome().equalsIgnoreCase("Nenhum valor encontrado");
		
		if(isNull || documentoCliente.equalsIgnoreCase("null")) {
			documentoCliente = "";
		}
		if(textFieldClienteDocumento == null)
			textFieldClienteDocumento = new JTextField();
		textFieldClienteDocumento.setText(documentoCliente);
		textFieldClienteDocumento.setBackground(Color.white);
		textFieldClienteDocumento.setEditable(false);
		textFieldClienteDocumento.setEnabled(false);
		textFieldClienteDocumento.setDisabledTextColor(Color.black);
	}
	
	public void incluiCliente(Boolean choice) {
		if(choice) {
			Date dataNasc;
			if(!textFieldClienteDataNasc.getText().trim().equalsIgnoreCase("")) {
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				try {
					dataNasc = format.parse(textFieldClienteDataNasc.getText().trim());
				} catch (ParseException e) {
					dataNasc = new Date();
				}
			} else {
				dataNasc = null;
			}
			
			cliente = new Cliente(textFieldClienteNome.getText().trim(), textFieldClienteEndereco.getText().trim(), 
					textFieldClienteCidade.getText().trim(), textFieldClienteEstado.getText().trim(), textFieldClienteTelefone.getText().trim(), 
					textFieldClienteDocumento.getText().trim(), dataNasc, textFieldClienteEmail.getText().trim());
			try {
				clienteDao = new ClienteDao(systemInterface.getSystemInterfaceDatabaseURL());
				clienteDao.insert(cliente);
				
				systemInterface.clearSystemInterface(!Main.isBrunoTesting);
				systemInterface.getSystemInterfacePanelMain().add(systemInterface.getSystemInterfaceClientes().cadastraCliente());
				
				systemInterface.getSystemInterfaceLabelStatus().setText("Cliente cadastrado com sucesso!");
			} catch (SQLException e1) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Houve uma falha ao inserir os dados do cliente no banco!");
			}
		}
	}
	
	public void atualizaCliente(Boolean choice) {
		if(choice) {
			try {
				Date dataNasc;
				if(!textFieldClienteDataNasc.getText().trim().equalsIgnoreCase("")) {
					DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					try {
						dataNasc = format.parse(textFieldClienteDataNasc.getText().trim());
					} catch (ParseException e) {
						dataNasc = new Date();
					}
				} else {
					dataNasc = null;
				}
				
				clienteDao = new ClienteDao(systemInterface.getSystemInterfaceDatabaseURL());
				cliente = new Cliente(cliente.getClienteId(), comboBoxClienteNome.getSelectedItem().toString().trim(), textFieldClienteEndereco.getText().trim(), 
						textFieldClienteCidade.getText().trim(), textFieldClienteEstado.getText().trim(), textFieldClienteTelefone.getText().trim(), 
						textFieldClienteDocumento.getText().trim(), dataNasc, textFieldClienteEmail.getText().trim(), 
						comboBoxClienteAtivo.getSelectedItem().toString().equalsIgnoreCase("Sim") ? 1 : 0);
				clienteDao.update(cliente);
				
				systemInterface.clearSystemInterface(!Main.isBrunoTesting);
				systemInterface.getSystemInterfacePanelMain().add(systemInterface.getSystemInterfaceClientes().consultaCliente(cliente));
				cliente = null;
				
				systemInterface.getSystemInterfaceLabelStatus().setText("Cliente atualizado com sucesso!");
			} catch (SQLException e1) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Houve uma falha ao inserir os dados do cliente no banco!");
			}
		}
	}
	
	private JComboBox getComboBoxClienteNome() {
		return comboBoxClienteNome;
	}
	
	protected static class HandlerAddCliente implements MouseListener {
		
		private SystemInterface systemInterface;
		
		public HandlerAddCliente(SystemInterface systemInterface) {
			this.systemInterface = systemInterface;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			textFieldClienteNome.setBackground(Color.white);
			if(!textFieldClienteNome.getText().trim().equalsIgnoreCase("") && 
					(textFieldClienteDataNasc.getText().trim().equalsIgnoreCase("") || Common.isValidDate(textFieldClienteDataNasc.getText())) && 
					!verificaCliente(textFieldClienteNome.getText().trim())) {
				conf.requestConfirmation(1, this);
			} else if(textFieldClienteNome.getText().trim().equalsIgnoreCase("")) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Não há nome informado para o novo cliente!");
				textFieldClienteNome.setBackground(Color.yellow);
				textFieldClienteNome.requestFocus();
			} else if(!textFieldClienteDataNasc.getText().trim().equalsIgnoreCase("") && !Common.isValidDate(textFieldClienteDataNasc.getText())) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Data de nascimento informada é inválida!");
				textFieldClienteDataNasc.setBackground(Color.yellow);
				textFieldClienteDataNasc.requestFocus();
			} else {
				systemInterface.getSystemInterfaceLabelStatus().setText("Cliente já existente!");
				textFieldClienteNome.setBackground(Color.yellow);
				textFieldClienteNome.requestFocus();
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Adiciona o cliente informado ao banco");
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
	
	private static class HandlerComboBox implements ActionListener {
		
		private ModuloCliente source;
		
		public HandlerComboBox(ModuloCliente source) {
			this.source = source;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			source.completaCampos(clientes.get(source.getComboBoxClienteNome().getSelectedIndex()));
		}
	}
	
	private static class HandlerAlterCliente implements MouseListener {
		
		private SystemInterface systemInterface;
		
		public HandlerAlterCliente(SystemInterface systemInterface) {
			this.systemInterface = systemInterface;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			cliente = clientes.get(comboBoxClienteNome.getSelectedIndex());
			
			alterButton.setText("Salvar");
			alterButton.removeMouseListener(alterButton.getMouseListeners()[1]);
			alterButton.addMouseListener(new HandlerConfirmAlterCliente(systemInterface));
			
			textFieldClienteDataNasc.setEnabled(true);
			textFieldClienteDataNasc.setEditable(true);
			textFieldClienteEmail.setEnabled(true);
			textFieldClienteEmail.setEditable(true);
			textFieldClienteEndereco.setEnabled(true);
			textFieldClienteEndereco.setEditable(true);
			textFieldClienteTelefone.setEnabled(true);
			textFieldClienteTelefone.setEditable(true);
			textFieldClienteCidade.setEnabled(true);
			textFieldClienteCidade.setEditable(true);
			textFieldClienteEstado.setEnabled(true);
			textFieldClienteEstado.setEditable(true);
			textFieldClienteDocumento.setEnabled(true);
			textFieldClienteDocumento.setEditable(true);
			
			comboBoxClienteNome.setEditable(true);
			comboBoxClienteNome.removeActionListener(comboBoxClienteNome.getActionListeners()[0]);
			comboBoxClienteAtivo.setEnabled(true);
			
			cancelButton.setText("Cancelar");
			cancelButton.removeMouseListener(cancelButton.getMouseListeners()[1]);
			cancelButton.addMouseListener(new HandlerCancelCliente(systemInterface, cliente));
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setForeground(Color.black);
			systemInterface.getSystemInterfaceLabelStatus().setText("Altera as informações do cliente selecionado");
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
	
	private static class HandlerCancelCliente implements MouseListener {
		
		private SystemInterface systemInterface;
		private Cliente cliente;
		
		public HandlerCancelCliente(SystemInterface systemInterface, Cliente cliente) {
			this.systemInterface = systemInterface;
			this.cliente = cliente;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			systemInterface.clearSystemInterface(!Main.isBrunoTesting);
			systemInterface.getSystemInterfacePanelMain().add(systemInterface.getSystemInterfaceClientes().consultaCliente(cliente));
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
	
	protected static class HandlerConfirmAlterCliente implements MouseListener {
		
		private SystemInterface systemInterface;
		
		public HandlerConfirmAlterCliente(SystemInterface systemInterface) {
			this.systemInterface = systemInterface;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			comboBoxClienteNome.setBackground(Color.white);
			if(!comboBoxClienteNome.getSelectedItem().toString().trim().equalsIgnoreCase("") && 
					(textFieldClienteDataNasc.getText().trim().equalsIgnoreCase("") || Common.isValidDate(textFieldClienteDataNasc.getText())) &&  
					!verificaCliente(comboBoxClienteNome.getSelectedItem().toString().trim(), cliente)) {
				conf.requestConfirmation(1, this);
			} else if(comboBoxClienteNome.getSelectedItem().toString().trim().equalsIgnoreCase("")) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Não há nome informado para o novo cliente!");
				textFieldClienteNome.setBackground(Color.yellow);
				textFieldClienteNome.requestFocus();
			} else if(!textFieldClienteDataNasc.getText().trim().equalsIgnoreCase("") && !Common.isValidDate(textFieldClienteDataNasc.getText())) {
				systemInterface.getSystemInterfaceLabelStatus().setText("Data de nascimento informada é inválida!");
				textFieldClienteDataNasc.setBackground(Color.yellow);
				textFieldClienteDataNasc.requestFocus();
			} else {
				systemInterface.getSystemInterfaceLabelStatus().setText("Cliente já existente!");
				comboBoxClienteNome.setBackground(Color.yellow);
				comboBoxClienteNome.requestFocus();
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Adiciona o cliente informado ao banco");
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
