package sistema;

import interfaces.SystemInterface;

import java.io.File;
import java.io.IOException;

public class SystemRun {
	
	private static String systemDatabaseURL;
	private SystemInterface systemInterface;
	private String systemStartMessage;
	
	public SystemRun(String systemNomeLoja) {
		setSystemDatabaseURL();
		setSystemInterface(systemNomeLoja);
	}
	
	private void setSystemDatabaseURL() {
		try {
			systemDatabaseURL = "jdbc:sqlite:" + new File("lib/.").getCanonicalPath() + "/" + "CDT_database.sqlite";
			systemStartMessage = "Conectado ao banco de dados!";
		}
		catch(IOException e) {
			systemStartMessage = "O caminho do banco de dados nao foi encontrado!";
		}
	}
	
	private void setSystemInterface(String systemNomeLoja) {
		systemInterface = new SystemInterface(systemDatabaseURL, systemNomeLoja);
		systemInterface.setSystemInterfaceStatusMessage(systemStartMessage);
	}
	
	@SuppressWarnings("unused")
	private SystemInterface getSystemInterface() {
		return systemInterface;
	}
	
	public static String getSystemDatabaseURL() {
		return systemDatabaseURL;
	}
}
