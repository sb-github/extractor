package com.crawler.extractor.model;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Yevhenii R
 *
 * @data 10 January 2018
 * 
 *       Class that represents collection of graph_skill;
 *
 */
@Document(collection = "graph_skill")
public class GraphSkill {
	@Id
	private int _id;
	private String skill;
	private List<Connect> connects;

	public GraphSkill() {}

	public GraphSkill(String skill, List<Connect> connects) {
		this.skill = skill;
		this.connects = connects;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public List<Connect> getConnects() {
		return connects;
	}

	public void setConnects(List<Connect> connects) {
		this.connects = connects;
	}

}
