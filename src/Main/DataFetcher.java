package Main;

import java.io.File;
import java.io.IOException;

public class DataFetcher {
	public static void Fetch(String usrnm, String pass, String ip){
		try {
			String path = (new File("").getAbsolutePath()) + "\\IPTables.wsf " + ip + " " + usrnm + " " + pass;
			Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd C:\\Users\\student2435 && " + path + "\"");
			Thread.sleep(1000);
			Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
		} catch (IOException e) {} 
		catch (InterruptedException e) {}
		
	}
}
