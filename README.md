# BudgetMunch
An Application designed to help users budget select food choices in their immediate area. 

## **Table of Contents**

* Features
* Technologies Used
* Setup Instructions
* Usage
* Database Schema
* API Endpoints
* Contributing
* License

## Features

### **Key Features**

1. User management (registration, login, update profile)
2. Integration with Google Maps API
3. Storage and retrieval of nearby restaurants
4. Address geolocation support

## **Technologies Used**

1. [x] Backend Framework: Spring Boot
2. [x] Database: PostgreSQL
3. [x] API Integration: Google Maps API
4. [x] Security: Spring Security with JWT
5. [x] Build Tool: Maven
6. [x] Environment Configuration: .env file for sensitive credentials
7. [x] Testing: Mockito, JUnit

## **Directory Structure**
*** src/main/java/com/example/project2/**
* ├── controller/
* │   ├── MapController.java
* │   └── JsonParser.java
* ├── entity/
* │   ├── Response.java
* │   ├── Result.java
* │   ├── User.java
* │   ├── UserAddress.java
* │   └── repository/
* │       └── UserRepository.java
* ├── service/
* │   ├── EmailService.java
* │   └── UserService.java
* └── Project2Application.java


## **Setup Instructions**

* Prerequisites
* Java: Ensure JDK 17.
* Maven: Ensure Maven is installed.
* .env [_**very important**_]
* PostgreSQL: Ensure PostgreSQL is running and configured.
* Google Maps API Key: Obtain an API key from Google Cloud Console.

# **Installation**
1) Clone the repository:

git clone https://github.com/tSimpsonsWork/BudgetMunch.git

2) Set up your .env file for sensitive credentials: 
   1. DATASOURCE_URL=
   2. DATASOURCE_USER=
   3. DATASOURCE_PASSWORD=
   4. _**API_KEY**_=


3) Build the project:

./mvnw clean package -DskipTests


4) Run the application:

java -jar target/Project2-0.0.1-SNAPSHOT.jar

5) The application will automatically create tables based on the JPA configuration. [Once run JPA will created database tables]


# **Usage**
1. User Registration: Create a new user account.
2. Login: Authenticate with credentials to receive a JWT token.
3. Fetch Nearby Restaurants: Use the provided API with location parameters.