package output;

import pipeAndFilter.IFilter;

public interface IOutput extends IFilter<Iterable<String>, String>{

	public void print(String line);
}
