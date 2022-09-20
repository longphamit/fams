FROM openjdk:8-jdk-alpine
MAINTAINER longpc.com
EXPOSE 8081
COPY /output/jar/rest-gateway.jar fams-rest-gateway
ENTRYPOINT ["java","-jar","/fams-rest-gateway"]