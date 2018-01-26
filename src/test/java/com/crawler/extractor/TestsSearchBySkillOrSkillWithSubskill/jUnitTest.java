package com.crawler.extractor;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.crawler.extractor.api.ExtractorController;
import com.crawler.extractor.model.Skill;

@RunWith(SpringRunner.class)
@SpringBootTest
public class jUnitTest {

	@Autowired
	ExtractorController extractorController;

	@Test
	public void getBySkill() {
		String skill ="JavaTest";
		String subskill ="no";
		ResponseEntity result = extractorController.getBySkillAndSubSkill(skill,subskill);
		ArrayList<Skill> r = (ArrayList<Skill>) result.getBody();
		assertThat(r).isNotEmpty().as("Whether list of skills is null");
		assertEquals(r.size(), 1);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getBySkillAndSubskill() {
		String skill ="JavaTest";
		String subskill ="yes";
		ResponseEntity result = extractorController.getBySkillAndSubSkill(skill,subskill);
		ArrayList<Skill> r = (ArrayList<Skill>) result.getBody();
		assertThat(r).isNotEmpty().as("Whether list of skills is null");
		assertEquals(result.getStatusCode(), HttpStatus.OK);
	}

}
