package com.crawler.extractor.interfaces;

import com.crawler.extractor.model.CrawlerConf;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 
 * @author Dmytro Bilyi
 *
 * @data 09 January 2018
 * 
 */
public interface ICrawlerConfRepository extends MongoRepository<CrawlerConf, ObjectId> {

}
