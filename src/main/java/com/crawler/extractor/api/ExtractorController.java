package com.crawler.extractor.api;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.crawler.extractor.model.Connect;
import com.crawler.extractor.model.GraphSkill;
import com.crawler.extractor.repository.IGraphSkillRepository;

/**
 * 
 * @author Yevhenii R
 * 
 * @date 10 January 2018
 * 
 *       RESTful API for controller with find method;
 *
 */
@RestController
@RequestMapping
public class ExtractorController {
	@Autowired
	private IGraphSkillRepository iGraphSkillRepository;

	
	/**
	 * Find all skills
	 * 
	 * @param skill  is name of skill what we are looking for;
	 * @param subskill is option that includes or excludes display subskills;
	 * @return HashMap with the name of the skill and its quantity;
	 */

	@RequestMapping(value = "extractor/crawler", method = RequestMethod.GET)
	public HashMap<String, Integer> findBySkillAndSubSkill(
			@RequestParam(value = "skill") String skill, @RequestParam(value = "subskill",
					required = false, defaultValue = "no") String subSkill) {
		GraphSkill graphSkill = iGraphSkillRepository.findBySkill(skill);
		Set<Integer> quantity = new HashSet<>();

		List<Connect> connects = graphSkill.getConnects();
		for (Connect c : connects) {
			List<Integer> i = c.getParserId();
			for (Integer integ : i) {
				quantity.add(integ);
			}
		}
		HashMap<String, Integer> result = new HashMap<>();
		result.put(skill, quantity.size());
		if (subSkill.equals("yes")) {
			for (Connect c : connects) {
				result.put(c.getSubskill(), c.getWeight());
			}
			return result;
		} else if (subSkill.equals("no")) {

		}
		return result;

	}

}
