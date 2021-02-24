# Payment Service

A RESTful API to transfer money between accounts.

## Frameworks and Libraries used

- **Java** 15
- **Postgresql** 12
- **Spring Boot** 2.4.3
- **Spring Data JPA**
- **Spring Validation**
- **Spring JDBC**
- **Hibernate-types-52**
- **Flyway**
- **Springdoc-openapi**

## Starting the application

1. Create a manual DB or start docker-compose on folder db/, or start make command `make init`
2. Run the following command: `./gradlew bootJar`
3. This will create a single fat jar which can be executed
   with `java -jar build/libs/payment-service-0.0.1-SNAPSHOT.jar`

## Using the REST API

### Swagger Documentation

`http://localhost:8080/swagger-ui.html`

### Clients

#### Create a client with accounts

`curl -X POST "http://localhost:8080/api/clients" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{\"first_name\":\"string\",\"last_name\":\"string\",\"accounts\":[{\"balance\":0,\"account_num\":\"string\",\"account_type\":\"card\"}]}"`
.

#### Get client accounts

`curl -X GET "http://localhost:8080/api/clients/1/get_accounts" -H  "accept: application/xml"`

### Payments

#### Create payment

`curl -X POST "http://localhost:8080/api/payments" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{\"amount\":0,\"reason\":\"string\",\"source_acc_id\":0,\"dest_acc_id\":0}"`

#### Create bulk payments

`curl -X POST "http://localhost:8080/api/payments/bulk_create" -H  "accept: application/xml" -H  "Content-Type: application/json" -d "[{\"amount\":0,\"reason\":\"string\",\"source_acc_id\":0,\"dest_acc_id\":0}]"`

#### Find payments for dynamic parameters

`curl -X POST "http://localhost:8080/api/payments/find_by_criteria" -H  "accept: application/xml" -H  "Content-Type: application/json" -d "{\"payer_id\":0,\"recipient_id\":0,\"source_acc_id\":0,\"dest_acc_id\":0}"`