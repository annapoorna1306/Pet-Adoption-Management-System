# Pet Adoption Management System - API Testing Guide

## Overview
This guide provides step-by-step instructions to test all endpoints of the Pet Adoption Management System REST API using Postman.

---

## Prerequisites

1. **Java 17+** - Make sure Java is installed
2. **MongoDB** - Running locally on `localhost:27017` or use MongoDB Atlas
3. **Maven** - For building the project
4. **Postman** - For testing the API (Download from https://www.postman.com/downloads/)

---

## Setup Instructions

### 1. Configure MongoDB Connection

**For Local MongoDB:**
- Ensure MongoDB is running on `localhost:27017`
- The database `pet-adoption-db` will be created automatically

**For MongoDB Atlas (Cloud):**
- Edit `src/main/resources/application.properties`
- Replace the URI with your MongoDB Atlas connection string:
  ```
  spring.data.mongodb.uri=mongodb+srv://username:password@cluster.mongodb.net/pet-adoption-db
  ```

### 2. Build the Project

```bash
cd pet-adoption-api
mvn clean install
```

### 3. Run the Application

```bash
mvn spring-boot:run
```

Or run the JAR file:
```bash
java -jar target/pet-adoption-api-1.0.0.jar
```

The application will start on `http://localhost:8080`

### 4. Access Swagger Documentation

Once the application is running, visit:
```
http://localhost:8080/swagger-ui.html
```

---

## Import Postman Collection

1. Open Postman
2. Click **Import** button
3. Select **Upload Files**
4. Choose `postman_collection.json` from the project root
5. Collection will be imported with all endpoints

Alternatively, manually create the requests following the examples below.

---

## Complete Testing Workflow

### Step 1: Create a Shelter

**Request:**
```
POST http://localhost:8080/api/shelters
Content-Type: application/json

{
  "name": "Happy Paws Shelter",
  "address": "789 Animal St, Pet City, USA",
  "phone": "555-5678",
  "email": "info@happypaws.com",
  "capacity": 50
}
```

**Expected Response (201 Created):**
```json
{
  "id": "shelter_123456",
  "name": "Happy Paws Shelter",
  "address": "789 Animal St, Pet City, USA",
  "phone": "555-5678",
  "email": "info@happypaws.com",
  "capacity": 50
}
```

**Save the `shelter_123456` ID for later use.**

---

### Step 2: Add Pets to the Shelter

**Request:**
```
POST http://localhost:8080/api/pets
Content-Type: application/json

{
  "name": "Buddy",
  "species": "Dog",
  "breed": "Golden Retriever",
  "age": 3,
  "healthStatus": "Healthy",
  "shelterId": "shelter_123456",
  "adoptionStatus": "available"
}
```

**Expected Response (201 Created):**
```json
{
  "id": "pet_789012",
  "name": "Buddy",
  "species": "Dog",
  "breed": "Golden Retriever",
  "age": 3,
  "healthStatus": "Healthy",
  "shelterId": "shelter_123456",
  "adoptionStatus": "available"
}
```

**Repeat to add more pets:**
```json
{
  "name": "Mittens",
  "species": "Cat",
  "breed": "Persian",
  "age": 2,
  "healthStatus": "Healthy",
  "shelterId": "shelter_123456",
  "adoptionStatus": "available"
}
```

**Save pet IDs for later.**

---

### Step 3: Create Adopters

**Request:**
```
POST http://localhost:8080/api/adopters
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "phone": "555-0123",
  "address": "123 Main St, Springfield, USA"
}
```

**Expected Response (201 Created):**
```json
{
  "id": "adopter_345678",
  "name": "John Doe",
  "email": "john@example.com",
  "phone": "555-0123",
  "address": "123 Main St, Springfield, USA"
}
```

**Add another adopter:**
```json
{
  "name": "Jane Smith",
  "email": "jane@example.com",
  "phone": "555-0124",
  "address": "456 Oak Ave, Springfield, USA"
}
```

**Save adopter IDs for later.**

---

### Step 4: Add Veterinarians

**Request:**
```
POST http://localhost:8080/api/veterinarians
Content-Type: application/json

{
  "name": "Dr. Jane Smith",
  "specialization": "Small Animals",
  "clinicAddress": "321 Vet Lane, Pet City, USA",
  "phone": "555-9999"
}
```

**Expected Response (201 Created):**
```json
{
  "id": "vet_567890",
  "name": "Dr. Jane Smith",
  "specialization": "Small Animals",
  "clinicAddress": "321 Vet Lane, Pet City, USA",
  "phone": "555-9999"
}
```

---

### Step 5: Test Pet Queries

#### Get All Pets
```
GET http://localhost:8080/api/pets
```

#### Get Pets by Species
```
GET http://localhost:8080/api/pets/species/Dog
```

#### Get Available Pets
```
GET http://localhost:8080/api/pets/status/available
```

#### Get Pets in a Specific Shelter
```
GET http://localhost:8080/api/shelters/shelter_123456/pets
```

#### Get Single Pet by ID
```
GET http://localhost:8080/api/pets/pet_789012
```

---

### Step 6: Update Records

#### Update Pet Details
```
PUT http://localhost:8080/api/pets/pet_789012
Content-Type: application/json

{
  "healthStatus": "Vaccinated",
  "age": 4
}
```

#### Update Adopter Details
```
PUT http://localhost:8080/api/adopters/adopter_345678
Content-Type: application/json

{
  "phone": "555-0125",
  "address": "789 Pine St, Springfield, USA"
}
```

---

### Step 7: Process Adoption

**This is the key operation that:**
- Verifies pet is available
- Verifies adopter exists
- Updates pet status to "adopted"
- Creates an adoption record

**Request:**
```
POST http://localhost:8080/api/adoptions/process
Content-Type: application/json

{
  "petId": "pet_789012",
  "adopterId": "adopter_345678"
}
```

**Expected Response (201 Created):**
```json
{
  "id": "adoption_111223",
  "petId": "pet_789012",
  "adopterId": "adopter_345678",
  "adoptionDate": "2024-06-18T15:30:00.000+00:00",
  "status": "completed",
  "notes": null
}
```

**Verify pet status changed:**
```
GET http://localhost:8080/api/pets/pet_789012
```

Should show `"adoptionStatus": "adopted"`

---

### Step 8: Test Adoption Records

#### Get All Adoptions
```
GET http://localhost:8080/api/adoptions
```

#### Get Adoptions by Pet ID
```
GET http://localhost:8080/api/adoptions/pet/pet_789012
```

#### Get Adoptions by Adopter ID
```
GET http://localhost:8080/api/adoptions/adopter/adopter_345678
```

---

### Step 9: Error Handling Tests

#### Test 404 - Resource Not Found
```
GET http://localhost:8080/api/pets/nonexistent_id
```

**Expected Response (404 Not Found):**
```json
{
  "status": 404,
  "message": "Pet not found with id: nonexistent_id",
  "timestamp": "2024-06-18T15:35:00",
  "path": "/api/pets/nonexistent_id"
}
```

#### Test 400 - Bad Request (Adopt an already adopted pet)
```
POST http://localhost:8080/api/adoptions/process
Content-Type: application/json

{
  "petId": "pet_789012",
  "adopterId": "adopter_999999"
}
```

**Expected Response (400 Bad Request):**
```json
{
  "status": 400,
  "message": "Pet is not available for adoption",
  "timestamp": "2024-06-18T15:36:00",
  "path": "/api/adoptions/process"
}
```

---

### Step 10: Delete Operations

#### Delete Pet
```
DELETE http://localhost:8080/api/pets/pet_789012
```

**Expected Response (200 OK):**
```
"Pet deleted successfully"
```

#### Delete Adopter
```
DELETE http://localhost:8080/api/adopters/adopter_345678
```

#### Delete Shelter
```
DELETE http://localhost:8080/api/shelters/shelter_123456
```

---

## API Endpoint Summary

| Method | Endpoint | Description |
|--------|----------|-------------|
| **Pets** | | |
| POST | `/api/pets` | Add new pet |
| GET | `/api/pets` | Get all pets |
| GET | `/api/pets/{id}` | Get pet by ID |
| GET | `/api/pets/species/{species}` | Get pets by species |
| GET | `/api/pets/status/{status}` | Get pets by status |
| PUT | `/api/pets/{id}` | Update pet |
| DELETE | `/api/pets/{id}` | Delete pet |
| **Adopters** | | |
| POST | `/api/adopters` | Add new adopter |
| GET | `/api/adopters` | Get all adopters |
| GET | `/api/adopters/{id}` | Get adopter by ID |
| PUT | `/api/adopters/{id}` | Update adopter |
| DELETE | `/api/adopters/{id}` | Delete adopter |
| **Shelters** | | |
| POST | `/api/shelters` | Add new shelter |
| GET | `/api/shelters` | Get all shelters |
| GET | `/api/shelters/{id}` | Get shelter by ID |
| GET | `/api/shelters/{id}/pets` | Get pets in shelter |
| PUT | `/api/shelters/{id}` | Update shelter |
| DELETE | `/api/shelters/{id}` | Delete shelter |
| **Veterinarians** | | |
| POST | `/api/veterinarians` | Add new vet |
| GET | `/api/veterinarians` | Get all vets |
| GET | `/api/veterinarians/{id}` | Get vet by ID |
| PUT | `/api/veterinarians/{id}` | Update vet |
| DELETE | `/api/veterinarians/{id}` | Delete vet |
| **Adoptions** | | |
| POST | `/api/adoptions` | Create adoption record |
| POST | `/api/adoptions/process` | Process adoption |
| GET | `/api/adoptions` | Get all adoptions |
| GET | `/api/adoptions/{id}` | Get adoption by ID |
| GET | `/api/adoptions/pet/{petId}` | Get adoptions by pet |
| GET | `/api/adoptions/adopter/{adopterId}` | Get adoptions by adopter |
| PUT | `/api/adoptions/{id}` | Update adoption record |
| DELETE | `/api/adoptions/{id}` | Delete adoption record |

---

## HTTP Status Codes

- **200 OK** - Successful GET, PUT, DELETE
- **201 Created** - Successful POST
- **400 Bad Request** - Invalid input or business logic error
- **404 Not Found** - Resource not found
- **500 Internal Server Error** - Server error

---

## Troubleshooting

### MongoDB Connection Error
- Ensure MongoDB is running
- Check connection string in `application.properties`
- Check MongoDB URI for correct credentials if using Atlas

### Port 8080 Already in Use
- Change port in `application.properties`:
  ```
  server.port=9090
  ```

### Cannot find API Endpoints
- Ensure application is running: `mvn spring-boot:run`
- Check console for startup errors
- Access Swagger UI to verify: `http://localhost:8080/swagger-ui.html`

### Bad Request on Adoption Process
- Verify pet is in "available" status
- Verify both pet and adopter IDs exist
- Check JSON format is correct

---

## Notes

- MongoDB automatically generates IDs if not provided
- All timestamps are in UTC
- The system prevents adopting pets that are already adopted
- Cascade deletes are not implemented; be careful deleting shelters with pets
