FROM openjdk:16-jdk-alpine

VOLUME /tmp
ADD ./build/libs/breakdowns-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar
