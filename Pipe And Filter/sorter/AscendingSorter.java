package sorter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import pipeAndFilter.*;

public class AscendingSorter extends Filter<Iterable<String>, Iterable<String>> implements IAscendingSorter{
	
	protected Set<String> toBeSorted;
	
	public AscendingSorter() {
		toBeSorted = new HashSet<String>();
	}
	
	@Override
	public Iterable<String> sortAscending(Iterable<String> unsortedList) {
		return toBeSorted.stream().sorted(new SortAscendingIgnoreCase()).collect(Collectors.toList());
	}
	
}
