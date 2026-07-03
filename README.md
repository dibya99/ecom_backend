# 🛒 E-Commerce Backend 

Trying to develop a backend for an e-commerce system using Java Spring Boot framework.

## 📦 Phase 1 Goals

Build a **production-grade monolithic e-commerce backend** using:

- ✅ Clean Architecture
- ✅ Core Spring Boot Concepts
- ✅ JPA Mappings
- ✅ AOP (Aspect-Oriented Programming)
- ✅ Exception Handling
- ✅ Contextual Design Patterns

---

## 🧾 User Stories for Phase 1

| ID    | User Story                                                              |
|-------|-------------------------------------------------------------------------|
| US-1  | As a user, I want to register with email/password to create an account. |
| US-2  | As a system, I want to prevent duplicate emails during registration.    |
| US-3  | As a user, I want to log in and get a JWT token for secure access.      |
| US-4  | As a user, I should be assigned the USER role by default.               |
| US-5  | As an admin, I want to create new products.                             |
| US-6  | As a user, I want to view all products and get product details.         |
| US-7  | As a user, I want to add products to my cart.                           |
| US-8  | As a user, I want to view and manage my cart (view/remove items).       |
| US-9  | As a user, I want to place an order using the items in my cart.         |
| US-10 | As a user, I want to view my previous orders.                           |
| US-11 | As an admin, I want to view all orders placed by users.                 |

---

## 🚀 API Endpoints to Implement (Phase 1)

### 🔐 Auth APIs

| Method | Endpoint             | Description                          |
|--------|----------------------|--------------------------------------|
| POST   | `/api/auth/register` | Register a new user                  |
| POST   | `/api/auth/login`    | Login with credentials (returns JWT) |

---

### 👤 User APIs

| Method | Endpoint               | Description                  |
|--------|------------------------|------------------------------|
| GET    | `/api/users/me`        | Get logged-in user's profile |

---

### 🛒 Cart APIs

| Method | Endpoint                          | Description              |
|--------|-----------------------------------|--------------------------|
| POST   | `/api/carts/items`                | Add product to cart      |
| GET    | `/api/carts`                      | View current user's cart |
| DELETE | `/api/carts/products/{productId}` | Remove product from cart |
| DELETE | `/api/carts/items`                | Clear entire cart        |

---

### 📦 Product APIs

| Method | Endpoint                   | Description         | Role   |
|--------|----------------------------|---------------------|--------|
| GET    | `/api/products`            | List all products   | PUBLIC |
| GET    | `/api/products/{id}`       | Get product details | PUBLIC |
| POST   | `/api/admin/products`      | Create new product  | ADMIN  |
| PUT    | `/api/admin/products/{id}` | Update product      | ADMIN  |
| DELETE | `/api/admin/products/{id}` | Delete product      | ADMIN  |

---

### 📤 Order APIs

| Method | Endpoint            | Description            | Role  |
|--------|---------------------|------------------------|-------|
| POST   | `/api/orders`       | Place order using cart | USER  |
| GET    | `/api/orders`       | View user’s own orders | USER  |
| GET    | `/api/admin/orders` | View all orders        | ADMIN |

---

## 🛡️ Role Assignment Logic (US-4)

- Happens automatically in `/auth/register`
- Every newly registered user is assigned: `ROLE_USER` by default

---

## 🔐 Security Rules

- All APIs except `/auth/*` and `GET /products` are **protected by JWT**
- Admin-only routes are secured with:  
  `@PreAuthorize("hasRole('ADMIN')")`
- Cart and Orders are always **user-specific**  
  (based on the logged-in JWT user)

---

## 🧩 Optional Enhancements (Future)

| Feature             | Endpoint Idea                   |
|---------------------|---------------------------------|
| Product search      | `/api/products/search?q=shoes`  |
| Order cancel/return | `PATCH /api/orders/{id}/cancel` |
| Payment integration | `/api/orders/{id}/pay`          |

---

## ✅ Completed So Far

- [X] **US-1:** Register user via `POST /api/auth/register`
    - [X] **US-1.1** Added a global exception handler and a proper error DTO
    - [X] **US-1.2** Added validations in request DTO and added exception handling in GlobalExceptionHandler
    - [X] **US-1.3** Added logging and aspects
- [X] US-2: Email duplication check
- [X] US-3: Login and JWT
- [X] US-4: User has have user role by default
- [X] US-5: As an admin, I want to create new products.
- [X] US-6 As a user, I want to view all products and get product details.
- [X] US-7 As a user, I want to add products to my cart.
- [X] US-8 As a user, I want to view and manage my cart (view/remove items).
- [X] US-9 As a user, I want to place an order using the items in my cart.
- [X] US-10 As a user, I want to view my previous orders.
- [X] US-11 As an admin, I want to view all orders placed by users.

---

---

# 🚀 Phase 1.5 - Refactoring & Production Readiness

## 🎯 Goal

Improve maintainability, readability, extensibility, and observability of the codebase without introducing major new business features.

This phase focuses on writing code like an experienced backend engineer rather than adding more APIs.

---

## 🧾 User Stories for Phase 1.5

