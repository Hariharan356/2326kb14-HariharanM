
🍽️ RESTAURANT ORDERING SYSTEM
------------------------------

📌 Description:
----------------
A console-based Java application that simulates a restaurant ordering system.
The system supports three roles — Admin, Staff, and Client — to manage and
place food orders with proper logging and control.

The project uses Java, JDBC, and MySQL and follows OOP and design patterns
(DAO, Singleton, Factory). It provides order placement, role-based access,
and order management features, including cancellation and restoration.

🛠️ Technologies Used:
----------------------
- Java (JDK 8+)
- JDBC
- MySQL Database
- Console-based UI

🧩 Key Modules:
----------------
1. User Management
   - Admin/Staff login using credentials
   - Clients log in directly (no auth)
   - Role-based access to features

2. Menu Management
   - View menu items by category (Veg/Non-Veg)
   - Add/Remove menu items (Admin/Staff)

3. Order Management
   - Place orders (Client/Staff)
   - Cancel within 5 minutes
   - Admin can cancel/restore any order

4. Logging
   - Cancellations/restorations tracked via 'cancelled_by' and 'restored_by' fields in DB

🗃️ Project Structure:
----------------------
src/
└── main/
    └── java/
        └── Restaurent_Ordering_System/
            ├── controller/
            │   └── RestaurantApp.java
            ├── dao/
            │   ├── MenuDAO.java
            │   ├── OrderDAO.java
            │   └── UserDAO.java
            ├── model/
            │   ├── MenuItem.java
            │   ├── Order.java
            │   ├── OrderItem.java
            │   └── User.java
            ├── service/
            │   ├── Restaurant.java
            │   └── ServiceFactory.java
            ├── util/
            │   └── DBUtil.java
            └── exception/
                ├── EmptyOrderException.java
                ├── InvalidLoginException.java
                └── OutOfStockException.java

🗄️ Database Tables:
--------------------

1. client_orders / staff_orders
   - id (INT, PK)
   - user_id (INT)
   - total_amount (DOUBLE)
   - order_time (TIMESTAMP)
   - cancelled_by (VARCHAR)
   - restored_by (VARCHAR)

2. client_order_items / staff_order_items
   - id (INT, PK)
   - order_id (INT, FK)
   - item_id (INT)
   - quantity (INT)

3. menu_items
   - id (INT, PK)
   - name (VARCHAR)
   - price (DOUBLE)
   - category (VARCHAR)
   - stock (INT)

4. users
   - id (INT, PK)
   - name (VARCHAR)
   - role (VARCHAR)
   - username (VARCHAR)
   - password (VARCHAR)

▶️ How to Run:
---------------
1. Open in Eclipse or IntelliJ.
2. Set up your MySQL server and database schema.
3. Edit DBUtil.java with your DB credentials.
4. Run RestaurantApp.java.
5. Use CLI for interaction.

👥 User Roles:
---------------

Admin:
- View all orders
- Cancel / Restore any order
- Manage staff/client activities

Staff:
- Login with credentials
- Place and cancel orders (within 5 mins)
- View staff order history

Client:
- No login needed
- Place orders
- Cancel own orders within 5 mins

Team Members:
-------------
- Tested by Hariharan

Institution:
------------
KG College Of Arts And Science
Department of Computer Technology
Mini Project Submission – Full Stack Development
Submission Date: 28/06/2025
