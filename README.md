# Fitness Tracking Service

Simple Spring Boot REST service for fitness tracking system. 
It uses an in-memory H2 database for data storage with JPA/Hibernate for ORM. 
It exposes endpoints for managing users and their fitness activities.

## Prerequisites

- Java 17+ (or the project's configured JDK)
- Maven 3.6+
- Git (install on Windows from https://git-scm.com/download/win)
- (Optional) GitHub CLI `gh` for convenient repo creation

## Build and run

From the project root (where `pom.xml` lives):

```bash
mvn clean package
mvn spring-boot:run
