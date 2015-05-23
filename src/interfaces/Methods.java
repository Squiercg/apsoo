package interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Methods {
	
	public static JPanel cadastraLote(Dimension preferredSize) {
		JPanel mainPanel = new JPanel(new BorderLayout());
		Border defaultBorder = BorderFactory.createEmptyBorder();
		makeLateralBorders(mainPanel, preferredSize, defaultBorder);
		
		JPanel sectionPanel = new JPanel(new BorderLayout());
		mainPanel.add(sectionPanel, BorderLayout.CENTER);
		
		JPanel workingPanel = new JPanel(new BorderLayout());
		sectionPanel.add(workingPanel, BorderLayout.NORTH);
		
		JLabel placeHolder = new JLabel("");
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		workingPanel.add(placeHolder, BorderLayout.NORTH);
		
		placeHolder = new JLabel("Cadastro de Lotes");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 20));
		workingPanel.add(placeHolder, BorderLayout.CENTER);
		
		String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
		JComboBox petList = new JComboBox(petStrings);
		petList.setSelectedIndex(4);
		workingPanel.add(petList, BorderLayout.SOUTH);
		
		
		return mainPanel;
	}
	
	private static void makeLateralBorders(JPanel panel, Dimension reference, Border style) {
		JLabel placeHolder = new JLabel("");
		placeHolder.setBorder(style);
		placeHolder.setPreferredSize(new Dimension((int) (reference.getWidth() / 16), (int) (reference.getHeight())));
		panel.add(placeHolder, BorderLayout.WEST);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(style);
		placeHolder.setPreferredSize(new Dimension((int) (reference.getWidth() / 32), (int) (reference.getHeight())));
		panel.add(placeHolder, BorderLayout.EAST);
	}
}
