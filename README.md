# SportCompassWebApplication

Requirements:
- minimum Java 8 is required
- Eclipse EE environment

Set up application:

1. Download zip(Binary Distributions -> Core) Tomcat v8.5 from : https://tomcat.apache.org/download-80.cgi

2. Unpackage on disk

3. In Eclipse menu select Window -> Show View -> Servers. If there is no servers available, click the link, 
then in Apache folder select Tomcat v8.5 Server. Hit Browse button and select folder where Tomcat is unpacked, then click Finish

4. Download project from GitHub: https://github.com/EmilWalewski/SportCompassWebApplication.git

5. Unpackage project.

6. In Eclipse menu select File -> Import, type maven, select Existing Maven Projects, click Next, click Browse and select unpacked project folder. Indicate option below and hit Finish. If any error occurs, please ignore it 

Creating database:

1. In file browser type h2 and select h2-1.4.192.jar
2. In JDBC URL paste: jdbc:h2:tcp://localhost/~/task2
3. Test connection and Connect
4. In command window paste and run one by one

-create table posts(id int primary key, postheader varchar, postcontent varchar)

-create table comments(id int primary key, idpost int, author varchar, content varchar)

-alter table  comments add  foreign key (idpost) references posts (id) on delete cascade on update cascade

Application is ready to use.

Additional information:

-Embedded H2 Database is added by maven dependency  
-In application I created interface using bootstrap

