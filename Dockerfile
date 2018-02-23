FROM gradle:jdk8 AS BUILD_IMAGE
USER root
RUN mkdir /apps
WORKDIR /apps
ADD . .
RUN gradle build -x test

FROM openjdk:8-jre
COPY --from=BUILD_IMAGE /apps/build/libs/extractor.jar .
# ENTRYPOINT java -jar extractor.jar
CMD bash startup.sh