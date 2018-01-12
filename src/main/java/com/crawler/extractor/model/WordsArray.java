package com.crawler.extractor.model;

/**
 * Class that represents collection of stop_words;
 * 
 * @author Dmytro Bilyi
 *
 * @data 12 January 2018
 *
 */
public class WordsArray {

	private String[] words;

	WordsArray() {}

	public String[] getWords() {
		return words;
	}
	
	public String getWordsByIndex(int index) {
		return words[index];
	}

	public void setWords(String[] words) {
		this.words = words;
	}
}
