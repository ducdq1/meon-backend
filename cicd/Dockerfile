FROM 10.60.156.72/library/openjdk:8-jdk-alpine

WORKDIR /

RUN mkdir -p /newrelic
copy ./newrelic /newrelic
ENV JAVA_OPTS="$JAVA_OPTS -Dnewrelic.environment=production -javaagent:/newrelic/newrelic.jar"


COPY ./serviceTelecare-0.0.1-SNAPSHOT.jar ./serviceTelecare-0.0.1-SNAPSHOT.jar

RUN cp /usr/share/zoneinfo/Asia/Ho_Chi_Minh /etc/localtime

EXPOSE 9797

ENTRYPOINT ["java","-jar","/serviceTelecare-0.0.1-SNAPSHOT.jar"]

