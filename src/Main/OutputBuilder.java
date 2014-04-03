package Main;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class OutputBuilder {
	private BufferedReader br = null;
	private List<String> divInterface = new ArrayList<String>();
	private List<String> oppInterface = new ArrayList<String>();
	private List<String> IP = new ArrayList<String>();
	
	public OutputBuilder(){
		try {
			br = new BufferedReader(new FileReader((new File("").getAbsolutePath() + "\\output.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void buildData() throws IOException {
		String str;
	    while ((str = br.readLine()) != null && str.length() != 0){
	    	if(str.contains("Entry address")){
	    		String line = br.readLine();
	    		String[] split = line.split("\\s+");
	    		IP.add(split[3]);
	    	}
	    	else if(str.contains("Port ID (outgoing port)")){
	    		str = str.replace("GigabitEthernet", "G");
	    		str = str.replace("FastEthernet", "F");
	    		str = str.replace(",", "");
	    		String[] split = str.split("\\s+");
	    		divInterface.add(split[1]);
	    		oppInterface.add(split[6]);
	    	}
	    }
		orderLists();
	}
	
	private void orderLists() {
		List<String> _divInterface = new ArrayList<String>();
		List<String> _oppInterface = new ArrayList<String>();
		List<String> _IP = new ArrayList<String>();
		
		for(int i = 0; i < 48; i++){
			_divInterface.add(null);
			_oppInterface.add(null);
			_IP.add(null);
		}
		
		for(int i = 0; i < IP.size(); i++){
			int index = getIndex((String) divInterface.get(i));
			_divInterface.set(index, divInterface.get(i));
			_oppInterface.set(index, oppInterface.get(i));
			_IP.set(index, IP.get(i));
		}
		divInterface = _divInterface;
		oppInterface = _oppInterface;
		IP = _IP;
	}

	private int getIndex(String string) {
		String index = "";
		char[] chars = string.toCharArray();
		int i = chars.length - 1;
		while(chars[i] != '/'){
			index = chars[i] + index;
			i--;
		}
		return new Integer(index);
	}

	public String toString(){
		try {
			buildData();
		} catch (IOException e) {}
		
		Object[] ipAddresses = IP.toArray();
		Object[] oppositeInt = oppInterface.toArray();
		Object[] diviceInt = divInterface.toArray();
		
		String table = "";
		
		for(int i = 0; i < diviceInt.length; i++){
			if(ipAddresses[i] != null){
				table = table + diviceInt[i] + " - " + ipAddresses[i] + " - " + oppositeInt[i] + "\n"; 
			}
		}
		
		return table;
	}

}
