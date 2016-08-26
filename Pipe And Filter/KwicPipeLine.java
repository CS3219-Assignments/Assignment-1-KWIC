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
	
	private ArrayList<Thread> threadPool;
	
	public KwicPipeLine(String filePath, String noiseFilePath){
		//Get noise words
		List<String> noise = initNoise(noiseFilePath);
		
		//Create required AsyncFilters
		outputFilter = new AsyncOutput();
		sortingFilter = new AsyncAscendingSorter();
		circularFilter = new AsyncCircularShift(noise, new RightShift());
		alphabetizerFilter = new AsyncAlphabetizer(noise);
		repositoryFilter = new AsyncRepository(filePath);

		//Connect pipes
		sortingFilter.connectOutputPipeTo(outputFilter.getInputPipe());
		circularFilter.connectOutputPipeTo(sortingFilter.getInputPipe());
		alphabetizerFilter.connectOutputPipeTo(circularFilter.getInputPipe());
		repositoryFilter.connectOutputPipeTo(alphabetizerFilter.getInputPipe());
		
		//create threads
		threadPool = new ArrayList<Thread>();
		threadPool.add(new Thread(outputFilter));
		threadPool.add(new Thread(sortingFilter));
		threadPool.add(new Thread(circularFilter));
		threadPool.add(new Thread(alphabetizerFilter));
		threadPool.add(new Thread(repositoryFilter));
		
	}
	
	//start threads
	public void start(){
		for(Thread thread : threadPool)
			thread.start();
	}
	
	private List<String> initNoise(String noiseFilePath){
		if(noiseFilePath == null)
			return new ArrayList<String>();
		
		FileRepository fileRepo = new FileRepository(noiseFilePath);
		fileRepo.initFileReader(noiseFilePath);
		return fileRepo.getAll();
	}
}
