# Employee JDBC App

## Overview
This is a Java console application that connects to a MySQL database to perform CRUD operations on an **Employee** table. It demonstrates Java JDBC connectivity and basic database operations using **PreparedStatement**.

## Features
- Add a new employee
- View all employees
- Update employee details
- Delete an employee
- Uses **MySQL** database
- Uses **JDBC** for database connectivity

## Prerequisites
- Java JDK 8 or above
- MySQL Server installed
- MySQL Connector/J `.jar` file (e.g., `mysql-connector-j-8.0.33.jar`)
- VS Code or Eclipse IDE

## Database Setup
1. Open MySQL Workbench or terminal.
2. Create database:
```sql
CREATE DATABASE employee_db;
