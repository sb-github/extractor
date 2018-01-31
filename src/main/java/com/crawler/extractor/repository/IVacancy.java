package com.crawler.extractor.repository;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.crawler.extractor.model.Status;
import com.crawler.extractor.model.Vacancy;

/**
 * 
 * @author Alexander Torchynskyi
 *
 * @date 31 January 2018
 */

@Repository
public interface IVacancy extends MongoRepository<Vacancy, ObjectId> {

	List<Vacancy> findByCrawlerIdAndStatus(ObjectId crawlerId, Status status);

	long countByCrawlerIdAndStatus(ObjectId crawlerId, Status status);

}
