FROM openjdk:8
EXPOSE 8082

ADD target/snmpdatabase-0.0.1-SNAPSHOT.jar snmpdatabase.jar
ENTRYPOINT ["java","-jar","snmpdatabase.jar"]