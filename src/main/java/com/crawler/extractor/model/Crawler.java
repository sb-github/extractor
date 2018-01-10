package com.crawler.extractor.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

/**
 *
 * @author Yevhenii R
 *
 * @data 05 January 2018
 *
 *       <p>
 *       Class that represents collection of crawler;
 */
@Document(collection = "crawler")
public class Crawler {

	@Id
	private ObjectId id;
	private Date createdDate;
	private Date modifiedDate;
	private Status status;
	private String error_message;

	public Crawler() {}

	public Crawler(Date createdDate, Date modifiedDate, Status status) {
		this();
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.status = status;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
}
