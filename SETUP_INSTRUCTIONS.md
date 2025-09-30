# TravelFlow Application Setup Instructions

## Prerequisites
- Java 17 or higher
- Node.js 18 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

## Database Setup
1. Create a MySQL database named `TravelFlow`:
```sql
CREATE DATABASE TravelFlow;
```

2. Update the database credentials in `TravelFlowBackend/src/main/resources/application.properties` if needed:
```properties
spring.datasource.username=root
spring.datasource.password=admin
```

## Backend Setup (Spring Boot)
1. Navigate to the backend directory:
```bash
cd TravelFlowBackend
```

2. Run the Spring Boot application:
```bash
mvn spring-boot:run
```

The backend will start on `http://localhost:8081`

## Frontend Setup (Angular)
1. Navigate to the frontend directory:
```bash
cd TravelFlowFrontEnd
```

2. Install dependencies:
```bash
npm install
```

3. Start the Angular development server:
```bash
ng serve
```

The frontend will start on `http://localhost:4200`

## Testing the Data Flow

### 1. Test Backend APIs directly:
- Get all trips: `GET http://localhost:8081/api/trips`
- Get trip by ID: `GET http://localhost:8081/api/trips/1`
- Get all reservations: `GET http://localhost:8081/api/reservations`

### 2. Test Frontend:
1. Open `http://localhost:4200` in your browser
2. You should see the trips loaded from the backend
3. Click on "Voir Détails" to view trip details
4. Click "Réserver Maintenant" to create a reservation
5. Navigate to "Mes Réservations" to see your reservations

## Key Features Implemented:
✅ **Backend-Frontend Integration**: Services properly configured to call backend APIs
✅ **Type Safety**: TypeScript models for Trip, Reservation, and Client
✅ **Error Handling**: Loading states and error messages in the frontend
✅ **CORS Configuration**: Backend allows requests from Angular frontend
✅ **Data Models**: Proper JPA entities with relationships
✅ **Sample Data**: Initial trips and clients seeded in the database

## API Endpoints:
- `GET /api/trips` - Get all trips
- `GET /api/trips/{id}` - Get trip by ID
- `GET /api/reservations` - Get all reservations
- `POST /api/reservations` - Create a new reservation
- `PUT /api/reservations/{id}` - Update a reservation
- `DELETE /api/reservations/{id}` - Delete a reservation

## Troubleshooting:
1. **Backend not starting**: Check if MySQL is running and database exists
2. **Frontend not loading data**: Ensure backend is running on port 8081
3. **CORS errors**: Verify CORS configuration allows localhost:4200
4. **Database errors**: Check database credentials and connection