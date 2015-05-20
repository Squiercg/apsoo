package interfaces;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SystemInterface {
	
	private JFrame systemInterfaceFrame;
	private JPanel systemInterfacePanelMain;
	private JPanel systemInterfacePanelNorth;
	private JPanel systemInterfacePanelSouth;
	private JPanel systemInterfacePanelCenter;
	private JMenuBar systemInterfaceMenuBar;
	private JMenu systemInterfaceMenu;
	private JMenuItem systemInterfaceMenuItem;
	private JLabel systemInterfaceLabelStatus;
	private JLabel systemInterfaceLabelImage;
	private JScrollPane systemInterfaceScrollNorth;
	private JScrollPane systemInterfaceScrollSouth;
	private JScrollPane systemInterfaceScrollCenter;
	private JTable systemInterfaceTableNorth;
	private JTable systemInterfaceTableSouth;
	private JTable systemInterfaceTableCenter;
	private JTextField systemInterfaceFieldNorth;
	private JTextField systemInterfaceFieldSouth;
	private JTextField systemInterfaceFieldCenter;
	private ButtonGroup systemInterfaceButtonGroup;
	private Border systemInterfaceBorderDefault;
	private Border systemInterfaceBorderEmpty;
	private String systemInterfaceStatusMessage;
	
	public SystemInterface() {
		setSystemDefaultBorders();
		setSystemInterfaceFrame();
		setSystemInterfaceLabelStatus();
		setSystemInterfaceMenuBar();
		setSystemHomeButton();
		setSystemInterfaceMenu();
		setSystemExitButton();
		setSystemInterface();
	}
	
	private void setSystemDefaultBorders() {
		systemInterfaceBorderDefault = BorderFactory.createRaisedBevelBorder();
		systemInterfaceBorderEmpty = BorderFactory.createEmptyBorder();
	}
	
	private void setSystemInterfaceFrame() {
		systemInterfaceFrame = new JFrame("Sistema de Controle de Vendas e Estoque - CdT");
		try {
			String systemIconImagePath = new File("lib/.").getCanonicalPath() + "\\" + "CDT_icon.png";
			systemInterfaceFrame.setIconImage((new ImageIcon(systemIconImagePath)).getImage());
		}
		catch (IOException exPathNotFound) {
			systemInterfaceLabelStatus.setText("A imagem do icone do sistema nao foi encontrada!");
			System.out.print(systemInterfaceLabelStatus.getText());
		}
		systemInterfaceFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		systemInterfaceFrame.setLayout(new BorderLayout());
		systemInterfaceFrame.setSize(1024, 768);
	}
	
	private void setSystemInterfaceLabelStatus() {
		systemInterfaceLabelStatus = new JLabel("Inicializando sistemas..");
		systemInterfaceLabelStatus.setBorder(systemInterfaceBorderDefault);
		systemInterfaceLabelStatus.setFont(new Font(null, Font.PLAIN + Font.BOLD, systemInterfaceLabelStatus.getFont().getSize() + 2));
		systemInterfaceFrame.add(systemInterfaceLabelStatus, BorderLayout.PAGE_END);
	}
	
	private void setSystemInterfaceMenuBar() {
		systemInterfaceMenuBar = new JMenuBar();
		systemInterfaceMenuBar.setBorder(systemInterfaceBorderDefault);
		systemInterfaceFrame.add(systemInterfaceMenuBar, BorderLayout.PAGE_START);
		systemInterfaceFrame.setJMenuBar(systemInterfaceMenuBar);
	}
	
	private void setSystemHomeButton() {
		JLabel systemInterfaceLabelHome = new JLabel("   Home   ");
		systemInterfaceLabelHome.setFont(new Font(null, Font.PLAIN + Font.BOLD, systemInterfaceLabelHome.getFont().getSize() + 4));
		systemInterfaceLabelHome.setVerticalAlignment(JLabel.BOTTOM);
		systemInterfaceLabelHome.addMouseListener(new HandlerHomeButton());
		systemInterfaceMenuBar.add(systemInterfaceLabelHome);
	}
	
	private void setSystemInterfaceMenu() {
		ArrayList<String> systemInterfaceMenuNames = new ArrayList<String>();
		systemInterfaceMenuNames.add("Lotes");
		systemInterfaceMenuNames.add("Vendas");
		systemInterfaceMenuNames.add("Clientes");
		systemInterfaceMenuNames.add("Fornecedores");
		systemInterfaceMenuNames.add("Categorias");
		systemInterfaceMenuNames.add("Produtos");
		ArrayList<String> systemInterfaceMenuItemNames = new ArrayList<String>();
		systemInterfaceMenuItemNames.add("Cadastrar");
		systemInterfaceMenuItemNames.add("Consultar");
		for(String systemInterfaceMenuName : systemInterfaceMenuNames) {
			systemInterfaceMenu = new JMenu(systemInterfaceMenuName);
			systemInterfaceMenu.setFont(new Font(null, Font.PLAIN + Font.BOLD, systemInterfaceMenu.getFont().getSize() + 4));
			systemInterfaceMenu.addMouseListener(new HandlerMenuOptions(systemInterfaceMenuName, null));
			for(String systemInterfaceMenuItemName : systemInterfaceMenuItemNames) {
				systemInterfaceMenuItem = new JMenuItem(systemInterfaceMenuItemName);
				systemInterfaceMenuItem.setFont(new Font(null, Font.PLAIN + Font.BOLD, systemInterfaceMenuItem.getFont().getSize() + 2));
				systemInterfaceMenuItem.addMouseListener(new HandlerMenuOptions(systemInterfaceMenuName, systemInterfaceMenuItemName));
				systemInterfaceMenu.add(systemInterfaceMenuItem);
			}
			systemInterfaceMenuBar.add(systemInterfaceMenu);
		}
	}
	
	private void setSystemExitButton() {
		JLabel systemInterfaceLabelExit = new JLabel("   Sair   ");
		systemInterfaceLabelExit.addMouseListener(new HandlerExitButton());
		systemInterfaceLabelExit.setFont(new Font(null, Font.PLAIN + Font.BOLD, systemInterfaceLabelExit.getFont().getSize() + 4));
		systemInterfaceLabelExit.setVerticalAlignment(JLabel.BOTTOM);
		systemInterfaceMenuBar.add(Box.createHorizontalGlue());
		systemInterfaceMenuBar.add(systemInterfaceLabelExit);
	}
	
	private void setSystemInterface() {
		systemInterfaceLabelImage = null;
		try {
			String systemWelcomeImagePath = new File("lib/.").getCanonicalPath() + "\\" + "CDT_welcome.jpg";
			systemInterfaceLabelImage = new JLabel(new ImageIcon(systemWelcomeImagePath));
			systemInterfaceFrame.add(systemInterfaceLabelImage);
			systemInterfaceFrame.setVisible(true);
			try {
			    Thread.sleep(1337 * 2);
			}
			catch(InterruptedException exThreadFailed) {
				systemInterfaceLabelStatus.setText("Houve algum erro ao carregar o programa!");
			    Thread.currentThread().interrupt();
			}
			systemInterfaceFrame.remove(systemInterfaceLabelImage);
		}
		catch(IOException exPathNotFound) {
			systemInterfaceLabelStatus.setText("Imagem da tela de login nao encontrada!");
		}
		finally {
			systemInterfaceFrame.repaint();
			systemInterfaceStatusMessage = "Home";
			systemInterfaceLabelStatus.setText(systemInterfaceStatusMessage);
			systemInterfacePanelMain = new JPanel(new BorderLayout());
			systemInterfaceFrame.add(systemInterfacePanelMain, BorderLayout.CENTER);
		}
	}
	
	private void clearSystemInterface() {
		if(systemInterfacePanelNorth != null) {
			systemInterfacePanelNorth.removeAll();
			systemInterfacePanelNorth.revalidate();
			systemInterfacePanelNorth.repaint();
		}
		if(systemInterfacePanelSouth != null) {
			systemInterfacePanelSouth.removeAll();
			systemInterfacePanelSouth.revalidate();
			systemInterfacePanelSouth.repaint();
		}
		if(systemInterfacePanelCenter != null) {
			systemInterfacePanelCenter.removeAll();
			systemInterfacePanelCenter.revalidate();
			systemInterfacePanelCenter.repaint();
		}
		systemInterfacePanelMain.removeAll();
		systemInterfacePanelMain.revalidate();
		systemInterfacePanelMain.repaint();
	}
	
	private class HandlerHomeButton implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			clearSystemInterface();
			systemInterfaceStatusMessage = "Home";
			systemInterfaceLabelStatus.setText(systemInterfaceStatusMessage);
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterfaceLabelStatus.setText("Retorna à tela principal");
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			systemInterfaceLabelStatus.setText(systemInterfaceStatusMessage);
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			mouseEntered(e);
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			mouseClicked(e);
		}
	}
	
	private class HandlerExitButton implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
			    Thread.sleep(1337 * 2);
			    systemInterfaceFrame.dispose();
			}
			catch(InterruptedException exThreadFailed) {
				systemInterfaceLabelStatus.setText("Houve algum erro ao encerrar o programa!");
			    Thread.currentThread().interrupt();
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterfaceLabelStatus.setText("Encerra o sistema");
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			systemInterfaceLabelStatus.setText(systemInterfaceStatusMessage);
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			clearSystemInterface();
			systemInterfaceLabelStatus.setText("Encerrando conexoes..");
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			mouseClicked(e);
		}
	}
	
	private class HandlerMenuOptions implements MouseListener {
		
		private String systemInterfaceMenuName;
		private String systemInterfaceMenuItemName;
		
		public HandlerMenuOptions(String systemInterfaceMenuName, String systemInterfaceMenuItemName) {
			this.systemInterfaceMenuName = systemInterfaceMenuName;
			this.systemInterfaceMenuItemName = systemInterfaceMenuItemName;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(!(e.getSource() instanceof JMenu)) {
				systemInterfaceStatusMessage = 
					String.format("Home > %s > %s %s", systemInterfaceMenuName, systemInterfaceMenuItemName, systemInterfaceMenuName.toLowerCase());
				systemInterfaceLabelStatus.setText(systemInterfaceStatusMessage);
				if(e.getSource() instanceof JMenuItem) {
					clearSystemInterface();
					if(systemInterfaceMenuItemName.equals("Cadastrar")) {
//						cadastraAlgo(systemInterfaceMenuName); // Implementar
					}
					else if(systemInterfaceMenuItemName.equals("Consultar")) {
//						consultaAlgo(systemInterfaceMenuName); // Implementar
					}
				}
			}
			else {
				mouseEntered(e);
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterfaceStatusMessage = systemInterfaceStatusMessage != null && e.getSource() instanceof JMenu &&  
				!(systemInterfaceStatusMessage.equals(systemInterfaceLabelStatus.getText())) ?  
					systemInterfaceStatusMessage : systemInterfaceLabelStatus.getText();
			if(e.getSource() instanceof JMenu) {
				systemInterfaceLabelStatus.setText("Acessa o modulo de " + systemInterfaceMenuName.toLowerCase());
			}
			else {
				systemInterfaceLabelStatus.setText(systemInterfaceMenuItemName + " " + systemInterfaceMenuName.toLowerCase()); 
			}
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			systemInterfaceLabelStatus.setText(systemInterfaceStatusMessage);
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			mouseEntered(e);
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			mouseClicked(e);
		}
	}
}
