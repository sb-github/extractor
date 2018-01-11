package com.crawler.extractor.api;

import com.crawler.extractor.model.Crawler;
import com.crawler.extractor.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * RESTful API for controller with CRUD operations;
 * 
 * @author YevheniiR, Dmytro Bilyi
 * 
 * @data 05 January 2017
 * 
 */
@PropertySource(value = "classpath:crawler.properties")
@RestController
@RequestMapping("extractor/rest/v1/crawler")
public class CrawlerController {

	@Autowired
	private CrawlerService crawlerService;

	/**
	 * Find all crawlers on some page
	 * 
	 * @param page is number of page;
	 * @param size is amount of elements displayed per page;
	 * @return ResponseEntity with the paged list of crawlers and http status 'OK' or an error
	 *         message with the http status 'INTERNAL_SERVER_ERROR'
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(
			@RequestParam(value = "page", defaultValue = "${default_page}") int page,
			@RequestParam(value = "size", defaultValue = "${default_size}") int size) {
		try {
			List<Crawler> list = crawlerService.findAll(page, size);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Insert a crawler into database
	 * 
	 * @param crawler the model that should be added to database;
	 * @return ResponseEntity with the id of created crawler and http status 'OK' or an error
	 *         message with the http status 'INTERNAL_SERVER_ERROR'
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Crawler crawler) {
		try {
			return new ResponseEntity<>(crawlerService.create(crawler), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Update the crawler in database
	 * 
	 * @param crawler with fields that should be updated;
	 * @return ResponseEntity with the http status 'OK' or an error message with the http status
	 *         'INTERNAL_SERVER_ERROR'
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody Crawler crawler) {
		try {
			crawlerService.update(crawler);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
