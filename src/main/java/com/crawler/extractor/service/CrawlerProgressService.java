package com.crawler.extractor.service;

import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.crawler.extractor.model.CrawlerProgress;
import com.crawler.extractor.model.Status;
import com.crawler.extractor.repository.IParsedVacancy;
import com.crawler.extractor.repository.IVacancy;

/**
 * 
 * This service represent the API to get the view representation of the stage in which crawler is.
 * 
 * @author Alexander Torchynskyi
 *
 * @date 31 January 2018
 */

@Component
public class CrawlerProgressService {

	private final String NAME_OF_VACANCY_COLLECTION = "vacancy";
	private final String NAME_OF_PARSED_VACANCY_COLLECTION = "parsed_vacancy";

	@Autowired
	private IVacancy vacancy;
	@Autowired
	private IParsedVacancy parsedVacancy;

	private List<CrawlerProgress> crawlerProgress;

	public List<CrawlerProgress> getProgress(ObjectId crawlerId) {
		crawlerProgress = new ArrayList<>();
		getVacancyProgress(crawlerId);
		getParsedVacancyProgress(crawlerId);
		return crawlerProgress;
	}

	private void getVacancyProgress(ObjectId crawlerId) {
		CrawlerProgress progressOfVacancyNew = new CrawlerProgress(NAME_OF_VACANCY_COLLECTION,
				getVacancyStatusNew(crawlerId), Status.NEW);

		CrawlerProgress progressOfvacancyProgressed =
				new CrawlerProgress(NAME_OF_VACANCY_COLLECTION,
						getVacancyStatusProgressed(crawlerId), Status.PROCESSED);

		crawlerProgress.add(progressOfvacancyProgressed);
		crawlerProgress.add(progressOfVacancyNew);
	}

	private void getParsedVacancyProgress(ObjectId crawlerId) {
		CrawlerProgress progressOfVacancyNew =
				new CrawlerProgress(NAME_OF_PARSED_VACANCY_COLLECTION,
						getProgressedVacancyStatusNew(crawlerId), Status.NEW);

		CrawlerProgress progressOfvacancyProgressed =
				new CrawlerProgress(NAME_OF_PARSED_VACANCY_COLLECTION,
						getProgressedVacancyStatusProgressed(crawlerId), Status.PROCESSED);

		crawlerProgress.add(progressOfvacancyProgressed);
		crawlerProgress.add(progressOfVacancyNew);
	}

	private long getVacancyStatusNew(ObjectId crawlerId) {
		return vacancy.countByCrawlerIdAndStatus(crawlerId, Status.NEW);
	}

	private long getVacancyStatusProgressed(ObjectId crawlerId) {
		return vacancy.countByCrawlerIdAndStatus(crawlerId, Status.PROCESSED);
	}

	private long getProgressedVacancyStatusNew(ObjectId crawlerId) {
		return parsedVacancy.countByCrawlerIdAndStatus(crawlerId, Status.NEW);
	}

	private long getProgressedVacancyStatusProgressed(ObjectId crawlerId) {
		return parsedVacancy.countByCrawlerIdAndStatus(crawlerId, Status.PROCESSED);
	}
}
