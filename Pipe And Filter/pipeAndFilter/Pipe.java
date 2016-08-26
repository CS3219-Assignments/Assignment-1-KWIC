package pipeAndFilter;
import java.io.NotActiveException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Pipe<T> implements IPipe<T> {

	//To hold data
	private volatile BlockingQueue<T> queue;
	
	private volatile boolean isClosed;
	
 	public Pipe(){
		queue = new ArrayBlockingQueue<T>(2056);
		isClosed = false;
	}
	
	@Override
	public void putData(T entity) {//only to outputpipe
		if(!isClosed)
			try {
				queue.put(entity);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
	}

	@Override
	public T getData() throws NotActiveException{//only from inputpipe
		
		while(true){
			
			if(isClosed && isEmpty()){
				throw new NotActiveException();
			
			}
			else if(isEmpty()){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.err.println(e.getMessage());
				}
				
			}
			else //not empty
				return queue.poll();
			
		}
	}

	@Override
	public boolean isEmpty() {
		return queue.peek() == null;
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
