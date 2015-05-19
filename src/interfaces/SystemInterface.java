package interfaces;

import javax.swing.ButtonGroup;
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
		
		public SystemInterface(System systemMain) {
			
		}
}
