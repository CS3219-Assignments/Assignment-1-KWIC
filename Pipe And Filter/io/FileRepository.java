package io;

import java.io.File;
import java.util.List;

import pipeAndFilter.Filter;


public class FileRepository extends Filter<String, String> implements IFileRepository {

	protected String filePath;
	protected String tempFilePath;
	protected FileReader reader;
	protected FileWriter writer;
	
	public FileRepository(String filePath) {
		this.filePath = filePath;
		tempFilePath = getTempFilePath();
		reader = null;
		writer = null;
	}
	
	private String getTempFilePath(){
		return filePath.substring(0, filePath.length()-4) + "999.txt";
	}
	
	@Override
	public void add(String text) {
		
		if(writer.fileExists()){
			writer.writeLine(text);
		}
		
	}

	@Override
	public void remove(String entity) {
		int index = find(entity);
		
		if(index == -1) //not found
			return;
		else{
			initFileWriter(tempFilePath);
			initFileReader(filePath);
			
			String lineRead = readNext();
			int currentIndex = 0;
			
			while(lineRead != null){
				if(currentIndex != index){
					writer.writeLine(lineRead);
				}
			}
			closeFileReader();
			closeFileWriter();
			
			new File(filePath).delete();
			new File(tempFilePath).renameTo(new File(filePath));
		}
		
	}

	@Override
	public String readNext() {
		return reader.readNext();
	}
	
	@Override
	public void initFileReader(String filePath) {
		reader = new FileReader(filePath);
	}

	@Override
	public void initFileWriter(String filePath) {
		writer = new FileWriter(filePath);
		
	}

	@Override
	public void initFileWriter(String filePath, boolean isAppend) {
		writer = new FileWriter(filePath, isAppend);
	}

	@Override
	public void closeFileReader() {
		reader.close();
		
	}

	
	@Override
	public void closeFileWriter() {
		writer.close();
	}


	@Override
	public int find(String entity) {
		if(reader != null)
			closeFileReader();
		initFileReader(filePath);
		
		String lineRead = readNext();
		int index = 0;
		
		while(lineRead != null){
			if(lineRead.equals(entity))
				return index;
			lineRead = readNext();
			index++;
		}
			
			
		return -1;
	}

	@Override
	public List<String> getAll() {
		return reader.readAll();
	}

	

	
	
}
