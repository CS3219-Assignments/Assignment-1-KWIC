import java.util.*;
import java.io.*;

public class Kwic{


	public static void main(String args[]){
		
		new Kwic(new Input(), new CircularShift(), new AlphaShift(), new Output()).run();
	}

	private IInput in;
	private ICircularShift cs;
	private IAlphaShift as;
	private IOutput out;
	
	public Kwic(IInput in, ICircularShift cs, IAlphaShift as, IOutput out){
		this.in = in;
		this.cs = cs;
		this.as = as;
		this.out = out;
	}
	
	public void run(){
		ArrayList<String> inputList = in.getInputList();
		ArrayList<String> ignoreList = in.getIgnoreList();
		
		ArrayList<String> circularList = cs.getCircularList(inputList, ignoreList);

		ArrayList<String> sortedList = as.sortList(circularList);
		
		out.printResults(sortedList);
		out.saveFile(sortedList);
	}
}