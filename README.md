# Campaign Management App

**Note:** This repository contains a project developed as part of a coding challenge/assignment.  
The task was to implement the core functionality within a strict 2-day deadline.

A backend REST API built using Spring Boot 3.4.4 and Java 21 that enables product campaign management for sellers.

Sellers can:
- Create, update, and delete their products.
- Create and manage campaigns tied to a specific product.
- Filter their campaigns by product, name, keyword, status, and town.
- Select a town from a predefined list.
- Select keywords via typeahead (dynamic suggestions) from a predefined list.
- Campaigns are linked to seller accounts, and their emerald funds are reduced accordingly.

Frontend: [campaign-management-frontend (React + Vite)](https://github.com/ArthurAndCode/campaign-management-frontend)
  
---

## Tech Stack

| Layer      | Technology            |
|------------|-----------------------|
| Language   | Java 21               |
| Framework  | Spring Boot 3.4.4     |
| Database   | H2 (In-Memory)        |
| Build Tool | Maven                 |
| API Style  | RESTful               |

---

## Dependencies

Key dependencies managed via Maven:

- `spring-boot-starter-web` – REST API
- `spring-boot-starter-data-jpa` – ORM & DB access
- `spring-boot-starter-validation` – DTO validation (Jakarta)
- `spring-boot-starter-security` – enables future integration of user authentication and security
- `spring-boot-devtools` – hot reload during dev
- `lombok` – boilerplate reduction
- `h2` – lightweight dev database
- `spring-boot-starter-test` – testing utilities
- `spring-security-test` – test support for secured endpoints

---

## API Endpoints

You can find the Postman collection [here](https://github.com/ArthurAndCode/campaign-management/blob/main/postman%20collection/campaign-management-app.postman_collection.json).

### Campaigns `/api/v1/campaigns`

| Method | Endpoint                                      | Description |
|--------|-----------------------------------------------|-------------|
| `GET`  | `/`                                           | Get filtered campaigns by ownerId, productId, town, name, keyword, status. Supports pagination. |
| `GET`  | `/owners/{ownerId}`                           | Get paginated campaigns for a given owner. |
| `GET`  | `/{id}`                                       | Get a single campaign by its ID. |
| `POST` | `/`                                           | Create a new campaign (requires `CampaignCreateRequest`). |
| `PATCH`| `/{id}`                                       | Update an existing campaign (requires `CampaignUpdateRequest`). |
| `DELETE`| `/{campaignId}/owners/{ownerId}`            | Delete a campaign by campaignId and ownerId. |

---

### Products `/api/v1/products`

| Method   | Endpoint                    | Description                                 |
|----------|-----------------------------|---------------------------------------------|
| `GET`    | `/{id}`                     | Get a product by ID.                        |
| `GET`    | `/owners/{ownerId}`         | Get paginated list of products by owner ID. |
| `POST`   | `/`                         | Create a new product.                       |
| `PATCH`  | `/{id}`                     | Update an existing product.                 |
| `DELETE` | `/{id}`                     | Delete a product by ID.                     |

---

### Owners `/api/v1/owners`

| Method | Endpoint        | Description |
|--------|-----------------|-------------|
| `GET`  | `/`             | Get a mocked owner with ID = 1 (used for frontend integration). |

---

### Towns `/api/v1/towns`

| Method | Endpoint        | Description |
|--------|-----------------|-------------|
| `GET`  | `/`             | Get list of all predefined towns. |

---

### Keywords `/api/v1/keywords`

| Method | Endpoint        | Description |
|--------|-----------------|-------------|
| `GET`  | `/`             | Get list of all predefined keywords for campaigns. |

