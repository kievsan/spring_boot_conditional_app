FROM openjdk:21

EXPOSE 8081

ADD target/spring_boot_conditional_app-0.0.1-SNAPSHOT.jar myapp.jar

ENTRYPOINT ["java","-jar","/myapp.jar"]
