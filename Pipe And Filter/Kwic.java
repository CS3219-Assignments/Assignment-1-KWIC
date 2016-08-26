import java.util.Scanner;
import java.nio.file.*;

public class Kwic {

	private static final String PROMPT_TITLE = "Enter titles file path: ";
	private static final String PROMPT_NOISE = "Enter noise file path: ";
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args){
		
		String inputFilePath, noiseFilePath;
		
		prompt(PROMPT_TITLE);
		inputFilePath = getIntputPath();
		//inputFilePath = "C:\\Users\\User\\Desktop\\input3.txt";
		
		prompt(PROMPT_NOISE);
		noiseFilePath = getIntputPath();
		//noiseFilePath = "C:\\Users\\User\\Desktop\\bb.txt";
		new KwicPipeLine(inputFilePath, noiseFilePath).start();
		
		
	}
	
	private static void prompt(String text){
		System.out.println(text);
	}
	
	
	private static String getIntputPath(){
		
		String line = sc.nextLine();
		
		if(!line.contains("\\"))
			line = Paths.get("").toAbsolutePath().toString() + "\\" + line;
		
		return line;
		
	}
}
