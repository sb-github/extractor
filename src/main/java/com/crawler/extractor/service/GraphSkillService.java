package com.crawler.extractor.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.crawler.extractor.model.Connect;
import com.crawler.extractor.model.GraphSkill;
import com.crawler.extractor.repository.IGraphSkillRepository;
import com.crawler.extractor.model.Skill;

/**
 *
 * @author Yevhenii R
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
	 * @param skill
	 *            is name of skill what we are looking for;
	 * @param subskill
	 *            is option that includes or excludes display subskills;
	 * @return List<Skill> with the name of the skill with subskills and its
	 *         quantity;
	 */
	public List<Skill> findBySkillAndSubSkill(String skill, String subskill) {
		GraphSkill graphSkill = iGraphSkillRepository.findBySkill(skill);
		if (graphSkill == null) {
			return Collections.emptyList();
		}
		Set<ObjectId> i = new HashSet<>();
		List<Connect> connects = graphSkill.getConnects();
		List<Skill> skillAndSubskills = new ArrayList<>();
		for (Connect c : connects) {
			i.addAll(c.getParserId());
		}
		skillAndSubskills.add(new Skill(skill, i.size()));
		if (subskill.equals("yes")) {
			for (Connect c : connects) {
				skillAndSubskills.add(new Skill(c.getSubSkill(), c.getWeight()));
			}
		}
		return skillAndSubskills;
	}
}