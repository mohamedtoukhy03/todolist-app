# 📝 Todo List Application

A full-featured Todo List application built with Spring Boot 3.5.6 and PostgreSQL, featuring team collaboration, user management, and task assignment capabilities.

## 🚀 Features

- **User Management**: User registration and authentication with role-based access
- **Team Collaboration**: Create teams with parent-child hierarchy support
- **Task Management**: Create, assign, and manage tasks
- **Team Tasks**: Assign tasks to users within specific teams
- **Messaging**: Team-based messaging system
- **API Documentation**: Interactive API documentation with Swagger/OpenAPI

## 🛠️ Technologies

- **Backend**: Spring Boot 3.5.6
- **Language**: Java 24
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA
- **API Documentation**: SpringDoc OpenAPI (Swagger UI)
- **Build Tool**: Maven
- **Additional**: Spring Actuator for monitoring

## 📋 Prerequisites

Before running this application, make sure you have the following installed:

- Java 24 or higher
- PostgreSQL database
- Maven 3.6+ (or use the included Maven wrapper)

## 🗄️ Database Schema

The application uses the following main entities:

- **User**: User accounts with authentication
- **Team**: Teams with hierarchical structure (parent-child relationships)
- **Task**: Tasks that can be assigned to users
- **User_Team_Tasks**: Many-to-many relationship for task assignments
- **Messages**: Team messaging functionality

### Database Setup

1. Create a PostgreSQL database for the application
2. Run the SQL script located in `sql_scripts/sql` to create the required tables:

```bash
psql -U your_username -d your_database -f sql_scripts/sql
```

## ⚙️ Configuration

Create or update the `application.properties` file in `src/main/resources/` with your database credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## 🚀 Getting Started

### Running with Maven Wrapper (Recommended)

#### On Linux/Mac:
```bash
./mvnw spring-boot:run
```

#### On Windows:
```bash
mvnw.cmd spring-boot:run
```

### Running with Maven

```bash
mvn clean install
mvn spring-boot:run
```

### Building JAR file

```bash
./mvnw clean package
java -jar target/todolist-0.0.1-SNAPSHOT.jar
```

## 📚 API Documentation

Once the application is running, you can access the interactive API documentation at:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

## 🔍 Monitoring

Spring Boot Actuator endpoints are available for monitoring:

- **Health**: http://localhost:8080/actuator/health
- **Info**: http://localhost:8080/actuator/info

## 📁 Project Structure

```
todolist-app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── todolist/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── sql_scripts/
│   └── sql (Database schema)
├── pom.xml
└── README.md
```

## 🔧 Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Actuator
- Spring Boot Starter Validation
- PostgreSQL Driver
- SpringDoc OpenAPI UI
- Spring Security (for testing)
- Spring Boot DevTools

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is open source and available under the [MIT License](LICENSE).
