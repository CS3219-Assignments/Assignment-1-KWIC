package alphabetizer;

import java.io.NotActiveException;
import java.util.ArrayList;

public class AsyncAlphabetizer extends Alphabetizer implements IAsyncAlphabetizer{

	@Override
	public void run() {
		Iterable<String> data = null;

		while(true){
			try {
				data = getDataFromInputPipe();
				
				for(String line : data)
					sortedList.add(line);
				
			} catch (NotActiveException e) {
				ArrayList<String> sortedOutput = new ArrayList<String>();
				
				while(!sortedList.isEmpty()){
				 sortedOutput.add(sortedList.pollFirst());
				}
				sendDataToOutputPipe(sortedOutput);
				closeOutputPipes();
				return;
			}
		}
		
	}

}
