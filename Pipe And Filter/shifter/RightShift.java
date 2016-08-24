package shifter;

public class RightShift implements IRightShift{
	
	@Override
	public String shift(String text) {
		String[] words = text.split(DELIMITER);
		int lastIndex = words.length - 1;
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(words[lastIndex]);
		stringBuilder.append(DELIMITER);
		for(int i = 0; i < lastIndex; i++){
			stringBuilder.append(words[i]);
			stringBuilder.append(DELIMITER);
		}
		
		return stringBuilder.toString().trim();
	}

	
}
