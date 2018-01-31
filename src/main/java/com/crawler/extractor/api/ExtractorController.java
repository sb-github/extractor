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
import com.crawler.extractor.model.CrawlerProgress;
import com.crawler.extractor.model.GraphSkill;
import com.crawler.extractor.model.Skill;
import com.crawler.extractor.model.Vacancy;
import com.crawler.extractor.service.CrawlerProgressService;
import com.crawler.extractor.service.GraphSkillService;

/**
 * RESTful API for graph_skill controller;
 * 
 * 
 * @author Yevhenii R, Alexander Torchynskyi
 * 
 * @date 10 January 2018
 * 
 */
@PropertySource(value = "classpath:crawler.properties")
@RestController
@RequestMapping("extractor/rest/v1/crawler")
public class ExtractorController {
	@Autowired
	private GraphSkillService graphSkillService;
	@Autowired
	private CrawlerProgressService crawelerProgressService;

	/**
	 * 
	 * The method accept parameter skill and boolean parameter subskill (optional), then return the
	 * skill with amount of iteration, and the amount of iteration for subskills.
	 * 
	 * @param skill
	 * @param subskill
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
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

	/**
	 * 
	 * The method accept the crawlerId and return paged list of graph_skills by this id; You can
	 * also specify page you want to view and amount of items to show on that page;
	 * 
	 * @param crawlerId
	 * @param page
	 * @param size
	 * @return
	 */

	@RequestMapping(value = "/graphskill", method = RequestMethod.GET)
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

	/**
	 * 
	 * The method accept the crawlerId and return list that include: vacancy and parsed_vacancy with
	 * its amount and status
	 * 
	 * @param crawlerId
	 * @return List<CrawlerProgress>
	 */

	@RequestMapping(value = "/progress", method = RequestMethod.GET)
	public ResponseEntity<?> getCrawlerProgressCount(
			@RequestParam(value = "crawler_id") ObjectId crawlerId) {
		try {
			List<CrawlerProgress> result = crawelerProgressService.getProgress(crawlerId);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
