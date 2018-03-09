cloud-function-template
---

Template project for creating lambda functions on AWS.

##### _ad rem:_
The current documentation for cloud functions is lackluster (as the project is in its infancy). The main issues being generating the correct JAR file with gradle, and lambda recognizing the function that's deployed. This project is to facilitate skipping all the required tinkering.

### Technology
* Spring / Spring cloud
    * Feign
* AWS
    * Lambda
    * DynamoDB
    * lambda-log4j
* docker / docker-compose

I left out a logging configuration.

### Installing / Running
```
docker-compose up
```
and then wait a while.

#### or
(untested)
1) Install `docker`, `java jdk 8+`.
2) Start the docker dynamoDB database.
3) From the root directory run the gradle wrapper: `./gradlew bootRun`

### Interaction

Add a customer to the database:
```
curl -d '{"customerId":"value1", "firstName": "Petros", "lastName": "Ring"}' -H "Content-Type: application/json" -X POST http://localhost:8080/createCustomerFunction
```

Get a customer from the database:
```
curl -H "Content-Type: application/json" -X GET http://localhost:8080/getCustomerFunction/value1
```


### Concepts

Marshalling into dynamo
Handlers / Functions
Selecting which function to execute with `FUNCTION_NAME`


### License

MIT, 2018.