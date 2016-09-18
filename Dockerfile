FROM wisvch/alpine-java:8_server-jre_unlimited 
ADD build/libs/password.jar /srv/password.jar
WORKDIR /srv
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/srv/password.jar"]
