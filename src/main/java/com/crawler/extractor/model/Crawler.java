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

	public Crawler() {}

	public Crawler(Date createdDate, Date modifiedDate, Status status) {
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

}
