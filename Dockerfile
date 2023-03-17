FROM gradle:jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM ibm-semeru-runtimes:open-17.0.5_8-jre
COPY /build/libs/app-1.0.0.jar app.jar
ENTRYPOINT ["java","-Xmx128m","-jar","/app.jar"]
