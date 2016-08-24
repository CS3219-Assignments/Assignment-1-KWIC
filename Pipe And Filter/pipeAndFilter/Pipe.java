package pipeAndFilter;
import java.io.NotActiveException;
import java.util.*;


public class Pipe<T> implements IPipe<T> {

	//To hold data
	private volatile Queue<T> queue;
	
	private volatile boolean isClosed;
	
 	public Pipe(){
		queue = new LinkedList<T>();
		isClosed = false;
	}
	
	@Override
	public void putData(T entity) {
		if(!isClosed)
			queue.offer(entity);
	}

	@Override
	public T getData() throws NotActiveException{
		
		while(true){
			
			if(isEmpty()){
				
				if(isClosed)
					throw new NotActiveException();
				else{
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						System.err.println(e.getMessage());
					}
				}
			}
			else //not empty
				return queue.poll();
			
		}
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	
	@Override
	public boolean isClosed() {
		return isClosed;
	}

	
	@Override
	public void close() {
		isClosed = true;
	}

	
}
