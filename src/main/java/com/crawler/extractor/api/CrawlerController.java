package com.crawler.extractor.api;

import java.util.List;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.crawler.extractor.exception.CrawlerException;
import com.crawler.extractor.model.Crawler;
import com.crawler.extractor.service.CrawlerService;

/**
 * RESTful API for controller with CRUD operations.
 * 
 * @author YevheniiR, Dmytro Bilyi, Stas Omelchenko
 * 
 * @date 05 January 2017
 * 
 */

@RestController
@RequestMapping("extractor/rest/v1/crawler")
public class CrawlerController {
	
	private static final Logger LOGGER = Logger.getLogger("api-logger");

	@Autowired
	private CrawlerService crawlerService;

	/**
	 * Find all crawlers on some page.
	 * 
	 * @param page is number of page
	 * @param size is amount of elements displayed per page
	 * @return ResponseEntity with the paged list of crawlers and http status 'OK' or an error
	 *         message with the http status 'INTERNAL_SERVER_ERROR'
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(
			@RequestParam(value = "page", defaultValue = "${default_page}") int page,
			@RequestParam(value = "size", defaultValue = "${default_size}") int size) {
		try {
			LOGGER.info("page: " + page + ", size: " + size);
			List<Crawler> list = crawlerService.findAll(page, size);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Run a crawler.
	 * 
	 * @param searchCondition the criteria for crawler
	 * @return ResponseEntity with http status 'OK' or an error message with the http status
	 *         'INTERNAL_SERVER_ERROR'
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/run")
	public ResponseEntity<?> run(@RequestParam(value = "searchcondition") String searchCondition) {
		try {
			LOGGER.info("searchCondition: " + searchCondition);
			crawlerService.run(searchCondition);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (CrawlerException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Update the crawler in database.
	 * 
	 * @param crawler with fields that should be updated
	 * @return ResponseEntity with the http status 'OK' or an error message with the http status
	 *         'INTERNAL_SERVER_ERROR'
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody Crawler crawler) {
		try {
			LOGGER.info("crawler: " + crawler);
			crawlerService.update(crawler);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Find the Crawler by id
	 * 
	 * @param id the id of Crawler that should be shown
	 * @return the entity of Crawler form db
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable(value = "id") ObjectId id) {
		try {
			LOGGER.info("id: " + id);
			return new ResponseEntity<>(crawlerService.findOne(id), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
