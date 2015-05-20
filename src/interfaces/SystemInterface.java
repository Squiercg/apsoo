package interfaces;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
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
		setBorders();
		setSystemInterfaceFrame();
		setSystemInterfaceLabelStatus();
		setSystemInterfaceMenuBar();
		setHomeButton();
		setSystemInterface();
	}
	
	private void setBorders() {
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
		systemInterfaceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	
	private void setHomeButton() {
		JLabel systemInterfaceLabelHome = new JLabel("   Home  ");
		systemInterfaceLabelHome.setFont(new Font(null, Font.PLAIN + Font.BOLD, systemInterfaceLabelStatus.getFont().getSize() + 2));
		systemInterfaceLabelHome.addMouseListener(new HandlerHomeButton());
		systemInterfaceMenuBar.add(systemInterfaceLabelHome);
	}
	
	private void setSystemInterface() {
		JLabel systemWelcomeImage = null;
		try {
			String systemWelcomeImagePath = new File("lib/.").getCanonicalPath() + "\\" + "CDT_welcome.jpg";
			systemWelcomeImage = new JLabel(new ImageIcon(systemWelcomeImagePath));
			systemInterfaceFrame.add(systemWelcomeImage);
			systemInterfaceFrame.setVisible(true);
			try {
			    Thread.sleep(1337 * 2);
			}
			catch(InterruptedException exThreadFailed) {
				systemInterfaceLabelStatus.setText("Houve algum erro ao carregar o programa!");
			    Thread.currentThread().interrupt();
			}
			systemInterfaceFrame.remove(systemWelcomeImage);
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
}
