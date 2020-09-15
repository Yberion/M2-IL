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

### Test JPA

``Run AS -> Java Application`` on the class ``TP1/JpaTest.java`` on Eclipse.

It will create 3 Kanbans :

- TAA1
- TAA2
- TAA3

And create for each of them 3 sections :

- En attente
- En cours
- Réalisé

Kanbans' name will be displayed in the console.
