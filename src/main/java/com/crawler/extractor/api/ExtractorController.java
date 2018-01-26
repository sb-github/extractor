package com.crawler.extractor.api;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.crawler.extractor.model.GraphSkill;
import com.crawler.extractor.model.Skill;
import com.crawler.extractor.service.GraphSkillService;

/**
 * 
 * @author Yevhenii R
 * 
 * @date 10 January 2018
 * 
 *       RESTful API for graph_skill controller;
 *
 */
@PropertySource(value = "classpath:crawler.properties")
@RestController
@RequestMapping
public class ExtractorController {
	@Autowired
	private GraphSkillService graphSkillService;

	@RequestMapping(value = "extractor/crawler", method = RequestMethod.GET)
	public ResponseEntity<?> getBySkillAndSubSkill(@RequestParam(value = "skill") String skill,
			@RequestParam(value = "subskill", required = false,
					defaultValue = "no") String subskill) {
		try {
			List<Skill> result = graphSkillService.findBySkillAndSubSkill(skill, subskill);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "extractor/graphskill", method = RequestMethod.GET)
	public ResponseEntity<?> getGraphSkillByCrawler(
			@RequestParam(value = "crawler_id") ObjectId crawlerId,
			@RequestParam(value = "page", defaultValue = "${default_page_for_graph}") int page,
			@RequestParam(value = "size", defaultValue = "${default_size_for_graph}") int size) {
		try {
			List<GraphSkill> result = graphSkillService.findByCrawlerId(crawlerId, page, size);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
