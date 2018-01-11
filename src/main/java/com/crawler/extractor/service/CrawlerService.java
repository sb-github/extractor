package com.crawler.extractor.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.crawler.extractor.model.Crawler;
import com.crawler.extractor.repository.IExtractorRepository;

/**
 * 
 * @author Dmytro Bilyi
 * 
 * @data 10 January 2017
 * 
 */
@Service
public class CrawlerService {

	@Autowired
	private IExtractorRepository extractorRepository;

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
