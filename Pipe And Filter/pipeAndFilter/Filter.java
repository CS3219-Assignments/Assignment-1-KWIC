package pipeAndFilter;

import java.io.NotActiveException;
import java.util.ArrayList;


public abstract class Filter<I,O> implements IFilter<I,O>{

	private  IPipe<I> inputPipe;
	private  ArrayList<IPipe<O>> outputPipeList;
	
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
	public I getDataFromInputPipe() throws NotActiveException{ return inputPipe.getData(); }
	
	@Override
	public boolean isInputPipeEmpty(){ return inputPipe.isEmpty();	}
	
	public boolean isOutputPipeEmpty() { 
		for(IPipe<O> outputPipe : outputPipeList)
			if(!outputPipe.isEmpty())
				return false;
		return true;
	}
	@Override
	public void closeOutputPipes(){
		for(IPipe<O> outputPipe : outputPipeList){
			outputPipe.close();
		}

		outputPipeList = null;
		inputPipe = null;
			
	}
	
	@Override
	public IPipe<I> getInputPipe() {
		return inputPipe;
	}
	
	
}
