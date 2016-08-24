package input;

import java.util.*;
import java.io.*;

public class FileWriter {

	private Formatter formatter;
	
	public FileWriter(String filePath){
		try {
			formatter = new Formatter(filePath);
		} catch (FileNotFoundException e) {
			formatter = null;
		}
	}
	
	public boolean exists(){
		return formatter != null;
	}
	
	public void write(String text){
		formatter.format("%s", text);
	}
	
	public void close(){
			formatter.close();
	}
}
