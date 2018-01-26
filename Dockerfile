FROM  gradle:jdk8
USER root
RUN mkdir /apps
WORKDIR /apps
ADD . .
RUN gradle build -x test
ENTRYPOINT java -jar build/libs/extractor-0.0.1-SNAPSHOT.jar

