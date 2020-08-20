# ![Check city connected Example using Spring boot]

> ### Spring boot + Unit Test skeleton and exception handler  codebase containing real world examples of connected cities and graph algorithm 

This codebase was created to demonstrate a fully fledged fullstack application built with Spring boot including Junit, and REST Service exception handler



# How it works

The application uses Spring boot .

* Use the idea of Domain Driven Design to separate the business term and infrastruture term.
* Use SpringBoot Event Listner to load data when application starts .
* Use Controller Advice to handle any kind of exception.

And the code organize as this:

1. `controller` is the web layer to implement by Spring MVC
2. `service` is the service model - due to short timeframe there is no business Model
3. `infrastructure`  contains all the implementation classes as the technique details



# FILE LOADER

It uses Spring class loader to load data file.

# Getting started

You need Java 8 installed.


To test that it works, open a browser tab at http://localhost:8080/tags .  
Alternatively, you can run

    curl http://localhost:8080/connected?origin=Boston&destination=Newark
    curl http://localhost:8080/connected?origin=Boston&destination=Philadelphia
    curl http://localhost:8080/connected?origin=Philadelphia&destination=Albany



# Run test

The repository contains a lot of test cases for exception handler and skeleton for service and controller to cover end to end unit test.


# Help

Please fork and PR to improve the code.
