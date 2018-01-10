package com.crawler.extractor.api;

import com.crawler.extractor.model.CrawlerConf;
import com.crawler.extractor.repository.ICrawlerConfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * RESTful API for controller with CRUD operations;
 * 
 * @author Dmytro Bilyi
 *
 * @data 09 January 2018
 * 
 */
@RestController
@RequestMapping("extractor/rest/v1/crawler/conf")
public class CrawlerConfController {

	@Autowired
	private ICrawlerConfRepository crawlerConfRepository;

	/**
	 * Find all crawlerConf on some page
	 *
	 * @return List<Crawler> a paged list of crawlerConf;
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<CrawlerConf> getAll() {
		return crawlerConfRepository.findAll();
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
}
