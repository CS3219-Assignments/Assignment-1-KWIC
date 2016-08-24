package alphabetizer;

import pipeAndFilter.IFilter;

public interface IAlphabetizer extends IFilter<String, String> {

	public static final String DELIMITER = " ";
	
	public String alphabetize(String line);
	
}
