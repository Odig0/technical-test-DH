                                                        Todo Application
                                                        
This project is a Todo application built using Angular 17 for the frontend and Java 17 with Spring Boot 3.2 for the backend. The application allows you to perform CRUD operations (Create, Read, Update, Delete) on tasks.

Technologies Used
Frontend: Angular 17
Backend: Java 17 with Spring Boot 3.2
Prerequisites
Before getting started, make sure you have the following installed on your machine:

Node.js (version 18 or higher)
Angular CLI (version 17)
Java Development Kit (JDK) (version 17)
Maven or Gradle for dependency management in the backend
Installation
1. Clone the Repository
bash
Copiar código
git clone https://github.com/your_username/todo-app.git
cd todo-app
2. Install Frontend Dependencies
Navigate to the frontend directory and run the following command to install Angular dependencies:

bash
Copiar código
cd frontend
npm install
3. Install Backend Dependencies
Navigate to the backend directory and run the following command to install Java dependencies:

Using Maven
bash
Copiar código
cd backend
mvn install
Using Gradle
bash
Copiar código
cd backend
./gradlew build
Running the Application
1. Run the Backend
To start the backend server, navigate to the backend directory and run:

bash
Copiar código
mvn spring-boot:run
or if you are using Gradle:

bash
Copiar código
./gradlew bootRun
The server will start at http://localhost:8080.

2. Run the Frontend
To start the frontend application, navigate to the frontend directory and run:

bash
Copiar código
ng serve
The application will be available at http://localhost:4200.
