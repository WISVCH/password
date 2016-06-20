# Password modify self service

Provides a simple web form to modify your own password in LDAP. 

This application uses the [RFC 3062 LDAP Password Modify Extended Operation](https://www.ietf.org/rfc/rfc3062.txt)
as implemented by the [Ldaptive LDAP library](http://www.ldaptive.org).

## Development

This project is a Spring Boot application. To run, clone the repository and do:
  * Command line: `./gradlew bootRun`, or
  * IntelliJ IDEA: open the project folder and run the `PasswordApplication` class

The application will then be available at `http://localhost:8080/`.

## Deployment

The `master` branch is automatically deployed by TeamCity.
