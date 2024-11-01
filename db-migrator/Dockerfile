FROM amazoncorretto:21-alpine-jdk
COPY build/libs/db-migrator.jar db-migrator.jar
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=cloud","db-migrator.jar"]