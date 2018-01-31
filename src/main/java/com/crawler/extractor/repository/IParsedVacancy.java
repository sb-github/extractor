package com.crawler.extractor.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.crawler.extractor.model.ParsedVacancy;
import com.crawler.extractor.model.Status;

/**
 * 
 * @author Alexander Torchynskyi
 *
 * @date 31 January 2018
 */

@Repository
public interface IParsedVacancy extends MongoRepository<ParsedVacancy, ObjectId> {

	long countByCrawlerIdAndStatus(ObjectId crawlerId, Status status);
}
