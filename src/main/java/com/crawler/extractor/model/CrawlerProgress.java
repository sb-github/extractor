package com.crawler.extractor.model;

/**
 * 
 * @author Alexander Torchynskyi
 *
 * @date 31 January 2018
 */
public class CrawlerProgress {

	private String collecetion;
	private long amount;
	private Status vacancyStatus;

	public CrawlerProgress() {}

	public CrawlerProgress(String collecetion, long amount, Status vacancyStatus) {
		this.collecetion = collecetion;
		this.amount = amount;
		this.vacancyStatus = vacancyStatus;
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

	public String getCollecetion() {
		return collecetion;
	}

	public void setCollecetion(String collecetion) {
		this.collecetion = collecetion;
	}
}
