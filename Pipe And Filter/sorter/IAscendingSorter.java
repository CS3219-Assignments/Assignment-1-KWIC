package sorter;

import pipeAndFilter.IFilter;

public interface IAscendingSorter extends IFilter<Iterable<String>, Iterable<String>>{

	public Iterable<String> sortAscending(Iterable<String> unsortedList);
}
