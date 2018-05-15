

Project is written using Spring Boot version 2.0.0 (with Tomcat embedded)

## Prerequisites
You will need:
 - Apache Maven 3.0.3
 - Java 8

## Starting application

To start this application execute the following command from main project directory
```sh
$ mvn clean spring-boot:run
```    

Or prepare executable:
```sh
$  mvn clean package spring-boot:repackage
```
Then in your /target directory you will have `CalculatorS-0.0.1-exec.jar` file.  
To start application run script `calculatorS.sh` from main project directory.     
     
Once the application is started, it can be reached at

    http://localhost:8080

## REST Endpoints

Angular app communicates with backend by rest services:

| HTTP Verb | URL                                           | Description                         | Status codes |
| --------- | --------------------------------------------- | ----------------------------------- | -------------|
| GET       | http://localhost:8080/countries                  | List all available countries                    | 200 OK|
| GET       | http://localhost:8080/rate?dailyRate={dailyRate}&country={countryCode}  | Compute monthly rate for country when daily rate is {dailyRate}   | 200 OK, or 400 Bad Request when incorrect parameters |    
