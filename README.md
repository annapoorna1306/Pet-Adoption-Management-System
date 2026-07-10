# 🐕 Pet Adoption Management System - REST API

A complete REST API for managing pet adoptions built with Spring Boot and MongoDB.

## 📋 Project Overview
This system manages 5 core modules: Pets, Adopters, Shelters, Veterinarians, and Adoption Records with full CRUD operations.

## 🛠️ Technology Stack
- **Java** 21
- **Spring Boot** 3.1.5
- **Spring Data MongoDB** 4.1.5
- **MongoDB** 6.0+
- **Maven** 3.9.16
- **Swagger/OpenAPI** 2.2.0

## ✨ Features
- ✅ Complete CRUD operations for 5 modules
- ✅ 25+ REST API endpoints
- ✅ MongoDB with relationships (One-to-Many, Many-to-One, One-to-One)
- ✅ Global exception handling
- ✅ Swagger documentation
- ✅ Postman testing

## 🚀 How to Run

```bash
# Navigate to project
cd pet-adoption-api

# Build
mvn clean install

# Run
mvn spring-boot:run
