# 💰 Money Manager - Full-Stack Personal Finance Platform

[![Live Demo](https://img.shields.io/badge/Demo-Live%20Website-success?style=for-the-badge&logo=netlify)](https://earnest-travesseiro-472868.netlify.app)
[![Backend API](https://img.shields.io/badge/API-Render-informational?style=for-the-badge&logo=render)](https://moneymanager-api-vk4a.onrender.com/api/v1.0/status)
[![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-18-blue?style=for-the-badge&logo=react)](https://react.dev/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql)](https://www.mysql.com/)

A modern, cloud-native full-stack personal finance and expense tracking web application. Built with **Spring Boot 3 (Java 21)**, **Spring Security 6**, **JWT Authentication**, and **React 18 (Vite + Tailwind CSS)**, containerized with **Docker** and deployed on cloud infrastructure.

---

## 🚀 Live Links
* **Frontend Web App**: [https://earnest-travesseiro-472868.netlify.app](https://earnest-travesseiro-472868.netlify.app)
* **Backend REST API**: [https://moneymanager-api-vk4a.onrender.com](https://moneymanager-api-vk4a.onrender.com)
* **API Context Path**: `/api/v1.0`

---

## ✨ Key Features

* 🔐 **Secure Authentication**: Stateless JWT (JSON Web Token) authorization with BCrypt password hashing.
* ✉️ **Account Activation**: Token-based activation flow integrated with transactional emails.
* 📊 **Interactive Financial Analytics**: Dynamic Dashboard with real-time balance calculations, line charts, and breakdown pie charts using Recharts.
* 🏷️ **Categorized Transaction Management**: Foreign-key relational mapping for custom Income and Expense categories with emoji icons.
* 📑 **Excel Export & Email Reports**: Server-side spreadsheet generation using **Apache POI** (`.xlsx`) with automated attachment delivery to user inboxes.
* 📸 **Profile Management**: Profile photo upload and hosting powered by Cloudinary.
* 🐳 **Cloud-Native & Containerized**: Multi-stage Dockerfile packaging for efficient cloud deployments.

---

## 🛠️ Architecture & Tech Stack

### Backend
* **Language & Framework**: Java 21, Spring Boot 3.5.3
* **Security**: Spring Security 6.5, JJWT 0.11.5, CORS Filter
* **Data Persistence**: Spring Data JPA, Hibernate, MySQL 8.0 (Aiven Cloud)
* **Email & Integrations**: Brevo HTTP REST API (bypassing cloud SMTP restrictions)
* **Reporting**: Apache POI (5.2.5) for Excel generation
* **Containerization**: Multi-stage Docker (Eclipse Temurin JRE 21)

### Frontend
* **Core**: React 18, Vite
* **Styling**: Tailwind CSS, Lucide React Icons
* **Routing & State**: React Router DOM v6, React Context API
* **Networking**: Axios with interceptors
* **Visualization**: Recharts (PieChart, LineChart)

---

## 🏗️ System Architecture

```
[ User Browser ]
       │
       ▼
[ Netlify CDN ] ── (React 18 + Vite SPA)
       │
   HTTP / REST API (JWT Authenticated)
       │
       ▼
[ Render Cloud ] ── (Spring Boot 3.5 Docker Container)
   ├── Security Filter Chain (JWT Filter, CORS)
   ├── REST Controllers (/incomes, /expenses, /categories, /dashboard)
   ├── Service Layer (Apache POI Excel Service, Brevo Email Service)
   └── Spring Data JPA Layer
       │
       ├──► [ Aiven Cloud MySQL 8.0 ] (SSL Encrypted)
       ├──► [ Brevo API ] (Transactional Activation & Excel Delivery)
       └──► [ Cloudinary ] (Profile Image Storage)
```

---

## 💡 Key Engineering Challenges Solved

1. **Overcoming Cloud SMTP Port Blocks**: Cloud platforms like Render block standard outbound SMTP ports (25, 465, 587). Engineered an HTTP-based transactional mailer using Brevo's REST API (`/v3/smtp/email`) to ensure reliable email delivery for activation and Excel reports.
2. **Container Build Efficiency**: Implemented a multi-stage Dockerfile separating the Maven compilation phase from the lightweight Eclipse Temurin JRE runtime image, eliminating build dependencies from the final production container.
3. **Single Page Application Routing**: Configured Netlify rewrites (`_redirects`) to redirect all client-side navigation to `/index.html` with HTTP 200, preventing 404 errors on browser page refreshes.

---

## 💻 Local Development Setup

### Prerequisites
* JDK 21+
* Maven 3.9+
* Node.js 18+ & npm
* MySQL 8.0

### Backend Setup
1. Navigate to the backend directory:
   ```bash
   cd moneymanager
   ```
2. Configure database credentials in `src/main/resources/application.properties` or via environment variables (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `JWT_SECRET`).
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
   Backend starts on `http://localhost:8080/api/v1.0`.

### Frontend Setup
1. Navigate to the frontend directory:
   ```bash
   cd moneymanagerwebapp
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the Vite dev server:
   ```bash
   npm run dev
   ```
   Frontend runs on `http://localhost:5173`.

---

## 👤 Author
* **Harika Bollu** — [GitHub Profile](https://github.com/BolluHarika)
