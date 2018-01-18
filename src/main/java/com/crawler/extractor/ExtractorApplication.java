package com.crawler.extractor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import com.mongodb.MongoClient;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;


@PropertySource(value = "classpath:application.properties")
@SpringBootApplication
public class ExtractorApplication {

	@Value("${spring.data.mongodb.host}")
	private String hostName;
	@Value("${spring.data.mongodb.port}")
	private Integer portNumber;
	@Value("${spring.data.mongodb.database}")
	private String dbName;

	public static void main(String[] args) {
		SpringApplication.run(ExtractorApplication.class, args);
	}

	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(new MongoClient(hostName, portNumber), dbName);
	}

	@Bean
	public MongoRepositoryFactory mongoRepositoryFactory() {
		return new MongoRepositoryFactory(mongoTemplate());

	public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MongoMappingContext context) {
		MappingMongoConverter converter =
				new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));

		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);

		return mongoTemplate;

	}
}
