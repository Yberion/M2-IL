# Gradle

Run ``gradle build`` in a terminal.

## hsqldb

Download the last version here : http://hsqldb.org/ and place it under the folder ``hsqldb``.

Start the ``hsqldb`` server by running ``hsqldb/run_server.sh/bat``.

You can display the current state of any DB using the GUI tool by running ``hsqldb/GUI_hsqldb.sh/bat``.

Connect to the DB :

- User : ``SA``
- Password : 
- URL : ``jdbc:hsqldb:hsql://localhost/``

To remove all existing DB remove all ``test.*`` files.

## jetty

https://plugins.gradle.org/plugin/org.gretty

Doc : https://gretty-gradle-plugin.github.io/gretty-doc/

Command to start the server with gradle :

``gradle appRunWar``

Then check:

http://localhost:8080/TP2/
http://localhost:8080/TP2/myform.html
http://localhost:8080/TP2/kanban/ManageKanban

Press any key on the gradle terminal to stop jetty.

## jaxrs

You need to run the hsqldb server before testing jaxrs since we're using the DAO classes from JPA.

Be sure to not have jetty started already.

``Run As -> Java Application`` on the class ``TP2/jaxrs/RestServer.java``.

I added all XML annotations in the JPA implementation, check in the package ``TP2/jpa/kanban/domain``, I only did it for ``Kanban.java``.

## swagger-ui

You can use swagger-ui to use API Rest.

Start the server and use http://localhost:8080/api/

## Postman

In Postman, simple tests :

GET
    url: http://localhost:8080/pet/1

POST
    url: http://localhost:8080/pet/
    Headers:
        Content-Type: application/json
    Body:
```json
{
    "id": 1,
    "name": "toto",
    "tag": []
}
```

For the Kanban part, since it uses the DAO classes we can actually get a result from the GET method.

Be carefull to not request unvailable object, there isn't any protection implemented since it's not the case of the TP, this way, do not ask for Kanban ID 200 if it doesn't exist, also, do not override an existing one.

You can test with :

POST
- url: http://localhost:8080/kanban/add/

Headers:
- Content-Type: application/json

Body:
```json
{
    "id": 0,
    "section": [],
    "name": "TAA20"
}
```

POST
- url: http://localhost:8080/kanban/add/

Headers:
- Content-Type: application/json

Body:
```json
{
    "id": 1,
    "section": [],
    "name": "TAA500"
}
```

GET
- url: http://localhost:8080/kanban/get/1

GET
- url: http://localhost:8080/kanban/get/2
