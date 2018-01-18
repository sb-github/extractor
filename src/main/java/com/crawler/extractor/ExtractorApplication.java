package com.crawler.extractor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import com.mongodb.MongoClient;

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
	}
}
