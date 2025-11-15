## Environment:
- Java version: 25
- Gradle version: 8.14.3
- Spring Boot version: 3.5.7

## Dummy Data Files:
- src/test/resources/testData.txt

## Data Structure:
Example of a product data JSON object:
```json
{
    "id":1,
    "name":"product_x",
    "enteredByUser":"user_x",
    "enteredDate":"2020-05-10T13:00:41.499",
    "buyingPrice":50.0,
    "sellingPrice":55.0,
    "lastModifiedDate":"2020-05-10T13:00:41.498",
    "lastModifiedByUser":"user_y",
    "status":"AVAILABLE | SOLD"
}
```

# 📦 Inventory Management API

This project implements a basic inventory management system using **Spring Boot**, **ORM/Hibernate**, and **MVC architecture**. The system manages a single entity: `Product`.

## ✅ REST Endpoints

All endpoints are under the base path:
```
/api/stock/product
```

### 1. **Create an Product**
**POST** `/api/stock/product`

- **Description:** Inserts a new product into the database.
- **Request Body:** JSON with product data.
- **Behavior:**
    - If `productId` already exists → **400 Bad Request**
    - If `productId` does not exist → Inserts and returns the created product with **201 Created**

---

### 2. **Update an product**
**PUT** `/api/stock/product/{productId}`

- **Description:** Updates an existing product.
- **Path Variable:** `productId`
- **Request Body:** JSON with updated data.
- **Behavior:**
    - If `productId` exists → Updates and returns the product with **200 OK**
    - If `productId` does not exist → **404 Not Found**

---

### 3. **Delete an product**
**DELETE** `/api/stock/product/{productId}`

- **Description:** Deletes a specific product.
- **Path Variable:** `productId`
- **Behavior:**
    - If `productId` exists → Deletes and returns **200 OK**
    - If `productId` does not exist → **400 Bad Request**

---

### 4. **Delete All products**
**DELETE** `/api/stock/product`

- **Description:** Deletes all products from the database.
- **Behavior:** Returns **200 OK**

---

### 5. **Get an product by ID**
**GET** `/api/stock/product/{productId}`

- **Description:** Retrieves a specific product.
- **Path Variable:** `productId`
- **Behavior:**
    - If `productId` exists → Returns the product with **200 OK**
    - If `productId` does not exist → **404 Not Found**

---

### 6. **Get All products**
**GET** `/api/stock/product`

- **Description:** Returns all products.
- **Behavior:** Returns list with **200 OK**

---

### 7. **Filter products by Status and User**
**GET** `/api/stock/product?productStatus={status}&productEnteredByUser={enteredBy}`

- **Description:** Filters products by status and the user who entered them.
- **Query Params:**
    - `productStatus` → product status
    - `productEnteredByUser` → User who entered the product
- **Behavior:** Returns filtered list with **200 OK**

---

### 8. **Pagination and Sorting**
**GET** `/api/stock/product?pageSize={pageSize}&page={page}&sortBy={sortByField}`

- **Description:** Returns paginated and sorted products.
- **Query Params:**
    - `pageSize` → Page size
    - `page` → Page number
    - `sortBy` → Field to sort by
- **Behavior:** Returns requested page with **200 OK**

---

## 🗄️ Database
- Default database: **H2**
- Configuration included in the project.

## Commands
- run:

`gradle clean build`

`gradle bootRun`

- test:

`gradle clean test`
