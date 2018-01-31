package com.crawler.extractor.graphskilltests;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crawler.extractor.api.ExtractorController;
import com.crawler.extractor.model.Skill;
import com.crawler.extractor.service.GraphSkillService;

/**
 * jUnit test for Extractor controller.
 * 
 * @author Sasha Kuchmiy
 * 
 * @date 25 January 2017
 * 
 */
public class GraphSkillJunitTests {

	private MockMvc mockMvc;

	@Mock
	private GraphSkillService graphSkillService;

	@InjectMocks
	private ExtractorController extractorController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(extractorController).build();
	}

	@Test
	public void testGetSkill() throws Exception {
		List<Skill> response = new ArrayList<>();
		response.add(new Skill("SpringTest", 3));
		when(graphSkillService.findBySkillAndSubSkill("SpringTest", "no")).thenReturn(response);

		mockMvc.perform(get("/extractor/rest/v1/?skill=SpringTest&subskill=no").contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].skill", is(response.get(0).getSkill())))
				.andExpect(jsonPath("$[0].quantity", is(response.get(0).getQuantity()))).andExpect(status().isOk());
		verify(graphSkillService, times(1)).findBySkillAndSubSkill("SpringTest", "no");
		verifyNoMoreInteractions(graphSkillService);
	}

	@Test
	public void testGetSkillAndSubskill() throws Exception {
		List<Skill> response = new ArrayList<>();
		response.add(new Skill("Spring", 13));
		response.add(new Skill("Java", 16));
		response.add(new Skill("GitHub", 3));
		response.add(new Skill("PHP", 2));
		response.add(new Skill("HTML", 1));
		response.add(new Skill("C#", 6));
		when(graphSkillService.findBySkillAndSubSkill("SpringTest", "yes")).thenReturn(response);

		mockMvc.perform(get("/extractor/rest/v1/?skill=SpringTest&subskill=yes").contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", Matchers.hasSize(6)))
				.andExpect(jsonPath("$[0].skill", is(response.get(0).getSkill())))
				.andExpect(jsonPath("$[0].quantity", is(response.get(0).getQuantity())))
				.andExpect(jsonPath("$[1].skill", is(response.get(1).getSkill())))
				.andExpect(jsonPath("$[1].quantity", is(response.get(1).getQuantity())))
				.andExpect(jsonPath("$[2].skill", is(response.get(2).getSkill())))
				.andExpect(jsonPath("$[2].quantity", is(response.get(2).getQuantity())))
				.andExpect(jsonPath("$[3].skill", is(response.get(3).getSkill())))
				.andExpect(jsonPath("$[3].quantity", is(response.get(3).getQuantity())))
				.andExpect(jsonPath("$[4].skill", is(response.get(4).getSkill())))
				.andExpect(jsonPath("$[4].quantity", is(response.get(4).getQuantity())))
				.andExpect(jsonPath("$[5].skill", is(response.get(5).getSkill())))
				.andExpect(jsonPath("$[5].quantity", is(response.get(5).getQuantity()))).andExpect(status().isOk());

		verify(graphSkillService, times(1)).findBySkillAndSubSkill("SpringTest", "yes");
		verifyNoMoreInteractions(graphSkillService);
	}

}