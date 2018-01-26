package com.crawler.extractor.crawlertests;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crawler.extractor.api.CrawlerController;
import com.crawler.extractor.model.Crawler;
import com.crawler.extractor.model.Status;
import com.crawler.extractor.service.CrawlerService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * jUnit test for Crawler Controller.
 * 
 * @author Sasha Kuchmiy
 * 
 * @date 25 January 2017
 * 
 */

public class CrawlerjUnitTest {
	private static int page = 0, size = 5;

	private MockMvc mockMvc;
	private ObjectMapper objectMapper = new ObjectMapper();

	@Mock
	private CrawlerService crawlerService;

	@InjectMocks
	private CrawlerController crawlerController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(crawlerController).build();
	}

	@Test
	public void testGetByIdSuccess() throws Exception {

		Crawler crawler =initCrawler();
		when(crawlerService.findOne(crawler.getId())).thenReturn(crawler);

		mockMvc.perform(get("/extractor/rest/v1/crawler/{id}", crawler.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is(crawler.getId().toString()))).andExpect(jsonPath("$.searchCondition", is("Java")))
				.andExpect(status().isOk());
		verify(crawlerService, times(1)).findOne(crawler.getId());
		verifyNoMoreInteractions(crawlerService);
	}

	@Test
	public void testGetAllSuccess() throws Exception {
		Crawler crawler =	initCrawler();
		List<Crawler> crawlers = new ArrayList<>();
		crawlers.add(crawler);
		when(crawlerService.findAll(page, size)).thenReturn(crawlers);
		mockMvc.perform(get("/extractor/rest/v1/crawler?page=0&size=5")).andExpect(status().isOk());

		verify(crawlerService, times(1)).findAll(page, size);
		verifyNoMoreInteractions(crawlerService);
	}

	
	@Test
	public void testUpdateCrawlerSuccess() throws Exception {
		Crawler crawler = initCrawler();
		doNothing().when(crawlerService).update(crawler);
		mockMvc.perform(put("/extractor/rest/v1/crawler").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(crawler))).andExpect(status().isOk());
		verify(crawlerService, times(1)).update(refEq(crawler));
		verifyNoMoreInteractions(crawlerService);
	}
	
	@Test
	public void testRun() throws Exception {
		
		doNothing().when(crawlerService).run("java");
		mockMvc.perform(get("/extractor/rest/v1/crawler/run?searchcondition=java"))
				.andExpect(status().isOk());
		verify(crawlerService, times(1)).run("java");
		verifyNoMoreInteractions(crawlerService);
	}
	
	private Crawler initCrawler()
	{
		Date date = new Date();
		ObjectId  id = new ObjectId();
		Crawler crawler = new Crawler();
		crawler.setId(id);
		crawler.setStatus(Status.NEW);
		crawler.setCreatedDate(date);
		crawler.setModifiedDate(date);
		crawler.setSearchCondition("Java");
		crawler.setErrorMessage("ERROR");
		return crawler;
	}
}