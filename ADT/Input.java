import java.util.*;
import java.io.*;

public class Input{
	/**
	* Get the list of input
	* @Return  An arraylist of the input
	*/
	public ArrayList<String> getInputList(){
		String inputFilename;
		ArrayList<String> inputList = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);

		do{
			System.out.print("Input File: ");
			inputFilename = sc.nextLine();
			inputList  = readFile(inputFilename);
		}while(inputList.size() == 0);

		return inputList;
	}

	/**
	* Get the list of ignore words
	* @Return  An arraylist of the ignore words
	*/
	public ArrayList<String> getIgnoreList(){
		String ignoreWordsFilename;
		String decision;
		boolean isExist = false;
		ArrayList<String> ignoreList = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);

		do{
			System.out.print("Ignore Words File: ");
			ignoreWordsFilename = sc.nextLine();

			decision = "";
			isExist = isFileExist(ignoreWordsFilename);

			if(isExist){
				ignoreList = readFile(ignoreWordsFilename);
				if(ignoreList.size() == 0){
					do{
						System.out.println("There is no ignore words in '" + ignoreWordsFilename + "'.");
						System.out.println("Do you want to define ignore words?"); 
						System.out.println("Enter ('Y' or 'y') to insert a new ingnore word file.");
						System.out.println("Enter ('N' or 'n') to continue and generate the output.");
						decision = sc.nextLine();
					}while(!decision.toUpperCase().equals("Y") && !decision.toUpperCase().equals("N"));
				}
			}

			

		}while(decision.toUpperCase().equals("Y") || isExist == false);
		
		return ignoreList;
	}

	/**
	 * Read the specific textfile and print out all the text in it
	 * @Return An arraylist of content of the textfile
	 */
	private static  ArrayList<String> readFile(String fileName){
		// This will reference one line at a time
        String line = null;
        ArrayList<String> inputArrayList = new ArrayList<String>();
        try {

        	
        	//int count = 0;
        	
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			if(isFileEmpty(fileName)){
            	System.out.println("'" + fileName + "'' is empty");
            }else{
				while((line = bufferedReader.readLine()) != null) {
					//System.out.println(++count + ". " + line);
					inputArrayList.add(line);
				}   
			}			
            bufferedReader.close();   
            
        }catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                   
        }

       
        return inputArrayList;
	}
	
	/**
	 * Check if the file is empty
	 * @return true/false
	 */
	private static boolean isFileEmpty(String fileName){
		File file = new File(fileName);

		if(file.length() == 0){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * Check if the file exist 
	 * @return true/false
	 */
	private static boolean isFileExist(String fileName){
		File file = new File(fileName);

		if(file.exists()){
			return true;
		}
		else{
			System.out.println("'" + fileName + "' does not exist");
			return false;
		}
	}

}