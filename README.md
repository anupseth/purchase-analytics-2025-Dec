# 🧾 Receipt Analytics

Receipt Analytics is a **Java Spring Boot application** that scans grocery receipts, extracts structured data, translates it to English, stores it in a database, and generates spending analytics.

The application is designed to run as a **containerized service on a Raspberry Pi**, with a **production-grade CI/CD pipeline** and safe, versioned deployments.

---

## 🎯 Project Goals

- Scan grocery receipts (images / PDFs)
- Extract items, prices, totals, and store information
- Translate Polish receipt data to English
- Normalize item names and categories
- Store data in a relational database
- Generate spending insights over time

---

## 🧱 Technology Stack

### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- Flyway (database migrations)

### Database
- PostgreSQL

### Infrastructure
- Docker
- Docker Compose (Raspberry Pi)
- GitHub Actions (CI/CD)
- Docker Hub (image registry)

---

## 🧠 High-Level Architecture

```
Receipt Image / PDF
        ↓
   (OCR Pipeline)
        ↓
 Translation (PL → EN)
        ↓
 Normalization
        ↓
 PostgreSQL
        ↓
 Analytics APIs
```

---

## 📁 Repository Structure

```
.
├── .github/workflows/
│   └── docker-publish.yml      # CI/CD pipeline
├── src/main/java/
│   └── com/anup/receipt_analytics
│       ├── domain/             # JPA entities
│       ├── repository/         # Spring Data repositories
│       └── ReceiptAnalyticsApplication.java
├── src/main/resources/
│   ├── application-prod.yml
│   └── db/migration/           # Flyway SQL migrations
├── docker-compose.yml
├── Dockerfile
└── README.md
```

---

## 🚀 CI/CD Overview

### Core Rules

- CI runs **only on Git tags**
- Tests **must pass** before Docker images are published
- Every release is **versioned and immutable**
- Raspberry Pi deployments use **explicit versions**, never `latest`

---

## 🔄 CI/CD Flow

```
Code change
   ↓
Git commit
   ↓
Git tag (vX.Y.Z)
   ↓
GitHub Actions
   ↓
Run tests
   ↓
Build multi-arch Docker image
   ↓
Push image to Docker Hub
   ↓
Raspberry Pi pulls versioned image
   ↓
Docker Compose restarts app
```

---

## 🏷️ Versioning Strategy

Semantic Versioning is used:

```
vMAJOR.MINOR.PATCH
```

Examples:
- `v1.0.0` – first stable release
- `v1.0.1` – bug fix
- `v1.1.0` – new feature
- `v2.0.0` – breaking change

---

## 🐳 Docker Images

Images are published to Docker Hub:

```
anupdochub/receipt-analytics:<version>
```

---

## 🖥️ Running on Raspberry Pi

### docker-compose.yml (excerpt)

```yaml
services:
  receipt-analytics:
    image: anupdochub/receipt-analytics:${APP_VERSION:-1.0.0}
    ports:
      - "8080:8080"
    restart: unless-stopped
```

---

## 📦 How to Release a New Version

### Local machine

```bash
git add .
git commit -m "Your commit message"
git tag v1.0.3
git push origin v1.0.3
```

---

## 🚀 Deploying on Raspberry Pi

```bash
export APP_VERSION=1.0.3
docker compose pull
docker compose up -d
```

---

## 🧪 Running locally (development)

To run the application locally with the development PostgreSQL container, activate the `local` profile which enables the project's development Docker Compose configuration:

```bash
mvn -Dspring-boot.run.profiles=local -DskipTests spring-boot:run
```

Alternatively start the DB manually and run without activating the profile:

```bash
docker compose -f docker-compose.local.yml up -d
mvn -DskipTests spring-boot:run
```

> Note: The default profile has Docker Compose disabled to avoid unexpected environment changes during quick local runs.

---

## 🔄 Rollback

```bash
export APP_VERSION=1.0.2
docker compose up -d
```

---

## 📌 Notes

- Never reuse Git tags
- Never deploy `latest` in production
- Always verify deployment via `/api/health`

---

This repository is designed to scale from a personal project to a production-ready system.
