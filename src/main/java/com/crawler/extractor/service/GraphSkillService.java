package com.crawler.extractor.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import com.crawler.extractor.model.Connect;
import com.crawler.extractor.model.GraphSkill;
import com.crawler.extractor.model.Skill;
import com.crawler.extractor.repository.IGraphSkillRepository;

/**
 *
 * @author Yevhenii R, Alexander Torchynskyi
 *
 * @date 15 January 2018
 * 
 */
@Component
public class GraphSkillService {
	@Autowired
	private IGraphSkillRepository iGraphSkillRepository;

	/**
	 * Find all skills
	 * 
	 * @param skill is name of skill what we are looking for;
	 * @param subskill is option that includes or excludes display subskills;
	 * @return List<Skill> with the name of the skill with subskills and its quantity;
	 */
	public List<Skill> findBySkillAndSubSkill(String skill, String subskill) {
		// TODO change the of checking method make sure you wont accept null;
		// change "yes" or "no" values to boolean;
		GraphSkill graphSkill = iGraphSkillRepository.findBySkill(skill);
		if (graphSkill == null) {
			return Collections.emptyList();
		}
		Set<ObjectId> skillQuantity = new HashSet<>();
		List<Connect> connects = graphSkill.getConnects();
		List<Skill> skillAndSubskills = new ArrayList<>();
		for (Connect c : connects) {
			skillQuantity.addAll(c.getParserId());
		}
		skillAndSubskills.add(new Skill(skill, skillQuantity.size()));
		if (subskill.equals("yes")) {
			for (Connect c : connects) {
				skillAndSubskills.add(new Skill(c.getSubSkill(), c.getWeight()));
			}
		}
		return skillAndSubskills;
	}

	/**
	 * Find all graph skills by crawlerId;
	 * 
	 * @param crawlerId - the id of searched crawler;
	 * @param page - which page should be rendered;
	 * @param size - amount of elements per page;
	 * @return list of skills with its subskills;
	 */
	public List<GraphSkill> findByCrawlerId(ObjectId crawlerId, int page, int size) {
		if (page >= 0 && size >= 0) {
			List<GraphSkill> listOfGrpahSkills =
					iGraphSkillRepository.findByCrawlerId(crawlerId, new PageRequest(page, size));
			return listOfGrpahSkills;
		}
		return new ArrayList<>();
	}
}
