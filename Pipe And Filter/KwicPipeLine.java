
import alphabetizer.AsyncAlphabetizer;
import io.AsyncInputFileRepository;
import io.FileRepository;
import io.AsyncOutputFileRepository;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import outputconsole.AsyncOutput;
import pipeAndFilter.IAsyncFilter;
import shifter.AsyncCircularShift;
import shifter.RightShift;
import sorter.AsyncAscendingSorter;


public class KwicPipeLine {

	private IAsyncFilter<String, String> repositoryFilter;
	private IAsyncFilter<String, String> alphabetizerFilter;
	private IAsyncFilter<String, Iterable<String>> circularFilter;
	private IAsyncFilter<Iterable<String>, Iterable<String>> sortingFilter;
	private IAsyncFilter<Iterable<String>, String> outputConsoleFilter;
	private IAsyncFilter<String, String> outputFileFilter;
	
	private ArrayList<Thread> threadList;
	
	public KwicPipeLine(String filePath, String noiseFilePath){
		//Get noise words
		List<String> noise = initNoise(noiseFilePath);
		
		//Create required AsyncFilters
		outputFileFilter = new AsyncOutputFileRepository(Paths.get("").toAbsolutePath().toString() +  "\\output.txt");
		outputConsoleFilter = new AsyncOutput();
		sortingFilter = new AsyncAscendingSorter();
		circularFilter = new AsyncCircularShift(noise, new RightShift());
		alphabetizerFilter = new AsyncAlphabetizer(noise);
		repositoryFilter = new AsyncInputFileRepository(filePath);

		//Connect pipes
		outputConsoleFilter.connectOutputPipeTo(outputFileFilter.getInputPipe());
		sortingFilter.connectOutputPipeTo(outputConsoleFilter.getInputPipe());
		circularFilter.connectOutputPipeTo(sortingFilter.getInputPipe());
		alphabetizerFilter.connectOutputPipeTo(circularFilter.getInputPipe());
		repositoryFilter.connectOutputPipeTo(alphabetizerFilter.getInputPipe());
		
		//create threads
		threadList = new ArrayList<Thread>();
		threadList.add(new Thread(outputFileFilter));
		threadList.add(new Thread(outputConsoleFilter));
		threadList.add(new Thread(sortingFilter));
		threadList.add(new Thread(circularFilter));
		threadList.add(new Thread(alphabetizerFilter));
		threadList.add(new Thread(repositoryFilter));
		
	}
	
	//start threads
	public void start(){
		for(Thread thread : threadList)
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
