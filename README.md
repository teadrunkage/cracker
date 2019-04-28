
=======
# Cracker-shop marketplace.

This is a training group project for NetCracker learning center 2019.

## To build from Docker:
$ mvn clean package docker:build

This is a multi-module project. Marketplace works together with local shops. Make sure to run with the following apps launched:
https://github.com/teadrunkage/workingrepo/
https://github.com/MaximKrasikov/NCprojects

##To build without Docker:
$ mvn spring-boot:run

App listens on port 5030, look docker-compose.yml
