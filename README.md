# Etiqa Bookstore Manager

Etiqa Bookstore Manager is an application designed to manage products and customers for the Etiqa Bookstore.

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Setup & Installation](#setup--installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

- **Product Management**: Add, update, delete, and retrieve products.
- **Customer Management**: Add, update, delete, and retrieve customers.
- **Logging**: Detailed application logging for monitoring and troubleshooting.
- **Exception Handling**: Robust error handling to ensure smooth user experience.

## Requirements

- Java 17
- Oracle Database
- Maven

## Setup & Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/khairil1996/etiqa-bookstore-manager.git
   ```

2. **Navigate to the Project Directory**

   ```bash
   cd etiqa-bookstore-manager
   ```

3. **Install Dependencies**

   ```bash
   mvn clean install
   ```

4. **Setting Up Oracle Database**

   - Ensure Oracle Database is set up and running.
   - Execute SQL scripts located in the `bookstoremanager/setupDocument` directory to initialize required tables and sequences.

5. **Configuration**

   - Head to `src/main/resources`.
   - Update the `application.properties` file with the appropriate database credentials and other configurations.
   - spring.datasource.url, spring.datasource.username, spring.datasource.password need to be update

6. **Run the Application**

   ```bash
   mvn spring-boot:run
   ```
   - OR
   - got to BookstoremanagerApplication.java and click the RUN button
   

## Usage

- open postman and import the postman-collection provide in the setupDocument - file name is BookstoreAPI.postman_collection.json
- you can test all the api by changing the payload


