

Project is written using Spring Boot version 2.0.2 (with Tomcat embedded) and Angular version 6.0.1.

## Prerequisites
You will need:
 - Apache Maven 3
 - Java 8

## Starting application

To start this application execute the following command from main project directory
```sh
$ mvn clean spring-boot:run
```    
*Note: this command doesn't run unit tests.*  

Or prepare executable:
```sh
$  mvn clean package spring-boot:repackage
```
Then in your /target directory you will have `CalculatorS-0.0.1-exec.jar` file.  
To start application run script `calculatorS.sh` from main project directory.     
     
Once the application is started, it can be reached at

    http://localhost:8080
    
You need to choose country of employment and provide correct daily rate (must be >=1). Then click *Compute* button to obtain monthly rate.    

## REST Endpoints

Angular app communicates with backend by rest services:

| HTTP Verb | Path                                           | Description                         | Status codes | Parameters |
| --------- | --------------------------------------------- | ----------------------------------- | -------------|--------------| 
| GET       | /countries                  | List all available countries                    | 200 OK| No parameters |
| GET       | /rate?dailyRate={dailyRate}&country={countryCode}  | Compute monthly rate for country when daily rate is {dailyRate}   | 200 OK, or 400 Bad Request when incorrect parameters |  *country* required, must be one of PL, UK, DE; *dailyRate* required, must be number >=1| 
