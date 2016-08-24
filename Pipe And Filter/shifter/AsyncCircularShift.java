package shifter;
import java.io.NotActiveException;

public class AsyncCircularShift extends CircularShift implements IAsyncCircularShift{

	public AsyncCircularShift(IRightShift rightshift) {
		super(rightshift);
	}

	
	@Override
	public void run() {
		String data = null;
		Iterable<String> circularShiftedLines = null;
		
		while(true){
			try {
				data = getDataFromInputPipe();
				circularShiftedLines = shift(data);
				sendDataToOutputPipe(circularShiftedLines);
			} catch (NotActiveException e) {
				closeOutputPipes();
				return;
			}
		}
	}

	
}
