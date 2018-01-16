package com.crawler.extractor.model;

import java.util.List;

/**
 * Class that represents collection of stop_words;
 * 
 * @author Dmytro Bilyi
 *
 * @data 12 January 2018
 *
 */
public class WordsArray {

	private List<String> words;

	WordsArray() {}

	public List<String> getWords() {
		return words;
	}

	public void setWords(List<String> words) {
		this.words = words;
	}
}
