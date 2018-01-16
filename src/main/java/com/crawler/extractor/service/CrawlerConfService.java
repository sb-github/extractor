package com.crawler.extractor.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.crawler.extractor.interfaces.ICrawlerConfRepository;
import com.crawler.extractor.model.CrawlerConf;

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
}
