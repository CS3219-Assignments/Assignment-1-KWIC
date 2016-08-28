import java.util.Scanner;
import java.io.File;
import java.nio.file.*;

public class Kwic {

	private static final String PROMPT_TITLE = "Input File: ";
	private static final String PROMPT_NOISE = "Enter noise file path: ";
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args){
		
		String inputFilePath, noiseFilePath;
		
		/* Get input file path */
		do{
			prompt(PROMPT_TITLE);
			inputFilePath = getInputPath();
			
			if(!fileExists(inputFilePath)){
				System.out.println(inputFilePath + " does not exists.");
				continue;
			}
			
		}while(getFileSize(inputFilePath) ==0);
		
		String decision = "N";
		
		/* get output file path */
		do{
			prompt(PROMPT_NOISE);
			noiseFilePath = getInputPath();
			
			if(!fileExists(noiseFilePath)){
				System.out.println(noiseFilePath + " does not exists.\nEnter [Y/y] to specify another noise file path. [N/n] to continue.");
				decision = getInput();
				noiseFilePath = null;
			}
			else
				break;
			
		}while(decision.toUpperCase().equals("Y"));
		
		
		/* Start Pipeline */
		new KwicPipeLine(inputFilePath, noiseFilePath).start();
		
		
	}
	
	private static void prompt(String text){
		System.out.println(text);
	}
	
	
	private static String getInput(){
		return sc.nextLine();
	}
	
	private static String getInputPath(){
		String line = getInput();	
		return !line.contains("\\") ? Paths.get("").toAbsolutePath().toString() + "\\" + line : line;
	}
	
	private static long getFileSize(String path){
		return new File(path).length();
	}
	
	private static boolean fileExists(String path){
		return new File(path).exists();
	}
}
