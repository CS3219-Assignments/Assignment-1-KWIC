package input;

import java.util.*;
import java.io.*;

public class FileWriter {

	private Formatter formatter;
	private String filePath;
	
	public FileWriter(String filePath){
		this(filePath, true);
	}
	
	public FileWriter(String filePath, boolean isAppend){
		this.filePath = filePath;
		if(!isAppend){
			if(fileExists()){
				new File(filePath).delete();
			}else{
				try {
					new File(filePath).createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		try {
			formatter = new Formatter(filePath);
		} catch (FileNotFoundException e) {
			formatter = null;
		}
	}
	
	public boolean fileExists(){
		return new File(filePath).exists();
	}
	
	public void write(String text){
		formatter.format("%s", text);
	}
	
	public void writeLine(String text){
		write(text + "\n");
	}
	
	public void writeLines(Iterable<String> lines){
		for(String line : lines)
			writeLine(line);
	}
	public void close(){
			formatter.close();
	}
}
