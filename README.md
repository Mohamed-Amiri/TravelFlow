# 🌍 SafariHub API

A production-ready **REST API** for a travel booking platform. Users can browse trips, make reservations, save favorites, write reviews, and administrators manage the platform.

Built with **Spring Boot 3.5.6** (Java 17), following **Clean Layered Architecture**, **SOLID** principles, and enterprise best practices.

---

## 🏗 Architecture

```
Clean Layered Architecture
┌─────────────────────────────────────────┐
│              Controllers                 │  ← REST endpoints, validation
├─────────────────────────────────────────┤
│              Services (Interface)        │  ← Business contracts
│         Services (Implementation)         │  ← Business logic, rules
├─────────────────────────────────────────┤
│              Repositories                │  ← JPA / Specification
├─────────────────────────────────────────┤
│              Entities                    │  ← Domain model (never exposed)
└─────────────────────────────────────────┘
```

### Layers

| Layer     | Package              | Responsibility                                       |
|-----------|----------------------|-------------------------------------------------------|
| Config    | `config`             | OpenAPI, CORS, properties, data seeder               |
| Controller| `controller`         | HTTP endpoints, request/response mapping              |
| DTO       | `dto`                | Request & response objects with Jakarta Validation    |
| Entity    | `entity`             | JPA domain model with Lombok                          |
| Repository| `repository`         | Spring Data JPA + JpaSpecificationExecutor          |
| Service   | `service`            | Interfaces for business contracts                     |
| Service   | `service/impl`       | Business logic implementation                          |
| Mapper    | `mapper`             | MapStruct entity ↔ DTO mappers                        |
| Security  | `security`           | JWT filter, SecurityConfig, UserDetailsService        |
| Exception | `exception`          | Custom exceptions + GlobalExceptionHandler            |
| Util      | `util`               | JWT token generation/validation                       |

---

## ✨ Features

- **JWT Authentication** — stateless, BCrypt-encrypted passwords, role-based access
- **Default Admin Seeding** — auto-created on first startup
- **Trip Management** — search, filter (destination/country/category/price), sort, pagination
- **Reservations** — book, cancel, view own; duplicate & seat-availability guards
- **Reviews** — create, update own, delete own, read per trip
- **Favorites** — add, remove, list own
- **Admin Dashboard** — user listing, promote, delete, platform statistics
- **Validation** — Jakarta Validation on every request DTO
- **Error Handling** — standardized JSON error responses (400/401/403/404/409/500)
- **Swagger UI** — interactive API docs at `/swagger-ui.html`
- **Docker** — Dockerfile + docker-compose for MySQL + app

---

## 🛠 Technologies

| Technology           | Version / Purpose                        |
|----------------------|------------------------------------------|
| Java                 | 17                                       |
| Spring Boot          | 3.5.6                                    |
| Spring Security      | JWT + BCrypt + method-level `@PreAuthorize` |
| Spring Data JPA      | Hibernate, `JpaSpecificationExecutor`    |
| MySQL                | 8.x                                      |
| Lombok               | Boilerplate reduction                    |
| MapStruct            | Entity ↔ DTO mapping                     |
| JJWT                 | 0.12.6 — JWT sign/verify                 |
| SpringDoc OpenAPI    | 2.8.x — Swagger UI                       |
| Maven                | Build                                    |
| Docker               | Containerization                         |

---

## 📦 Installation

### Prerequisites

- Java 17+
- Maven 3.6+
- MySQL 8.0+

### 1. Database Setup

```sql
CREATE DATABASE TravelFlow;
```

### 2. Configure Application

Edit `TravelFlowBackend/src/main/resources/application.properties`:

```properties
spring.datasource.username=root
spring.datasource.password=your_password
```

### 3. Run Locally

```bash
cd TravelFlowBackend
./mvnw spring-boot:run
```

The API starts at **http://localhost:8081**

### 4. Run with Docker

```bash
docker-compose up --build
```

The API starts at **http://localhost:8081**, MySQL at **localhost:3307**

---

## 📖 API Endpoints

### Authentication (`/api/v1/auth`)

| Method | Endpoint              | Auth  | Description                    |
|--------|-----------------------|-------|--------------------------------|
| POST   | `/auth/register`     | No    | Register new user (ROLE_USER)  |
| POST   | `/auth/login`         | No    | Login & get JWT                |
| GET    | `/auth/me`            | Yes   | Get current user profile       |
| PUT    | `/auth/profile`       | Yes   | Update profile                |
| PATCH  | `/auth/change-password`| Yes  | Change password               |

