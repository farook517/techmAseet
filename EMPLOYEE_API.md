# Employee API Documentation

This API provides CRUD operations for managing employees in the TechMAsset application.

## Base URL
```
/api/employees
```

## Endpoints

### 1. Get All Employees
- **Method**: GET
- **URL**: `/api/employees`
- **Response**: 200 OK
- **Response Body**: Array of EmployeeDTO objects

### 2. Get Employee by ID
- **Method**: GET
- **URL**: `/api/employees/{id}`
- **Response**: 200 OK (if found) or 404 Not Found
- **Response Body**: EmployeeDTO object

### 3. Get Employee by Email
- **Method**: GET
- **URL**: `/api/employees/email/{email}`
- **Response**: 200 OK (if found) or 404 Not Found
- **Response Body**: EmployeeDTO object

### 4. Create Employee
- **Method**: POST
- **URL**: `/api/employees`
- **Request Body**: EmployeeDTO object (without id)
- **Validations**:
  - name: required, not blank
  - email: required, not blank, valid email format
  - department: optional
  - position: optional
- **Response**: 201 Created
- **Response Body**: Created EmployeeDTO object with generated id

### 5. Update Employee
- **Method**: PUT
- **URL**: `/api/employees/{id}`
- **Request Body**: EmployeeDTO object
- **Validations**: Same as Create
- **Response**: 200 OK (if found) or 404 Not Found
- **Response Body**: Updated EmployeeDTO object

### 6. Delete Employee
- **Method**: DELETE
- **URL**: `/api/employees/{id}`
- **Response**: 204 No Content (if deleted) or 404 Not Found

## EmployeeDTO Schema

```json
{
  "id": "Long (auto-generated)",
  "name": "String (required)",
  "email": "String (required, valid email)",
  "department": "String (optional)",
  "position": "String (optional)"
}
```

## Example Usage

### Create an Employee
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john.doe@example.com",
    "department": "IT",
    "position": "Software Developer"
  }'
```

### Get All Employees
```bash
curl http://localhost:8080/api/employees
```

### Get Employee by ID
```bash
curl http://localhost:8080/api/employees/1
```

### Update Employee
```bash
curl -X PUT http://localhost:8080/api/employees/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john.doe@company.com",
    "department": "Engineering",
    "position": "Senior Developer"
  }'
```

### Delete Employee
```bash
curl -X DELETE http://localhost:8080/api/employees/1
```

## Notes

- The existing `/allEmployees` endpoint remains available for backward compatibility but returns a different format (Map<String, String>)
- For testing, the application uses an in-memory H2 database
- In production, the application connects to Azure SQL Database
