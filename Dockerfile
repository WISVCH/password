FROM wisvch/alpine-java:8_server-jre_unlimited 
ADD build/libs/password.jar /srv/password.jar
WORKDIR /srv
CMD "/srv/password.jar"
