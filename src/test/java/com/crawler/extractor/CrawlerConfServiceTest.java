package com.crawler.extractor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;
import java.util.Date;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.crawler.extractor.model.Crawler;
import com.crawler.extractor.model.CrawlerConf;
import com.crawler.extractor.model.Status;
import com.crawler.extractor.repository.ICrawlerConfRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrawlerConfServiceTest {
	
	private final String URL_TO_UPDATE_CRAWLER_CONF = "extractor/rest/v1/crawler/conf";
	
	@LocalServerPort
	private int port;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	private CrawlerConf crawlerConf;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private ICrawlerConfRepository iCrawlerConfRepository;

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.writeValueAsString(object);
	}
	
	@Before
	public void setup() throws Exception {
		mockMvc = webAppContextSetup(webApplicationContext).build();
		crawlerConf = new CrawlerConf();
		crawlerConf.setId(new ObjectId());
		crawlerConf.setMaxNumberActiveCrawler(4);
		crawlerConf.setCreatedDate(new Date());
		crawlerConf.setCreatedDate(new Date());
		iCrawlerConfRepository.save(crawlerConf);
	}
	
	@After
	public void deleteCrawlerConf() throws Exception {
		iCrawlerConfRepository.delete(crawlerConf.getId());
	}
	
	
	@Test
	public void testUpdateCrawlerConf() throws Exception {
		crawlerConf.setMaxNumberActiveCrawler(2);
		mockMvc.perform(
				put(createURLWithPort(URL_TO_UPDATE_CRAWLER_CONF)).contentType(contentType).content(mapToJson(crawlerConf)))
				.andExpect(status().isOk());
		crawlerFieldComparison(iCrawlerConfRepository.findOne(crawlerConf.getId()));
	}
	
	@Test
	public void testGetAllCrawlerConf() throws Exception {
		mockMvc.perform(
				get(createURLWithPort(URL_TO_UPDATE_CRAWLER_CONF))).andExpect(status().isOk());
		assertThat(iCrawlerConfRepository.findAll()).as("Whether list of crawlers is empty").isNotEmpty();

	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	private void crawlerFieldComparison(CrawlerConf returnedCrawlerConf) {
		assertThat(returnedCrawlerConf).as("Whether object is not null").isNotNull();
		assertThat(returnedCrawlerConf.getId()).as("Whether id field of object is not null").isNotNull();
		assertThat(returnedCrawlerConf.getMaxNumberActiveCrawler()).as("Whether maxNumberActiveCrawler field of object is not null").isNotNull();
		assertThat(returnedCrawlerConf.getCreatedDate()).as("Whether createdDate field of object is not null").isNotNull();
		assertThat(returnedCrawlerConf.getModifiedDate()).as("Whether modifiedDate of object is empty").isNotNull();
	}

}
