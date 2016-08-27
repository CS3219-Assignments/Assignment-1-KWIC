package output;

import java.io.NotActiveException;
import input.FileRepository;
import input.IAsyncRepository;

public class AOut extends FileRepository implements IAsyncRepository{

	public AOut(String filePath) {
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
