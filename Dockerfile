FROM openjdk

RUN mkdir /app

COPY gradle /app/gradle
COPY src /app/src
COPY build.gradle /app
COPY gradlew /app

RUN cd /app \
    && ./gradlew clean \
    && ./gradlew build -x test \
    && cp $(find . -name *.jar) /app.jar \
    && sh -c 'chmod +x /app.jar' \
    && cd / \
    && rm -rf /app

EXPOSE 8080

CMD [ "java", "-Xss10m", "-Xms256m", "-Xmx1024m", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar" ]