package hangman;

import java.util.ArrayList;
import java.util.List;

public class RandomWordGenerator {
	private String[] words = {"computer", "inheritance", "abstract", "method", "class", "science", "confusing", "set", "map", "code","spring","fall","winter", "school", "motorcycle"};
	private List<String> usedWordsList = new ArrayList<>();
	private int index;
	
	public RandomWordGenerator(){}
	
	public String generateRandomWord(){
		getIndex();
		usedWordsList.add(words[index]);
		return words[index];
	}
	
	private void getIndex(){
		int index = (int)(Math.random() * words.length); 
		while(usedWordsList.contains(words[index])){
			index = (int)(Math.random() * words.length);
		}
		this.index = index;
	}
	
	public String getWord(){
		return words[index];
	}
	
	public boolean noMoreWords(){
		if(usedWordsList.size() == words.length){
			return true;
		}
		else{
			return false;
		}
	}
}
