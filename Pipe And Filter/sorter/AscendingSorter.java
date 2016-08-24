package sorter;

import java.util.concurrent.ConcurrentSkipListSet;

import pipeAndFilter.*;

public class AscendingSorter extends Filter<Iterable<String>, Iterable<String>> {
	
	protected ConcurrentSkipListSet<String> sortedList;

	
	public AscendingSorter() {
		sortedList = new ConcurrentSkipListSet<String>(new SortAscendingIgnoreCase());
	}
	
}
