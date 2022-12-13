FROM openjdk:11
RUN mkdir /app
COPY target/users-0.0.1-SNAPSHOT.jar /app
EXPOSE 9010
ENV JAVA_OPTS=""
WORKDIR /app
CMD java $JAVA_OPTS -jar users-0.0.1-SNAPSHOT.jar