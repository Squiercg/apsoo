package interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Methods {
	
	public static JPanel cadastraLote(Dimension preferredSize) {
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JPanel workingPanel = new JPanel(new BorderLayout());
		mainPanel.add(workingPanel, BorderLayout.NORTH);		
		
		JLabel placeHolder = new JLabel("Cadastro de Lotes");
		placeHolder.setBorder(BorderFactory.createRaisedBevelBorder());
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 10));
		workingPanel.add(placeHolder, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(BorderFactory.createRaisedBevelBorder());
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 32), (int) (preferredSize.getHeight())));
		workingPanel.add(placeHolder, BorderLayout.WEST);
		
		return mainPanel;
	}
}
