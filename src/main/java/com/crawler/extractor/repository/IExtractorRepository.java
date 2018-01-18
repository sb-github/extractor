package com.crawler.extractor.repository;

import com.crawler.extractor.model.Crawler;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 
 * @author Dmytro Bilyi
 * 
 * @data 09 January 2017
 * 
 */
public interface IExtractorRepository extends MongoRepository<Crawler, ObjectId> {

}
