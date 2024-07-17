# BookVerse: Your All-In-One Library Hub

Welcome to **BookVerse**, your comprehensive solution for managing a library system efficiently and effectively. This project leverages Data Structures and Algorithms (DSA) concepts to provide a robust and scalable application.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [System Analysis](#system-analysis)
- [Core Components](#core-components)
- [User Interactions](#user-interactions)
- [Workflow](#workflow)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgements](#acknowledgements)

## Introduction

BookVerse is designed to simplify the process of managing books, users, and library operations. It includes functionalities for both administrators and regular users, ensuring a smooth and user-friendly experience.

## Features

- **User Registration and Authentication**: Secure user registration and login/logout functionality.
- **Book Management**: Add, update, and delete book records.
- **Search Functionality**: Efficient searching of books by ID or title.
- **Issue and Return Books**: Easy book issuance and return process with fine calculation for late returns.
- **Admin Dashboard**: Special privileges for admin users to manage the system.
- **User-friendly Interface**: Intuitive and interactive user interface.
- **Sorting**: List all books sorted by title.
- **Late Return Fine Calculation**: Calculate fines for late book returns.

## System Analysis

The system is broken down into core components to ensure modularity and scalability. The user requirements and functionalities are clearly defined to meet the needs of both admin and regular users.

## Core Components

- **Book Class**: Represents a book with attributes like ID, title, author, quantity, issued count, issued status, issued to, and issued date.
- **User Class**: Represents a user with attributes like username, password, and admin status.
- **Library Class**: Manages the collection of books and user interactions, including registration, login, logout, book management, and book transactions.

## User Interactions

### Admin Users
- Manage user accounts
- Add/update/delete book records
- View all book records and user activities

### Regular Users
- Register and log in
- Search for books by ID or title
- Issue and return books

## Workflow

1. **Registration**: Users register to the system.
2. **Login/Logout**: Users log in to access the system and log out when done.
3. **Book Management**: Admin users manage the book inventory.
4. **Searching**: Users search for books using various filters.
5. **Issuing/Returning**: Users issue books and return them after reading.
6. **Listing/Deleting**: Admin users list all books sorted by title and delete records if necessary.
7. **Updating**: Admin update book information.
8. **Late Return Fine Calculation**: Calculate fines for late book returns.
9. 
## Technologies Used

- **Java**: Core programming language.
- **Data Structures and Algorithms**: Arrays, Strings, Sorting, HashMap.

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/Aryan-788/BookVerse.git
    ```
2. Navigate to the project directory:
    ```bash
    cd BookVerse
    ```
3. Compile the project:
    ```bash
    javac -d bin src/*.java
    ```
4. Run the project:
    ```bash
    java -cp bin BookVerse
    ```

## Usage

1. **Admin User**: 
   - Log in with admin credentials.
   - Manage users and books.
   - View all records and activities.

2. **Regular User**:
   - Register and log in.
   - Search for books by ID or title.
   - Issue and return books.

## Contributing

We welcome contributions from the community. Please follow these steps to contribute:

1. Fork the repository.
2. Create a new branch:
    ```bash
    git checkout -b feature-branch
    ```
3. Make your changes.
4. Commit the changes:
    ```bash
    git commit -m "Description of changes"
    ```
5. Push to the branch:
    ```bash
    git push origin feature-branch
    ```
6. Create a pull request.

## Acknowledgements

- Thanks to Techvanto Academy for the DSA training.
- Special thanks to Navnit Sir for project supervision.
- Lovely Professional University for the academic support.

---

Feel free to reach out for any questions or support!

