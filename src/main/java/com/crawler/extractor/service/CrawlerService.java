package com.crawler.extractor.service;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.crawler.extractor.interfaces.IExtractorRepository;
import com.crawler.extractor.model.Crawler;

/**
 * 
 * @author Dmytro Bilyi
 * 
 * @data 10 January 2017
 * 
 */
@Service
public class CrawlerService {

	@Autowired
	private IExtractorRepository extractorRepository;

	/**
	 * Method get crawlers which are located on the current page
	 * 
	 * @param page the number of page;
	 * @param size the amount of items that will be shown;
	 * @return a paged and sized collection of items;
	 */
	public List<Crawler> findAll(int page, int size) {
		Page<Crawler> records = extractorRepository.findAll(new PageRequest(page, size));
		return records.getContent();
	}

	/**
	 * Insert a crawler into database
	 * 
	 * @param crawler the model that should be added to database;
	 * @return id of created crawler
	 */
	public ObjectId create(Crawler crawler) {
		extractorRepository.insert(crawler);
		return crawler.getId();
	}

	/**
	 * Update the crawler in database
	 * 
	 * @param crawler with fields that should be updated;
	 */
	public void update(Crawler crawler) {
		extractorRepository.save(crawler);
	}
}
