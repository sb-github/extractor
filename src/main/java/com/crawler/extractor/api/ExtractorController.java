package com.crawler.extractor.api;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
			HashMap<String, Integer> result =
					graphSkillService.findBySkillAndSubSkill(skill, subskill);
			if (result.size() == 0) {
				throw new Exception("ERROR_NO_DATA");
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
