FROM java:8

COPY /target/monitor-0.0.1-SNAPSHOT.jar /monitor.jar

RUN /bin/bash -c "touch /monitor.jar"

EXPOSE 8083

ENTRYPOINT ["java","-jar","monitor.jar","spring.profiles.active=test", "-g", "daemon off;"]

