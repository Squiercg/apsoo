package main;

import interfaces.Autentica;
import sistema.SystemRun;

@SuppressWarnings("unused")
public class Main {
	
	public static String userName = "admin";
	public static String passWord = "admin";
	public static String nomeLoja = "Advertência Modas";
	
	public static Boolean isBrunoTesting = false;
	
	public static void main(String[] args) {
		
		Autentica auth = new Autentica();
	}
	
	public static void run() {
		SystemRun run = new SystemRun(nomeLoja);
	}
}
