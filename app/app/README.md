# BudgetApp

A REST API for tracking personal income and expenses, built with Java 21 and Spring Boot.

Each transaction has a name, amount, category, date, optional description and a type (`INCOME` or `EXPENSE`). Transactions can be created, listed, filtered by type, updated and deleted.

## Tech stack

- Java 21
- Spring Boot (Web MVC, Data JPA, Validation)
- H2 in-memory database
- Lombok
- Maven

## Getting started

Requirements: JDK 21. Maven is included through the wrapper.

```bash
git clone https://github.com/P-Forsberg/BudgetApp.git
cd BudgetApp/app/app
./mvnw spring-boot:run
```

The API starts on `http://localhost:8080`. Data is kept in memory and is reset when the application restarts.

## API

| Method | Endpoint | Description |
| --- | --- | --- |
| `POST` | `/transaction/add` | Create a transaction |
| `GET` | `/transaction` | List all transactions |
| `GET` | `/transaction?type=EXPENSE` | List transactions of one type (`INCOME` or `EXPENSE`) |
| `GET` | `/transaction/{id}` | Get one transaction |
| `PUT` | `/transaction/{id}` | Update a transaction |
| `DELETE` | `/transaction/{id}` | Delete a transaction (returns `204 No Content`) |

A missing id returns `404 Not Found`.

### Example request

```bash
curl -X POST http://localhost:8080/transaction/add \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Groceries",
    "amount": 642.50,
    "category": "Food",
    "description": "Weekly shopping",
    "date": "2026-10-14",
    "type": "EXPENSE"
  }'
```

### Validation

When creating a transaction:

- `name` and `category` must not be empty
- `amount` must not be zero
- `type` must be `INCOME` or `EXPENSE`

## Project structure

```
src/main/java/Budget/app/transaction/
├── TransactionController.java    REST endpoints
├── TransactionService.java       Business logic and validation
├── TransactionRepository.java    Spring Data JPA repository
├── Transaction.java              JPA entity
├── TransactionDTO.java           Request body
├── TransactionType.java          INCOME / EXPENSE
└── Exceptions/
    └── TransactionNotfound.java  Mapped to 404
```

Amounts are stored as `BigDecimal` to avoid floating-point rounding errors with money.

## Roadmap

- Return `400 Bad Request` with clear messages for invalid input, using `@Valid` and a global exception handler
- Unit and integration tests for the service and controller
- Monthly summaries: total income, expenses and balance per month and per category
- Persistent database (PostgreSQL)
