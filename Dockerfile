FROM openjdk:8-jre-alpine

ADD /target/ ./executables/

ENTRYPOINT ["java", "-jar", "./executables/contact-list-0.0.1-SNAPSHOT.jar"]
