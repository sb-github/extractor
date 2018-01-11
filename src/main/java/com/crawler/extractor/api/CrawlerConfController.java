package com.crawler.extractor.api;

import com.crawler.extractor.model.CrawlerConf;
import com.crawler.extractor.service.CrawlerConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	private CrawlerConfService crawlerConfService;

	/**
	 * Find all crawlerConf
	 *
	 * @return ResponseEntity with the list of crawlerConf and http status 'OK' or an error message
	 *         with the http status 'INTERNAL_SERVER_ERROR'
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		try {
			List<CrawlerConf> list = crawlerConfService.findAll();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Update the crawlerConf in database
	 * 
	 * @param crawlerConf with fields that should be updated;
	 * @return ResponseEntity with the http status 'OK' or an error message with the http status
	 *         'INTERNAL_SERVER_ERROR'
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody CrawlerConf crawlerConf) {
		try {
			crawlerConfService.update(crawlerConf);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
