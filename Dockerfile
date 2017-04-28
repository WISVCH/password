FROM wisvch/alpine-java:8_server-jre_unlimited 
ADD build/libs/password.jar /srv/password.jar
WORKDIR /srv
ENV JAVA_OPTS="-Xms128M -Xmx128M -Djava.security.egd=file:/dev/./urandom"
CMD "/srv/password.jar"
