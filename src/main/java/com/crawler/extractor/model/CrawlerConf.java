package com.crawler.extractor.model;

import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Dmytro Bilyi
 *
 * @data 09 January 2018
 *
 *       <p>
 *       Class that represents collection of Crawler_conf;
 */
@Document(collection = "crawler_conf")
public class CrawlerConf {

	@Id
	private ObjectId id;
	private int max_number_active_crawler;
	private Date createdDate;
	private Date modifiedDate;


	public CrawlerConf() {}

	public CrawlerConf(ObjectId id, int max_number_active_crawler, Date createdDate,
			Date modifiedDate) {
		this();
		this.id = id;
		this.max_number_active_crawler = max_number_active_crawler;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public int getMax_number_active_crawler() {
		return max_number_active_crawler;
	}

	public void setMax_number_active_crawler(int max_number_active_crawler) {
		this.max_number_active_crawler = max_number_active_crawler;
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
