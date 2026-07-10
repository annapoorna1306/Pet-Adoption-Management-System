# Pet Adoption Management System - Project Structure Guide

## Complete File Organization

```
d:\Documents\Mongo db tasks\Pet Adoption Management System\
└── pet-adoption-api/
    ├── pom.xml                                    # Maven build configuration
    ├── README.md                                  # Project overview and setup guide
    ├── TESTING_GUIDE.md                           # Complete API testing guide
    ├── postman_collection.json                    # Postman API collection for testing
    ├── PROJECT_STRUCTURE.md                       # This file
    │
    └── src/
        ├── main/
        │   ├── java/
        │   │   └── com/adoption/
        │   │       ├── PetAdoptionApplication.java              # Main Spring Boot application
        │   │       │
        │   │       ├── model/                                   # Entity Classes (5 files)
        │   │       │   ├── Pet.java                             # Pet entity with annotations
        │   │       │   ├── Adopter.java                         # Adopter entity
        │   │       │   ├── Shelter.java                         # Shelter entity
        │   │       │   ├── Veterinarian.java                    # Veterinarian entity
        │   │       │   └── AdoptionRecord.java                  # Adoption record entity
        │   │       │
        │   │       ├── repository/                              # Data Access Layer (5 files)
        │   │       │   ├── PetRepository.java                   # Custom queries: findBySpecies, findByAdoptionStatus, findByShelterId
        │   │       │   ├── AdopterRepository.java               # Basic CRUD operations
        │   │       │   ├── ShelterRepository.java               # Basic CRUD operations
        │   │       │   ├── VeterinarianRepository.java          # Basic CRUD operations
        │   │       │   └── AdoptionRecordRepository.java         # Custom queries: findByPetId, findByAdopterId
        │   │       │
        │   │       ├── service/                                 # Business Logic Layer (5 files)
        │   │       │   ├── PetService.java                      # Pet operations & queries
        │   │       │   ├── AdopterService.java                  # Adopter operations
        │   │       │   ├── ShelterService.java                  # Shelter operations + getPetsByShelter()
        │   │       │   ├── VeterinarianService.java             # Veterinarian operations
        │   │       │   └── AdoptionService.java                 # Adoption operations + processAdoption()
        │   │       │
        │   │       ├── controller/                              # REST API Controllers (5 files)
        │   │       │   ├── PetController.java                   # 7 REST endpoints
        │   │       │   ├── AdopterController.java               # 5 REST endpoints
        │   │       │   ├── ShelterController.java               # 6 REST endpoints
        │   │       │   ├── VeterinarianController.java          # 5 REST endpoints
        │   │       │   └── AdoptionRecordController.java         # 8 REST endpoints
        │   │       │
        │   │       └── exception/                               # Exception Handling (4 files)
        │   │           ├── ResourceNotFoundException.java        # 404 exception
        │   │           ├── BadRequestException.java              # 400 exception
        │   │           ├── ErrorResponse.java                    # Standard error response DTO
        │   │           └── GlobalExceptionHandler.java           # Global exception handler
        │   │
        │   └── resources/
        │       └── application.properties                        # Spring Boot configuration
        │
        └── test/
            └── java/
                └── com/adoption/                                # Test classes (for future)
                    └── (Test files to be added)
```

## File Descriptions

### Root Level Files

#### 1. **pom.xml** (Maven Configuration)
- Defines project metadata
- Manages all dependencies
- Configures Maven plugins
- Sets Java version to 17
- Spring Boot 3.1.5 parent

**Key Dependencies:**
- spring-boot-starter-web
- spring-boot-starter-data-mongodb
- lombok
- springdoc-openapi-starter-webmvc-ui (Swagger)
- spring-boot-starter-validation

#### 2. **README.md** (Project Overview)
- Complete setup instructions
- Technology stack explanation
- API endpoint reference
- Installation guide
- Troubleshooting section

#### 3. **TESTING_GUIDE.md** (Testing Instructions)
- Step-by-step testing workflow
- cURL/REST examples
- Postman instructions
- Complete API endpoint testing
- Error handling tests

#### 4. **postman_collection.json** (Postman Collection)
- Pre-built Postman requests
- All 31 API endpoints
- Sample request bodies
- Variable placeholders for IDs

### Source Code Files

#### Model Layer (5 Files - com.adoption.model)

**Pet.java**
- @Document(collection = "pets")
- Fields: id, name, species, breed, age, healthStatus, shelterId, adoptionStatus
- Uses Lombok: @Data, @NoArgsConstructor, @AllArgsConstructor

**Adopter.java**
- @Document(collection = "adopters")
- Fields: id, name, email, phone, address

**Shelter.java**
- @Document(collection = "shelters")
- Fields: id, name, address, phone, email, capacity

**Veterinarian.java**
- @Document(collection = "veterinarians")
- Fields: id, name, specialization, clinicAddress, phone

