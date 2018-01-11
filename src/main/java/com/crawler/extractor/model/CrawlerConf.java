package com.crawler.extractor.model;

import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that represents collection of Crawler_conf;
 * 
 * @author Dmytro Bilyi
 *
 * @data 09 January 2018
 *
 */
@Document(collection = "crawler_conf")
public class CrawlerConf {

	@Id
	private ObjectId id;
	@JsonProperty(value = "max_number_active_crawler")
	private int maxNumberActiveCrawler;
	private Date createdDate;
	private Date modifiedDate;


	public CrawlerConf() {}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public int getMaxNumberActiveCrawler() {
		return maxNumberActiveCrawler;
	}

	public void setMaxNumberActiveCrawler(int maxNumberActiveCrawler) {
		this.maxNumberActiveCrawler = maxNumberActiveCrawler;
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
