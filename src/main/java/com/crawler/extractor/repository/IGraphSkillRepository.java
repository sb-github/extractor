package com.crawler.extractor.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.crawler.extractor.model.GraphSkill;

public interface IGraphSkillRepository extends MongoRepository<GraphSkill, ObjectId> {

	public GraphSkill findBySkill(String skill);
}
