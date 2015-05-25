package interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class SystemInterface {
	
	private Integer systemInterfaceLoadTime;
	private Boolean systemInterfaceBusy;
	private Dimension systemInterfaceDimension;
	private Border systemInterfaceBorderDefault;
	private JFrame systemInterfaceFrame;
	private JPanel systemInterfacePanelMain;
	private JMenuBar systemInterfaceMenuBar;
	private JMenu systemInterfaceMenu;
	private JMenuItem systemInterfaceMenuItem;
	private JLabel systemInterfaceLabelStatus;
	private JLabel systemInterfaceLabelImage;
	private String systemInterfaceStatusMessage;
	private String systemInterfaceDatabaseURL;
	private CadastraLotes systemInterfaceCadastraLotes;
	
	public SystemInterface(String systemDatabaseURL) {
		setSystemInterfaceDatabaseURL(systemDatabaseURL);
		setSystemInterfaceLoadTime(1);
		setSystemInterfaceBusy();
		setSystemInterfaceDefaultBorder();
		setSystemInterfaceDimension();
		setSystemInterfaceFrame();
		setSystemInterfaceLabelStatus();
		setSystemInterfaceMenuBar();
		setSystemInterfaceHomeButton();
		setSystemInterfaceMenu();
		setSystemInterfaceExitButton();
		setSystemInterface();
		setSystemInterfaceBusy();
		setSystemInterfaceCadastraLotes();
	}
	
	private void setSystemInterfaceLoadTime(int systemInterfaceLoadTime) {
		this.systemInterfaceLoadTime = systemInterfaceLoadTime;
	}
	
	private void setSystemInterfaceBusy() {
		if(systemInterfaceBusy == null)
			systemInterfaceBusy = true;
		else
			systemInterfaceBusy = !systemInterfaceBusy;
	}
	
	private void setSystemInterfaceDefaultBorder() {
		systemInterfaceBorderDefault = BorderFactory.createRaisedBevelBorder();
	}
	
	private void setSystemInterfaceDimension() {
		systemInterfaceDimension = new Dimension(1024, 800);
	}
	
	private void setSystemInterfaceDatabaseURL(String systemInterfaceDatabaseURL) {
		this.systemInterfaceDatabaseURL = systemInterfaceDatabaseURL;
	}
	
	private void setSystemInterfaceFrame() {
		systemInterfaceFrame = new JFrame("Sistema de Controle de Vendas e Estoque - CdT");
		try {
			String systemIconImagePath = new File("lib/.").getCanonicalPath() + "\\" + "CDT_icon.png";
			systemInterfaceFrame.setIconImage((new ImageIcon(systemIconImagePath)).getImage());
		} catch (IOException exPathNotFound) {
			systemInterfaceLabelStatus.setText("A imagem do icone do sistema nao foi encontrada!");
			System.out.print(systemInterfaceLabelStatus.getText());
		}
		systemInterfaceFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		systemInterfaceFrame.setResizable(false);
		systemInterfaceFrame.setLayout(new BorderLayout());
		systemInterfaceFrame.setSize(systemInterfaceDimension);
		systemInterfaceFrame.setLocationRelativeTo(null);
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
	
	private void setSystemInterfaceHomeButton() {
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
			systemInterfaceMenu.addMouseListener(new HandlerMenuOptions(systemInterfaceMenuName, null, this));
			for(String systemInterfaceMenuItemName : systemInterfaceMenuItemNames) {
				systemInterfaceMenuItem = new JMenuItem(systemInterfaceMenuItemName);
				systemInterfaceMenuItem.setFont(new Font(null, Font.PLAIN + Font.BOLD, systemInterfaceMenuItem.getFont().getSize() + 3));
				systemInterfaceMenuItem.addMouseListener(new HandlerMenuOptions(systemInterfaceMenuName, systemInterfaceMenuItemName, this));
				systemInterfaceMenu.add(systemInterfaceMenuItem);
			}
			systemInterfaceMenuBar.add(systemInterfaceMenu);
		}
		systemInterfaceMenu = new JMenu("Relatorios");
		systemInterfaceMenu.setFont(new Font(null, Font.PLAIN + Font.BOLD, systemInterfaceMenu.getFont().getSize() + 4));
		systemInterfaceMenu.addMouseListener(new HandlerMenuOptions("Relatorios", null, this));
		systemInterfaceMenuItemNames.clear();
		systemInterfaceMenuItemNames.add("Conferencia de Estoque");
		systemInterfaceMenuItemNames.add("Historico de Lotes");
		systemInterfaceMenuItemNames.add("Comprovante de Venda");
		systemInterfaceMenuItemNames.add("Historico de Vendas");
		
		for(String systemInterfaceMenuItemName : systemInterfaceMenuItemNames) {
			systemInterfaceMenuItem = new JMenuItem(systemInterfaceMenuItemName);
			systemInterfaceMenuItem.setFont(new Font(null, Font.PLAIN + Font.BOLD, systemInterfaceMenuItem.getFont().getSize() + 3));
			systemInterfaceMenuItem.addMouseListener(new HandlerMenuOptions("Relatorios", systemInterfaceMenuItemName, this));
			systemInterfaceMenu.add(systemInterfaceMenuItem);
		}
		systemInterfaceMenuBar.add(systemInterfaceMenu);
	}
	
	private void setSystemInterfaceExitButton() {
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
			try {
			    Thread.sleep(systemInterfaceLoadTime * 2);
			} catch(InterruptedException exThreadFailed) {
				systemInterfaceLabelStatus.setText("Houve algum erro ao carregar o programa!");
			    Thread.currentThread().interrupt();
			}
			systemInterfaceFrame.remove(systemInterfaceLabelImage);
			systemWelcomeImagePath = new File("lib/.").getCanonicalPath() + "\\" + "CDT_background.jpg";
			systemInterfaceLabelImage = new JLabel(new ImageIcon(systemWelcomeImagePath));
		} catch(IOException exPathNotFound) {
			systemInterfaceLabelStatus.setText("Imagem da tela de login nao encontrada!");
		} finally {
			systemInterfaceFrame.setVisible(true);
			systemInterfaceFrame.repaint();
			systemInterfaceStatusMessage = "Home";
			systemInterfaceLabelStatus.setText(systemInterfaceStatusMessage);
			systemInterfacePanelMain = new JPanel(new BorderLayout());
			systemInterfacePanelMain.add(systemInterfaceLabelImage);
			systemInterfaceFrame.add(systemInterfacePanelMain, BorderLayout.CENTER);
		}
	}
	
	public void setSystemInterfaceStatusMessage(String systemInterfaceStatusMessage) {
		systemInterfaceLabelStatus.setText(systemInterfaceStatusMessage);
	}
	
	public void setSystemInterfaceCadastraLotes() {
		systemInterfaceCadastraLotes = new CadastraLotes(this);
	}
	
	public Dimension getSystemInterfaceDimension() {
		return systemInterfaceDimension;
	}
	
	public JLabel getSystemInterfaceLabelStatus() {
		return systemInterfaceLabelStatus;
	}
	
	public String getSystemInterfaceStatusMessage() {
		return systemInterfaceStatusMessage;
	}
	
	public String getSystemInterfaceDatabaseURL() {
		return systemInterfaceDatabaseURL;
	}
	
	public CadastraLotes getSystemInterfaceCadastraLotes() {
		return systemInterfaceCadastraLotes;
	}
	
	protected void clearSystemInterface(Boolean fullClear) {
		systemInterfacePanelMain.removeAll();
		systemInterfacePanelMain.revalidate();
		systemInterfacePanelMain.repaint();
		if(!fullClear)
			systemInterfacePanelMain.add(systemInterfaceLabelImage);
	}
	
	protected class HandlerHomeButton implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(!systemInterfaceBusy) {
				clearSystemInterface(false);
				systemInterfaceCadastraLotes.getPopUps().encerraLote();
				systemInterfaceStatusMessage = "Home";
				systemInterfaceLabelStatus.setText(systemInterfaceStatusMessage);
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			if(!systemInterfaceBusy)
				systemInterfaceLabelStatus.setText("Retorna à tela principal");
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			if(!systemInterfaceBusy)
				systemInterfaceLabelStatus.setText(systemInterfaceStatusMessage);
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if(!systemInterfaceBusy)
				mouseEntered(e);
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			if(!systemInterfaceBusy)
				mouseClicked(e);
		}
	}
	
	private class HandlerExitButton implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(!systemInterfaceBusy) {
				try {
				    Thread.sleep(systemInterfaceLoadTime * 2);
				    systemInterfaceFrame.dispose();
				} catch(InterruptedException exThreadFailed) {
					systemInterfaceLabelStatus.setText("Houve algum erro ao encerrar o programa!");
				    Thread.currentThread().interrupt();
				}
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			if(!systemInterfaceBusy)
				systemInterfaceLabelStatus.setText("Encerra o sistema");
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			if(!systemInterfaceBusy)
				systemInterfaceLabelStatus.setText(systemInterfaceStatusMessage);
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if(!systemInterfaceBusy) {
				clearSystemInterface(false);
				systemInterfaceLabelStatus.setText("Encerrando conexoes..");
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			if(!systemInterfaceBusy)
				mouseClicked(e);
		}
	}
	
	private class HandlerMenuOptions implements MouseListener {
		
		private String systemInterfaceMenuName;
		private String systemInterfaceMenuItemName;
		
		public HandlerMenuOptions(String systemInterfaceMenuName, String systemInterfaceMenuItemName, SystemInterface systemInterface) {
			this.systemInterfaceMenuName = systemInterfaceMenuName;
			this.systemInterfaceMenuItemName = systemInterfaceMenuItemName;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(!systemInterfaceBusy) {
				if(!(e.getSource() instanceof JMenu)) {
					systemInterfaceStatusMessage = systemInterfaceMenuName.equalsIgnoreCase("Relatorios") ? 
						String.format("Home > %s > %s", systemInterfaceMenuName, systemInterfaceMenuItemName) : 
						String.format("Home > %s > %s %s", systemInterfaceMenuName, systemInterfaceMenuItemName, systemInterfaceMenuName.toLowerCase());
					systemInterfaceLabelStatus.setText(systemInterfaceStatusMessage);
					if(e.getSource() instanceof JMenuItem) {
						clearSystemInterface(true);
						if(systemInterfaceMenuItemName.equalsIgnoreCase("Cadastrar")) {
							if(systemInterfaceMenuName.equalsIgnoreCase("Lotes")) {
								systemInterfacePanelMain.add(systemInterfaceCadastraLotes.cadastraLote());
							} else {
								////////////////////////////////////////////////////////////////////////
								systemInterfacePanelMain.add(systemInterfaceCadastraLotes.underConstruction());								
								////////////////////////////////////////////////////////////////////////
							}
						} else if(systemInterfaceMenuItemName.equalsIgnoreCase("Consultar")) {
							////////////////////////////////////////////////////////////////////////
							systemInterfacePanelMain.add(systemInterfaceCadastraLotes.underConstruction());
							////////////////////////////////////////////////////////////////////////
						} else {
							////////////////////////////////////////////////////////////////////////
							systemInterfacePanelMain.add(systemInterfaceCadastraLotes.underConstruction());
							////////////////////////////////////////////////////////////////////////
						}
					}
				} else {
					mouseEntered(e);
				}
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			if(!systemInterfaceBusy) {
				systemInterfaceStatusMessage = systemInterfaceStatusMessage != null && e.getSource() instanceof JMenu &&  
					!(systemInterfaceStatusMessage.equals(systemInterfaceLabelStatus.getText())) ?  
						systemInterfaceStatusMessage : systemInterfaceLabelStatus.getText();
				if(e.getSource() instanceof JMenu) {
					systemInterfaceLabelStatus.setText("Acessa o modulo de " + systemInterfaceMenuName.toLowerCase());
				} else {
					if(systemInterfaceMenuName.equalsIgnoreCase("Relatorios")) {
						systemInterfaceLabelStatus.setText(systemInterfaceMenuName + " de " + systemInterfaceMenuItemName.toLowerCase());
					} else {
						systemInterfaceLabelStatus.setText(systemInterfaceMenuItemName + " " + systemInterfaceMenuName.toLowerCase());
					}
				}
			}
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			if(!systemInterfaceBusy)
				systemInterfaceLabelStatus.setText(systemInterfaceStatusMessage);
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if(!systemInterfaceBusy)
				mouseEntered(e);
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			if(!systemInterfaceBusy)
				mouseClicked(e);
		}
	}
}

