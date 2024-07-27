**SpringBoot Bank Application**

Welcome to the SpringBoot Bank Application repository. This application is a simple banking system developed using Spring Boot, which provides various banking functionalities like account management, transactions, loan and card management and more.

**Table of Contents**

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [Usage](#usage)
  - [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Features

- User Registration and Authentication
- Account Management (Create, Fetch, Update, Delete accounts)
- Transaction Management (Deposit, Withdraw, Transfer)
- Card Management (Create, Fetch, Update, Delete cards)
- Loans Management (Create, Fetch, Update, Delete Loan sanctions)
- Balance Inquiry
- Transaction History

## Technologies Used

- **Java**: Programming Language
- **Spring Boot**: Framework for building the application
- **Spring Data JPA**: For database interactions
- **MySQL**: Relational database for development and testing
- **Maven**: Build and dependency management tool
- **JUnit**: Testing framework

## Getting Started

### Prerequisites

- JDK 11 or later
- Maven 3.6.0 or later

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/Meghamala/SpringBootBankApplication.git
    ```
2. Navigate to the project directory:
    ```sh
    cd SpringBootBankApplication
    ```
3. Build the project:
    ```sh
    mvn clean install
    ```

### Running the Application

1. Start the application:
    ```sh
    mvn spring-boot:run
    ```
2. The application will be accessible at `http://localhost:8080`.

## Usage

### API Endpoints

Here are some of the main API endpoints available in this application:

- **Customer Management**
  - `POST /api/users/register` - Register a new user
  - `POST /api/users/login` - User login
- **Account Management**
  - `POST /api/accounts` - Create a new account
  - `GET /api/accounts/{id}` - Get account details
  - `PUT /api/accounts/{id}` - Update account information
  - `DELETE /api/accounts/{id}` - Delete an account
- **Cards Management**
  - `POST /api/cards` - Create a new card and attach to account
  - `GET /api/cards/{id}` - Get card details
  - `PUT /api/cards/{id}` - Update card information
  - `DELETE /api/cards/{id}` - Detach and delete a card
- **Transaction Management**
  - `POST /api/transactions/deposit` - Deposit money into an account
  - `POST /api/transactions/withdraw` - Withdraw money from an account
  - `POST /api/transactions/transfer` - Transfer money between accounts
  - `GET /api/transactions/history/{accountId}` - Get transaction history for an account

## Contributing

Contributions are welcome! Please fork this repository and submit pull requests with improvements.

1. Fork the repository.
2. Create a new feature branch (`git checkout -b feature/YourFeature`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/YourFeature`).
5. Create a new Pull Request.
