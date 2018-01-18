package com.crawler.extractor.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.crawler.extractor.model.CrawlerConf;
import com.crawler.extractor.repository.ICrawlerConfRepository;

/**
 * 
 * @author Dmytro Bilyi, Stas Omelchenko
 * 
 * @date 05 January 2017
 * 
 */
@Component
public class CrawlerConfService {

	@Autowired
	private ICrawlerConfRepository crawlerConfRepository;

	/**
	 * Find all crawlerConf
	 * 
	 * @return collection of items;
	 */
	public List<CrawlerConf> findAll() {
		return crawlerConfRepository.findAll();
	}

	/**
	 * Update the crawlerConf in database
	 * 
	 * @param crawlerConf with fields that should be updated;
	 */
	public void update(CrawlerConf crawlerConf) {
		crawlerConfRepository.save(crawlerConf);
	}
	
	/**
	 * Get maximum number of active crawler
	 * 
	 * @return maximum number
	 */
	public Integer getMaxNumberActiveCrawler() {
		return findAll().get(0).getMaxNumberActiveCrawler();
	}

}
