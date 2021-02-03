# car-rental

[![Java CI with Maven](https://github.com/akelsch/car-rental/workflows/Java%20CI%20with%20Maven/badge.svg)](https://github.com/akelsch/car-rental/actions) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=akelsch_car-rental&metric=alert_status)](https://sonarcloud.io/dashboard?id=akelsch_car-rental) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=akelsch_car-rental&metric=coverage)](https://sonarcloud.io/dashboard?id=akelsch_car-rental)

Car Rental JavaFX desktop application powered by Spring

## Background

This project started out as a university project during our Bachelor studies as part of the *Programming 3* class at
the [University of Applied Sciences in Saarbrücken](https://www.htwsaar.de/). We picked it up for our Master studies
again, this time as part of the *Software Quality Engineering* class. Originally, the goal was to get familiar with UI
programming. Now, we want to leverage our experience and knowledge to increase the overall quality of the project.

## Technology Stack

- Java 15
- JavaFX
- Spring Boot

## Getting Started

### Building from Source

We use Maven for building the source code. Run the following command to compile with tests:

```sh
mvn clean verify
```

#### Using headful mode

By default, the JavaFX tests run in headless mode. Use the following property if you want to run in headful mode:

```sh
mvn clean verify -Dtests.testfx.headless=false
```

### Running the Application

We can make use of Springs Maven plugin to start the application like so:

```sh
mvn spring-boot:run
```

#### Changing Languages

To change the language of the application you can replace the JVM locale like so:

```sh
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Duser.language=de -Duser.country=DE"
```
