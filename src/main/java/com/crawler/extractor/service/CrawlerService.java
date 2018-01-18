package com.crawler.extractor.service;

import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.crawler.extractor.exception.CrawlerException;
import com.crawler.extractor.model.Crawler;
import com.crawler.extractor.model.Status;
import com.crawler.extractor.repository.IExtractorRepository;

/**
 * 
 * @author Dmytro Bilyi, Stas Omelchenko
 * 
 * @date 10 January 2017
 * 
 */
@PropertySource(value = "classpath:application.properties")
@Service
public class CrawlerService {

	@Autowired
	private IExtractorRepository extractorRepository;
	@Autowired
	private CrawlerConfService confService;
	@Value("${crawler.url}")
	private String crawlerURL;
	@Value("${crawler.urn}")
	private String crawlerURN;

	/**
	 * Method get crawlers which are located on the current page.
	 * 
	 * @param page the number of page
	 * @param size the amount of items that will be shown;
	 * @return a paged and sized collection of items;
	 */
	public List<Crawler> findAll(int page, int size) {
		Page<Crawler> records = extractorRepository.findAll(new PageRequest(page, size));
		return records.getContent();
	}

	/**
	 * Method gets crawler by id
	 * 
	 * @param id the id of Crawler that should be shown
	 * @return the entity of Crawler form db
	 */
	public Crawler findOne(ObjectId id) {
		return extractorRepository.findOne(id);
	}

	/**
	 * Update the crawler in database.
	 * 
	 * @param crawler with fields that should be updated
	 */
	public void update(Crawler crawler) {
		extractorRepository.save(crawler);
	}

	/**
	 * Run the crawler.
	 * 
	 * @param searchCondition the criteria for crawler
	 * @throws CrawlerException
	 */
	public void run(String searchCondition) throws CrawlerException {
		if (isMaxActive()) {
			throw new CrawlerException("The maximum number of crawlers is already running.");
		}

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<?> result = restTemplate.getForEntity(
				crawlerURL + crawlerURN + "?id=" + createCrawler(searchCondition),
				ResponseEntity.class, searchCondition);

		if (result.getStatusCode() != HttpStatus.OK)
			throw new CrawlerException("Crawler wasn't created.");
	}

	private boolean isMaxActive() {
		Integer maxActiveCrawler = confService.getMaxNumberActiveCrawler();
		Integer activeCrawler =
				extractorRepository.findByStatusIn(Status.NEW, Status.IN_PROCESS).size();

		return activeCrawler >= maxActiveCrawler;
	}

	private ObjectId createCrawler(String searchCondition) {
		return extractorRepository
				.insert(new Crawler(Status.NEW, new Date(), new Date(), searchCondition)).getId();
	}
}
