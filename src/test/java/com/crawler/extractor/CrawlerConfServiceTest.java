package com.crawler.extractor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
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
@SpringBootTest
public class CrawlerConfServiceTest {
	
	private final String URL = "/extractor/rest/v1/crawler/conf";
	private static int maxNumberActiveCrawlers = 4;
	
	private MockMvc mockMvc;
	private Date date = new Date();
	private CrawlerConf crawlerConf;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private ICrawlerConfRepository iCrawlerConfRepository;
	
	@Autowired
	private ObjectMapper objMapper;

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
		assertThat(iCrawlerConfRepository.findAll()).isNotEmpty().as("Whether list of crawlers is empty");
		assertEquals(iCrawlerConfRepository.findAll().size(), 1);
	}

	private void checkIfCrawlerConfIsNotNull(CrawlerConf crawlerConf) {
		assertThat(crawlerConf).isNotNull().as("Whether object is not null");
		assertThat(crawlerConf.getId()).isNotNull().as("Whether id field of object is not null");
		assertThat(crawlerConf.getMaxNumberActiveCrawler()).isNotNull()
				.as("Whether maxNumberActiveCrawler field of object is not null");
		assertThat(crawlerConf.getCreatedDate()).isNotNull().as("Whether createdDate field of object is not null");
		assertThat(crawlerConf.getModifiedDate()).isNotNull().as("Whether modifiedDate of object is empty");
	}

	private String getURL() {
		return URL;
	}
}