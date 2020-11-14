# Gradle

Run ``gradle build``

## hsqldb

Start the ``hsqldb`` server by running ``hsqldb/run_server.sh/bat``.

You can display the current state of any DB using the GUI tool by running ``hsqldb/GUI_hsqldb.sh/bat``.

Connect to the DB :

- User : ``SA``
- Password : `` ``
- URL : ``jdbc:hsqldb:hsql://localhost/test``

To remove all existing DB remove all ``test.*`` files.

## Spring boot

You have to start the ``HSQLDB`` server (well, not needed for the first and second part of the TP).

### Part 1 & 2

To start the application run ``gradle bootRun``/``gradle run``, you can also run the application through Eclipse, right click on ``Tp3Application.java`` -> ``Run As -> Java Application``.

The AOP is used on all methods within ``part1_2.*.implementation.*`` java files for the logging system (just a print for the test), there's also @Around used on all methods within ``part1_2.bank.implementation.*`` java files.

I'm using constructors for dependencies injection instead of ``@Autowired``.

### Part 3

On this part, since there's "lot" of things involved, I am using [Lombok](https://projectlombok.org/) and [MapStruct](https://mapstruct.org/) (I could use [JMapper](https://github.com/jmapper-framework/jmapper-core) as well, but it's not maintained anymore?).
Check [Performance of Java Mapping Frameworks](https://www.baeldung.com/java-performance-mapping-frameworks).

For ``Lombok`` you need to [install the plugin](https://projectlombok.org/setup/eclipse) on your IDE using the ``.jar``. ``Lombok 1.18.14`` is currently broken on Eclipse <https://github.com/rzwitserloot/lombok/issues/2599>, download the [version 1.18.12](https://search.maven.org/search?q=g:org.projectlombok%20AND%20a:lombok&core=gav).

For ``MapStruct`` you need to [install the plugin](https://mapstruct.org/documentation/ide-support/) on your IDE.

I am also using ``swagger`` through ``springfox-boot-starter 3.0.0`` and OpenAPI is enabled.

#### Run

To start the application run the application through Eclipse, right click on ``SampleDataJpaApplication.java`` -> ``Run As -> Java Application``.

#### Swagger

You can access ``swagger-ui`` through:

<http://localhost:8080/swagger-ui/>

#### AOP

I defined an Aspect that log every Rest access.

It detect all method that contain the annotations:

- GetMapping
- PostMapping
- PutMapping
- DeleteMapping

The detection is done in all subpackage of ``web`` (included).

The log look like this:

``(GET) /api/v1/kanban/get/{id} (getKanbanById -> class fr.brandon.tp3.part3.web.rest.v1.api.kanban.KanbanResource)``

I'm using ``@Slf4j`` from ``Lombok`` this way I don't have to create a logger, I can directly use ``log.method(...)``.

``Slf4j`` because ``LogBack`` is used by Spring.

#### Rest

Only Rest access for ``Kanban`` and ``Section`` are created.

You can add, get, update and delete.

You can use ``swagger-ui`` or ``Postman`` to test it out.

##### Test Rest

**Kanban**

Add a kanban with 2 sections associated to this kanban (cascade persist).

<http://localhost:8080/api/v1/kanban/add>

```json
{
  "name": "kanban1",
  "sections": [
    {
      "cartes": [],
      "name": "section1"
    },
    {
      "cartes": [],
      "name": "section2"
    }
  ]
}
```

Get the newly created kanban with ``id = 1``, result:

<http://localhost:8080/api/v1/kanban/get/1>

```json
{
  "id": 1,
  "sections": [
    {
      "id": 2,
      "name": "section1",
      "kanbanId": 1,
      "cartes": []
    },
    {
      "id": 3,
      "name": "section2",
      "kanbanId": 1,
      "cartes": []
    }
  ],
  "name": "kanban1"
}
```

Put (update) the name of ``kanban1`` to ``kanban15``, ``id = 1``:

<http://localhost:8080/api/v1/kanban/update/1>

```json
{
  "name": "kanban15"
}
```

result:

```json
{
  "id": 1,
  "sections": [
    {
      "id": 2,
      "name": "section1",
      "kanbanId": 1,
      "cartes": []
    },
    {
      "id": 3,
      "name": "section2",
      "kanbanId": 1,
      "cartes": []
    }
  ],
  "name": "kanban15"
}
```

Delete a kanban with ``id = 1``:

<http://localhost:8080/api/v1/kanban/delete/1>

``Kanban removed``

**Section**

Create a kanban without section (use the correct id since it's auto generated, in my test this one has ``id 4``):

<http://localhost:8080/api/v1/kanban/add>

```json
{
  "name": "kanban20",
  "sections": []
}
```

Add a section:

<http://localhost:8080/api/v1/section/add>

```json
{
  "cartes": [],
  "name": "section50"
}
```

Get the newly created section (find the good id, in my test ``id 5``), result (0 mean not associated to a kanban):

<http://localhost:8080/api/v1/section/get/5>

```json
{
  "id": 5,
  "name": "section1",
  "kanbanId": 0,
  "cartes": []
}
```

Put (update) the name of ``section50`` to ``section999`` and we associate it to a kanban (``id 4``), ``id = 5`` (same section id as above):

<http://localhost:8080/api/v1/section/update/5>

```json
{
  "name": "section999",
  "kanbanId": 4
}
```

result:

<http://localhost:8080/api/v1/section/get/5>

```json
{
  "id": 5,
  "name": "section999",
  "kanbanId": 4,
  "cartes": []
}
```

We can check that the section is affected on the kanban by doing a Get on kanban ``id 4``:

<http://localhost:8080/api/v1/kanban/get/4>

```json
{
  "id": 4,
  "sections": [
    {
      "id": 5,
      "name": "section999",
      "kanbanId": 4,
      "cartes": []
    }
  ],
  "name": "kanban1"
}
```

Delete a section with ``id = 5``:

<http://localhost:8080/api/v1/kanban/delete/5>

``Section removed``

Which gives when getting kanban ``id 4``:

<http://localhost:8080/api/v1/kanban/get/4>

```json
{
  "id": 4,
  "sections": [],
  "name": "kanban1"
}
```

Since we have 2 kanbans and because we need to get all kanbans for the GLI TP5, I added an api call to get all Kanbans. Just don't put the ``id``.

<http://localhost:8080/api/v1/kanban/get/>

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

