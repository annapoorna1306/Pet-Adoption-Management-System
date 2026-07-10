# Pet Adoption API - Quick Start Guide

**For Beginners: 5-Minute Setup**

## 1. Prerequisites Check (2 minutes)

Verify you have:
- **Java 17+**: Open terminal/PowerShell
  ```
  java -version
  ```
  Should show version 17 or higher

- **Maven 3.6+**:
  ```
  mvn --version
  ```
  Should show version 3.6 or higher

- **MongoDB**: Either installed locally OR MongoDB Atlas account
  - **Local**: Start MongoDB with `mongod`
  - **Atlas**: Get connection string from https://www.mongodb.com/cloud/atlas

## 2. Install Dependencies (1 minute)

In the project directory (`pet-adoption-api`), run:

```bash
mvn clean install
```

This downloads all required libraries (first time may take 2-3 minutes).

## 3. Configure MongoDB (1 minute)

**For Local MongoDB:**
- Just ensure MongoDB is running locally
- File: `src/main/resources/application.properties`
- Already configured for: `mongodb://localhost:27017/pet-adoption-db`

**For MongoDB Atlas (Cloud):**
- Edit `src/main/resources/application.properties`
- Change this line:
  ```
  spring.data.mongodb.uri=mongodb+srv://YOUR_USERNAME:YOUR_PASSWORD@YOUR_CLUSTER.mongodb.net/pet-adoption-db
  ```
- Get your connection string from MongoDB Atlas dashboard

## 4. Run the Application (1 minute)

```bash
mvn spring-boot:run
```

Wait for message: "Started PetAdoptionApplication in X seconds"

## 5. Test It Works (1 minute)

Open your browser and go to:
```
http://localhost:8080/swagger-ui.html
```

You should see interactive API documentation!

---

## First API Call

### Using Browser/Curl (Simplest)

Open new terminal/PowerShell and run:

```bash
curl -X GET http://localhost:8080/api/adopters
```

Should return: `[]` (empty array)

### Using Postman (Recommended)

