package interfaces;

import java.awt.BorderLayout;
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
	
	public SystemInterface() {
		setBorders();
		setSystemInterfaceFrame();
		
		setSystemInterface();
	}
	
	private void setBorders() {
		systemInterfaceBorderDefault = BorderFactory.createRaisedBevelBorder();
		systemInterfaceBorderEmpty = BorderFactory.createEmptyBorder();
	}
	
	private void setSystemInterfaceFrame() {
		systemInterfaceFrame = new JFrame("SCVE-CdT");
		try {
			String systemIconImagePath = new File("../lib/.").getCanonicalPath() + "\\" + "CDT_icon.PNG";
			systemInterfaceFrame.setIconImage((new ImageIcon(systemIconImagePath)).getImage());
		}
		catch (IOException exPathNotFound) {
			systemInterfaceLabelStatus.setText("A imagem do icone do sistema nao foi encontrada!");
		}
		systemInterfaceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		systemInterfaceFrame.setLayout(new BorderLayout());
		systemInterfaceFrame.setSize(1024, 768);
	}
	
	private void setSystemInterface() {
		try {
			String systemWelcomeImagePath = new File("../lib/.").getCanonicalPath() + "\\" + "CDT_welcome.jpg";
			JLabel systemWelcomeImage = new JLabel(new ImageIcon(systemWelcomeImagePath));
			systemInterfaceFrame.add(systemWelcomeImage);
			systemInterfaceFrame.setVisible(true);
		}
		catch(IOException exPathNotFound) {
			systemInterfaceLabelStatus.setText("Imagem da tela de login nao encontrada!");
		}
		try {
		    Thread.sleep(1337);
		}
		catch(InterruptedException exThreadFailed) {
			systemInterfaceLabelStatus.setText("Houve algum erro ao carregar o programa!");
		    Thread.currentThread().interrupt();
		}
	}
}
