Trying to develop a backend for an e-commerce system using Java Springboot framework

Phase -1

Build a production-grade monolithic e-commerce backend using:
	• Clean architecture
	• Core Spring Boot concepts
	• JPA mappings
	• AOP
	• Exception Handling
Design patterns applied contextually

🧾 User Stories for Phase 1
ID	User Story
US-1	As a user, I want to register with email/password to create an account.
US-2	As a system, I want to prevent duplicate emails during registration.
US-3	As a user, I want to log in and get a JWT token for secure access.
US-4	As a user, I should be assigned the USER role by default.
US-5	As an admin, I want to create new products.
US-6	As a user, I want to view all products and get product details.
US-7	As a user, I want to add products to my cart.
US-8	As a user, I want to view and manage my cart (view/remove items).
US-9	As a user, I want to place an order using the items in my cart.
US-10	As a user, I want to view my previous orders.
US-11	As an admin, I want to view all orders placed by users.


APIs to implement for Phase - I
 API List (Grouped by Feature)

🔐 Auth APIs
Method	Endpoint	Description
POST	/api/auth/register	Register a new user
POST	/api/auth/login	Login with credentials (returns JWT token)


👤 User APIs
Method	Endpoint	Description
GET	/api/users/me	Get logged-in user's profile
GET	/api/users/me/orders	Get user's order history
GET	/api/users/me/cart	View user's current cart


🛒 Cart APIs
Method	Endpoint	Description
POST	/api/cart/add/{productId}	Add product to cart
DELETE	/api/cart/remove/{productId}	Remove product from cart
GET	/api/cart	View cart contents
DELETE	/api/cart/clear	Empty the cart


📦 Product APIs
Method	Endpoint	Description	Role
GET	/api/products	List all products	PUBLIC
GET	/api/products/{id}	Get product details	PUBLIC
POST	/api/admin/products	Create new product	ADMIN
PUT	/api/admin/products/{id}	Update product	ADMIN
DELETE	/api/admin/products/{id}	Delete product	ADMIN


📤 Order APIs
Method	Endpoint	Description	Role
POST	/api/orders	Place order using cart	USER
GET	/api/orders/me	View user’s own orders	USER
GET	/api/admin/orders	View all orders	ADMIN


🛡️ Role Assignment Logic (US-4)
	• This happens automatically in backend logic when you register (/auth/register)
	• Assigned: ROLE_USER by default

🧠 Notes
	• All APIs except /auth/* and GET /products should be protected by JWT
	• Admin-only routes should be role-restricted using @PreAuthorize("hasRole('ADMIN')")
	• Cart is user-specific, so /api/cart is always scoped to logged-in user
	• Order placement pulls from the current cart → then clears the cart after placing order

🧩 Optional Enhancements Later
Feature	Endpoint Idea
Product search	/api/products/search?q=shoes
Order cancel/return	PATCH /api/orders/{id}/cancel
Payment integration	/api/orders/{id}/pay


US-I : Completed, currently have a POST API to register user