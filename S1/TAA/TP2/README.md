### Using jetty

https://plugins.gradle.org/plugin/org.gretty

Doc : https://gretty-gradle-plugin.github.io/gretty-doc/

Command to start the server with gradle :

``gradle appRun``

Then check http://localhost:8080/TP2/

### hsqldb

Download the last version here : http://hsqldb.org/ and place it under the folder ``hsqldb``.

Start the ``hsqldb`` server by running ``hsqldb/run_server.sh/bat``.

You can display the current state of any DB using the GUI tool by running ``hsqldb/GUI_hsqldb.sh/bat``.

Connect to the DB :

- User : ``SA``
- Password : 
- URL : ``jdbc:hsqldb:hsql://localhost/``

To remove all existing DB remove all ``test.*`` files.