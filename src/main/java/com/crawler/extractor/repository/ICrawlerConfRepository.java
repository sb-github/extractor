package com.crawler.extractor.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.crawler.extractor.model.CrawlerConf;

/**
 * 
 * @author Dmytro Bilyi, Stas Omelchenko
 *
 * @date 09 January 2018
 * 
 */
public interface ICrawlerConfRepository extends MongoRepository<CrawlerConf, ObjectId> {

}
