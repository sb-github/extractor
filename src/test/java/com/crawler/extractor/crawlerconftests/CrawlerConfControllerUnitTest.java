package com.crawler.extractor.crawlerconftests;

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

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crawler.extractor.api.CrawlerConfController;
import com.crawler.extractor.model.CrawlerConf;
import com.crawler.extractor.service.CrawlerConfService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CrawlerConfControllerUnitTest {
	
	private MockMvc mockMvc;
	private ObjectMapper objectMapper = new ObjectMapper();
	private static int maxNumberActiveCrawler = 4;

	@Mock
	private CrawlerConfService confService;

	@InjectMocks
	private CrawlerConfController crawlerConfController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(crawlerConfController).build();
	}

	 @Test
	 public void testGetAllCrawlerConf() throws Exception {
	 CrawlerConf crawlerConf = createCrawlerconf();
	 List<CrawlerConf> result = Arrays.asList(crawlerConf);
	 when(confService.findAll()).thenReturn(result);
	 mockMvc.perform(get("/extractor/rest/v1/crawler/conf").contentType(MediaType.APPLICATION_JSON))
	 .andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
	 .andExpect(jsonPath("$[0].id", is(crawlerConf.getId().toString())))
	 .andExpect(jsonPath("$[0].maxNumberActiveCrawler", is(maxNumberActiveCrawler)));
	 verify(confService, times(1)).findAll();
	 verifyNoMoreInteractions(confService);
	 }

	@Test
	public void testUpdateCrawlerConf() throws Exception {
		CrawlerConf crawlerConf = createCrawlerconf();
		doNothing().when(confService).update(crawlerConf);
		mockMvc.perform(put("/extractor/rest/v1/crawler/conf").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(crawlerConf))).andExpect(status().isOk());
		verify(confService, times(1)).update(refEq(crawlerConf));
		verifyNoMoreInteractions(confService);
	}
	
	private CrawlerConf createCrawlerconf() {
		CrawlerConf crawlerConf = new CrawlerConf();
		 ObjectId id = ObjectId.get();
		 crawlerConf.setId(id);
		 crawlerConf.setMaxNumberActiveCrawler(maxNumberActiveCrawler);
		 crawlerConf.setCreatedDate(new Date());
		 crawlerConf.setModifiedDate(new Date());
		return crawlerConf;
	}
}