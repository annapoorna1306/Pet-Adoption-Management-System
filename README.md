# Pet Adoption Management System - REST API

A complete Spring Boot 3.1.5 REST API for managing pet adoptions, built with Java 17, Spring Data MongoDB, and MongoDB.

## Project Overview

This project implements a comprehensive Pet Adoption Management System with the following core entities:
- **Pets** - Animals available for adoption
- **Adopters** - People who can adopt pets
- **Shelters** - Facilities that house and manage pets
- **Veterinarians** - Medical professionals who treat pets
- **Adoption Records** - Transaction records for pet adoptions

## Technology Stack

- **Java 17+**
- **Spring Boot 3.1.5**
- **Spring Web (REST APIs)**
- **Spring Data MongoDB**
- **MongoDB Atlas (Cloud) / Local MongoDB**
- **Maven** - Build tool
- **Lombok** - Boilerplate code reduction
- **Springdoc OpenAPI 2.2.0** - Swagger/OpenAPI documentation
- **JUnit & Spring Test** - Testing

## Project Structure

```
pet-adoption-api/
├── src/
│   ├── main/
│   │   ├── java/com/adoption/
│   │   │   ├── controller/          # REST Controllers (5 files)
│   │   │   │   ├── PetController.java
│   │   │   │   ├── AdopterController.java
│   │   │   │   ├── ShelterController.java
│   │   │   │   ├── VeterinarianController.java
│   │   │   │   └── AdoptionRecordController.java
│   │   │   ├── service/             # Business Logic Services (5 files)
│   │   │   │   ├── PetService.java
│   │   │   │   ├── AdopterService.java
│   │   │   │   ├── ShelterService.java
│   │   │   │   ├── VeterinarianService.java
│   │   │   │   └── AdoptionService.java
│   │   │   ├── repository/          # Data Access Layer (5 files)
│   │   │   │   ├── PetRepository.java
│   │   │   │   ├── AdopterRepository.java
│   │   │   │   ├── ShelterRepository.java
│   │   │   │   ├── VeterinarianRepository.java
│   │   │   │   └── AdoptionRecordRepository.java
│   │   │   ├── model/               # Entity Classes (5 files)
│   │   │   │   ├── Pet.java
│   │   │   │   ├── Adopter.java
│   │   │   │   ├── Shelter.java
│   │   │   │   ├── Veterinarian.java
│   │   │   │   └── AdoptionRecord.java
│   │   │   ├── exception/           # Exception Handling
│   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   ├── BadRequestException.java
│   │   │   │   ├── ErrorResponse.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   └── PetAdoptionApplication.java  # Main Application Class
│   │   └── resources/
│   │       └── application.properties       # Configuration
│   └── test/java/com/adoption/              # Test classes
├── pom.xml                          # Maven dependencies
├── postman_collection.json          # Postman API collection
├── TESTING_GUIDE.md                 # Complete testing guide
└── README.md                        # This file
```

## Features

### 1. Complete CRUD Operations
- Create, Read, Update, Delete operations for all 5 entities
- Proper HTTP status codes (200, 201, 400, 404, 500)

### 2. Relationship Management
- One-to-Many: Shelter → Pets
- One-to-Many: Adopter → Adoptions
- One-to-One: AdoptionRecord → Pet/Adopter
- Referential integrity through service layer logic

### 3. Advanced Adoption Processing
- `processAdoption()` method atomically:
  - Validates pet availability
  - Verifies adopter exists
  - Updates pet status to "adopted"
  - Creates adoption record with timestamp

### 4. Comprehensive Query Methods
- Find pets by species
- Find pets by adoption status (available/adopted)
- Find all pets in a specific shelter
- Find adoption records by pet or adopter
- Flexible filtering and searching

### 5. Exception Handling
- Custom exceptions: `ResourceNotFoundException`, `BadRequestException`
- Global exception handler with `@ControllerAdvice`
- Standardized error response format
- Proper HTTP status codes for all error scenarios

### 6. API Documentation
- Swagger/OpenAPI integration at `/swagger-ui.html`
- Auto-generated interactive API documentation
- Request/response examples for all endpoints

### 7. Database Integration
- MongoDB Atlas cloud database support
- Local MongoDB support
- Automatic index creation
- Document collections with MongoDB annotations

## Prerequisites

