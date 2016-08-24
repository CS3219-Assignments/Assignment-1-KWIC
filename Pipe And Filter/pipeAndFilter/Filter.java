package pipeAndFilter;

import java.io.NotActiveException;
import java.util.ArrayList;


public abstract class Filter<I,O> implements IFilter<I,O>{

	private volatile IPipe<I> inputPipe;
	private volatile ArrayList<IPipe<O>> outputPipeList;
	
	public Filter(){
		inputPipe = new Pipe<I>();
		outputPipeList = new ArrayList<IPipe<O>>();
	}
	
	/*
	 * Connects this filters output to another filters input
	 */
	@Override
	public void connectOutputPipeTo(IPipe<O> otherFiltersInputPipe){
		outputPipeList.add(otherFiltersInputPipe);
	}
	
	@Override
	public void sendDataToOutputPipe(O data){ 
		for(IPipe<O> outputPipe : outputPipeList)
			outputPipe.putData(data);
	}
	
	@Override
	public void sendDataToInputPipe(I data){ 
		inputPipe.putData(data);
	}
	
	@Override
	public I getDataFromInputPipe() throws NotActiveException{ return inputPipe.getData(); }
	
	@Override
	public boolean isInputPipeEmpty(){ return inputPipe.isEmpty();	}
	
	@Override
	public void closeOutputPipes(){
		for(IPipe<O> outputPipe : outputPipeList)
			outputPipe.close();
	}
	
	@Override
	public IPipe<I> getInputPipe() {
		return inputPipe;
	}
}
