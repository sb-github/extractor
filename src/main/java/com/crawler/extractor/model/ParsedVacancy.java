package com.crawler.extractor.model;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * 
 * @author Yevhenii R, Alexander Torchynskyi
 * 
 * @date 10 January 2018
 */
@Document(collection = "parsed_vacancy")
public class ParsedVacancy {

	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	private ObjectId id;
	@JsonSerialize(using = ToStringSerializer.class)
	@Field("crawler_id")
	private ObjectId crawlerId;
	private Status status;
	private String link;
	@Field("row_vacancy")
	private List<String> rowVacancy;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public ObjectId getCrawlerId() {
		return crawlerId;
	}

	public void setCrawlerId(ObjectId crawlerId) {
		this.crawlerId = crawlerId;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<String> getRowVacancy() {
		return rowVacancy;
	}

	public void setRowVacancy(List<String> rowVacancy) {
		this.rowVacancy = rowVacancy;
	}
}
