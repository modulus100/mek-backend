FROM amazoncorretto:21-alpine-jdk
COPY build/libs/app.jar app.jar
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=cloud","app.jar"]