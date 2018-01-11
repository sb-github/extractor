package com.crawler.extractor.repository;

import com.crawler.extractor.model.Crawler;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IExtractorRepository extends MongoRepository<Crawler,ObjectId>{

}
