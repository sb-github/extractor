package com.crawler.extractor.model;

import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * Class that represents collection of graph_skill;
 *
 * @author Yevhenii R
 *
 * @date 10 January 2018
 * 
 */
@Document(collection = "graph_skill")
public class GraphSkill {
	@Id
	@JsonIgnore
	@JsonSerialize(using = ToStringSerializer.class)
	private ObjectId id;
	private String skill;
	@Field(value = "crawler_id")
	private ObjectId crawlerId;
	private List<Connect> connects;
	@Field(value = "created_date")
	private Date createdDate;
	@Field(value = "modified_date")
	private Date modifiedDate;

	public GraphSkill() {}

	public GraphSkill(String skill, List<Connect> connects, Date createdDate, Date modifiedDate) {
		this.skill = skill;
		this.connects = connects;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public ObjectId getCrawlerId() {
		return crawlerId;
	}

	public void setCrawlerId(ObjectId crawlerId) {
		this.crawlerId = crawlerId;
	}

	public List<Connect> getConnects() {
		return connects;
	}

	public void setConnects(List<Connect> connects) {
		this.connects = connects;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
