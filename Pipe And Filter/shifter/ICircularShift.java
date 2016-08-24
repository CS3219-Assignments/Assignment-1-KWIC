package shifter;

import pipeAndFilter.IFilter;

public interface ICircularShift extends IFilter<String, Iterable<String>>{
	Iterable<String> shift(String text);
}
