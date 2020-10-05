#!/bin/bash
java -cp hsqldb.jar org.hsqldb.server.Server --database.0 file:test --dbname.0 test
