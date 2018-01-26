package com.crawler.extractor.crawlerconftests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.Date;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.crawler.extractor.model.CrawlerConf;
import com.crawler.extractor.repository.ICrawlerConfRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrawlerConfServiceTest {
	
	private final String URL = "/extractor/rest/v1/crawler/conf";
	private static int maxNumberActiveCrawlers = 4;

	private MockMvc mockMvc;
	private Date date = new Date();
	private CrawlerConf crawlerConf;
	private ObjectMapper objMapper = new ObjectMapper();

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private ICrawlerConfRepository iCrawlerConfRepository;

	

	@Before
	public void setup() throws Exception {
		mockMvc = webAppContextSetup(webApplicationContext).build();
		crawlerConf = new CrawlerConf();
		crawlerConf.setId(new ObjectId());
		crawlerConf.setMaxNumberActiveCrawler(maxNumberActiveCrawlers);
		crawlerConf.setCreatedDate(date);
		crawlerConf.setModifiedDate(date);
		iCrawlerConfRepository.save(crawlerConf);
	}

	@After
	public void deleteCrawlerConf() throws Exception {
		iCrawlerConfRepository.delete(crawlerConf.getId());
	}

	@Test
	public void testUpdateCrawlerConf() throws Exception {
		assertEquals(iCrawlerConfRepository.findOne(crawlerConf.getId()).getMaxNumberActiveCrawler(),
				crawlerConf.getMaxNumberActiveCrawler());
		crawlerConf.setMaxNumberActiveCrawler(2);
		mockMvc.perform(put(getURL()).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objMapper.writeValueAsBytes(crawlerConf))).andExpect(status().isOk());
		assertEquals(iCrawlerConfRepository.findOne(crawlerConf.getId()).getMaxNumberActiveCrawler(),
				crawlerConf.getMaxNumberActiveCrawler());
		checkIfCrawlerConfIsNotNull(iCrawlerConfRepository.findOne(crawlerConf.getId()));
	}

	@Test
	public void testGetAllCrawlerConf() throws Exception {
		mockMvc.perform(get(getURL())).andExpect(status().isOk());
		assertFalse("List of crawlerConf must not be empty", iCrawlerConfRepository.findAll().isEmpty());
		assertEquals(iCrawlerConfRepository.findAll().size(), 2);
	}

	private void checkIfCrawlerConfIsNotNull(CrawlerConf crawlerConf) {
		assertNotNull("The crawlerConf must not be null", crawlerConf);
		assertNotNull("The crawlerConf id field must not be null", crawlerConf.getId());
		assertNotNull("The crawlerConf maxNumberActiveCrawler field must not be null",
				crawlerConf.getMaxNumberActiveCrawler());
		assertNotNull("The crawlerConf createdDate field must not be null", crawlerConf.getCreatedDate());
		assertNotNull("The crawlerConf modifiedDate field must not be null", crawlerConf.getModifiedDate());
	}

	private String getURL() {
		return URL;
	}
}
