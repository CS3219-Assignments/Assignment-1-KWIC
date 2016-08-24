package input;

public interface IFileRepository extends IRepository<String>{

	void initFileReader(String filePath);
	void initFileWriter(String filePath);
	
	void closeFileReader();
	void closeFileWriter();
}
