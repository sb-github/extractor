package com.crawler.extractor.model;

import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * Class that represents collection of crawler.
 *
 * @author Yevhenii R, Dmytro Bilyi, Stas Omelchenko
 *
 * @date 05 January 2018
 *
 */
@Document(collection = "crawler")
public class Crawler {

	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	private ObjectId id;
	@Field(value = "search_condition")
	private String searchCondition;
	private Status status;
	@Field(value = "error_message")
	private String errorMessage;
	@Field(value = "created_date")
	private Date createdDate;
	@Field(value = "modified_date")
	private Date modifiedDate;

	public Crawler() {}

	public Crawler(Status status, Date createdDate, Date modifiedDate, String searchCondition) {
		this.status = status;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.searchCondition = searchCondition;
	}

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

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}		
}
