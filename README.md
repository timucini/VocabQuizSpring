# VocabQuizSpring
Vocabulary Quiz with Java Spring


## Features

- Simple vocabulary quiz made with Java using Spring Boot, Spring Data JPA, Spring Web and React as frontend-framework.

## Requirements
For building and running the application you need:
 - [JDK 11](https://www.oracle.com/de/java/technologies/javase-jdk11-downloads.html)
 - [Maven 4](https://maven.apache.org)

For the frontend client you need:  
 - [nodejs Version > 12](https://nodejs.org/en/download/)


## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `configuration/src/main/java/vocab/WebApplication.java` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Project Structure
This project is a multi module Maven project using Spring Boot.
In the `pom.xml` in the project root you can find all relevant dependencies.
- Configuration: Start the spring container here. All application.properties can be found here.


## Frontend
The frontend is build with React.
To build the frontend client open `web/src/main/client` in your command line and run `npm install`.
This will install the client and all relevant node modules locally.


To start the app run "npm start" as described below:
### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.\
You will also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.

