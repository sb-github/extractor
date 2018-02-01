package com.crawler.extractor.graphskilltests;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.Arrays;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.crawler.extractor.model.Connect;
import com.crawler.extractor.model.GraphSkill;
import com.crawler.extractor.repository.IGraphSkillRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GraphSkillTests {

	private MockMvc mockMvc;

	final String URL = "/extractor/rest/v1/";

	@Autowired
	private WebApplicationContext webApplicationContext;

	private List<GraphSkill> graphSkills;
	@Autowired
	private IGraphSkillRepository iGraphSkillRepository;

	@Before
	public void setup() throws Exception {
		mockMvc = webAppContextSetup(webApplicationContext).build();
		ObjectId idSpring = new ObjectId();
		ObjectId idJava = new ObjectId();
		ObjectId idMySql = new ObjectId();
		ObjectId parser1 = new ObjectId();
		ObjectId parser2 = new ObjectId();
		graphSkills = Arrays.asList(
				new GraphSkill("SpringTest",
						Arrays.asList(new Connect("JavaTest", 2, Arrays.asList(parser1)),
								new Connect("MysqlTest", 2, Arrays.asList(parser1, parser2))),
						new Date(), new Date()),
				new GraphSkill("JavaTest",
						Arrays.asList(new Connect("SpringTest", 2, Arrays.asList(parser1)),
								new Connect("MysqlTest", 2, Arrays.asList(parser1, parser2))),
						new Date(), new Date()),
				new GraphSkill("MysqlTest", Arrays.asList(new Connect("SpringTest", 2, Arrays.asList(parser1)),
						new Connect("JavaTest", 2, Arrays.asList(parser1))), new Date(), new Date()));
		graphSkills.get(0).setId(idSpring);
		graphSkills.get(1).setId(idJava);
		graphSkills.get(2).setId(idMySql);
		iGraphSkillRepository.save(graphSkills.get(0));
		iGraphSkillRepository.save(graphSkills.get(1));
		iGraphSkillRepository.save(graphSkills.get(2));
	}

	@After
	public void clean() throws Exception {
		iGraphSkillRepository.delete(graphSkills.get(0).getId());
		iGraphSkillRepository.delete(graphSkills.get(1).getId());
		iGraphSkillRepository.delete(graphSkills.get(2).getId());
	}

	@Test
	public void testGetSubskillsQuantity() throws Exception {
		mockMvc.perform(get("/extractor/rest/v1/?skill=" + graphSkills.get(0).getSkill())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].skill", is(graphSkills.get(0).getSkill())))
				.andExpect(jsonPath("$[0].quantity", is(2)));
	}

	@Test
	public void testGetSubskills() throws Exception {
		mockMvc.perform(get("/extractor/rest/v1/?skill=" + graphSkills.get(0).getSkill() + "&subskill=yes"))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].skill", is(graphSkills.get(0).getSkill())))
				.andExpect(jsonPath("$[0].quantity", is(2)))
				.andExpect(jsonPath("$[1].skill", is(graphSkills.get(1).getSkill())))
				.andExpect(jsonPath("$[1].quantity", is(2)))
				.andExpect(jsonPath("$[2].skill", is(graphSkills.get(2).getSkill())))
				.andExpect(jsonPath("$[2].quantity", is(2)));
	}
}