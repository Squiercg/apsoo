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

import main.Main;

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
	private CadastraVendas systemInterfaceCadastraVendas;
	private ModuloCategoria systemInterfaceCategorias;
	private SystemInterface systemInterfaceSelfReference;
	private Confirmation systemInterfaceConfirmation;
	
	public SystemInterface(String systemDatabaseURL, String systemInterfaceNomeLoja) {
		setSystemInterfaceSelfReference();
		setSystemInterfaceDatabaseURL(systemDatabaseURL);
		setSystemInterfaceLoadTime(1);
		setSystemInterfaceBusy();
		setSystemInterfaceDefaultBorder();
		setSystemInterfaceConfirmation();
		setSystemInterfaceDimension();
		setSystemInterfaceFrame(systemInterfaceNomeLoja);
		setSystemInterfaceLabelStatus();
		setSystemInterfaceMenuBar();
		setSystemInterfaceHomeButton();
		setSystemInterfaceMenu();
		setSystemInterfaceExitButton();
		setSystemInterface();
		setSystemInterfaceBusy();
		setSystemInterfaceModules();
	}
	
	private void setSystemInterfaceSelfReference() {
		systemInterfaceSelfReference = this;
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
	
	private void setSystemInterfaceConfirmation() {
		Border systemInterfaceAlternateBorder = Main.isBrunoTesting ? systemInterfaceBorderDefault : BorderFactory.createEmptyBorder();
		systemInterfaceConfirmation = new Confirmation(systemInterfaceSelfReference, systemInterfaceAlternateBorder, this);
	}
	
	private void setSystemInterfaceDimension() {
		systemInterfaceDimension = new Dimension(1024, 800);
	}
	
	private void setSystemInterfaceDatabaseURL(String systemInterfaceDatabaseURL) {
		this.systemInterfaceDatabaseURL = systemInterfaceDatabaseURL;
	}
	
	private void setSystemInterfaceFrame(String systemInterfaceNomeLoja) {
		systemInterfaceFrame = new JFrame("SCVE-CdT - " + systemInterfaceNomeLoja);
		try {
			String systemIconImagePath = new File("lib/.").getCanonicalPath() + "/" + "CDT_icon.png";
			systemInterfaceFrame.setIconImage((new ImageIcon(systemIconImagePath)).getImage());
		} catch (IOException exPathNotFound) {
			systemInterfaceLabelStatus.setText("A imagem do �cone do sistema n�o foi encontrada!");
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
				if(!systemInterfaceMenuName.equalsIgnoreCase("Lotes") && !systemInterfaceMenuName.equalsIgnoreCase("Vendas") || !systemInterfaceMenuItemName.equalsIgnoreCase("Consultar")) {
					systemInterfaceMenuItem = new JMenuItem(systemInterfaceMenuItemName);
					systemInterfaceMenuItem.setFont(new Font(null, Font.PLAIN + Font.BOLD, systemInterfaceMenuItem.getFont().getSize() + 3));
					systemInterfaceMenuItem.addMouseListener(new HandlerMenuOptions(systemInterfaceMenuName, systemInterfaceMenuItemName, this));
					systemInterfaceMenu.add(systemInterfaceMenuItem);
				}
			}
			systemInterfaceMenuBar.add(systemInterfaceMenu);
		}
		systemInterfaceMenu = new JMenu("Relat�rios");
		systemInterfaceMenu.setFont(new Font(null, Font.PLAIN + Font.BOLD, systemInterfaceMenu.getFont().getSize() + 4));
		systemInterfaceMenu.addMouseListener(new HandlerMenuOptions("Relat�rios", null, this));
		systemInterfaceMenuItemNames.clear();
		systemInterfaceMenuItemNames.add("Confer�ncia de Estoque");
		systemInterfaceMenuItemNames.add("Hist�rico de Lotes");
		systemInterfaceMenuItemNames.add("Comprovante de Venda");
		systemInterfaceMenuItemNames.add("Hist�rico de Vendas");
		
		for(String systemInterfaceMenuItemName : systemInterfaceMenuItemNames) {
			systemInterfaceMenuItem = new JMenuItem(systemInterfaceMenuItemName);
			systemInterfaceMenuItem.setFont(new Font(null, Font.PLAIN + Font.BOLD, systemInterfaceMenuItem.getFont().getSize() + 3));
			systemInterfaceMenuItem.addMouseListener(new HandlerMenuOptions("Relat�rios", systemInterfaceMenuItemName, this));
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
			String systemWelcomeImagePath = new File("lib/.").getCanonicalPath() + "/" + "CDT_welcome.jpg";
			systemInterfaceLabelImage = new JLabel(new ImageIcon(systemWelcomeImagePath));
			systemInterfaceFrame.add(systemInterfaceLabelImage);
			systemInterfaceFrame.repaint();
			systemInterfaceFrame.setVisible(true);
			try {
			    Thread.sleep(systemInterfaceLoadTime * 2);
			} catch(InterruptedException exThreadFailed) {
				systemInterfaceLabelStatus.setText("Houve algum erro ao carregar o programa!");
			    Thread.currentThread().interrupt();
			}
			systemInterfaceFrame.remove(systemInterfaceLabelImage);
			systemWelcomeImagePath = new File("lib/.").getCanonicalPath() + "/" + "CDT_background.jpg";
			systemInterfaceLabelImage = new JLabel(new ImageIcon(systemWelcomeImagePath));
		} catch(IOException exPathNotFound) {
			systemInterfaceLabelStatus.setText("Imagem da tela de login n�o encontrada!");
		} finally {
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
	
	public void setSystemInterfaceModules() {
		systemInterfaceCadastraLotes = new CadastraLotes(this);
		systemInterfaceCadastraVendas = new CadastraVendas(this);
		systemInterfaceCategorias = new ModuloCategoria(this);
	}
	
	public SystemInterface getSystemInterfaceSelfReference() {
		return systemInterfaceSelfReference;
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
	
	public CadastraVendas getSystemInterfaceCadastraVendas() {
		return systemInterfaceCadastraVendas;
	}
	
	public ModuloCategoria getSystemInterfaceCategorias() {
		return systemInterfaceCategorias;
	}
	
	public JPanel getSystemInterfacePanelMain() {
		return systemInterfacePanelMain;
	}
	
	public JFrame getSystemInterfaceFrame() {
		return systemInterfaceFrame;
	}
	
	protected void clearSystemInterface(Boolean fullClear) {
		systemInterfaceCadastraVendas.getPopUps().encerraVenda();
		systemInterfaceCadastraLotes.getPopUps().encerraLote();
		
		systemInterfacePanelMain.removeAll();
		systemInterfacePanelMain.revalidate();
		systemInterfacePanelMain.repaint();
		if(!fullClear)
			systemInterfacePanelMain.add(systemInterfaceLabelImage);
	}
	
	protected void exitSystemInterface(Boolean systemInterfaceChoice) {
		if(systemInterfaceChoice) {
			if(!systemInterfaceBusy) {
				try {
				    Thread.sleep(systemInterfaceLoadTime);
				    systemInterfaceFrame.dispose();
				} catch(InterruptedException exThreadFailed) {
					systemInterfaceLabelStatus.setText("Houve algum erro ao encerrar o programa!");
				    Thread.currentThread().interrupt();
				}
			}
		}
	}
	
	protected class HandlerHomeButton implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(!systemInterfaceBusy) {
				clearSystemInterface(false);
				systemInterfaceStatusMessage = "Home";
				systemInterfaceLabelStatus.setText(systemInterfaceStatusMessage);
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			if(!systemInterfaceBusy)
				systemInterfaceLabelStatus.setText("Retorna � tela principal");
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
			if(!Main.isBrunoTesting) {
				systemInterfaceConfirmation.requestConfirmation(0);
			} else {
				if(!systemInterfaceBusy) {
					try {
					    Thread.sleep(systemInterfaceLoadTime);
					    systemInterfaceFrame.dispose();
					} catch(InterruptedException exThreadFailed) {
						systemInterfaceLabelStatus.setText("Houve algum erro ao encerrar o programa!");
					    Thread.currentThread().interrupt();
					}
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
				systemInterfaceLabelStatus.setText("Encerrando conex�es..");
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
					systemInterfaceStatusMessage = systemInterfaceMenuName.equalsIgnoreCase("Relat�rios") ? 
						String.format("Home > %s > %s", systemInterfaceMenuName, systemInterfaceMenuItemName) : 
						String.format("Home > %s > %s %s", systemInterfaceMenuName, systemInterfaceMenuItemName, systemInterfaceMenuName.toLowerCase());
					systemInterfaceLabelStatus.setText(systemInterfaceStatusMessage);
					if(e.getSource() instanceof JMenuItem) {
						clearSystemInterface(true);
						if(systemInterfaceMenuItemName.equalsIgnoreCase("Cadastrar")) {
							if(systemInterfaceMenuName.equalsIgnoreCase("Lotes")) {
								systemInterfacePanelMain.add(systemInterfaceCadastraLotes.cadastraLote());
							} else if(systemInterfaceMenuName.equalsIgnoreCase("Vendas")) {
								systemInterfacePanelMain.add(systemInterfaceCadastraVendas.cadastraVenda());
							} else if(systemInterfaceMenuName.equalsIgnoreCase("Categorias")) {
								systemInterfacePanelMain.add(systemInterfaceCategorias.cadastraCategoria());
							} else {
								////////////////////////////////////////////////////////////////////////
								systemInterfacePanelMain.add(Common.underConstruction(systemInterfaceSelfReference));								
								////////////////////////////////////////////////////////////////////////
							}
						} else if(systemInterfaceMenuItemName.equalsIgnoreCase("Consultar")) {
							if(systemInterfaceMenuName.equalsIgnoreCase("Categorias")) {
								systemInterfacePanelMain.add(systemInterfaceCategorias.consultaCategoria(null));
							} else {
								////////////////////////////////////////////////////////////////////////
								systemInterfacePanelMain.add(Common.underConstruction(systemInterfaceSelfReference));								
								////////////////////////////////////////////////////////////////////////
							}
						} else {
							////////////////////////////////////////////////////////////////////////
							systemInterfacePanelMain.add(Common.underConstruction(systemInterfaceSelfReference));
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
					systemInterfaceLabelStatus.setText("Acessa o m�dulo de " + systemInterfaceMenuName.toLowerCase());
				} else {
					if(systemInterfaceMenuName.equalsIgnoreCase("Relat�rios")) {
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