**AdoptionRecord.java**
- @Document(collection = "adoptionRecords")
- Fields: id, petId, adopterId, adoptionDate, status, notes

#### Repository Layer (5 Files - com.adoption.repository)

**PetRepository.java**
```java
extends MongoRepository<Pet, String>
Methods:
- findBySpecies(String)
- findByAdoptionStatus(String)
- findByShelterId(String)
```

**AdopterRepository.java**
```java
extends MongoRepository<Adopter, String>
Default CRUD methods only
```

**ShelterRepository.java**
```java
extends MongoRepository<Shelter, String>
Default CRUD methods only
```

**VeterinarianRepository.java**
```java
extends MongoRepository<Veterinarian, String>
Default CRUD methods only
```

**AdoptionRecordRepository.java**
```java
extends MongoRepository<AdoptionRecord, String>
Methods:
- findByPetId(String)
- findByAdopterId(String)
```

#### Service Layer (5 Files - com.adoption.service)

**PetService.java**
```java
@Service with CRUD methods:
- addPet(Pet)
- getAllPets()
- getPetById(String)
- updatePet(String, Pet)
- deletePet(String)

Query methods:
- getPetsBySpecies(String)
- getPetsByAdoptionStatus(String)
- getPetsByShelterId(String)
```

**AdopterService.java**
```java
@Service with CRUD methods:
- addAdopter(Adopter)
- getAllAdopters()
- getAdopterById(String)
- updateAdopter(String, Adopter)
- deleteAdopter(String)
```

**ShelterService.java**
```java
@Service with CRUD methods + special:
- addShelter(Shelter)
- getAllShelters()
- getShelterById(String)
- updateShelter(String, Shelter)
- deleteShelter(String)
- getPetsByShelter(String) ⭐ Verifies shelter exists & returns pets
```

**VeterinarianService.java**
```java
@Service with CRUD methods:
- addVeterinarian(Veterinarian)
- getAllVeterinarians()
- getVeterinarianById(String)
- updateVeterinarian(String, Veterinarian)
- deleteVeterinarian(String)
```

**AdoptionService.java**
```java
@Service with CRUD methods + special:
- addAdoptionRecord(AdoptionRecord)
- getAllAdoptionRecords()
- getAdoptionRecordById(String)
- updateAdoptionRecord(String, AdoptionRecord)
- deleteAdoptionRecord(String)
- getAdoptionsByPetId(String)
- getAdoptionsByAdopterId(String)
- processAdoption(String petId, String adopterId) ⭐ Core business logic
```

#### Controller Layer (5 Files - com.adoption.controller)

**PetController.java** (@RequestMapping = "/api/pets")
```
7 Endpoints:
POST   /              - Add pet (201)
GET    /              - Get all (200)
GET    /{id}          - Get by ID (200)
GET    /species/{s}   - Get by species (200)
GET    /status/{s}    - Get by status (200)
PUT    /{id}          - Update (200)
DELETE /{id}          - Delete (200)
```

**AdopterController.java** (@RequestMapping = "/api/adopters")
```
5 Endpoints:
POST   /              - Add adopter (201)
GET    /              - Get all (200)
GET    /{id}          - Get by ID (200)
PUT    /{id}          - Update (200)
DELETE /{id}          - Delete (200)
```

**ShelterController.java** (@RequestMapping = "/api/shelters")
```
6 Endpoints:
POST     /              - Add shelter (201)
GET      /              - Get all (200)
GET      /{id}          - Get by ID (200)
GET      /{id}/pets     - Get pets in shelter (200)
PUT      /{id}          - Update (200)
DELETE   /{id}          - Delete (200)
```

**VeterinarianController.java** (@RequestMapping = "/api/veterinarians")
```
5 Endpoints:
POST   /              - Add vet (201)
GET    /              - Get all (200)
GET    /{id}          - Get by ID (200)
PUT    /{id}          - Update (200)
DELETE /{id}          - Delete (200)
```

**AdoptionRecordController.java** (@RequestMapping = "/api/adoptions")
```
8 Endpoints:
POST            /              - Create record (201)
POST            /process       - Process adoption (201) ⭐
GET             /              - Get all (200)
GET             /{id}          - Get by ID (200)
GET             /pet/{petId}   - Get by pet (200)
GET             /adopter/{aid} - Get by adopter (200)
PUT             /{id}          - Update (200)
DELETE          /{id}          - Delete (200)
```

#### Exception Layer (4 Files - com.adoption.exception)

**ResourceNotFoundException.java**
- Extends RuntimeException
- Thrown when resource not found
- Used with HTTP 404

**BadRequestException.java**
- Extends RuntimeException
- Thrown for invalid requests
- Used with HTTP 400

**ErrorResponse.java**
- Data Transfer Object (DTO)
- Standard error response format
- Fields: status, message, timestamp, path

