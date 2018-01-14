package com.crawler.extractor.model;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Field;
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
	@Field(value = "parser_id")
	private List<Integer> parserId;

	public Connect() {}

	public Connect(String subskill, int weight, List<Integer> parserId) {

		this.subSkill = subskill;
		this.weight = weight;
		this.parserId = parserId;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getSubskill() {
		return subSkill;
	}

	public void setSubskill(String subskill) {
		this.subSkill = subskill;
	}

	public List<Integer> getParserId() {
		return parserId;
	}

	public void setParser_id(List<Integer> parserId) {
		this.parserId = parserId;
	}
}
