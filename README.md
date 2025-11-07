# Library Management System

A simple **Library Management System** implemented in Java using **JDBC** to interact with a **MySQL** database.  
Demonstrates core Java concepts like **OOP**, **Exception Handling**, and basic **Collections**.

## Features
- **Add Book**: Add new books to the library database.
- **View Books**: List all books with details and availability.
- **Borrow Book**: Borrow books with validation for availability.
- **Return Book**: Return borrowed books.
- **Delete Book**: Remove books from the library.
- **Exception Handling**: Handles invalid inputs and database errors gracefully.

## Technologies Used
- **Languages**: Java (Core Java, OOP)
- **Database**: MySQL
- **Libraries**: JDBC (MySQL Connector JAR)
- **Tools**: VS Code / Eclipse, Git, GitHub

## Project Structure
Project Structure
-----------------
Library_Management_System/
│
├── lib/                  - External library (MySQL Connector JAR)
├── src/                  - Java source files
│   └── library/
│       ├── Main.java     - Main application
│       ├── Library.java  - Library operations
│       └── Book.java     - Book model
└── README.txt            - Project documentation

Setup Instructions
------------------
1. Clone the repository:
   git clone https://github.com/Ashitosh9922/Library_Management_System.git
   cd Library_Management_System

2. Install MySQL and create a database:
   CREATE DATABASE library_db;
   CREATE TABLE books (
       id INT AUTO_INCREMENT PRIMARY KEY,
       title VARCHAR(255) NOT NULL,
       author VARCHAR(255) NOT NULL,
       year INT NOT NULL,
       available BOOLEAN DEFAULT TRUE
   );

3. Add MySQL Connector JAR (included in lib folder) to your project classpath.

4. Compile and run:
   javac -cp ".;lib/mysql-connector-java-9.5.0.jar" src/library/*.java
   java -cp ".;lib/mysql-connector-java-9.5.0.jar" library.Main