### Trips (`/api/v1/trips`)

| Method | Endpoint           | Auth   | Description                          |
|--------|--------------------|--------|--------------------------------------|
| GET    | `/trips`           | No     | Search / filter / paginate trips     |
| GET    | `/trips/{id}`      | No     | Get single trip                      |
| POST   | `/trips`           | ADMIN  | Create trip                          |
| PUT    | `/trips/{id}`      | ADMIN  | Update trip                          |
| DELETE | `/trips/{id}`      | ADMIN  | Delete trip                          |

**Query parameters (GET /trips):** `keyword`, `destination`, `country`, `category`, `minPrice`, `maxPrice`, `page`, `size`, `sort`

### Reservations (`/api/v1/reservations`)

| Method | Endpoint                  | Auth  | Description                |
|--------|---------------------------|-------|----------------------------|
| POST   | `/reservations`           | Yes   | Book a trip                |
| PUT    | `/reservations/{id}/cancel`| Yes | Cancel reservation        |
| GET    | `/reservations/my`         | Yes   | View my reservations       |

### Reviews (`/api/v1/reviews`)

| Method | Endpoint              | Auth  | Description                 |
|--------|-----------------------|-------|-----------------------------|
| GET    | `/reviews/trip/{id}`  | No    | Read reviews for a trip     |
| POST   | `/reviews`            | Yes   | Create review               |
| PUT    | `/reviews/{id}`       | Yes   | Update own review           |
| DELETE | `/reviews/{id}`       | Yes   | Delete own review           |

### Favorites (`/api/v1/favorites`)

| Method | Endpoint          | Auth  | Description              |
|--------|-------------------|-------|--------------------------|
| POST   | `/favorites`      | Yes   | Add trip to favorites     |
| DELETE | `/favorites/{id}` | Yes   | Remove from favorites     |
| GET    | `/favorites/my`   | Yes   | View my favorites         |

### Admin (`/api/v1/admin`)

| Method | Endpoint                  | Auth  | Description              |
|--------|---------------------------|-------|--------------------------|
| GET    | `/admin/users`            | ADMIN | List all users           |
| PUT    | `/admin/users/{id}/promote`| ADMIN | Promote user to ADMIN |
| DELETE | `/admin/users/{id}`       | ADMIN | Delete user              |
| GET    | `/admin/stats`            | ADMIN | Platform statistics      |

---

## 🔐 Authentication Flow

1. **Register** → `POST /api/v1/auth/register` (creates ROLE_USER)
2. **Login** → `POST /api/v1/auth/login` → receive JWT token
3. **Include token** in subsequent requests:
   ```
   Authorization: Bearer <your_jwt_token>
   ```

### Default Admin

On first startup, a default admin is created:

| Field    | Value                |
|----------|----------------------|
| Email    | admin@safarihub.com  |
| Password | Admin@123            |
| Role     | ROLE_ADMIN           |

> Login via `/api/v1/auth/login` — no registration for admin.

---

## 📂 Folder Structure

```
TravelFlowBackend/
├── src/main/java/com/safarihub/
│   ├── config/                 # CORS, OpenAPI, properties, seeder
│   ├── controller/             # REST API endpoints
│   ├── dto/
│   │   ├── admin/              # StatsResponse
│   │   ├── auth/               # RegisterRequest, LoginRequest, AuthResponse…
│   │   ├── common/             # ApiResponse, PaginationMeta, PagedResponse
│   │   ├── favorite/           # FavoriteRequest, FavoriteResponse
│   │   ├── reservation/        # ReservationRequest, ReservationResponse
│   │   ├── review/             # ReviewRequest, ReviewResponse
│   │   └── trip/               # TripRequest, TripResponse
│   ├── entity/                 # JPA entities + BaseEntity, Role enum
│   ├── exception/              # Custom exceptions + GlobalExceptionHandler
│   ├── mapper/                 # MapStruct mappers
│   ├── repository/             # Spring Data JPA
│   ├── security/               # JWT filter, SecurityConfig, UserDetails
│   ├── service/                # Service interfaces
│   ├── service/impl/           # Service implementations
│   ├── util/                   # JwtService
│   └── SafariHubApplication.java
├── src/main/resources/
│   └── application.properties
├── src/test/java/com/safarihub/
├── Dockerfile
├── pom.xml
└── postman/
    └── SafariHub.postman_collection.json
```

---

## 📄 License

This project is for educational and demonstration purposes.
