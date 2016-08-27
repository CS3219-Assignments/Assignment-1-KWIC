package pipeAndFilter;

import java.io.NotActiveException;

public interface IFilter<I,O> {

	public void connectOutputPipeTo(IPipe<O> otherFiltersInputPipe);
	
	public void sendDataToOutputPipe(O data);
	
	public void sendDataToOutputPipe(O data, boolean isBroadcast);
	
	public I getDataFromInputPipe() throws NotActiveException;
	
	public boolean isInputPipeEmpty();
	
	public boolean isOutputPipeEmpty();
	
	public IPipe<I> getInputPipe();
	
	public void closeOutputPipes();
}
