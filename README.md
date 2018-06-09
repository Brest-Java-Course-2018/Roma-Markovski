Writers and Publications
------------------------

[![Build Status](https://travis-ci.org/Brest-Java-Course-2018/Roma-Markovski.svg?branch=master)](https://travis-ci.org/Brest-Java-Course-2018/Roma-Markovski)
[![Coverage Status](https://coveralls.io/repos/github/Brest-Java-Course-2018/Roma-Markovski/badge.svg?branch=master)](https://coveralls.io/github/Brest-Java-Course-2018/Roma-Markovski?branch=master)

‘Writers and publications’ is a web-application which allows users to record information about the writers and the publications they’ve written. The application provides:
+ storing publications and writers in a database;
+ viewing the list of publications;
+ changing the list of publications (adding, editing, removing);
+ viewing the list of writers;
+ changing the list of writers (adding, editing, removing);
+ displaying number of the writer’s publications;
+ publication filtration by date.

#### Required software
The following software need to be installed:
1. JDK 1.8
2. Maven (v3.3.9)

#### Check environment configuration

    $ java -version
    $ export JAVA_HOME = ...
    $ mvn -version

#### Build

    $ mvn clean install 


#### Reporting

    $ mvn site
    $ mvn site:stage
    Go to './target/staging/index.html'
    
#### Start REST application:
    
    $ mvn -pl rest-app/ jetty:run
Rest service should be available at 
<http://localhost:8088>

**Some rest requests**
    
    Get all publicationDTOs:        
    $ curl -X GET -v http://localhost:8088/publications
    
    Get publications filtered by date:
    $ curl -X GET -v http://localhost:8088/publications/2017-07-03/2018-03-13
        
    Add publication:
    $ curl -H "Content-Type: application/json" -X POST -d '{"writerId":"1","name":"Otello","description":"Tragedy", "numberOfPages":"245"}' -v http://localhost:8088/publications   
    
    Delete publication:
    $ curl -X DELETE -v http://localhost:8088/publications/2
    
    
    Get all writers:
    $ curl -X GET -v http://localhost:8088/writer_models
    
    Get writer by id:
    $ curl -X GET -v http://localhost:8088/writers/1
    
    Edit writer:
    $ curl -H "Content-Type: application/json" -X POST -d '{"name":"Lermontov Michael","country":"Russia", "id":"1"}' -v http://localhost:8088/writers/1
    
    ...
    
#### Start WEB application:

    $ mvn -pl web-app/ jetty:run
Web application should be available at 
<http://localhost:8080>

#### Travis CI Integration:

Visit <https://travis-ci.org/Brest-Java-Course-2018/Roma-Markovski>
 
--------

>Produced by Roman Markovski.  
Brest Java Courses  
2018 
