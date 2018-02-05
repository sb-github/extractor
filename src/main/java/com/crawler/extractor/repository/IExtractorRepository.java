package com.crawler.extractor.repository;

import com.crawler.extractor.model.Crawler;
import com.crawler.extractor.model.Status;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 
 * @author Stas Omelchenko, Dmytro Bilyi
 * 
 * @date 05 January 2017
 *
 */
public interface IExtractorRepository extends MongoRepository<Crawler, ObjectId> {
	List<Crawler> findByStatusIn(Status... status);
}
