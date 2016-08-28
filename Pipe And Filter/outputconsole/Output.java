package outputconsole;


import pipeAndFilter.Filter;

public class Output extends Filter<Iterable<String>, String> implements IOutput{
	
	public void print(String entity) {
		System.out.println(entity);
	}
	
}
