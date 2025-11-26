# Student Record Management System – Multithreaded Version  
### (Exception Handling, File Handling, Multithreading, Collections, OOP)

---

## Overview

This project implements a **multithreaded Student Record Management System** in Java with persistent storage.  
The application allows full CRUD operations on student records and demonstrates several advanced Java concepts such as:

- Exception Handling  
- File Handling (read/write persistent storage)  
- Multithreading (loading simulation)  
- Java Collections Framework  
- Searching and Sorting  
- OOP Principles (Inheritance, Abstraction, Interfaces)  

It extends the earlier basic versions and focuses on responsiveness, modularity, and maintainability.

---

## Features

### CRUD Operations (Add, Update, Delete, Search, View)
- Add new student records  
- Update marks or details  
- Delete student by roll number  
- Search student instantly  
- View complete student list  

### Persistent Storage
- Student data is stored and retrieved from a file (`students.txt`)  
- Automatically loads records at startup  
- Saves updated records on exit  

### Exception Handling
- Handles invalid marks, empty inputs, missing records, and file errors  
- Custom exceptions can be thrown for missing student data  

### Multithreading
- Simulates loading animations during long operations such as:
  - Adding records  
  - Updating records  
  - File saving  
- Enhances responsiveness of the system  

### Sorting (Using Comparator)
- Sort students by marks in descending order  
- Easily extendable to sort by name or roll number  

### Java Collections Framework
- Uses `ArrayList` to store and manage student objects  
- Uses `Iterator` to traverse records  
- Efficient search, update, and delete operations  

### OOP Concepts Demonstrated
- **Inheritance**  
- **Abstraction**  
- **Interfaces**  
- **Encapsulation**  
- **Modularity & Reusability**

---

## Project Structure
## StudentRecordManagementSystem
- ├── Person.java
- ├── Student.java
- ├── StudentManager.java
- ├── StudentNotFoundException.java
- ├── LoadingThread.java
- ├── StudentComparator.java
- ├── Main.java
- └── students.txt

---

## Author

**Name:** Aditya Chouhan  
**Roll No:** 2401830001  
**Course:** B.Sc (H) Cybersecurity  

---

## Notes

- The system combines multithreading, OOP, and persistent storage into a single modular application.  
- Sorting and search operations can be extended easily.  
- System is console-based for clarity and simplicity.  

---