**GlobalExceptionHandler.java**
- @ControllerAdvice
- Handles all exceptions globally
- Catches: ResourceNotFoundException (404)
- Catches: BadRequestException (400)
- Catches: All other exceptions (500)
- Returns ErrorResponse in JSON

#### Main Application File

**PetAdoptionApplication.java**
- @SpringBootApplication
- main() method entry point
- OpenAPI configuration bean
- Swagger UI configuration

#### Configuration File

**application.properties**
- Server port: 8080
- MongoDB URI/connection
- Database name: pet-adoption-db
- Logging configuration
- Swagger endpoints
- Jackson JSON settings

## Dependencies Overview

### Build Tool
- Maven 3.6+

### Core Framework
- Spring Boot 3.1.5
- Spring Web
- Spring Data MongoDB

### Database
- MongoDB (local or Atlas)
- MongoDB Java Driver

### Utilities
- Lombok (code generation)
- Jakarta Validation

### Documentation
- Springdoc OpenAPI 2.2.0
- Swagger UI

### Testing
- JUnit 5
- Spring Boot Test
- Mockito

## Architecture Layers

### 1. Controller Layer
- REST endpoints
- HTTP request/response handling
- Parameter validation at entry point
- @RestController, @RequestMapping

### 2. Service Layer
- Business logic implementation
- Data validation
- Complex operations (e.g., processAdoption)
- @Service, @RequiredArgsConstructor (Lombok)

### 3. Repository Layer
- Data access abstraction
- Database operations
- Query methods
- @Repository, extends MongoRepository

### 4. Model/Entity Layer
- Data structures
- MongoDB document mapping
- @Document, @Id annotations
- Lombok annotations for getters/setters

### 5. Exception Layer
- Custom exceptions
- Global exception handling
- Standardized error responses
- @ControllerAdvice

## Key Design Patterns

### 1. Dependency Injection
- Constructor injection using Lombok @RequiredArgsConstructor
- Spring automatically manages bean creation and injection

### 2. Repository Pattern
- Data access abstraction
- MongoRepository handles CRUD
- Custom queries defined in interfaces

### 3. Service Layer Pattern
- Business logic separation
- Reusable methods
- Transaction management

### 4. Exception Handling Pattern
- Custom exceptions for business logic
- Global exception handler
- Standardized error responses

### 5. DTO Pattern
- ErrorResponse for error handling
- Request/response standardization

## Data Flow Example: Process Adoption

```
1. Request → AdoptionRecordController.processAdoption()
   ↓
2. Controller → AdoptionService.processAdoption(petId, adopterId)
   ↓
3. Service validates:
   - Pet exists → PetService.getPetById()
   - Pet available → check status
   - Adopter exists → AdopterService.getAdopterById()
   ↓
4. If validation passes:
   - Update pet status → PetRepository.save()
   - Create adoption record → AdoptionRecordRepository.save()
   ↓
5. Response → AdoptionRecord (201 Created)
```

## Error Handling Flow

```
1. Request → Controller
   ↓
2. Controller/Service throws exception
   ↓
3. GlobalExceptionHandler catches exception
   ↓
4. Creates ErrorResponse with:
   - HTTP status code
   - Error message
   - Timestamp
   - Request path
   ↓
5. Response → Client (JSON ErrorResponse)
```

## Database Collections

MongoDB creates these collections:
- `pets` - Stores Pet documents
- `adopters` - Stores Adopter documents
- `shelters` - Stores Shelter documents
- `veterinarians` - Stores Veterinarian documents
- `adoptionRecords` - Stores AdoptionRecord documents

Each collection has auto-generated `_id` field (MongoDB)

## Total File Count

- **Java Files**: 26 (1 main + 5 models + 5 repos + 5 services + 5 controllers + 4 exceptions + 1 property file)
- **Configuration**: 1 (pom.xml)
- **Documentation**: 3 (README.md, TESTING_GUIDE.md, PROJECT_STRUCTURE.md)
- **Postman**: 1 (postman_collection.json)
- **Total**: 31 files

## API Endpoints Count

| Controller | Endpoints | Total |
|-----------|-----------|-------|
| PetController | 7 | 7 |
| AdopterController | 5 | 5 |
| ShelterController | 6 | 6 |
| VeterinarianController | 5 | 5 |
| AdoptionRecordController | 8 | 8 |
| **Total** | | **31 Endpoints** |

## Next Steps After Setup

1. **Build**: `mvn clean install`
2. **Run**: `mvn spring-boot:run`
3. **Test**: Import Postman collection
4. **Document**: Visit Swagger UI at http://localhost:8080/swagger-ui.html
5. **Monitor**: Check logs for any errors

---

This structure follows Spring Boot best practices and provides a clean, maintainable, and scalable REST API.
