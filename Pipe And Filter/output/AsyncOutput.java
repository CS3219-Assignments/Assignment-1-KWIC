package output;

import java.io.NotActiveException;

public class AsyncOutput extends Output implements IAsyncOutput{

	@Override
	public void run() {
		Iterable<String> data = null;
		
		while(true){
			try {
				data = getDataFromInputPipe();
				for(String line : data)
					System.out.println(line);
			} catch (NotActiveException e) {
				closeOutputPipes();	
				return;
			}
		}
		
		
	}

}
