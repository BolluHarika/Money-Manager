# Money Manager - Full-Stack Personal Finance Platform

A full-stack web application designed for personal finance tracking, category-based expense management, and financial reporting. Built with a Spring Boot RESTful backend, a React frontend, and cloud-hosted MySQL.

## Live Deployments
* **Web Application**: [https://earnest-travesseiro-472868.netlify.app](https://earnest-travesseiro-472868.netlify.app)
* **Backend API**: [https://moneymanager-api-vk4a.onrender.com/api/v1.0](https://moneymanager-api-vk4a.onrender.com/api/v1.0)
* **API Health Check**: [https://moneymanager-api-vk4a.onrender.com/api/v1.0/status](https://moneymanager-api-vk4a.onrender.com/api/v1.0/status)

---

## Core Features
* **User Authentication and Authorization**: Stateless JSON Web Token (JWT) implementation with Spring Security and BCrypt password encryption.
* **Account Verification**: Token-based profile activation flow utilizing automated transactional email verification.
* **Income and Expense Tracking**: CRUD operations for managing income sources and expenditures mapped to user-defined categories.
* **Category Management**: Relational data categorization for custom income and expense types.
* **Financial Reporting & Export**: Server-side Excel (`.xlsx`) report generation using Apache POI, with direct email attachment delivery.
* **Analytics Dashboard**: Visual financial summaries including expense distributions and income trends using Recharts.
* **Profile Management**: Profile image integration with Cloudinary cloud storage.

---

## Technology Stack

### Backend
* **Language**: Java 21
* **Framework**: Spring Boot 3.5.3
* **Security**: Spring Security 6.5, JJWT (Java JWT)
* **Data Access**: Spring Data JPA, Hibernate ORM
* **Database**: MySQL 8.0 (Aiven Cloud Managed)
* **Document Generation**: Apache POI (5.2.5)
* **Email Service**: Brevo HTTP REST API
* **Containerization**: Docker (Multi-stage build)
* **Hosting**: Render

### Frontend
* **Core**: React 18, Vite
* **Styling**: Tailwind CSS, Lucide Icons
* **HTTP Client**: Axios with request interceptors
* **Routing**: React Router DOM (v6)
* **Data Visualization**: Recharts
* **Hosting**: Netlify

---

## Architecture Overview

```
[ Client Browser ]
        │
        ▼
[ Netlify CDN ]  (React Single Page Application)
        │
   HTTPS / REST APIs (JWT Bearer Token)
        │
        ▼
[ Render Web Service ]  (Spring Boot 3 / Docker Container)
   ├── Security Filter Chain (JwtRequestFilter, CORS)
   ├── REST Controllers (/incomes, /expenses, /categories, /dashboard)
   ├── Service Layer (ProfileService, ExcelService, EmailService)
   └── Spring Data JPA Layer
        │
        ├──► [ Aiven Cloud MySQL 8.0 ] (SSL Encrypted Connection)
        ├──► [ Brevo HTTP API ] (Transactional Activation & Excel Delivery)
        └──► [ Cloudinary ] (Profile Image Storage)
```

---

## Key Technical Decisions

* **HTTP-based Transactional Emailing**: Standard outbound SMTP ports (25, 465, 587) are restricted in cloud environments. To ensure reliable email delivery for account activation and spreadsheet reports, an HTTP-based client was implemented using Brevo's REST API.
* **Container Optimization**: A multi-stage Dockerfile separates the Maven build environment from the Eclipse Temurin JRE runtime, reducing container footprint and ensuring clean deployments without committed build artifacts.
* **Stateless Session Architecture**: Configured Spring Security to disable HTTP session creation (`SessionCreationPolicy.STATELESS`), relying entirely on signed JWT tokens to handle scalable, cross-origin requests.
* **SPA Routing Configuration**: Implemented redirect rewrites (`_redirects`) to route client-side URLs through the root `index.html`, eliminating 404 errors during page refreshes on Netlify.

---

## Local Development Setup

### Prerequisites
* Java Development Kit (JDK 21+)
* Apache Maven 3.9+
* Node.js 18+ and npm
* MySQL 8.0+

### Backend Setup
1. Navigate to the backend folder:
   ```bash
   cd moneymanager
   ```
2. Set up environment variables in `application.properties` or system environment (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `JWT_SECRET`).
3. Run the application:
   ```bash
   mvn clean spring-boot:run
   ```

### Frontend Setup
1. Navigate to the frontend folder:
   ```bash
   cd moneymanagerwebapp
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start development server:
   ```bash
   npm run dev
   ```

---

## Author
* **Harika Bollu** — [GitHub Profile](https://github.com/BolluHarika)
