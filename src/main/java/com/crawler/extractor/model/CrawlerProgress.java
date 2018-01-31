package com.crawler.extractor.model;

/**
 * 
 * @author Alexander Torchynskyi
 *
 * @date 31 January 2018
 */
public class CrawlerProgress {

	private String collection;
	private long amount;
	private Status vacancyStatus;

	public CrawlerProgress() {
	}

	public CrawlerProgress(String collection, long amount, Status vacancyStatus) {
		this.collection = collection;
		this.amount = amount;
		this.vacancyStatus = vacancyStatus;
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Status getVacancyStatus() {
		return vacancyStatus;
	}

	public void setVacancyStatus(Status vacancyStatus) {
		this.vacancyStatus = vacancyStatus;
	}

}
