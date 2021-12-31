# TheJavaPlace

## The goal
This web application was created with one single goal in mind - to be a place where you can go to and get any of your questions, regarding Java and related technologies answered.

## To run the application
1. If you don't already have a MySQL user, you should create one, in order access the database trough JDBC.
2. Download the MySQL JDBC driver and insert it into the Tomcat /lib folder.
3. Run all of the queries inside the `create.sql` file, which is located in the `database` folder.
4. Update Tomcat’s JNDI resources to point to the new database. In order to do this you need to insert a new Resource entity in the context.xml file. Here is an example from my Tomcat 9 instance:
 ```
 <Resource  name="jdbc/TheJavaPlace"  type="javax.sql.DataSource"
maxActive="20"  maxIdle="5"  maxWait="10000"
username="tomcatUser"  password="insertYourPasswordHere"
driverClassName="com.mysql.jdbc.Driver"
defaultTransactionIsolation="READ_COMMITTED"
url="jdbc:mysql://localhost/TheJavaPlace"/>
 ```
5. Configure a HTTPS port for your Tomcat instance. This is achieved by inserting a new Connector entity inside of Tomcat's server.xml file. Here is an example from my Tomcat 9 instance:
```
<Connector  SSLEnabled="true"  port="8443"  protocol="org.apache.coyote.http11.Http11NioProtocol"  scheme="https"  secure="true">
	<SSLHostConfig>
		<Certificate certificateKeystoreFile="/directory/to/your/ssl/certificate"  certificateKeystorePassword="insertPasswordHere"  type="RSA"/>
	</SSLHostConfig>
</Connector>
```

6. Import the project into your IDE.
7. Compile and run the application.
8. Go to `localhost:8443/tjp` or `localhost:8080/tjp` (Spring security will automatically redirect you to 8443 which is the default for HTTPS).

## Walkthrough

Although you can browse the forum without being registered, in order to get the full experience you would want to sign up. In order to do so, click on the `Sign Up` button in the upper right corner.


![](https://i.imgur.com/KDrgaXc.png)

If you are already registered (and your account is not disabled) you can simply log in.


![](https://i.imgur.com/gw0sWCf.png)

Now that you are part of TheJavaPlace, you can create read update and delete threads and comments, browse user profiles and look trough their activity history customise profile image and do full text searches on threads. But be careful with your actions, our admins can alter every single action of the above mentioned, and if you are not communicating appropriately with the other users, your account may get suspended. 

![](https://i.imgur.com/hiZVWvM.png)
![](https://i.imgur.com/QMQTbBj.png)
![](https://i.imgur.com/9vN98HK.png)

If you have forgotten you password, don't worry. We have also got you covered. From the login page, go on to the `Forgot your password?` , enter your email and click send. A recovery message with instructions will be sent the email address, if there is a user registered with it. 

## This application was build using:

Back end:

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

The server side code of this application was written in Java JDK 16, utilising the Model View Controller and Controller Service Repository design patterns and the following Spring projects:
- Spring MVC;
- Spring Data JPA - With `Hibernate` as the implementation;
- Spring Security;

Database:

![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

A single MySQL database with 5 tables inside of it with different relationships between each other is being used. It's full text searching capabilities are being used in the searching functionality of the application. 



![](https://i.imgur.com/BfbdpSV.png)


Front end:

`Java Server Pages (JSP)` are being used extensively in the client side of the application, with a pinch of `JavaScript` DOM manipulation and its `XMLHttpRequest` object. `Bootstrap v5.0` is also being used extensively in order to give the web app a clean and responsive design. 
