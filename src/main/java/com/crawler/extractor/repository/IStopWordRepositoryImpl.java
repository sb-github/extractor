package com.crawler.extractor.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.stereotype.Repository;
import com.crawler.extractor.model.StopWord;
import com.crawler.extractor.repository.IStopWordRepository;
import com.mongodb.MongoClient;

/**
 * 
 * @author Dmytro Bilyi
 * 
 * @data 15 January 2017
 * 
 */
@Repository
public class IStopWordRepositoryImpl extends SimpleMongoRepository<StopWord, ObjectId>
		implements IStopWordRepository {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Autowired
	MongoOperations mongoOperations;

	@Autowired
	public IStopWordRepositoryImpl(MongoTemplate mongoTemplate,
			MongoRepositoryFactory mongoRepositoryFactory) {
		super(mongoRepositoryFactory.getEntityInformation(StopWord.class), mongoTemplate);
	}

	public IStopWordRepositoryImpl(MongoEntityInformation<StopWord, ObjectId> metadata,
			MongoOperations mongoOperations) {
		super(metadata, mongoOperations);
	}

	@Override
	public List<StopWord> findByKeyIn(List<String> key) {
		List<StopWord> listStopWords = new ArrayList<>();
		Query query = new Query(Criteria.where("key").in(key));
		listStopWords = mongoOperations.find(query, StopWord.class);
		return listStopWords;
	}

	@Override
	public void insertStopWords(List<String> words) {
		BulkOperations ops = mongoOperations.bulkOps(BulkMode.UNORDERED, StopWord.class);
		List<StopWord> stopWordsToAdd = new ArrayList<>();
		for (String item : words) {
			stopWordsToAdd.add(new StopWord(item, new Date(), new Date()));
		}
		ops.insert(stopWordsToAdd);
		ops.execute();
	}
}
