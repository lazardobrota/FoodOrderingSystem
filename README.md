# User management
<p>To be written</p>

#### <ins>Backend setup:</ins>
<p>

  1. Navigate to your project's main source directory: `backend/src/main`
  2. Create a new folder named `resources` inside `backend/src/main` directory. The folder structure should now look like this: `backend/src/main/resources`
  3. Inside the `resources` folder, create a new file named `application.properties`(File is case-sensitive).
</p>
<p>
  
  After completing the steps above, your should have a folder structure `backend/src/main/resources/application.properties`
</p>
<p>
  
  Inside `application.properties` you can add ket-value pairs to configure your application, copy template below and change values to the right of equals sign
</p>


```markdown
spring.application.name=backend

# Server
server.port=SERVER_PORT 
server.servlet.context-path=SPRING_BASE_URL


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
