package com.crawler.extractor.controller;

import com.crawler.extractor.model.Crawler;
import com.crawler.extractor.repository.IExtractorRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author YevheniiR
 * @data 05 January 2017
 *       <p>
 *       <p>
 *       RESTful API for controller with CRUD operations;
 */
@RestController
@RequestMapping("extractor/crawler")
public class CrawlerController {

	private static final String DEFAULT_PAGE = "0";
	private static final String DEFAULT_SIZE = "5";

	@Autowired
	private IExtractorRepository extractorRepository;

	/**
	 * Find all crawlers on some page
	 * 
	 * @param page is number of page; *
	 * @param size is amount of elements displayed per page; *
	 * @return List<Crawler> a paged list of crawlers;
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Crawler> getAll(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
			@RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size) {
		return findAll(page, size);
	}

	/**
	 * Insert a crawler into database
	 * 
	 * @param crawler the model that should be added to database;
	 * @return id of created crawler
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ObjectId create(@RequestBody Crawler crawler) {
		extractorRepository.insert(crawler);
		return crawler.getId();
	}

	/**
	 * Update the crawler in database
	 * 
	 * @param crawler with fields that should be updated;
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody Crawler crawler) {
		extractorRepository.save(crawler);
	}

	/**
	 * Delete the crawler by id
	 * 
	 * @param id the id of crawler that should be deleted;
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void delete(@PathVariable(value = "id") ObjectId id) {
		extractorRepository.delete(id);
	}

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
}
