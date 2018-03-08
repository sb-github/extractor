package com.crawler.extractor.repository;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
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

	// TODO delete if the one below works;
	public List<GraphSkill> findByCrawlerId(ObjectId id);

	public List<GraphSkill> findByCrawlerId(ObjectId id, Pageable pageable);
}
