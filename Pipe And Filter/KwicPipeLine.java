import input.AsyncRepository;
import output.AsyncOutput;
import pipeAndFilter.IAsyncFilter;
import shifter.AsyncCircularShift;
import shifter.RightShift;
import alphabetizer.AsyncAlphabetizer;


public class KwicPipeLine {

	private IAsyncFilter<String, String> repositoryFilter;
	private IAsyncFilter<String, Iterable<String>> circularFilter;
	private IAsyncFilter<Iterable<String>, Iterable<String>> alphabetizerFilter;
	private IAsyncFilter<Iterable<String>, String> outputFilter;
	
	private Thread repositoryThread, circularThread, alphabetizerThread, outputThread;
	
	public KwicPipeLine(String filePath){
		
		outputFilter = new AsyncOutput();
		alphabetizerFilter = new AsyncAlphabetizer();
		circularFilter = new AsyncCircularShift(new RightShift());
		repositoryFilter = new AsyncRepository(filePath);
		
		alphabetizerFilter.connectOutputPipeTo(outputFilter.getInputPipe());
		circularFilter.connectOutputPipeTo(alphabetizerFilter.getInputPipe());
		repositoryFilter.connectOutputPipeTo(circularFilter.getInputPipe());
		
		outputThread = new Thread(outputFilter);
		alphabetizerThread = new Thread(alphabetizerFilter);
		circularThread = new Thread(circularFilter);
		repositoryThread = new Thread(repositoryFilter);
	}
	
	public void start(){
		
		outputThread.start();
		alphabetizerThread.start();
		circularThread.start();
		repositoryThread.start();
	}
	
}
