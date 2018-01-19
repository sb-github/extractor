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
import com.crawler.extractor.model.Status;
import com.crawler.extractor.repository.IExtractorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrawlerServiceTest {
	private final String URL_TO_CREATE_CRAWLER = "extractor/rest/v1/crawler";
	@Autowired
	private IExtractorRepository iExtractorRepository;
	@LocalServerPort
	private int port;
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	private MockMvc mockMvc;
	private Crawler crawler;
	@Autowired
	private WebApplicationContext webApplicationContext;

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.writeValueAsString(object);
	}

	@Before
	public void setup() throws Exception {
		mockMvc = webAppContextSetup(webApplicationContext).build();
		crawler = new Crawler();
		crawler.setId(new ObjectId());
		crawler.setStatus(Status.NEW);
		crawler.setErrorMessage("Testing");
		crawler.setCreatedDate(new Date());
		crawler.setModifiedDate(new Date());
		mockMvc.perform(
				post(createURLWithPort(URL_TO_CREATE_CRAWLER)).contentType(contentType).content(mapToJson(crawler)))
				.andExpect(status().isOk());
	}

	@After
	public void deleteCreatedCralwer() {
		iExtractorRepository.delete(crawler.getId());
	}

	@Test
	public void testcreateCrawler() throws Exception {
		Crawler createdCrawler = crawler;
		createdCrawler.setId(new ObjectId());
		mockMvc.perform(post(createURLWithPort(URL_TO_CREATE_CRAWLER)).contentType(contentType)
				.content(mapToJson(createdCrawler))).andExpect(status().isOk());
		crawlerFieldComparison(iExtractorRepository.findOne(createdCrawler.getId()));
		iExtractorRepository.delete(createdCrawler.getId());
	}

	@Test
	public void testUpdateCrawler() throws Exception {
		crawler.setStatus(Status.PROCESSED);
		mockMvc.perform(
				put(createURLWithPort(URL_TO_CREATE_CRAWLER)).contentType(contentType).content(mapToJson(crawler)))
				.andExpect(status().isOk());
		crawlerFieldComparison(iExtractorRepository.findOne(crawler.getId()));
	}

	@Test
	public void testReadAll() throws Exception {
		mockMvc.perform(get(createURLWithPort(URL_TO_CREATE_CRAWLER))).andExpect(status().isOk());
		assertThat(iExtractorRepository.findAll()).as("Whether list of crawlers is empty").isNotEmpty();
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	private void crawlerFieldComparison(Crawler returnedCrawler) {
		assertThat(returnedCrawler).as("Whether object is not null").isNotNull();
		assertThat(returnedCrawler.getId()).as("Whether id field of object is not null").isNotNull();
		assertThat(returnedCrawler.getStatus()).as("Whether status field of object is not null").isNotNull();
		assertThat(returnedCrawler.getErrorMessage()).as("Whether errorMessege field of object is not null")
				.isNotNull();
		assertThat(returnedCrawler.getCreatedDate()).as("Whether createdDate field of object is not null").isNotNull();
		assertThat(returnedCrawler.getModifiedDate()).as("Whether modifiedDate of object is empty").isNotNull();
	}
}