package com.crawler.extractor.model;

import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Class that represents collection of Crawler_conf.
 * 
 * @author Dmytro Bilyi, Stas Omelchenko
 *
 * @date 09 January 2018
 *
 */
@Document(collection = "crawler_conf")
public class CrawlerConf {

	@Id
	private ObjectId id;
	@Field(value = "max_number_active_crawler")
	private Integer maxNumberActiveCrawler;
	private Date createdDate;
	private Date modifiedDate;


	public CrawlerConf() {}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Integer getMaxNumberActiveCrawler() {
		return maxNumberActiveCrawler;
	}

	public void setMaxNumberActiveCrawler(Integer maxNumberActiveCrawler) {
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
