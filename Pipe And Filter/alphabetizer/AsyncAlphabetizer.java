package alphabetizer;

import java.io.NotActiveException;
import java.util.List;

public class AsyncAlphabetizer extends Alphabetizer implements IAsyncAlphabetizer{

	public AsyncAlphabetizer(List<String> noiseWordsList) {
		super(noiseWordsList);
	}

	@Override
	public void run() {
		String data = null;
		String output = null;
		
		while(true){
			try {
				data = getDataFromInputPipe();
				
				if(data == null)
					continue;
				
				output = alphabetize(data);
				sendDataToOutputPipe(output);
			} catch (NotActiveException e) {
				closeOutputPipes();
				return;
				
				/*
				while(true){
					if(isOutputPipeEmpty()){
						closeOutputPipes();
						break;
					}
						
					else{
						try {
							Thread.sleep(500);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				return;
				*/
			}
		}
	}

}
