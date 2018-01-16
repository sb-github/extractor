package com.crawler.extractor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import com.crawler.extractor.interfaces.IStopWordRepository;
import com.crawler.extractor.model.StopWord;
import com.crawler.extractor.model.WordsArray;

@Component
public class StopWordService {

	@Autowired
	MongoTemplate mongoTemplate;

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

		List<String> words = wordsArray.getWords();
		words.replaceAll(String::toLowerCase);

		List<String> listExistedStopWords = new ArrayList<>();

		List<StopWord> stopWords = stopWordRepository.findByKeyIn(words);

		for (StopWord item : stopWords) {
			listExistedStopWords.add(item.getKey());
		}

		BulkOperations ops = mongoTemplate.bulkOps(BulkMode.UNORDERED, StopWord.class);
//		Query query = new Query(Criteria.where("key").in(words));
//		Update update = new Update().set("modified_date", new Date());
//
//		ops.updateMulti(query, update);

		words.removeAll(listExistedStopWords);

		List<StopWord> stopWordsToAdd = new ArrayList<>();
		for (String item : words) {
			stopWordsToAdd.add(new StopWord(item, new Date(), new Date()));
		}

		ops.insert(stopWordsToAdd);
//
		ops.execute();
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
