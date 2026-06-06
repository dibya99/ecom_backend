# 🛒 E-Commerce Backend - Phase 1

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
| GET    | `/api/users/me/orders` | Get user's order history     |
| GET    | `/api/users/me/cart`   | View user's current cart     |

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

## 📌 How to Run

```bash
./mvnw spring-boot:run

```

## Software Development Principles to be followed

- SOLID
- KISS
- DRY
    - Used a global exception handler to reduce multiple handlers in different controllers.
- YAGNI

## Design Patterns

- Used Builder pattern by using Lombok in entities and DTOs.
- Used Singleton design pattern. It is supported by springboot using @Autowired annotation
- Used Factory and Strategy to do a mock of payment systems.
- More Patterns to be used in future.

