package pipeAndFilter;

import java.io.NotActiveException;

public interface IPipe<T> {
	
	//add data to a pipe
	void putData(T entity);
	
	//Remove data from a pipe
	T getData() throws NotActiveException;
	
	//Check if pipe is empty
	boolean isEmpty();
	
	boolean isClosed();
	
	void close();
}
