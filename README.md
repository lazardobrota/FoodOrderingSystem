# Food Ordering
<p>
  Choose dishes, order food and wait for delivery through status changes and enjoy! Watch out what permissions you have though. Admins can see orders of all clients, identify invalid orders, manage all existing accounts...
</p>

## Getting Started
<h4><ins>Requirements:</ins></h4>
<p>

  - JDK 21 or above
  - Some SQL database
  - Node.js 20 or above
</p>

#### <ins>Backend setup:</ins>

##### Database
<p>
  
  1. Choosen your SQL database management system create schema. I Recommend to name it `food_ordering_system` since schema.sql is already setup in a project for easer removing, cleaning and adding of tables but you don't have to. Everything will still work but you will need to write your own SQL script if you want to clean tables.
  2. Connect Database schema to the project in IDE of your choice.
</p>

##### Spring Boot
<p>

  1. Navigate to your project's main source directory: `backend/src/main/resources`
  2. Inside the `resources` folder, create a new file named `application.properties`(File is case-sensitive).
</p>
<p>
  
  After completing the steps above, your should have a folder structure `backend/src/main/resources/application.properties`
</p>
<p>
  
  Inside `application.properties` you can add key-value pairs to configure your application, copy template below and change values to the right of equals sign that are in CAPITAL LETTERS
</p>


```markdown
spring.application.name=backend

# Server
server.port=8090

# Datastore
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/YOUR_DB_SCHEMA
spring.datasource.username=DATASOURCE_USERNAME
spring.datasource.password=DATASOURCE_PASSWORD
spring.jpa.hibernate.ddl-auto=update

# Secret key
user.secret.key=JWT_SECRET_KEY
```

<p>

  You can use this site https://generate-random.org/encryption-key-generator?count=1&bytes=32&cipher=aes-256-cbc&string=&password= or any other to generate your secret key, its only important for it to be 256-bit or more
</p>

#### <ins>Frontend setup:</ins>
<p>
  
  Inside `frontend` folder, open terminal at the root of the project and write:
  ```markdown
npm install
```
After everything is downloaded, in the same terminal write:
```markdown
npm run dev
```

Enjoy!!
</p>

## Features & Technologies 
#### <ins>Backend:</ins>
<p>
  
  - Java
  - Spring Boot
  - Spring Security
  - Maven
  - SQL
  - JWT
  - Web Sockets
  - React
  - TypeScript
  - Tailwindcss
</p> 
