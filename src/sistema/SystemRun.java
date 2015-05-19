package sistema;

import interfaces.SystemInterface;

import java.io.File;
import java.io.IOException;

public class SystemRun {
	
	private String systemDatabaseURL;
	private String systemLabelStatusText;
	private SystemInterface systemInterface;
	
	public SystemRun() {
		setSystemDatabaseURL();
	}
	
	private void setSystemDatabaseURL() {
		try {
			systemDatabaseURL = new File("../lib/.").getCanonicalPath() + "\\" + "CDT_database.sqlite";
			systemLabelStatusText = "Caminho para o banco de dados OK!";
		}
		catch(IOException e) {
			systemLabelStatusText = "O caminho do banco de dados nao foi encontrado!";
		}
	}
	
	
	
	public String getSystemDatabaseURL() {
		return systemDatabaseURL;
	}
	
	public String getSystemLabelStatusText() {
		return systemLabelStatusText;
	}
}
