package alphabetizer;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

import pipeAndFilter.*;

public class Alphabetizer extends Filter<Iterable<String>, Iterable<String>> implements ISortable<String>{
	
	protected ConcurrentSkipListSet<String> sortedList;

	
	public Alphabetizer() {
		sortedList = new ConcurrentSkipListSet<String>();
	}
	
	@Override
	public Iterable<String> sortAscending(Iterable<String> collection) {
		List<String> toBeSorted = new ArrayList<String>();
		collection.forEach(toBeSorted::add);
		Collections.sort(toBeSorted, new SortIgnoreCase());
		
		return toBeSorted;
	}

	@Override
	public Iterable<String> sortDescending(Iterable<String> collection) {
		List<String> toBeSorted = new ArrayList<String>();
		collection.forEach(toBeSorted::add);
		Collections.sort(toBeSorted, Collections.reverseOrder());
		
		return toBeSorted;
	}

	
}
