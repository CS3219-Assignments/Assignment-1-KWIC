import java.util.*;
import java.io.*;

public class Kwic{


	public static void main(String args[]){
		
		Input in = new Input();
		ArrayList<String> inputList = in.getInputList();
		ArrayList<String> ignoreList = in.getIgnoreList();
		
		CircularShift cs = new CircularShift();
		ArrayList<String> circularList = cs.getCircularList(inputList, ignoreList);

		AlphaShift as = new AlphaShift();
		ArrayList<String> sortedList = as.sortList(circularList);
		
		Output out = new Output();
		out.printResults(sortedList);
		out.saveFile(sortedList);
	}


}