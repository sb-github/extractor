package com.crawler.extractor.crawlertests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.crawler.extractor.api.CrawlerConfController;
import com.crawler.extractor.api.CrawlerController;
import com.crawler.extractor.model.Crawler;
import com.crawler.extractor.model.CrawlerConf;
import com.crawler.extractor.model.Status;
import com.crawler.extractor.repository.IExtractorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlerIntegrationTest {
	private final String URL = "/extractor/rest/v1/crawler";

	private MockMvc mockMvc;
	private Crawler crawler;
	private Date date = new Date();

	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private IExtractorRepository iExtractorRepository;
	@Autowired
	private ObjectMapper objectMapper;

	@Before
	public void setup() throws Exception {
		mockMvc = webAppContextSetup(webApplicationContext).build();
		crawler = new Crawler();
		crawler.setId(new ObjectId());
		crawler.setStatus(Status.NEW);
		crawler.setErrorMessage("Testing");
		crawler.setCreatedDate(date);
		crawler.setModifiedDate(date);
		iExtractorRepository.insert(crawler);
	}

	@After
	public void clean() {
		iExtractorRepository.delete(crawler.getId());
	}

	@Test
	public void testUpdate() throws Exception {
		crawler.setStatus(Status.PROCESSED);
		mockMvc.perform(put(getURL()).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsBytes(crawler))).andExpect(status().isOk());
		checkIfCrawlerConfIsNotNull(iExtractorRepository.findOne(crawler.getId()));
	}

	@Test
	public void testGetById() throws Exception {
		mockMvc.perform(get(getURL() + "/" + crawler.getId()).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsBytes(crawler))).andExpect(status().isOk());
		assertThat(iExtractorRepository.findOne(crawler.getId()))
				.as("Whether crawler id filed is not null").isNotNull();
	}

	@Test
	public void testGetAll() throws Exception {
		mockMvc.perform(get(getURL())).andExpect(status().isOk());
		assertThat(iExtractorRepository.findAll()).isNotEmpty().as("Whether list of crawlers is not empty");
	}

	private String getURL() {
		return URL;
	}

	private void checkIfCrawlerConfIsNotNull(Crawler crawler) {
		assertThat(crawler).as("Whether object is not null").isNotNull();
		assertThat(crawler.getId()).as("Whether id field of object is not null").isNotNull();
		assertThat(crawler.getStatus()).as("Whether status field of object is not null").isNotNull();
		assertThat(crawler.getErrorMessage()).as("Whether errorMessege field of object is not null").isNotNull();
		assertThat(crawler.getCreatedDate()).as("Whether createdDate field of object is not null").isNotNull();
		assertThat(crawler.getModifiedDate()).as("Whether modifiedDate of object is not null").isNotNull();
	}
}