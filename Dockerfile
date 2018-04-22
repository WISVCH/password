FROM wisvch/spring-boot-base:1
COPY ./build/libs/password.jar /srv/password.jar
CMD ["/srv/password.jar"]
