package io;

import java.io.NotActiveException;
import io.FileRepository;
import io.IAsyncRepository;

public class AsyncOutputFileRepository extends FileRepository implements IAsyncRepository{

	public AsyncOutputFileRepository(String filePath) {
		super(filePath);
	}

	
	@Override
	public void run() {
		initFileWriter(filePath, false);
		
		while(true){
			try {
				String data = getDataFromInputPipe();
				add(data);
			} catch (NotActiveException e) {
				closeFileWriter();
				return;
			}
		}
		
		
	}
}
