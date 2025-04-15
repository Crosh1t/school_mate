# School Mate

School management system built with Spring Boot.

## Overview
School Mate is a template web application for private schools, educational centers, and extracurricular programs. Its primary goal is to automate the management of educational processes, client interactions (parents and students), and administrative and financial operations.

### Key Modules
#### 🔒 Authentication and Roles
- User authentication with roles: Administration, Staff, Clients (Students/Parents).
- Customizable access control system.

#### 🏛️ Administration
- User management (staff, students, parents).
- Scheduling classes and assigning teachers.
- Digital gradebook (managing classes, homework, subjects, and grades).
- Classroom and resource management.
- 📦 Inventory management (tracking equipment and assets).
- 💰 Financial management:
   - Tracking client payments and refunds.
   - Staff salary calculations.
   - Miscellaneous payments.
- 📊 Reporting (finances, attendance, academic performance).
- 📤 Data import/export (Excel, CSV).

#### 👨‍🏫 Staff (Teachers, Personnel)
- View and edit the digital gradebook.
- Manage schedules, grades, and homework.
- Track attendance.
- Add individual comments for students and parents.
- Student portfolios (achievements, notes).
- Respond to parent inquiries.

#### 👨‍👩‍👧 Clients (Students and Parents)
- View gradebook, grades, and schedules.
- Personal dashboard (homework, debts).
- Comment and ask questions to teachers.
- Track attendance and academic history.
- Enroll in additional courses or events.
- Access electronic documents (invoices, certificates, contracts).

### Future Enhancements
- Mobile version support (responsive design).
- Internationalization (i18n) for multiple languages.
- REST API for external integrations.
- Messenger integration (e.g., Telegram bot for notifications).
- Cloud storage for files and documents.

## Technical Details
- **Backend**: Java, Spring Boot
- **Frontend**: Thymeleaf (with Bootstrap)
- **Database**: PostgreSQL (or any other by choice)
- **Security**: Spring Security (role-based authorization)
- **Testing**: JUnit, Mockito

## Prerequisites
- **Java 17**
- **Maven 3.8+**
- **Docker and Docker Compose** (optional, for containerized setup)
- **PostgreSQL** (optional, for database setup)
- IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)

## Project Structure

school_mate/
├── src/
│   ├── main/
│   │   ├── java/codereview/school_mate/
│   │   │   └── SchoolMateApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── .gitignore
└── README.md
└── README.ru.md


## Setup with Maven
1. Clone the repository:
   ```bash
   git clone https://github.com/fedeyak/school_mate.git

2. Navigate to the project directory:
   cd school_mate

3. Build the project:
   mvn clean install

4. Run the application:
   mvn spring-boot:run

The application starts on http://localhost:8080. 
By default, Liquibase and JPA are disabled 
(spring.liquibase.enabled=false, spring.jpa.hibernate.ddl-auto=none), 
so no database is required.

## Setup with Docker

1. Ensure Docker and Docker Compose are installed:
   docker --version
   docker-compose --version

2. Build and start the application and PostgreSQL database:
   docker-compose up --build

3. Access the application at http://localhost:8080.

4. (Optional) Connect to the PostgreSQL database at localhost:5432 with:
  - Database: school_mate
  - Username: postgres
  - Password: postgres

5. Stop the containers:
   docker-compose down

## Database Configuration

The project is set up to work with PostgreSQL and Liquibase, 
but the database is optional for initial development.
To enable the database:

1. 1. Configure `application.yml` (or create `application-local.yml` for local settings):
   spring:
      datasource:
         url: jdbc:postgresql://localhost:5432/school_mate
         username: your_username
         password: your_password
         driver-class-name: org.postgresql.Driver
      liquibase:
         enabled: true
         change-log: classpath:db/changelog/db.changelog-master.yaml
      jpa:
         hibernate:
            ddl-auto: none

2. Create a Liquibase changelog file 
(e.g., src/main/resources/db/changelog/db.changelog-master.yaml) 
when ready to add migrations.


3. Ensure the PostgreSQL database is running locally or use the Docker setup above.

## Contributing

- Create a new branch for your feature:
  git checkout -b feature/your-feature

- Commit changes and push to the repository.
- Create a pull request for review.

## Notes

- The project is designed for a distributed team, with minimal configuration to ensure easy setup.
- Use `application-local.yml` for local database settings (excluded via `.gitignore`).
- For debugging, enable detailed logging in `application.yml`:
  ```yaml
  logging:
    level:
      org.springframework: DEBUG

- Liquibase and JPA are disabled by default to avoid errors until the database is configured. 
If you encounter Failed to determine a suitable driver class, 
ensure spring.autoconfigure.exclude includes DataSourceAutoConfiguration 
and HibernateJpaAutoConfiguration in application.yml

## Troubleshooting

- Maven issues: Run mvn clean install to resolve dependency problems.
- Docker issues: Ensure Docker Desktop is running and try docker-compose up --build again.
- Database errors: Verify application.yml settings and PostgreSQL availability.

For further assistance, contact the team or check the repository issues.
