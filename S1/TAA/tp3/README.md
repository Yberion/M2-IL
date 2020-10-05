# Gradle

Run ``gradle build``

## hsqldb

Download the last version here : http://hsqldb.org/ and place it under the folder ``hsqldb``.

Start the ``hsqldb`` server by running ``hsqldb/run_server.sh/bat``.

You can display the current state of any DB using the GUI tool by running ``hsqldb/GUI_hsqldb.sh/bat``.

Connect to the DB :

- User : ``SA``
- Password : 
- URL : ``jdbc:hsqldb:hsql://localhost/test``

To remove all existing DB remove all ``test.*`` files.

## Spring boot

You have to start the ``HSQLDB`` server (well, not needed for the first and second part of the TP).

### Part 1 & 2

To start the application run ``gradle bootRun``/``gradle run``, you can also run the application through Eclipse, right click on ``Tp3Application.java`` -> ``Run As -> Java Application``.

The AOP is used on all methods within ``part1_2.*.implementation.*`` java files for the logging system (just a print for the test), there's also @Around used on all methods within ``part1_2.bank.implementation.*`` java files.

I'm using constructors for dependencies injection instead of ``@Autowired``.

### Part 3


___

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.3.RELEASE/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.3.RELEASE/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.3.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.3.3.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