1. Download [Postman](https://www.postman.com/downloads/)
2. Open Postman
3. Click **Import**
4. Choose `postman_collection.json` from project folder
5. All 31 API endpoints ready to test!

---

## Quick Test Workflow

### Step 1: Create a Shelter (Copy-Paste into Postman POST)
```
URL: http://localhost:8080/api/shelters
METHOD: POST
Body (Raw JSON):
{
  "name": "Happy Paws",
  "address": "123 Pet Lane",
  "phone": "555-1234",
  "email": "hello@happypaws.com",
  "capacity": 50
}
```
**Save the returned `id` - you'll need it**

### Step 2: Create a Pet
```
URL: http://localhost:8080/api/pets
METHOD: POST
Body:
{
  "name": "Buddy",
  "species": "Dog",
  "breed": "Golden Retriever",
  "age": 3,
  "healthStatus": "Healthy",
  "shelterId": "YOUR_SHELTER_ID",
  "adoptionStatus": "available"
}
```
**Save the returned pet `id`**

### Step 3: Create an Adopter
```
URL: http://localhost:8080/api/adopters
METHOD: POST
Body:
{
  "name": "John Doe",
  "email": "john@example.com",
  "phone": "555-5678",
  "address": "456 Main St"
}
```
**Save the returned adopter `id`**

### Step 4: Process Adoption (THE KEY FEATURE!)
```
URL: http://localhost:8080/api/adoptions/process
METHOD: POST
Body:
{
  "petId": "YOUR_PET_ID",
  "adopterId": "YOUR_ADOPTER_ID"
}
```

**What happens:**
- ✅ Validates pet exists and is available
- ✅ Validates adopter exists
- ✅ Updates pet status to "adopted"
- ✅ Creates adoption record with timestamp

### Step 5: Verify Pet Status Changed
```
URL: http://localhost:8080/api/pets/YOUR_PET_ID
METHOD: GET
```

Should show: `"adoptionStatus": "adopted"`

---

## Troubleshooting

| Problem | Solution |
|---------|----------|
| `mvn: command not found` | Maven not installed - Download from maven.apache.org |
| `java: command not found` | Java not installed - Download from oracle.com |
| Cannot connect to MongoDB | Start MongoDB (`mongod`) or check Atlas connection string |
| Port 8080 already in use | Kill the process or change port in `application.properties` |
| Swagger UI doesn't load | Wait 10 seconds after startup, refresh browser |
| API returns 404 | Check endpoint URL spelling and HTTP method |

---

## Project Structure Summary

```
pet-adoption-api/
├── src/main/java/com/adoption/
│   ├── controller/        ← REST endpoints
│   ├── service/           ← Business logic
│   ├── repository/        ← Database
│   ├── model/             ← Data classes
│   └── exception/         ← Error handling
├── pom.xml                ← Dependencies
├── README.md              ← Full guide
├── TESTING_GUIDE.md       ← Detailed tests
└── postman_collection.json ← Postman import
```

---

## All 31 API Endpoints (Overview)

### Pets (7 endpoints)
- POST /api/pets - Create
- GET /api/pets - Get all
- GET /api/pets/{id} - Get by ID
- GET /api/pets/species/{species} - Filter by species
- GET /api/pets/status/{status} - Filter by status
- PUT /api/pets/{id} - Update
- DELETE /api/pets/{id} - Delete

### Adopters (5 endpoints)
- POST /api/adopters - Create
- GET /api/adopters - Get all
- GET /api/adopters/{id} - Get by ID
- PUT /api/adopters/{id} - Update
- DELETE /api/adopters/{id} - Delete

### Shelters (6 endpoints)
- POST /api/shelters - Create
- GET /api/shelters - Get all
- GET /api/shelters/{id} - Get by ID
- GET /api/shelters/{id}/pets - Get pets in shelter
- PUT /api/shelters/{id} - Update
- DELETE /api/shelters/{id} - Delete

### Veterinarians (5 endpoints)
- POST /api/veterinarians - Create
- GET /api/veterinarians - Get all
- GET /api/veterinarians/{id} - Get by ID
- PUT /api/veterinarians/{id} - Update
- DELETE /api/veterinarians/{id} - Delete

### Adoptions (8 endpoints)
- POST /api/adoptions - Create record
- **POST /api/adoptions/process - Process adoption ⭐**
- GET /api/adoptions - Get all
- GET /api/adoptions/{id} - Get by ID
- GET /api/adoptions/pet/{petId} - Get by pet
- GET /api/adoptions/adopter/{adopterId} - Get by adopter
- PUT /api/adoptions/{id} - Update
- DELETE /api/adoptions/{id} - Delete

---

## Success Indicators

✅ **Application is working if:**
1. Terminal shows "Started PetAdoptionApplication"
2. http://localhost:8080/swagger-ui.html loads
3. GET http://localhost:8080/api/adopters returns `[]`
4. POST requests return 201 status with data

✅ **Pet adoption works if:**
1. Pet status changes from "available" to "adopted"
2. Adoption record is created with date/time
3. Cannot adopt an already-adopted pet (400 error)

---

## Next Steps

1. **Explore the Code**: Look at controllers (HTTP logic) and services (business logic)
2. **Full Testing**: Read `TESTING_GUIDE.md` for complete test scenarios
3. **Add Features**: Read `README.md` for enhancement ideas
4. **Deployment**: See `README.md` for production setup

---

## Key Files to Know

| File | Purpose |
|------|---------|
| `pom.xml` | Dependencies - DO NOT MODIFY unless adding packages |
| `application.properties` | Configuration - Change MongoDB URI here |
| `postman_collection.json` | Import to Postman for easy testing |
| `PetAdoptionApplication.java` | Startup file - DO NOT MODIFY |
| Controller files | Change API behavior here |
| Service files | Change business logic here |

---

## Video-Like Tutorial (Text Version)

**Minute 0:** `mvn clean install` (downloads everything)
**Minute 1:** `mvn spring-boot:run` (starts server)
**Minute 2:** Open http://localhost:8080/swagger-ui.html (see all endpoints)
**Minute 3:** Create a shelter via POST /api/shelters
**Minute 4:** Add a pet to the shelter via POST /api/pets
**Minute 5:** Create an adopter via POST /api/adopters
**Minute 6:** Process adoption via POST /api/adoptions/process
**Minute 7:** Verify pet status changed to "adopted"

---

## Support Resources

- **Swagger UI**: http://localhost:8080/swagger-ui.html (API documentation)
- **MongoDB Docs**: https://docs.mongodb.com/
- **Spring Boot Docs**: https://spring.io/projects/spring-boot
- **Testing Guide**: See `TESTING_GUIDE.md` in project
- **Full Guide**: See `README.md` in project

---

## Quick Reference

```bash
# Build project
mvn clean install

# Run application
mvn spring-boot:run

# Run tests
mvn test

# Create JAR
mvn package

# Run JAR
java -jar target/pet-adoption-api-1.0.0.jar

# Check if running
curl http://localhost:8080/swagger-ui.html
```

---

**You're all set! Start testing your API! 🎉**
