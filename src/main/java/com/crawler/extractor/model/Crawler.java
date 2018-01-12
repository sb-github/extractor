package com.crawler.extractor.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that represents collection of crawler;
 *
 * @author Yevhenii R, Dmytro Bilyi
 *
 * @data 05 January 2018
 *
 */
@Document(collection = "crawler")
public class Crawler {

	@Id
	private ObjectId id;
	private Status status;
	@Field(value = "error_message")
	private String errorMessage;
	@Field(value = "created_date")
	private Date createdDate;
	@Field(value = "modified_date")
	private Date modifiedDate;

	public Crawler() {}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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
