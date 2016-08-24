package alphabetizer;

import java.util.concurrent.ConcurrentSkipListSet;

import pipeAndFilter.*;

public class Alphabetizer extends Filter<Iterable<String>, Iterable<String>> {
	
	protected ConcurrentSkipListSet<String> sortedList;

	
	public Alphabetizer() {
		sortedList = new ConcurrentSkipListSet<String>(new SortAscendingIgnoreCase());
	}
	
}
