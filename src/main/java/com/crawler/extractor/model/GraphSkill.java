package com.crawler.extractor.model;

import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Yevhenii R
 *
 * @date 10 January 2018
 * 
 *       Class that represents collection of graph_skill;
 *
 */
@Document(collection = "graph_skill")
public class GraphSkill {
	@Id
	private ObjectId _id;
	private String skill;
	private List<Connect> connects;
	private Date created_date;
	private Date modified_date;

	public GraphSkill() {
	}

	public GraphSkill(String skill, List<Connect> connects, Date created_date, Date modified_date) {
		this.skill = skill;
		this.connects = connects;
		this.created_date = created_date;
		this.modified_date = modified_date;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
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

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
}