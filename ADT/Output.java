import java.util.*;
import java.io.*;

public class Output{
	/**
	* Print the output results
	*/
	public static void printResults(ArrayList<String> resultsList){
		for(String newSentence : resultsList){
			System.out.println(newSentence);
		}
	}

	/**
	* Save the output results in "output.txt"
	*/
	public static void saveFile(ArrayList<String> outputList){
		try {
			File file = new File("output.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			for (String output : outputList){
				bw.write(output);
				bw.newLine();
			}
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}

	}
}