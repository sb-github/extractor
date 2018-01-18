package com.crawler.extractor.repository;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.crawler.extractor.model.StopWord;

/**
 * 
 * @author Dmytro Bilyi
 * 
 * @data 15 January 2017
 * 
 */
public interface IStopWordRepository extends MongoRepository<StopWord, ObjectId> {

	public List<StopWord> findByKeyIn(List<String> key);

	public void insertStopWords(List<String> stopWords);
}
