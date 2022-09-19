FROM openjdk:11
MAINTAINER longpc.com
EXPOSE 8081
COPY output/jar/rest-gateway fams-rest-gateway
ENTRYPOINT ["java","-jar","/fams-rest-gateway"]