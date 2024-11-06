# Money Tracker App

This is a Money Tracker application built with Spring Boot. The application includes user authentication and various features to manage personal finances.

## Getting Started

### Prerequisites

- Java 21
- Maven
- Docker (for running PostgreSQL)

### Setup

1. **Clone the repository:**

    ```sh
    git clone https://github.com/adepuu/montrack.git
    cd montrack
    ```

2. **Configure Environment Variables:**

    Copy the `.env.example` file to `.env` and update the environment variables as needed.

    ```sh
    cp .env.example .env
    ```

3. **Run PostgreSQL using Docker:**

    Ensure Docker is running and then start the PostgreSQL container using Docker Compose:

    ```sh
    docker-compose up -d
    ```

4. **Run the Spring Boot Application:**

    Use Maven to run the Spring Boot application:

    ```sh
    ./mvnw spring-boot:run
    ```

    This will automatically apply the database migrations.

### Testing the Authentication System

A Postman collection is included to help you test the authentication system.

1. **Import the Postman Collection:**

    Open Postman and import the `Money Tracker APP.postman_collection.json` file.

2. **Run the Requests:**

    Use the imported collection to test the various endpoints, including user registration and login.

## Endpoints

### Authentication

- **Login:** `POST /api/v1/auth/login`
- **Register:** `POST /api/v1/users/register`
- **Bulk Register:** `POST /api/v1/users/bulk`

### Users

- **Get All Users (protected):** `GET /api/v1/users`
- **Get User by ID (protected):** `GET /api/v1/users/{id}`

## License

This project is licensed under the MIT License.