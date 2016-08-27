package input;

public interface IFileRepository extends IRepository<String>{

	void initFileReader(String filePath);
	void initFileWriter(String filePath);
	void initFileWriter(String filePath, boolean isAppend);
	
	void closeFileReader();
	void closeFileWriter();
}
