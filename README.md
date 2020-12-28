# POC IOBuilders

This is a sample to build a mini poc using a microservices stack.

We will have to perform actions and deposit accounts. All users can interact with all accounts. In this example users act like employees of a company that manages deposit accounts.

# Projects
- eureka-server 
- zuul-server
- auth-service
- users-app
- deposits-app
- truffle-project-deposit-accounts

## eureka-server
This server contains an eureka-server acting like **Service Registry**
## zuul-server
This server acts like an **API Gateway**, being a proxy facade between us and the microservices.
It includes a filter trying to obtains a **JWT Token**, otherwise it will throw a 401 status code response (Unauthorized).
It includes an Spring Configuration to exclude some endpoints to permit without authentication, for example ("/auth")

## auth-service
This is a controller exposing a "/auth" endpoint to login and get a valid JWT Token

## users-app
Project using hexagonal architecture that allows create new users.
This project is based on a multi module maven project to decouple it.

## deposits-app
Project using hexagonal architecture that allows to create a deposit account, deposit money inside, and withdraw.
This project is based on a multi module maven project to decouple it.


## truffle-project-deposit-accounts
A truffle project with a smart contract called "DepositAccount".
This smart contract allow to anybody send money to deposit, but only the owner of the contract can withdraw.

# Pre Requisites
- Java JDK 1.8 (at least)
- Maven 3
- Ganache to simulate a blockchain (the project connects to localhost in 7545, take care of enable Ganache to listen on all interfaces)

# How to run it?
- `cd eureka-server`
- `mvn spring-boot:run`
---
- `cd zuul-server`
- `mvn spring-boot:run`
---
- `cd auth-server`
- `mvn spring-boot:run`
---
- `cd users-app`
- `mvn clean install`
- `cd users-app-starter`
- `mvn spring-boot:run`
---
- `cd deposits-app`
- `mvn clean install`
- `cd deposits-app-starter`



# Pending and Improvements
- Move to event driven. Publishing events will not have to user a circuit breaker for example.
- We will have other problems like 'the uniqueness of the events' or 'duplication of events', etc
- Testing (The project only contains two test classes in users-app project).

> In users-app-core, a test mocking calls, etc. In users-app-domain, to check identity of a dto.


