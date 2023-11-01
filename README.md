# Splitwise Clone API

## Overview
This repository contains a Spring-based REST API that serves as a clone of the popular Splitwise application. Splitwise is a widely-used expense-sharing application that simplifies sharing expenses with friends and roommates. This API provides essential functionality for managing expenses, groups, users, and transactions, making it a solid foundation for building a Splitwise-like application.

## Features
- Group management: Create groups for expense sharing.
- Expense management: Add expenses within a group.
- SettleUp management: Gives the minimum number of transactions between group members to settle up.

## Demo Link
   'https://www.loom.com/share/6e93a85cb6454541b8970807580ed09d?sid=89ae9bfd-2fe6-408d-9cd4-2bf9fe76322f'

## Getting Started
Follow the steps below to get the API up and running on your local machine:

1. **Prerequisites**
   - Java Development Kit (JDK) 11 or higher
   - Apache Maven
   - Spring Boot
   - MySQL database

2. **Clone the Repository**
   ```bash
   git clone https://github.com/rohit200400/Splitwise.git
   ```

3. **Database Configuration**
   - Set up a MySQL database and update the database configuration in `src/main/resources/application.properties` with your database credentials.

4. **Build the Application**
   ```bash
   cd splitwise-clone-api
   mvn clean install
   ```

5. **Run the Application**
   ```bash
   java -jar target/splitwise-clone-api-0.1.0.jar
   ```

6. **API Documentation**
   Access the API documentation at `http://localhost:8080/swagger-ui.html` for detailed information on available endpoints and how to use them.

## Usage
You can use this API as a backend service for your Splitwise clone application. You can also test the API using tools like Postman or the Swagger UI.

## Contributing
We welcome contributions from the community. If you would like to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and commit them.
4. Push your changes to your fork.
5. Create a pull request to this repository with a clear description of your changes.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact
If you have any questions or need further assistance, please feel free to contact us at [rohitchaudhary200400@gmail.com].

Thank you for using the Splitwise Clone API! We hope it helps you build an amazing expense-sharing application.
