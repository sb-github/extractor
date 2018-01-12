package com.crawler.extractor.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.crawler.extractor.model.StopWord;
import com.crawler.extractor.model.WordsArray;
import com.crawler.extractor.service.StopWordService;

/**
 * RESTful API for controller with CRUD operations;
 * 
 * @author Dmytro Bilyi
 * 
 * @data 11 January 2017
 * 
 */
@PropertySource(value = "classpath:crawler.properties")
@RestController
@RequestMapping("extractor/rest/v1/stopwords")
public class StopWordController {

	@Autowired
	private StopWordService stopWordService;

	/**
	 * Find all stop words on some page
	 * 
	 * @param page is number of page;
	 * @param size is amount of elements displayed per page;
	 * @return ResponseEntity with the paged list of stop words and http status 'OK' or an error
	 *         message with the http status 'INTERNAL_SERVER_ERROR'
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(
			@RequestParam(value = "page", defaultValue = "${default_page}") int page,
			@RequestParam(value = "size", defaultValue = "${default_size}") int size) {
		try {
			List<StopWord> list = stopWordService.findAll(page, size);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Insert a stop word into database
	 * 
	 * @param wordsArray is the array of stop words that should be added to database;
	 * @return ResponseEntity with the http status 'OK' or an error message with the http status
	 *         'INTERNAL_SERVER_ERROR'
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody WordsArray wordsArray) {
		try {
			stopWordService.create(wordsArray);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Update the stop word in database
	 * 
	 * @param stop word with fields that should be updated;
	 * @return ResponseEntity with the http status 'OK' or an error message with the http status
	 *         'INTERNAL_SERVER_ERROR'
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody StopWord stopWord) {
		try {
			stopWordService.update(stopWord);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
