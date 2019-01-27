FROM openjdk:8u181-jdk-alpine3.8

WORKDIR /opt/reactive-app

COPY build/libs/*.jar ./reactive-app.jar

EXPOSE 8888

CMD ["java", "-Djava.security.edg=file:/dev/./urandom", "-jar", "reactive-app.jar"]