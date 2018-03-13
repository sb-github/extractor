package com.crawler.extractor.api;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.crawler.extractor.model.CrawlerProgress;
import com.crawler.extractor.model.GraphSkill;
import com.crawler.extractor.model.Skill;
import com.crawler.extractor.model.SkillsArray;
import com.crawler.extractor.service.CrawlerProgressService;
import com.crawler.extractor.service.GraphSkillService;

/**
 * RESTful API for graph_skill controller;
 * 
 * 
 * @author Yevhenii R, Alexander Torchynskyi, Dmytro Bilyi
 * 
 * @date 10 January 2018
 * 
 */
@PropertySource(value = "classpath:crawler.properties")
@RestController
@RequestMapping("extractor/rest/v1")
public class ExtractorController {
	@Autowired
	private GraphSkillService graphSkillService;
	@Autowired
	private CrawlerProgressService crawelerProgressService;

	/**
	 * 
	 * The method accepts parameter skill and boolean parameter subskill (optional), then return the
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
	 * The method accepts the crawlerId and return the paged list of graph_skills by this id; You
	 * can also specify the page you want to view and the amount of items to show on that page;
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
	 * This method accepts the crawlerId, skill and an array of subskills which must be included in
	 * the requested skill
	 * 
	 * @param crawlerId the id of crawler
	 * @param skill that must be shown
	 * @param subskills array of subskills which must be included in the requested skill
	 * @return graphSkill that was found
	 */
	@RequestMapping(value = "/graphskill", method = RequestMethod.POST)
	public ResponseEntity<?> getGraphBySkillSubskillAndCrawlerId(
			@RequestParam(value = "crawler_id") ObjectId crawlerId,
			@RequestParam(value = "skill") String skill, @RequestBody SkillsArray subskills) {
		try {
			GraphSkill graphSkill = graphSkillService.findBySkillAndSubSkillAndCrawlerId(skill,
					subskills, crawlerId);
			return new ResponseEntity<>(graphSkill, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This method accepts the crawlerId, an array of subskills which must be included in the each
	 * skill if its exist. You can also specify the page you want to view and the amount of items to
	 * show on that page;
	 * 
	 * @param crawlerId the id of crawler
	 * @param subskills array of subskills which must be included in the each skill if its exist
	 * @param page the number of page
	 * @param size the amount of items per page
	 * @return list of graphSkills where each skill is a subskill and each subskill is a skill
	 */
	@RequestMapping(value = "/graphskill/show-all", method = RequestMethod.POST)
	public ResponseEntity<?> getGraphBySkillSubskillAndCrawlerId(
			@RequestParam(value = "crawler_id") ObjectId crawlerId,
			@RequestBody SkillsArray subskills,
			@RequestParam(value = "page", defaultValue = "${default_page_for_graph}") int page,
			@RequestParam(value = "size", defaultValue = "${default_size_for_graph}") int size) {
		try {
			List<GraphSkill> listOfGraphSkill = graphSkillService
					.findBySkillAndSubSkillAndCrawlerId(subskills, crawlerId, page, size);
			return new ResponseEntity<>(listOfGraphSkill, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * The method accepts the crawlerId and return list that include: vacancy and parsed_vacancy
	 * with its amount and status
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
