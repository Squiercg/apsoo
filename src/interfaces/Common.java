package interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Common {
	
	public static void makeLateralBorders(JPanel panel, Dimension reference, Border style) {
		JLabel placeHolder = new JLabel("");
		placeHolder.setBorder(style);
		placeHolder.setPreferredSize(new Dimension((int) (reference.getWidth() / 16), (int) (reference.getHeight())));
		panel.add(placeHolder, BorderLayout.WEST);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(style);
		placeHolder.setPreferredSize(new Dimension((int) (reference.getWidth() / 32), (int) (reference.getHeight())));
		panel.add(placeHolder, BorderLayout.EAST);
	}
	
	private static int daysInMonth(int year, int month) {
		Calendar mycal = new GregorianCalendar(year, month, 1);
		return mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	private static Set<String> dates = new HashSet<String>();
	static {
	    for (int year = 1900; year < 2050; year++) {
	        for (int month = 1; month <= 12; month++) {
	            for (int day = 1; day <= daysInMonth(year, month); day++) {
	                StringBuilder date = new StringBuilder();
	                date.append(String.format("%02d/", day));
	                date.append(String.format("%02d/", month));
	                date.append(String.format("%04d", year));
	                dates.add(date.toString());
	            }
	        }
	    }
	}
	
	public static boolean isValidDate2(String dateString) {
	    return dates.contains(dateString);
	}
	
	public static boolean isInteger(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}
		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c <= '/' || c >= ':') {
				return false;
			}
		}
		return true;
	}
	
	public static JPanel underConstruction(SystemInterface systemInterface) {
		JPanel panel = new JPanel(new BorderLayout());
		try {
			String systemImagePath = new File("lib/.").getCanonicalPath() + "\\" + "CDT_underconstruction.png";
			JLabel systemInterfaceLabelImage = new JLabel(new ImageIcon(systemImagePath));
			panel.add(systemInterfaceLabelImage, BorderLayout.CENTER);
			systemInterface.getSystemInterfaceLabelStatus().setText("Módulo em manutenção, desculpe o transtorno");
		} catch(IOException exPathNotFound) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Imagem da tela de alerta nao encontrada!");
		}
		return panel;
	}
}
