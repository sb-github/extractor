package com.crawler.extractor.controller;

import com.crawler.extractor.model.CrawlerConf;
import com.crawler.extractor.repository.ICrawlerConfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author Dmytro Bilyi
 *
 * @data 09 January 2018
 *
 *       <p>
 *       RESTful API for controller with CRUD operations;
 */
@RestController
@RequestMapping("extractor/crawler/conf")
public class CrawlerConfController {

	private static final String DEFAULT_PAGE = "0";
	private static final String DEFAULT_SIZE = "5";

	@Autowired
	private ICrawlerConfRepository crawlerConfRepository;

	/**
	 * Find all crawlerConf on some page
	 * 
	 * @param page is number of page;
	 * @param size is amount of elements displayed per page;
	 * @return List<Crawler> a paged list of crawlerConf;
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<CrawlerConf> getAll(
			@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
			@RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size) {
		return findAll(page, size);
	}

	/**
	 * Update the crawlerConf in database
	 * 
	 * @param crawlerConf with fields that should be updated;
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody CrawlerConf crawlerConf) {
		crawlerConfRepository.save(crawlerConf);
	}

	/**
	 * Method get crawlerConf which are located on the current page
	 * 
	 * @param page the number of page;
	 * @param size the amount of items that will be shown;
	 * @return a paged and sized collection of items;
	 */
	public List<CrawlerConf> findAll(int page, int size) {
		Page<CrawlerConf> records = crawlerConfRepository.findAll(new PageRequest(page, size));
		return records.getContent();
	}
}
