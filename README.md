# SportCompassWebApplication

Requirements:
- minimum Java 8 is required
- Eclipse IDE for Java EE Developers environment

Setting up application:

1. Download zip(Binary Distributions -> Core) Tomcat v8.5 from : https://tomcat.apache.org/download-80.cgi

2. Unpackage on disk

3. In Eclipse menu select Window -> Show View -> Servers. If there is no servers available, click the link, 
then in Apache folder select Tomcat v8.5 Server. Press Browse button and select folder where Tomcat is unpacked, then click Finish

4. Download project from GitHub: https://github.com/EmilWalewski/SportCompassWebApplication.git

5. Unpackage project.

6. In Eclipse menu select File -> Import, type maven, select Existing Maven Projects, press Next, click Browse and select unpacked project folder. Indicate option below and hit Finish. If any error occurs, please ignore it 

Creating database:

1. In file browser type h2 and select h2-1.4.192.jar
2. In JDBC URL paste: jdbc:h2:tcp://localhost/~/task2
3. Test connection then connect
4. In command window paste and run one by one

- create table posts(id int primary key, postheader varchar, postcontent varchar)

- create table comments(id int primary key, idpost int, author varchar, content varchar)

- alter table  comments add  foreign key (idpost) references posts (id) on delete cascade on update cascade

Running tests:

Click the right button of the mouse on the project, select Run as and press JUnit Tests

Running application: 

Click the right button of the mouse on the project, select Run as and press Run on Server, press Next, add project to Configured panel and click Finish

Additional information:

- Embedded H2 Database is added by maven dependency  
- In application UI is created using bootstrap
- The views are in the folder: src -> main -> webapp -> WEB-INF
- Tests and application classes are in Java Resoucre folder