- **Java 17+** - [Download Java](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.6+** - [Download Maven](https://maven.apache.org/download.cgi)
- **MongoDB** - Either:
  - Local: [Download MongoDB Community](https://www.mongodb.com/try/download/community)
  - Cloud: [MongoDB Atlas](https://www.mongodb.com/cloud/atlas) (Free tier available)
- **Git** - [Download Git](https://git-scm.com/)
- **Postman** - [Download Postman](https://www.postman.com/downloads/) (for testing)

## Installation & Setup

### 1. Clone or Extract the Project

```bash
cd "Pet Adoption Management System"
cd pet-adoption-api
```

### 2. Configure MongoDB

**Option A: Local MongoDB**

1. Install and start MongoDB
2. The application will automatically create the database `pet-adoption-db`

**Option B: MongoDB Atlas (Cloud)**

1. Create a free MongoDB Atlas account at https://www.mongodb.com/cloud/atlas
2. Create a cluster and get your connection string
3. Edit `src/main/resources/application.properties`:
   ```properties
   spring.data.mongodb.uri=mongodb+srv://username:password@your-cluster.mongodb.net/pet-adoption-db
   ```

### 3. Build the Project

```bash
mvn clean install
```

This will:
- Download all dependencies
- Compile the source code
- Run any tests
- Create the JAR file

### 4. Run the Application

**Option A: Using Maven**
```bash
mvn spring-boot:run
```

**Option B: Using Java directly**
```bash
java -jar target/pet-adoption-api-1.0.0.jar
```

**Option C: Run from IDE**
- Right-click `PetAdoptionApplication.java`
- Select "Run" or "Debug"

### 5. Verify the Application is Running

- Application should be available at: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- API Docs: `http://localhost:8080/v3/api-docs`

## API Endpoints

### Base URL
```
http://localhost:8080/api
```

### Pets (`/api/pets`)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/` | Create new pet |
| GET | `/` | Get all pets |
| GET | `/{id}` | Get pet by ID |
| GET | `/species/{species}` | Get pets by species |
| GET | `/status/{status}` | Get pets by adoption status |
| PUT | `/{id}` | Update pet |
| DELETE | `/{id}` | Delete pet |

### Adopters (`/api/adopters`)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/` | Create new adopter |
| GET | `/` | Get all adopters |
| GET | `/{id}` | Get adopter by ID |
| PUT | `/{id}` | Update adopter |
| DELETE | `/{id}` | Delete adopter |

### Shelters (`/api/shelters`)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/` | Create new shelter |
| GET | `/` | Get all shelters |
| GET | `/{id}` | Get shelter by ID |
| GET | `/{id}/pets` | Get all pets in shelter |
| PUT | `/{id}` | Update shelter |
| DELETE | `/{id}` | Delete shelter |

### Veterinarians (`/api/veterinarians`)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/` | Create new veterinarian |
| GET | `/` | Get all veterinarians |
| GET | `/{id}` | Get veterinarian by ID |
| PUT | `/{id}` | Update veterinarian |
| DELETE | `/{id}` | Delete veterinarian |

### Adoptions (`/api/adoptions`)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/` | Create adoption record |
| POST | `/process` | Process complete adoption |
| GET | `/` | Get all adoption records |
| GET | `/{id}` | Get adoption by ID |
| GET | `/pet/{petId}` | Get adoptions by pet |
| GET | `/adopter/{adopterId}` | Get adoptions by adopter |
| PUT | `/{id}` | Update adoption record |
| DELETE | `/{id}` | Delete adoption record |

## Example Requests

### Create a Pet
```bash
curl -X POST http://localhost:8080/api/pets \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Buddy",
    "species": "Dog",
    "breed": "Golden Retriever",
    "age": 3,
    "healthStatus": "Healthy",
    "shelterId": "shelter_001",
    "adoptionStatus": "available"
  }'
```

### Create an Adopter
```bash
curl -X POST http://localhost:8080/api/adopters \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "555-0123",
    "address": "123 Main St, Springfield, USA"
  }'
```

### Process Adoption
```bash
curl -X POST http://localhost:8080/api/adoptions/process \
  -H "Content-Type: application/json" \
  -d '{
    "petId": "pet_id_here",
    "adopterId": "adopter_id_here"
  }'
```

## Testing the API

### Using Postman

1. Import `postman_collection.json` into Postman
2. Set variables for IDs as you create resources
3. Follow the complete workflow in `TESTING_GUIDE.md`

### Using cURL

See example requests above or check `TESTING_GUIDE.md` for complete cURL examples.

### Using Swagger UI

1. Open http://localhost:8080/swagger-ui.html
2. Expand endpoint sections
3. Click "Try it out"
4. Enter request body/parameters
5. Click "Execute"

## Configuration

Edit `src/main/resources/application.properties`:

```properties
# Server Port
server.port=8080

# MongoDB Connection
spring.data.mongodb.uri=mongodb://localhost:27017/pet-adoption-db
spring.data.mongodb.database=pet-adoption-db

# Logging
logging.level.root=INFO
logging.level.com.adoption=DEBUG

# Swagger Documentation
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/v3/api-docs
```

## Error Handling

### Response Format
All errors return a standardized format:
```json
{
  "status": 404,
  "message": "Pet not found with id: xyz",
  "timestamp": "2024-06-18T15:35:00",
  "path": "/api/pets/xyz"
}
```

### Status Codes
- **200** - OK (successful GET, PUT, DELETE)
- **201** - Created (successful POST)
- **400** - Bad Request (validation error, e.g., adopting non-available pet)
- **404** - Not Found (resource doesn't exist)
- **500** - Internal Server Error

## Key Features Explanation

### 1. Pet Adoption Flow
```
1. Create Shelter → Get shelter_id
2. Create Pet with shelter_id → Get pet_id (status: "available")
3. Create Adopter → Get adopter_id
4. Call POST /api/adoptions/process with pet_id and adopter_id
   → Validates pet is available
   → Updates pet status to "adopted"
   → Creates adoption record
```

### 2. Database Relationships
- Pets reference Shelters via `shelterId`
- Adoption Records reference Pets and Adopters via IDs
- No foreign key constraints (document database model)
- Relationships enforced at service layer

### 3. Service Layer
- Handles all business logic
- Validates data before database operations
- Manages complex operations like adoption processing
- Provides reusable methods for controllers

### 4. Repository Layer
- Extends `MongoRepository<T, String>`
- Automatic CRUD methods
- Custom query methods for searches
- Query method naming convention for automatic implementation

## Troubleshooting

### MongoDB Connection Failed
```
Error: Unable to connect to MongoDB
Solution: 
- Ensure MongoDB is running: mongod
- Check connection string in application.properties
- For Atlas, verify IP whitelist includes your IP
```

### Port 8080 Already in Use
```
Error: Port 8080 already in use
Solution: 
- Change port in application.properties: server.port=9090
- Or kill existing process on port 8080
```

### Compilation Error: "symbol not found"
```
Error: Cannot find symbol 'Lombok'
Solution:
- Ensure Lombok is in pom.xml dependencies
- Enable annotation processing in IDE
- Rebuild: mvn clean install
```

## Best Practices Used

1. **Separation of Concerns** - Model, Repository, Service, Controller layers
2. **Exception Handling** - Custom exceptions with global handler
3. **Validation** - Input validation in services
4. **Documentation** - Javadoc, Swagger annotations, README
5. **Security** - Proper error messages, no sensitive data in errors
6. **Testing** - Unit tests for services and repositories
7. **Code Quality** - Lombok reduces boilerplate, clean code
8. **REST Standards** - Proper HTTP methods, status codes, URL structure

## Development Tips

### Enable Hot Reload
The project includes Spring DevTools for automatic restart:
```bash
mvn spring-boot:run
# Make changes to code
# Application automatically restarts
```

### Enable Debug Logging
Edit `application.properties`:
```properties
logging.level.com.adoption=DEBUG
logging.level.org.springframework.data.mongodb=DEBUG
```

### Database Inspection
Using MongoDB Compass (GUI):
1. Download [MongoDB Compass](https://www.mongodb.com/products/compass)
2. Connect to MongoDB
3. Browse collections and documents

Using MongoSH (CLI):
```bash
mongosh "mongodb://localhost:27017/pet-adoption-db"
db.pets.find()
db.adopters.find()
```

## Next Steps / Future Enhancements

1. **Authentication & Authorization** - Add Spring Security
2. **API Rate Limiting** - Prevent abuse
3. **Pagination** - Handle large datasets
4. **Sorting & Filtering** - Advanced search capabilities
5. **File Upload** - Pet photos/documents
6. **Email Notifications** - Adoption confirmations
7. **User Roles** - Admin, Shelter Staff, Adopter roles
8. **Audit Logging** - Track all changes
9. **Caching** - Redis for performance
10. **Unit & Integration Tests** - Comprehensive test coverage

## Support & Contact

For issues or questions:
1. Check TESTING_GUIDE.md for troubleshooting
2. Review error messages in application logs
3. Visit Swagger UI for API documentation
4. Check MongoDB connection configuration

## License

This project is open source and available under the MIT License.

## Author

Pet Adoption Management System - Java/Spring Boot Team

---

**Happy Adopting! 🐾**
