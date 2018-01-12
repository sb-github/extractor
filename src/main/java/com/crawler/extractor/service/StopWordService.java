package com.crawler.extractor.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import com.crawler.extractor.model.StopWord;
import com.crawler.extractor.model.WordsArray;
import com.crawler.extractor.repository.IStopWordRepository;

@Component
public class StopWordService {

	@Autowired
	private IStopWordRepository stopWordRepository;

	/**
	 * Method gets stop words which are located on the current page
	 * 
	 * @param page the number of page;
	 * @param size the amount of items that will be shown;
	 * @return a paged and sized collection of items;
	 */
	public List<StopWord> findAll(int page, int size) {
		Page<StopWord> records = stopWordRepository.findAll(new PageRequest(page, size));
		return records.getContent();
	}

	/**
	 * Check if stop word is exist in database and insert if it doesn't exist or update if it exist
	 * 
	 * @param wordsArray the array of stop words;
	 */
	public void create(WordsArray wordsArray) {
		StopWord word;
		for (int i = 0; i < wordsArray.getWords().length; i++) {
			word = stopWordRepository.findByKey(wordsArray.getWordsByIndex(i).toLowerCase());
			if (word != null) {
				update(new StopWord(word.getId(), wordsArray.getWordsByIndex(i).toLowerCase(),
						word.getCreatedDate(), new Date()));
			} else {
				update(new StopWord(wordsArray.getWordsByIndex(i).toLowerCase(), new Date(),
						new Date()));
			}
		}
	}

	/**
	 * Update the stopWord in database
	 * 
	 * @param stopWord with fields that should be updated;
	 */
	public void update(StopWord stopWord) {
		stopWordRepository.save(stopWord);
	}
}
