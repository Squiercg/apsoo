package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import main.Main;

public class Autentica {
	
	private Dimension preferredSize;
	private static Border defaultBorder;
	private JTextField textFieldUserName;
	private JPasswordField textFieldPassWord;
	private JFrame frame;
	
	public Autentica() {
		preferredSize = new Dimension(1024, 800);
		Autentica.defaultBorder = BorderFactory.createEmptyBorder();
		geraTelaLogin();
	}
	
	public void geraTelaLogin() {
		frame = setSystemInterfaceFrame(preferredSize);
		JPanel panelLevel0 = new JPanel(new BorderLayout());
		frame.add(panelLevel0, BorderLayout.CENTER);
		
		Common.makeLateralBorders(panelLevel0, preferredSize, defaultBorder);
		
		JPanel panelLevel1 = new JPanel(new BorderLayout());
		panelLevel0.add(panelLevel1, BorderLayout.CENTER);
		JPanel panelLevel2 = new JPanel(new BorderLayout());
		panelLevel1.add(panelLevel2, BorderLayout.NORTH);
		JPanel panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.NORTH);
		
		JLabel placeHolder = new JLabel("");
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 2), (int) (preferredSize.getHeight() / 64)));
		placeHolder.setBorder(defaultBorder);
		panelLevel3.add(placeHolder, BorderLayout.NORTH);

		placeHolder = new JLabel("Usuário");
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 5));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 2), (int) (preferredSize.getHeight() / 32)));
		placeHolder.setBorder(defaultBorder);
		panelLevel3.add(placeHolder, BorderLayout.SOUTH);
		
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.CENTER);
		
		textFieldUserName = new JTextField();
		float[] hsbColor = Color.RGBtoHSB(184, 207, 229, null);
		Color innerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		hsbColor = Color.RGBtoHSB(122, 138, 153, null);
		Color outerBorderColor = Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]);
		Border compound = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(outerBorderColor), BorderFactory.createLineBorder(innerBorderColor));
		textFieldUserName.setBorder(compound);
		textFieldUserName.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 6), (int) (preferredSize.getHeight() / 32)));
		panelLevel3.add(textFieldUserName, BorderLayout.NORTH);
		
		placeHolder = new JLabel("");
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 2), (int) (preferredSize.getHeight() / 64)));
		placeHolder.setBorder(defaultBorder);
		panelLevel3.add(placeHolder, BorderLayout.CENTER);
		
		placeHolder = new JLabel("Senha");
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 5));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 2), (int) (preferredSize.getHeight() / 32)));
		placeHolder.setBorder(defaultBorder);
		panelLevel3.add(placeHolder, BorderLayout.SOUTH);
		
		panelLevel2 = new JPanel(new BorderLayout());
		panelLevel1.add(panelLevel2, BorderLayout.CENTER);
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.NORTH);
		
		textFieldPassWord = new JPasswordField();
		textFieldPassWord.setBorder(compound);
		textFieldPassWord.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 6), (int) (preferredSize.getHeight() / 32)));
		panelLevel3.add(textFieldPassWord, BorderLayout.NORTH);
		
		placeHolder = new JLabel("");
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 2), (int) (preferredSize.getHeight() / 32)));
		placeHolder.setBorder(defaultBorder);
		panelLevel3.add(placeHolder, BorderLayout.SOUTH);
		
		panelLevel2 = new JPanel(new BorderLayout());
		panelLevel2.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 2), (int) (preferredSize.getHeight() / 10) + 2));
		panelLevel1.add(panelLevel2, BorderLayout.SOUTH);
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.NORTH);
		
		placeHolder = new JLabel("");
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 5));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 6), (int) (preferredSize.getHeight() / 32)));
		placeHolder.setBorder(defaultBorder);
		panelLevel3.add(placeHolder, BorderLayout.WEST);
		
		JButton button = new JButton("OK");
		hsbColor = Color.RGBtoHSB(51, 122, 183, null); 
		button.setBackground(Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]));
		button.setForeground(Color.white);
		button.addActionListener(new Login(frame, textFieldUserName, textFieldPassWord));
		button.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 32) - 
				(int) (preferredSize.getHeight() / 64)));
		panelLevel3.add(button, BorderLayout.EAST);
		
		placeHolder = new JLabel("");
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 2), (int) (preferredSize.getHeight() / 64)));
		placeHolder.setBorder(defaultBorder);
		panelLevel2.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel3.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 2), (int) (preferredSize.getHeight() / 18)));
		panelLevel2.add(panelLevel3, BorderLayout.SOUTH);
		
		panelLevel2 = panelLevel3;
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.NORTH);
		
		
		placeHolder = new JLabel("");
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 6), (int) (preferredSize.getHeight() / 32)));
		placeHolder.setBorder(defaultBorder);
		panelLevel3.add(placeHolder, BorderLayout.WEST);
		
		button = new JButton("Cancelar");
		button.setBackground(Color.white);
		button.setForeground(Color.black);
		button.addActionListener(new Cancel(frame));
		button.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 32) - 
				(int) (preferredSize.getHeight() / 64)));
		panelLevel3.add(button, BorderLayout.EAST);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				textFieldUserName.requestFocusInWindow();
		    }
		});
		
		frame.setVisible(true);
		frame.repaint();
	}
	
	private static JFrame setSystemInterfaceFrame(Dimension preferredSize) {
		JFrame frame = new JFrame("SCVE-CdT");
		
		try {
			String systemIconImagePath = new File("lib/.").getCanonicalPath() + "\\" + "CDT_icon.png";
			frame.setIconImage((new ImageIcon(systemIconImagePath)).getImage());
		} catch (IOException exPathNotFound) {
			
		}
		frame.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.setSize(new Dimension((int) (preferredSize.getWidth() / 2), (int) (preferredSize.getHeight() / 3)));
		frame.setLocationRelativeTo(null);
		
		return frame;
	}
	
	private static class Login implements ActionListener {
		
		private JFrame source;
		private JTextField user;
		private JTextField pass;
		
		public Login(JFrame source, JTextField user, JTextField pass) {
			this.source = source;
			this.user = user;
			this.pass = pass;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(user.getText().equals(Main.userName) && pass.getText().equals(Main.passWord)) {
				source.setVisible(false);
				source.dispose();
				
				EventQueue.invokeLater(new Runnable() {
			        public void run() {
			        	Main.run();
			        }
			    });
			} else {
				user.setBackground(Color.yellow);
				pass.setBackground(Color.yellow);
			}
		}
	}
	
	private static class Cancel implements ActionListener {
		
		private JFrame source;
		
		public Cancel(JFrame source) {
			this.source = source;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			source.dispose();
		}
	}
}
