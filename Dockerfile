FROM openjdk:17
COPY target/demo3-*.jar app.jar
CMD ["java","-jar", "app.jar"]