| ID        | User Story |
|-----------|------------|
| [X] US-R1 | As a developer, I want a CurrentUserService so that authentication lookup logic is centralized. |
| [X] US-R2 | As a developer, I want an OrderMapper so that DTO conversion logic is reusable. |
| [X] US-R3 | As a developer, I want a CartMapper so that CartResponse generation is centralized. |
| [X] US-R4 | As a developer, I want a ProductMapper so that Product DTO mapping is reusable. |
| [X] US-R5 | As a developer, I want AOP-based request logging so that service execution can be traced. |
| [X] US-R6 | As a developer, I want execution time logging so that slow operations can be identified. |
| US-R7     | As a business owner, I want soft-delete support for products so that historical orders remain valid. |
| [X] US-R8 | As a developer, I want large service methods decomposed into smaller methods so that business logic is easier to maintain. |
| US-R9     | As a developer, I want API documentation and architecture diagrams so that onboarding becomes easier. |

---

## 📚 Concepts Covered in Phase 1.5

### Spring

- Spring AOP
- Bean Lifecycle
- Constructor Injection Best Practices
- Transaction Boundaries

### Code Quality

- Refactoring
- Separation of Concerns
- DRY Principle
- Mapper Layer
- Service Decomposition

### Design Improvements

- Soft Delete Pattern
- DTO Mapping Layer
- Authentication Facade / CurrentUserService

### Observability

- Request Logging
- Method Execution Logging
- Performance Metrics

---


## 📌 How to Run

```bash
./mvnw spring-boot:run

```

## Software Development Principles to be followed

- SOLID
- KISS
- DRY
    - Used a global exception handler to reduce multiple handlers in different controllers.
    - Using mappers to reduce code duplication
- YAGNI

## Design Patterns

### Implemented

- Builder Pattern (Lombok DTOs and Entities)
- Strategy Pattern (Payment Processing)
- Factory Pattern (Payment Strategy Resolution)
- Singleton Pattern (Spring-managed Beans)

### Planned

- Mapper Pattern (Phase 1.5)
- Proxy Pattern (Caching - Phase 3)
- Observer Pattern (Notifications - Phase 4)
- Adapter Pattern (External Integrations - Phase 4)

## Entity Relationships

- User → Cart (One-to-One)
- User → Order (One-to-Many)
- Cart → CartItem (One-to-Many)
- Order → OrderItem (One-to-Many)
- Product → CartItem (Many-to-One)
- Product → OrderItem (Many-to-One)

---

# 🧪 Phase 1.6 - Automated Testing

## 🎯 Goal

Add automated tests to protect existing business logic and make future refactoring safer.

Phase 1 and Phase 1.5 were validated primarily through manual API testing. This phase introduces repeatable automated tests that can be run locally with Maven.

---

## 🧾 User Stories for Phase 1.6

| ID  | User Story                                                                                                                                                  |
| --- | ----------------------------------------------------------------------------------------------------------------------------------------------------------- |
| T-1 | As a developer, I want unit tests for `ProductServiceImpl` so that product operations can be verified without manually calling APIs.                        |
| T-2 | As a developer, I want unit tests for `CurrentUserServiceImpl` so that authenticated-user lookup behavior is verified.                                      |
| T-3 | As a developer, I want unit tests for `CartServiceImpl` so that cart business rules and inventory validation are protected.                                 |
| T-4 | As a developer, I want unit tests for `OrderServiceImpl` so that checkout behavior, payment processing, inventory updates, and cart clearing are protected. |
| T-5 | As a developer, I want mapper unit tests so that entity-to-DTO conversion remains correct after future entity changes.                                      |
| T-6 | As a developer, I want integration tests for critical API flows so that controllers, security, services, and persistence work together correctly.           |

---

## 📚 Testing Stack

* JUnit 5
* Mockito
* Spring Boot Test
* MockMvc
* Testcontainers *(planned for database-backed integration tests)*

---

## 🧪 Testing Strategy

### Unit Tests

Unit tests will focus on service-layer business logic. Dependencies such as repositories, mappers, payment strategies, and `CurrentUserService` will be mocked using Mockito.

Initial unit-test coverage will include:

* Product creation, retrieval, update, deletion, and product-not-found behavior.
* Current user retrieval when the authenticated user exists or does not exist.
* Adding new and existing products to a cart.
* Cart inventory validation, item removal, and cart clearing.
* Successful order placement.
* Empty-cart order failure.
* Insufficient-inventory order failure.
* Payment failure behavior.
* Order total calculation, inventory reduction, and cart clearing after successful checkout.

### Integration Tests

Integration tests will validate critical end-to-end API flows using Spring Boot Test and MockMvc.

Planned flows include:

1. Register a user and log in.
2. Create a product as an admin.
3. Add a product to a cart.
4. Place an order.
5. Verify inventory reduction and order history.

---

## ▶️ Running Tests

Run all tests:

```bash
./mvnw test
```

Run the application:

```bash
./mvnw spring-boot:run
```

---

## 📌 Testing Principles

* Tests should verify behavior, not implementation details.
* Each test should follow the Arrange–Act–Assert pattern.
* Unit tests should be fast and independent of a real database.
* Integration tests should cover only high-value end-to-end flows.
* Every bug fixed in the future should ideally receive a regression test.


