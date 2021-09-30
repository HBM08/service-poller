### stage 1: build the app ###
FROM gradle:jdk11 as builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

### stage 2: copy the jar from previous step output and run it ###
FROM openjdk:11-jre-slim
COPY --from=builder /home/gradle/src/build/libs/service-poller-server-1.0.0.jar service-poller-server-1.0.0.jar
ENTRYPOINT ["java","-jar","/service-poller-server-1.0.0.jar"]