FROM wisvch/spring-boot-base:1
COPY ./build/libs/password.jar /srv/password.jar
USER spring-boot
CMD ["/srv/password.jar"]
