package com.crawler.extractor.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.crawler.extractor.model.GraphSkill;

/**
 * 
 * 
 * @author yevhenii
 *
 * @date 15 January 2018
 * 
 */
public interface IGraphSkillRepository extends MongoRepository<GraphSkill, ObjectId> {
	public GraphSkill findBySkill(String skill);
}