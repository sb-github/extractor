package com.crawler.extractor.model;

import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * Class that represents collection of stop_words
 * 
 * @author Dmytro Bilyi
 *
 * @data 11 January 2018
 *
 */
@Document(collection = "stop_word")
public class StopWord {

	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	private ObjectId id;
	private String key;
	@Field(value = "created_date")
	private Date createdDate;
	@Field(value = "modified_date")
	private Date modifiedDate;

	public StopWord() {}

	public StopWord(String key, Date createdDate, Date modifiedDate) {
		this();
		this.key = key;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
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
