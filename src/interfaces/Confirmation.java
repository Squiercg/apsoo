package interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Confirmation {
	
	public Confirmation(SystemInterface systemInterface, Border defaultBorder) {
		JFrame frame = setSystemInterfaceFrame(systemInterface);
		
		frame.setVisible(true);
		frame.repaint();
	}
	
	private static JFrame setSystemInterfaceFrame(SystemInterface systemInterface) {
		JFrame frame = new JFrame();
		try {
			String systemIconImagePath = new File("lib/.").getCanonicalPath() + "/" + "CDT_icon.png";
			frame.setIconImage((new ImageIcon(systemIconImagePath)).getImage());
		} catch (IOException exPathNotFound) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de categorias!");
		}
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.setSize(new Dimension((int) (systemInterface.getSystemInterfaceDimension().getWidth() / 4) + 32, (int) (systemInterface.getSystemInterfaceDimension().getHeight() / 6) + 32));
		frame.setLocationRelativeTo(null);
		
		frame.add(setConfirmationWindow(frame.getSize()), BorderLayout.CENTER);
		
		return frame;
	}
	
	private static JPanel setConfirmationWindow(Dimension reference) {
		JPanel panel = new JPanel(new BorderLayout());
		Common.makeLateralBorders(panel, reference, BorderFactory.createRaisedBevelBorder());
		
		return panel;
	}
}
