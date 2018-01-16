package com.crawler.extractor.model;
public class Skill {
	private String skill;
	private int quantity;
	
	public Skill() {
	}
	
	public Skill(String skill, int quantity) {
		this.skill = skill;
		this.quantity = quantity;
	}
	
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}