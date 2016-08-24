package input;

import java.util.*;
import java.io.*;

public class FileReader {

	private Scanner scanner;
	
	public FileReader(String filePath){
			try {
				scanner = new Scanner(new File(filePath));
			} catch (FileNotFoundException e) {
				scanner = null;
			}
	}
	
	public boolean exists(){
		return scanner != null;
	}
	
	public List<String> readAll(){
		List<String> lines = new ArrayList<String>();
		
		while(scanner.hasNextLine()){
			lines.add(scanner.nextLine());
		}
		
		return lines.size() == 0 ? null : lines;
	}

	public String readNext(){
		
		return scanner.hasNextLine() ? scanner.nextLine(): null; 
	}
	
	public void close(){
			scanner.close();
	}
}
