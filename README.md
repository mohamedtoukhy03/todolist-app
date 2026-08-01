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
- **Language**: Java 21
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA
- **API Documentation**: SpringDoc OpenAPI (Swagger UI)
- **Build Tool**: Maven
- **Additional**: Spring Actuator for monitoring

## 📋 Prerequisites

Before running this application, make sure you have the following installed:

- Java 21 or higher
- PostgreSQL database
- Maven 3.6+ (or use the included Maven wrapper)

## 🗄️ Database Schema & Migrations

The application uses **Flyway** for database migrations. Database tables are created automatically on application startup from `src/main/resources/db/migration/V1__initial_schema.sql`.

Main entities:
- **User**: User accounts with authentication (`"user"`, `user_auth`)
- **Team**: Teams with hierarchical structure (`team`)
- **Task**: Tasks assigned to individual users or teams (`task`)
- **User_Team_Task**: Composite relationship linking users, teams, and tasks (`user_team_task`)
- **Messages**: Team messaging functionality (`messages`)

### Database Setup

1. Create a PostgreSQL database (e.g. `todolist`) in your local PostgreSQL instance:
   ```sql
   CREATE DATABASE todolist;
   ```
2. Flyway will run the initial migration automatically when you start the Spring Boot app.

## ⚙️ Configuration (.env & Environment Variables)

The application uses **`spring-dotenv`** to automatically load environment variables from a local `.env` file at application startup.

### Local Configuration Steps:

1. Create a `.env` file in the project root directory:
   ```env
   DB_URL=jdbc:postgresql://localhost:5432/todolist
   DB_USERNAME=postgres
   DB_PASSWORD=your_actual_password
   ```

> **Security Note:** `.env` contains private credentials and is listed in `.gitignore` so it is **never** committed to Git.

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