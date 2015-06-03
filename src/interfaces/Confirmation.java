package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Confirmation {
	
	public Confirmation(SystemInterface systemInterface, Border defaultBorder) {
		JFrame frame = setSystemInterfaceFrame(systemInterface, defaultBorder);
		
		frame.setVisible(true);
		frame.repaint();
	}
	
	private static JFrame setSystemInterfaceFrame(SystemInterface systemInterface, Border defaultBorder) {
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
		frame.setSize(new Dimension((int) (systemInterface.getSystemInterfaceDimension().getWidth() / 4) + 32, (int) (systemInterface.getSystemInterfaceDimension().getHeight() / 6)));
		frame.setLocationRelativeTo(null);
		
		frame.add(setConfirmationWindow(frame.getSize(), defaultBorder), BorderLayout.CENTER);
		
		return frame;
	}
	
	private static JPanel setConfirmationWindow(Dimension preferredSize, Border defaultBorder) {
		JPanel panelLevel0 = new JPanel(new BorderLayout());
		Common.makeLateralBorders(panelLevel0, preferredSize, defaultBorder);
		
		JPanel panelLevel1 = new JPanel(new BorderLayout());
		panelLevel0.add(panelLevel1, BorderLayout.CENTER);
		JPanel panelLevel2 = new JPanel(new BorderLayout());
		panelLevel1.add(panelLevel2, BorderLayout.NORTH);
		JPanel panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.NORTH);
		
		JLabel placeHolder = new JLabel("");
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 8)));
		placeHolder.setBorder(defaultBorder);
		panelLevel3.add(placeHolder, BorderLayout.NORTH);
		
		placeHolder = new JLabel("Concluir operação?");
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 5));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 2), (int) (preferredSize.getHeight() / 6)));
		placeHolder.setHorizontalAlignment(SwingConstants.CENTER);
		placeHolder.setBorder(defaultBorder);
		panelLevel3.add(placeHolder, BorderLayout.SOUTH);
		
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 2), (int) (preferredSize.getHeight() / 8)));
		placeHolder.setBorder(defaultBorder);
		panelLevel3.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel2 = new JPanel(new BorderLayout());
		panelLevel1.add(panelLevel2, BorderLayout.CENTER);
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.NORTH);
		
		JButton button = new JButton("Sim");
		float[] hsbColor = Color.RGBtoHSB(51, 122, 183, null); 
		button.setBackground(Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]));
		button.setForeground(Color.white);
//		button.addMouseListener(new HandlerAcceptButton(systemInterface, frame));
		button.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + 16, (int) (preferredSize.getHeight() / 6) + 8));
		panelLevel3.add(button, BorderLayout.WEST);
		/*
		panelLevel1 = panelLevel2;
		panelLevel2 = new JPanel(new BorderLayout());
		panelLevel1.add(panelLevel2, BorderLayout.CENTER);
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.WEST);
		
		placeHolder = new JLabel("");
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 16), (int) (preferredSize.getHeight())));
		placeHolder.setBorder(defaultBorder);
		panelLevel3.add(placeHolder, BorderLayout.WEST);
		*/
		button = new JButton("Não");
		button.setBackground(Color.white);
		button.setForeground(Color.black);
//		button.addMouseListener(new HandlerBackButton(systemInterface, frame));
		button.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + 16, (int) (preferredSize.getHeight() / 6) + 8));
		panelLevel3.add(button, BorderLayout.EAST);
		
		return panelLevel0;
	}
}
