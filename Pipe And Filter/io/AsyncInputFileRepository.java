package io;


public class AsyncInputFileRepository extends FileRepository implements IAsyncRepository{

	public AsyncInputFileRepository(String filePath) {
		super(filePath);
	}
	
	@Override
	public void run() {
		String lineRead = null;

		while(true){
		
			initFileReader(filePath);
			lineRead = readNext();
			
			while(lineRead != null){
				sendDataToOutputPipe(lineRead);
				lineRead = readNext();
			}
			
			closeFileReader();
			closeOutputPipes();
						
			return;
		}
	}

}
