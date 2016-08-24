package shifter;

import java.util.*;

import pipeAndFilter.Filter;

public class CircularShift extends Filter<String, Iterable<String>> implements ICircularShift{

	private IRightShift rightShift;
	private List<String> noiseWords;
	
	
	public CircularShift(IRightShift rightshift) {
		this.rightShift = rightshift;
		noiseWords = new LinkedList<String>();
	}
	
	public CircularShift(List<String> noiseWordsList, IRightShift rightshift) {
		this(rightshift);
		
		for(String str : noiseWordsList)
			noiseWords.add(str.toLowerCase());
	}
	
	
	
	@Override
	public Iterable<String> shift(String text) {
		List<String> shiftedList = new ArrayList<String>();
		String shiftedText = text;
		int numberOfShiftsNeeded = text.split(IRightShift.DELIMITER).length;
		
		for(int i = 0; i < numberOfShiftsNeeded; i++){
			shiftedText = rightShift.shift(shiftedText);
			
			if(!IsNoise(shiftedText))
				shiftedList.add(shiftedText);
		}
		
		return shiftedList;
		
	}

	private boolean IsNoise(String line){
		String[] words = line.split(IRightShift.DELIMITER);
		
		return noiseWords.contains(words[0].toLowerCase());
	}
}
