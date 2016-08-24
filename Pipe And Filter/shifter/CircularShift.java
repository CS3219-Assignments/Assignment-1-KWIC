package shifter;

import java.util.*;

import pipeAndFilter.Filter;

public class CircularShift extends Filter<String, Iterable<String>> implements ICircularShift{

	private IRightShift rightShift;
	
	public CircularShift(IRightShift rightshift) {
		this.rightShift = rightshift;
	}
	
	@Override
	public Iterable<String> shift(String text) {
		List<String> shiftedList = new ArrayList<String>();
		String shiftedText = text;
		int numberOfShiftsNeeded = text.split(IRightShift.DELIMITER).length;
		
		for(int i = 0; i < numberOfShiftsNeeded; i++){
			shiftedText = rightShift.shift(shiftedText);
			shiftedList.add(shiftedText);
		}
		
		return shiftedList;
		
	}

}
