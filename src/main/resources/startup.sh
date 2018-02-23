#!/bin/bash -ex
java -jar extractor.jar --spring.config.name=application-${ENVIRONMENT:-development}
