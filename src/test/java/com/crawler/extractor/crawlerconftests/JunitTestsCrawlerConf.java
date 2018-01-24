package com.crawler.extractor.crawlerconftests;

import static org.junit.Assert.*;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.crawler.extractor.model.CrawlerConf;
import com.crawler.extractor.service.CrawlerConfService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JunitTestsCrawlerConf {
	
	private static int maxNumberActiveCrawlers = 4;
	
	private Date date = new Date();
	private CrawlerConf crawlerConf;
	
	@Autowired
	private CrawlerConfService crawlerConfService;

	@Test
	public void testUpdateCrawlerConf() throws Exception {
		List<CrawlerConf> result = crawlerConfService.findAll();
		if (result.size() == 0) {
			setupCrawler();
		} else {
			CrawlerConf crawlerConfig = result.get(0);
			Integer max = crawlerConfig.getMaxNumberActiveCrawler();
			crawlerConfig.setMaxNumberActiveCrawler(2);
			crawlerConfService.update(crawlerConfig);
			assertEquals(crawlerConfig.getMaxNumberActiveCrawler(), (Integer) 2);
			crawlerConfig.setMaxNumberActiveCrawler(max);
			crawlerConfService.update(crawlerConfig);
			checkIfCrawlerConfIsNotNull(crawlerConfig);
		}
	}

	@Test
	public void testGetAllCrawlerConf() throws Exception {
		List<CrawlerConf> result = crawlerConfService.findAll();
		if (result.size() == 0) {
			crawlerConf = new CrawlerConf();
			crawlerConf.setId(new ObjectId());
			crawlerConf.setMaxNumberActiveCrawler(maxNumberActiveCrawlers);
			crawlerConf.setCreatedDate(date);
			crawlerConf.setModifiedDate(date);
			crawlerConfService.update(crawlerConf);
			checkIfCrawlerConfIsNotNull(crawlerConf);
		} else {
			assertFalse("List of crawlerConf must not be empty", result.isEmpty());
			assertEquals(crawlerConfService.findAll().size(), 1);
		}
	}

	private void setupCrawler() {
		crawlerConf = new CrawlerConf();
		crawlerConf.setId(new ObjectId());
		crawlerConf.setMaxNumberActiveCrawler(maxNumberActiveCrawlers);
		crawlerConf.setCreatedDate(date);
		crawlerConf.setModifiedDate(date);
		checkIfCrawlerConfIsNotNull(crawlerConf);
		crawlerConfService.update(crawlerConf);
	}

	private void checkIfCrawlerConfIsNotNull(CrawlerConf crawlerConf) {
		assertNotNull("The crawlerConf must not be null", crawlerConf);
		assertNotNull("The crawlerConf id field must not be null", crawlerConf.getId());
		assertNotNull("The crawlerConf maxNumberActiveCrawler field must not be null",
				crawlerConf.getMaxNumberActiveCrawler());
		assertNotNull("The crawlerConf createdDate field must not be null", crawlerConf.getCreatedDate());
		assertNotNull("The crawlerConf modifiedDate field must not be null", crawlerConf.getModifiedDate());
	}
}