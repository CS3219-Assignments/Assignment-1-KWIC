package shifter;
import java.io.NotActiveException;
import java.util.List;

public class AsyncCircularShift extends CircularShift implements IAsyncCircularShift{

	public AsyncCircularShift(IRightShift rightshift) {
		super(rightshift);
	}

	public AsyncCircularShift(List<String> noiseWordsList, IRightShift rightshift) {
		super(noiseWordsList,rightshift);
	}
	
	
	@Override
	public void run() {
		String data = null;
		Iterable<String> circularShiftedLines = null;
		
		while(true){
			try {
				data = getDataFromInputPipe();
				if(data == null)
					return;
				
				
				circularShiftedLines = shift(data);
				sendDataToOutputPipe(circularShiftedLines);
			} catch (NotActiveException e) {
					closeOutputPipes();	
				return;
			}
		}
	}

	
}
