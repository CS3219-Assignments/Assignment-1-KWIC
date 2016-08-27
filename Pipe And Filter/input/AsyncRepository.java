package input;


public class AsyncRepository extends FileRepository implements IAsyncRepository{

	public AsyncRepository(String filePath) {
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
