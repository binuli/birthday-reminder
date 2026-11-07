# 🎂 Birthday Reminder

A simple web application that helps you keep track of birthdays and never forget a special day!

The application allows users to manage birthdays and automatically identifies people whose birthdays fall on the current date.

## ✨ Features

- **Today's Birthdays:** View people celebrating their birthdays today.
- **Add Birthdays:** Save a person's name and date of birth.
- **View All Birthdays:** Display all saved birthdays.
- **Update Birthdays:** Edit existing birthday details.
- **Delete Birthdays:** Remove birthday records.
- **Date Validation:** Validate birthday details before saving.
- **Duplicate Prevention:** Prevent duplicate entries for the same name and date of birth.
- **Sri Lankan Timezone Support:** Determine today's birthdays using the `Asia/Colombo` timezone.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java 21 | Backend programming language |
| Spring Boot 3 | Backend framework |
| Spring Data JPA | Database operations |
| Hibernate | ORM |
| PostgreSQL | Relational database |
| HTML5 | Frontend structure |
| CSS3 | Frontend styling |
| JavaScript | Frontend functionality |
| Maven | Dependency management and build |

## 🏗️ Project Structure

```text
birthday-reminder/
│
├── src/
│   └── main/
│       ├── java/com/binu/birthdayreminder/
│       │   ├── controller/
│       │   ├── entity/
│       │   ├── repository/
│       │   ├── service/
│       │   ├── exception/
│       │   └── BirthdayReminderApplication.java
│       │
│       └── resources/
│           ├── static/
│           │   ├── css/
│           │   │   └── style.css
│           │   ├── js/
│           │   │   └── app.js
│           │   └── index.html
│           │
│           └── application.properties
│
├── .gitignore
├── pom.xml
└── README.md
```

## ⚙️ Getting Started

### Prerequisites

Make sure you have the following installed:

- Java Development Kit (JDK) 21
- PostgreSQL
- Git

### 1. Clone the Repository

```bash
git clone https://github.com/binuli/birthday-reminder.git
```

Navigate to the project directory:

```bash
cd birthday-reminder
```

### 2. Create the Database

Open PostgreSQL and execute:

```sql
CREATE DATABASE birthday_reminder;
```

### 3. Configure Environment Variables

The application uses environment variables to manage database credentials securely.

Configure the following variables:

| Variable | Description |
|---|---|
| `DB_USERNAME` | PostgreSQL username |
| `DB_PASSWORD` | PostgreSQL password |

For Windows PowerShell:

```powershell
$env:DB_USERNAME = "your_postgres_username"
$env:DB_PASSWORD = "your_postgres_password"
```

### 4. Configure Database Connection

The database configuration is maintained in `src/main/resources/application.properties`.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/birthday_reminder
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
```

### 5. Run the Application

From the project root directory, execute:

**Windows:**

```powershell
.\mvnw spring-boot:run
```

**Linux / macOS:**

```bash
./mvnw spring-boot:run
```

### 6. Access the Application

Once the application starts successfully, open:

http://localhost:8080

## 🔗 REST API Endpoints

Base URL: `/api/birthdays`

| HTTP Method | Endpoint | Description |
|---|---|---|
| POST | `/api/birthdays` | Add a new birthday |
| GET | `/api/birthdays` | Retrieve all birthdays |
| GET | `/api/birthdays/{id}` | Retrieve a birthday by ID |
| PUT | `/api/birthdays/{id}` | Update an existing birthday |
| DELETE | `/api/birthdays/{id}` | Delete a birthday |
| GET | `/api/birthdays/today` | Retrieve today's birthdays |

### Example Request

**Add a Birthday**

`POST /api/birthdays`

Request body:

```json
{
    "name": "John",
    "dateOfBirth": "1998-11-07"
}
```

**Update a Birthday**

`PUT /api/birthdays/1`

Request body:

```json
{
    "name": "John Smith",
    "dateOfBirth": "1998-11-07"
}
```

## 🔒 Validation and Error Handling

The application includes:

- Input validation using Jakarta Bean Validation.
- Global exception handling.
- Appropriate HTTP status codes for errors.
- Duplicate birthday detection.
- Handling of requests for non-existing birthday records.

## 🚀 Future Improvements

Potential enhancements include:

- Display upcoming birthdays.
- Calculate and display age.
- Sort birthdays by month and day.
- Add search functionality.

## 👨‍💻 Author

**Binu**

GitHub: https://github.com/binuli

---

*Built as a hands-on project to explore Spring Boot, REST APIs, PostgreSQL, and frontend-backend integration.*