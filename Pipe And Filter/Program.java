import output.AsyncOutput;
import input.AsyncRepository;
import pipeAndFilter.IAsyncFilter;
import shifter.AsyncCircularShift;
import shifter.RightShift;
import alphabetizer.AsyncAlphabetizer;


public class Program {

	public static void main(String[] args){
		String filePath = "C:\\Users\\User\\Desktop\\input2.txt";
		
		new KwicPipeLine(filePath).start();;
		
		
	}
}
