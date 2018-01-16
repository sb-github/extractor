package com.crawler.extractor.interfaces;

import com.crawler.extractor.model.Crawler;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IExtractorRepository extends MongoRepository<Crawler,ObjectId>{

}
