# Credit Card microservice API

The Credit Card microservice API for storing and fetching credit card details (written in Java and Spring Boot). This API acts as the interface between the Credit Card UI and the in memory h2 Database.

All incoming JSON request payloads are validated using [Spring validation](https://spring.io/guides/gs/validating-form-input/).

### Routes

Base Path: `/credit-card-microservice/v1.0`

Routes:
- _POST_ &nbsp; `/add` - used to add credit card details in the database. The response code is 200 and string "SUCCESS". If a validation error occurs in the payload or the HTTP headers, an HTTP 400 error is sent back.
- _GET_ &nbsp; `/all` - retrieves previously stored card data by /add endpoint. The response is list of card details sent with HTTP 200.
-
### Idempotency Logic

Essentially for a first time POST request, new data is created and a HTTP 201 (CREATED) response is given. 
However, if the same card number is repeated, the  API will retrieve the existing data and return with a HTTP 409 (CONFLICT) response.

The Idempotency flow is driven by the mandatory `Card Number` in the body. If these card number values will change, you can expect new record to be created 201.

### Logging

The internal Log4j2 Configuration Logging is used on this project. This will capture HTTP header information per request for logging purposes. This is useful for tracking requests in environments such as SIT using the unique `txn-correlation-id` value.

### Validations

Spring-boot validation framework is used to validation the fields and also luhn algorithm validation is also used.
Below are the validations used in the API
1. Header validation for correlation-id mandatory check
2. Input request body validation 
3. Custom luhn algorithm validation for card number




### Error handling  

Global Exception Handling is used for all errors and unhappy path. This includes all validation issues, Not found paths and other Runtime Exceptions. Refer to this [link](https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc) for further information.

All captured errors are rendered in a consistent JSON format for the Channel API to process. See below for an example:

```
{
    "error": {
        "statusCode": 22003,
        "code": "ERR_INVALID_INPUT",
        "message": "creditCardNumber Card number length must be between 1 and 19"
    }
}
```

## Run

You can run this in your development environment via a local Server.
- java version "1.8.0_291"
- maven 3.8.1  
- Run Local - `mvn clean spring-boot:run`
- Run Local in DEBUG mode - `mvn clean spring-boot:debug`

## Endpoints

**** save end point POST http://localhost:9080/credit-card-microservice/v1.0/add *****

Request header
Accept:application/json
Content-Type:application/json
txn-correlation-id:11111

Request body
{
"name": "DP",
"creditCardNumber": "123456789072",
"balance": 1000,
"limit": 1000
}

Response 
201 CREATED

**** Fetch end point GET http://localhost:9080/credit-card-microservice/v1.0/all ******

Request header
Accept:application/json
Content-Type:application/json
txn-correlation-id:111111

Response Body
[
{
"name": "avi",
"creditCardNumber": "123456789007",
"balance": 0,
"limit": 20000
},
{
"name": "Jaya",
"creditCardNumber": "123456789015",
"balance": 1000,
"limit": 3000
},
{
"name": "DP",
"creditCardNumber": "123456789072",
"balance": 1000,
"limit": 1000
}
]


## Test

All tests junit  tests written in Groovy within the src/test/groovy directory. They can be run via maven commands or in your IDE (such as Eclipse or IntelliJ).

- Execute all tests - `mvn clean test`

All the above tests must pass for a successful build.
