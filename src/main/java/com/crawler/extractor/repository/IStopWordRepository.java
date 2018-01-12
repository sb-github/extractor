package com.crawler.extractor.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.crawler.extractor.model.StopWord;

/**
 * 
 * @author Dmytro Bilyi
 *
 * @data 11 January 2018
 * 
 */
public interface IStopWordRepository extends MongoRepository<StopWord, ObjectId> {

	public StopWord findByKey(String key);
}
