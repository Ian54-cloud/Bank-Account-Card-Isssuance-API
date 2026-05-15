# Card-Issuance-Api
A Spring Boot banking API that automates customer onboarding, bank account creation, and bank card issuance.  The system validates customer information, create accounts, issue cards, and stores all data in PostgreSQL.

## Features
- Customer registration
- Automatic bank account creation
- Payment card issuance
- PESEL validation
- Age verification
- Currency validation
- Account type validation
- PostgreSQL integration
- REST API architecture
- Docker support

  ## Tech Stack
- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Docker
- Docker Compose
- Lombok

## Validation Logic
The application validates:
- Customer age (18+)
- PESEL number format
- Currency support
- Account type support
- Nationality restrictions
- Document expiration date
