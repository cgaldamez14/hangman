package hangman;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WordTracker {
	
	private HangmanPane hp;
	private List<String> parsedWord  = new ArrayList<String>();
	private List<String> incorrect  = new ArrayList<String>();
	private List<String> word = new LinkedList<String>();
	private String randomWord;
	private int incorrectGuesses = 0;
	private int length;
	
	public WordTracker(RandomWordGenerator rw, HangmanPane hp){
		this.hp = hp;
		randomWord = rw.generateRandomWord();
		tryWord();
		for(int i = 0; i < getWordLength(); i++){
			parsedWord.add(randomWord.substring(i, i+1));
		}
	}
	
	public int getWordLength(){
		length = randomWord.length();
		return length;
	}
	
	public void tryWord(){
		
		for(int i = 0; i < getWordLength(); i++){
			word.add("*");
		}
	}
	
	
	public void letterGuess(String letter){
		
		if(containsCharacter(letter.toLowerCase())){
			for(Integer i: getMatchedIndexes(letter)){
				word.set(i, letter.toLowerCase());
			}
		}
		hp.setNumberWrong(getIncorrectGuesses());
		System.out.println(getIncorrectGuesses());
	}
	public boolean containsCharacter(String character){
		if(parsedWord.contains(character.toLowerCase())){
			return true;
		}
		else{
			if(!incorrect.contains(character.toLowerCase())){
				incorrect.add(character.toLowerCase());
				incorrectGuesses++;
			}
			return false;
		}
	}
	
	public List<Integer> getMatchedIndexes(String character){
		List<Integer> indexes = new ArrayList<Integer>();
		
		for(int i = 0; i < getWordLength(); i++){
			if(character.compareToIgnoreCase(parsedWord.get(i)) == 0){
				indexes.add(i);
			}
		}
		
		return indexes;
	}
	
	public int getIncorrectGuesses(){
		return incorrectGuesses;
	}
	
	public String getWord(){
		String sb = "";
		for(String s: word){
			sb += s + " ";
		}
		System.out.println(word.toString());
		return sb;
	}
	
	public String getIncorrectList(){
		String sb = "";
		for(String s: incorrect){
			sb += s + " ";
		}
		System.out.println(incorrect.toString());
		return sb;
	}
	
	public boolean wordGuessed(){
		if(!word.contains("*")){
			return true;
		}
		else{
			return false;
		}
	}
	
}
