FROM openjdk

RUN mkdir /app

COPY gradle /app/gradle
COPY src /app/src
COPY build.gradle /app
COPY gradlew /app

RUN cd /app \
    && ./gradlew clean \
    && ./gradlew shadowJar \
    && cp $(find . -name *aws.jar) /app.jar \
    && sh -c 'chmod +x /app.jar' \
    && cd / \
    && rm -rf /app

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=default

CMD [ "java", "-Xss10m", "-Xms256m", "-Xmx1024m", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar" ]