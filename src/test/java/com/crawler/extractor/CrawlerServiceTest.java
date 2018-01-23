package com.crawler.extractor;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.crawler.extractor.api.CrawlerController;
import com.crawler.extractor.model.Crawler;
import com.crawler.extractor.model.Status;
import com.crawler.extractor.repository.IExtractorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlerServiceTest {
	private final String URL = "/extractor/rest/v1/crawler";

	private MockMvc mockMvc;
	private Crawler crawler;

	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private IExtractorRepository iExtractorRepository;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private CrawlerController crawlerController;

	@Before
	public void setup() throws Exception {
		mockMvc = webAppContextSetup(webApplicationContext).build();
		crawler = new Crawler();
		crawler.setId(new ObjectId());
		crawler.setStatus(Status.NEW);
		crawler.setErrorMessage("Testing");
		crawler.setCreatedDate(new Date());
		crawler.setModifiedDate(new Date());
		iExtractorRepository.insert(crawler);
	}

	@After
	public void deleteCreatedCralwer() {
		iExtractorRepository.delete(crawler.getId());
	}
	
//	 @Test
//	 public void testRunCrawler() throws Exception {
//	 }

	@Test
	public void testUpdateCrawler() throws Exception {
		crawler.setStatus(Status.PROCESSED);
		mockMvc.perform(put(getURL()).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsBytes(crawler))).andExpect(status().isOk());
		checkIfCrawlerConfIsNotNull(iExtractorRepository.findOne(crawler.getId()));
	}

	@Test
	public void testGetById() throws Exception {
		mockMvc.perform(get(getURL() + "/" + crawler.getId()).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsBytes(crawler))).andExpect(status().isOk());
		assertThat(iExtractorRepository.findOne(crawler.getId())).isNotNull()
				.as("Whether crawler id filed is not null");
	}

	@Test
	public void testReadAll() throws Exception {
		mockMvc.perform(get(getURL())).andExpect(status().isOk());
		assertThat(iExtractorRepository.findAll()).isNotEmpty().as("Whether list of crawlers is not empty");
	}

	private String getURL() {
		return URL;
	}

	private void checkIfCrawlerConfIsNotNull(Crawler crawler) {
		assertThat(crawler).isNotNull().as("Whether object is not null");
		assertThat(crawler.getId()).isNotNull().as("Whether id field of object is not null");
		assertThat(crawler.getStatus()).isNotNull().as("Whether status field of object is not null");
		assertThat(crawler.getErrorMessage()).isNotNull().as("Whether errorMessege field of object is not null");
		assertThat(crawler.getCreatedDate()).isNotNull().as("Whether createdDate field of object is not null");
		assertThat(crawler.getModifiedDate()).isNotNull().as("Whether modifiedDate of object is empty");
	}
}