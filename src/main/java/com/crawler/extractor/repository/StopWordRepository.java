package com.crawler.extractor.repository;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.stereotype.Repository;
import com.crawler.extractor.interfaces.IStopWordRepository;
import com.crawler.extractor.model.StopWord;

@Repository
public class StopWordRepository extends MongoRepository<T, ID> implements IStopWordRepository {

	@Autowired
	private BulkOperations ops;

	@Autowired
	private MongoTemplate mongoTemplate;


	public StopWordRepository(MongoEntityInformation<StopWord, ObjectId> metadata,
			MongoOperations mongoOperations) {
		super(metadata, mongoOperations);
	}



	@Override
	public List<StopWord> findByKeyIn(List<String> key) {

		return null;
	}

	@Override
	public void updateStopWords(List<String> words) {

	}

}
