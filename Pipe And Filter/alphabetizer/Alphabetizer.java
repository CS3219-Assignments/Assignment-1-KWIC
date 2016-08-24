package alphabetizer;

import java.util.LinkedList;
import java.util.List;

import pipeAndFilter.Filter;

public class Alphabetizer extends Filter<String, String> implements IAlphabetizer{

	private List<String> noiseWords;
	
	public Alphabetizer (){
		noiseWords = new LinkedList<String>();
	}
	
	public Alphabetizer(List<String> noiseWordsList) {
		
		this();
		
		for(String str : noiseWordsList)
			noiseWords.add(str.toLowerCase());
	}
	
	@Override
	public String alphabetize(String line) {
		String[] words = line.split(DELIMITER);
		
		for(int i = 0; i < words.length; i++){
			if(noiseWords.contains(words[i].toLowerCase()))
				words[i] = toLowerCase(words[i]);
			else
				words[i] = capitalizeFirstLetter(words[i]);
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(String word : words){
			sb.append(word);
			sb.append(DELIMITER);
		}
		
		return sb.toString().trim();
	}

	private String capitalizeFirstLetter(String word){
		return word.substring(0, 1).toUpperCase() + word.substring(1);
	}
	
	private String toLowerCase(String word){
		return word.toLowerCase();
	}
}
