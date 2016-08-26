package sorter;

import java.io.NotActiveException;

public class AsyncAscendingSorter extends AscendingSorter implements IAsyncSorter{

	@Override
	public void run() {
		Iterable<String> data = null;

		while(true){
			try {
				data = getDataFromInputPipe();
				if(data == null)
					continue;
				
				for(String line : data)
					//sortedList.add(line);
					toBeSorted.add(line);
				
			} catch (NotActiveException e) {
				
				sendDataToOutputPipe(sortAscending(toBeSorted));
				closeOutputPipes();
				return;
			}
		}
		
	}

}
