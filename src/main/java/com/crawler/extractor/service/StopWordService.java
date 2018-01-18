package com.crawler.extractor.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import com.crawler.extractor.model.StopWord;
import com.crawler.extractor.model.WordsArray;
import com.crawler.extractor.repository.IStopWordRepositoryImpl;

/**
 * 
 * @author Dmytro Bilyi
 * 
 * @data 15 January 2017
 * 
 */
@Component
public class StopWordService {

	@Autowired
	private IStopWordRepositoryImpl iStopWordRepositoryImpl;

	/**
	 * Method gets stop words which are located on the current page
	 * 
	 * @param page the number of page;
	 * @param size the amount of items that will be shown;
	 * @return a paged and sized collection of items;
	 */
	public List<StopWord> findAll(int page, int size) {
		Page<StopWord> records = iStopWordRepositoryImpl.findAll(new PageRequest(page, size));
		return records.getContent();
	}

	/**
	 * Insert new stop words into database.
	 * 
	 * @param wordsArray the array of stop words;
	 */
	public void create(WordsArray wordsArray) {

		List<String> words = wordsArray.getWords();
		words.replaceAll(String::toLowerCase);

		List<String> listExistedStopWords = new ArrayList<>();

		List<StopWord> stopWords = iStopWordRepositoryImpl.findByKeyIn(words);

		for (StopWord item : stopWords) {
			listExistedStopWords.add(item.getKey());
		}

		words.removeAll(listExistedStopWords);

		iStopWordRepositoryImpl.insertStopWords(words);
	}

	/**
	 * Update the stopWord in database
	 * 
	 * @param stopWord with fields that should be updated;
	 */
	public void update(StopWord stopWord) {
		iStopWordRepositoryImpl.save(stopWord);
	}
}
