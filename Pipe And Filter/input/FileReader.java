package input;

import java.util.*;
import java.io.*;

public class FileReader {

	private BufferedReader reader;
	
	public FileReader(String filePath){
			try {
				reader = new BufferedReader(new java.io.FileReader(filePath));
			} catch (FileNotFoundException e) {
				reader = null;
			}
	}
	
	public boolean exists(){
		return reader != null;
	}
	
	public List<String> readAll() {
		List<String> lines = new ArrayList<String>();
		
		String line = null;
		try {
			line = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(line != null){
			lines.add(line);
			try {
				line = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return lines.size() == 0 ? null : lines;
	}

	public String readNext() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void close(){
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
