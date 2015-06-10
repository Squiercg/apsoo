package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import classes.ItemVenda;
import classes.Produto;

@SuppressWarnings("unused")
public class Confirmation {
	
	private Boolean choice;
	private JDialog frame;
	private Border defaultBorder;
	private SystemInterface systemInterface;
	private Object caller;
	
	public Confirmation(SystemInterface systemInterface, Border defaultBorder, Object caller) {
		this.systemInterface = systemInterface;
		this.defaultBorder = defaultBorder;
		this.caller = caller;
	}
	
	public void requestConfirmation(Integer option) {
		frame = setSystemInterfaceFrame(systemInterface, defaultBorder, choice, caller, option);
		frame.setVisible(true);
		frame.repaint();
	}
	
	private static JDialog setSystemInterfaceFrame(SystemInterface systemInterface, Border defaultBorder, Boolean choice, Object caller, Integer option) {
		JDialog frame = new JDialog(systemInterface.getSystemInterfaceFrame(), true);
		try {
			String systemIconImagePath = new File("lib/.").getCanonicalPath() + "/" + "CDT_icon.png";
			frame.setIconImage((new ImageIcon(systemIconImagePath)).getImage());
		} catch (IOException exPathNotFound) {
			systemInterface.getSystemInterfaceLabelStatus().setText("A imagem do icone do sistema nao foi encontrada!");
		}
		frame.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.setSize(new Dimension((int) (systemInterface.getSystemInterfaceDimension().getWidth() / 4) + 32, (int) (systemInterface.getSystemInterfaceDimension().getHeight() / 6)));
		frame.setLocationRelativeTo(null);
		
		frame.add(setConfirmationWindow(frame, frame.getSize(), defaultBorder, choice, caller, option), BorderLayout.CENTER);
		
		return frame;
	}
	
	private static JPanel setConfirmationWindow(JDialog source, Dimension preferredSize, Border defaultBorder, Boolean choice, Object caller, Integer option) {
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
		
		String text = option == 0 ? "Deseja realmente sair?" : "Concluir operação?";
		placeHolder = new JLabel(text);
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
		button.addActionListener(new ChooseYes(source, caller));
		button.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + 16, (int) (preferredSize.getHeight() / 6) + 8));
		panelLevel3.add(button, BorderLayout.WEST);
		
		button = new JButton("Não");
		button.setBackground(Color.white);
		button.setForeground(Color.black);
		button.addActionListener(new ChooseNo(source, caller));
		button.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + 16, (int) (preferredSize.getHeight() / 6) + 8));
		panelLevel3.add(button, BorderLayout.EAST);
		
		return panelLevel0;
	}
	
	public Boolean getChoice() {
		return choice;
	}
	
	private static class ChooseYes implements ActionListener {
		
		private JDialog source;
		private Object caller;
		
		public ChooseYes(JDialog source, Object caller) {
			this.source = source;
			this.caller = caller;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(caller instanceof CadastraVendas)
				((CadastraVendas) caller).incluiVenda(true);
			if(caller instanceof CadastraLotes)
				((CadastraLotes) caller).incluiLote(true);
			if(caller instanceof SystemInterface)
				((SystemInterface) caller).exitSystemInterface(true);
			if(caller instanceof ModuloCategoria)
				((ModuloCategoria) caller).incluiCategoria(true);
			
			source.dispose();
		}
	}
	
	private static class ChooseNo implements ActionListener {
		
		private JDialog source;
		private Object caller;
		
		public ChooseNo(JDialog source, Object caller) {
			this.source = source;
			this.caller = caller;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(caller instanceof CadastraVendas)
				((CadastraVendas) caller).incluiVenda(false);
			if(caller instanceof CadastraLotes)
				((CadastraLotes) caller).incluiLote(false);
			if(caller instanceof SystemInterface)
				((SystemInterface) caller).exitSystemInterface(false);
			if(caller instanceof ModuloCategoria)
				((ModuloCategoria) caller).incluiCategoria(false);
			
			source.dispose();
		}
	}
}
