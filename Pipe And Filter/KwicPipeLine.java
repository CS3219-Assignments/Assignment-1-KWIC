import java.util.ArrayList;
import java.util.List;

import alphabetizer.AsyncAlphabetizer;
import input.AsyncRepository;
import input.FileRepository;
import output.AsyncOutput;
import pipeAndFilter.IAsyncFilter;
import shifter.AsyncCircularShift;
import shifter.RightShift;
import sorter.AsyncAscendingSorter;


public class KwicPipeLine {

	private IAsyncFilter<String, String> repositoryFilter;
	private IAsyncFilter<String, String> alphabetizerFilter;
	private IAsyncFilter<String, Iterable<String>> circularFilter;
	private IAsyncFilter<Iterable<String>, Iterable<String>> sortingFilter;
	private IAsyncFilter<Iterable<String>, String> outputFilter;
	
	private Thread repositoryThread, alphabetizerThread, circularThread, sortingThread, outputThread;
	
	public KwicPipeLine(String filePath, String noiseFilePath){
		
		List<String> noise = initNoise(noiseFilePath);
		
		outputFilter = new AsyncOutput();
		sortingFilter = new AsyncAscendingSorter();
		circularFilter = new AsyncCircularShift(noise, new RightShift());
		alphabetizerFilter = new AsyncAlphabetizer(noise);
		repositoryFilter = new AsyncRepository(filePath);
		
		sortingFilter.connectOutputPipeTo(outputFilter.getInputPipe());
		circularFilter.connectOutputPipeTo(sortingFilter.getInputPipe());
		alphabetizerFilter.connectOutputPipeTo(circularFilter.getInputPipe());
		repositoryFilter.connectOutputPipeTo(alphabetizerFilter.getInputPipe());
		
		outputThread = new Thread(outputFilter);
		sortingThread = new Thread(sortingFilter);
		circularThread = new Thread(circularFilter);
		alphabetizerThread = new Thread(alphabetizerFilter);
		repositoryThread = new Thread(repositoryFilter);
	}
	
	public void start(){
		
		outputThread.start();
		sortingThread.start();
		circularThread.start();
		alphabetizerThread.start();		
		repositoryThread.start();
	}
	
	private List<String> initNoise(String noiseFilePath){
		FileRepository fileRepo = new FileRepository(noiseFilePath);
		fileRepo.initFileReader(noiseFilePath);
		return fileRepo.getAll();
	}
}
