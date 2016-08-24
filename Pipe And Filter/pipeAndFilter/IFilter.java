package pipeAndFilter;

import java.io.NotActiveException;

public interface IFilter<I,O> {

	public void connectOutputPipeTo(IPipe<O> otherFiltersInputPipe);
	
	public void sendDataToInputPipe(I data);
	
	public void sendDataToOutputPipe(O data);
	
	public I getDataFromInputPipe() throws NotActiveException;
	
	public boolean isInputPipeEmpty();
	
	public IPipe<I> getInputPipe();
	
	public void closeOutputPipes();
}
