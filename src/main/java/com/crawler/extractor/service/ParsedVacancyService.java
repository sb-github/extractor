package com.crawler.extractor.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.crawler.extractor.model.ParsedVacancy;
import com.crawler.extractor.repository.IParsedVacancy;

/**
 * Service for getting links from parsed_vacancy collection;
 * 
 * @author Alexander Torchynskyi
 * @date 16.04.2017
 *
 */
@Component
public class ParsedVacancyService {

	@Autowired
	private IParsedVacancy parsedVacancy;

	private Map<ObjectId, String> links;

	private List<ParsedVacancy> listOfParsedVacancies;

	/**
	 * It accepts an array of ids and find its links then return it in map collection;
	 * 
	 * @param listOfIds
	 * @return
	 */
	public Map<ObjectId, String> getLinks(List<ObjectId> listOfIds) {
		links = new HashMap<>();
		listOfParsedVacancies = parsedVacancy.findByIdIn(listOfIds);
		for (ParsedVacancy vacancy : listOfParsedVacancies) {
			links.put(vacancy.getId(), vacancy.getLink());
		}
		return links;
	}
}
