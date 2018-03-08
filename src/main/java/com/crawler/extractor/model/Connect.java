package com.crawler.extractor.model;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Yevhenii R
 *
 * @data 10 January 2018
 * 
 *       Class that represents collection of connects;
 *
 */

public class Connect {

	@Field(value = "subskill")
	private String subSkill;
	private int weight;
	@JsonIgnore
	@Field(value = "parser_id")
	private List<ObjectId> parserId;

	public Connect() {}

	public Connect(String subSkill, int weight, List<ObjectId> parserId) {
		this.subSkill = subSkill;
		this.weight = weight;
		this.parserId = parserId;
	}

	public String getSubSkill() {
		return subSkill;
	}

	public void setSubSkill(String subSkill) {
		this.subSkill = subSkill;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public List<ObjectId> getParserId() {
		return parserId;
	}

	public void setParserId(List<ObjectId> parserId) {
		this.parserId = parserId;
	}
}
