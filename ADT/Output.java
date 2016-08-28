import java.util.*;
import java.io.*;

public class Output{
	/**
	* Print the output results
	*/
	public void printResults(ArrayList<String> resultsList){

		resultsList = new ArrayList<String>(new LinkedHashSet<String>(resultsList));

		for(String newSentence : resultsList){
			System.out.println(newSentence);
		}
	}

	/**
	* Save the output results in "output.txt"
	*/
	public void saveFile(ArrayList<String> outputList){
		try {
			File file = new File("output.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);


			outputList = new ArrayList<String>(new LinkedHashSet<String>(outputList));

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