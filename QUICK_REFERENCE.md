# Quick Reference - JWT Authentication

## 🔑 Authentication Endpoints

### Register
```http
POST /auth/register
Content-Type: application/json

{
  "userName": "John Doe",
  "nickName": "johndoe", 
  "email": "john@example.com",
  "password": "password123"
}
```

### Login
```http
POST /auth/login
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "password123"
}
```

### Response Format
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsInVzZXJOYW1lIjoiSm9obiBEb2UiLCJzdWIiOiJqb2huQGV4YW1wbGUuY29tIiwiaWF0IjoxNjQwMDAwMDAwLCJleHAiOjE2NDAwODY0MDB9.signature",
  "type": "Bearer",
  "userId": 1,
  "email": "john@example.com",
  "userName": "John Doe",
  "nickName": "johndoe"
}
```

## 🛡️ Protected Endpoint Usage

### Include JWT Token in Header
```http
GET /users/1
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

### All Protected Endpoints
- `GET /users/{id}`
- `GET /users/team/{teamId}`
- `PUT /users/{id}`
- `DELETE /users/{id}`
- `GET /tasks/*`
- `POST /tasks/*`
- `PUT /tasks/*`
- `DELETE /tasks/*`
- `GET /teams/*`
- `POST /teams/*`
- `PUT /teams/*`
- `DELETE /teams/*`
- `GET /messages/*`
- `POST /messages/*`

## 📦 Project Structure

```
com.todolist
├── controller
│   ├── AuthController.java         # /auth/* endpoints
│   ├── UserController.java
│   ├── TaskController.java
│   ├── TeamController.java
│   └── MessageController.java
├── security
│   ├── JwtUtil.java                # Token generation/validation
│   ├── JwtAuthenticationFilter.java # Request interceptor
│   ├── CustomUserDetailsService.java # User loading
│   ├── JwtAuthenticationEntryPoint.java # Error handler
│   └── SecurityConfig.java         # Main security config
├── service
│   ├── AuthService.java            # Auth interface
│   └── implementation
│       └── AuthServiceImpl.java    # Auth implementation
├── dto
│   ├── request
│   │   ├── LoginRequest.java
│   │   └── RegisterRequest.java
│   └── response
│       └── AuthResponse.java
├── exception
│   ├── BadRequestException.java
│   ├── UnauthorizedException.java
│   ├── ForbiddenException.java
│   ├── EntityNotFoundException.java
│   └── DuplicateEntityException.java
└── entity
    ├── User.java
    ├── UserAuth.java              # Updated with role field
    ├── Task.java
    ├── Team.java
    └── Message.java
```

## ⚙️ Configuration

### application.properties
```properties
# JWT Settings
jwt.secret=${JWT_SECRET:default-secret-key}
jwt.expiration=${JWT_EXPIRATION:86400000}

# Database
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/todolist}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD}
```

### Environment Variables (Recommended for Production)
```bash
export JWT_SECRET="your-very-long-secret-key-at-least-256-bits"
export JWT_EXPIRATION=86400000
export DB_PASSWORD="your-secure-password"
```

## 🗄️ Database Migration

Run before first start:
```sql
ALTER TABLE user_auth 
ADD COLUMN IF NOT EXISTS role VARCHAR(50) DEFAULT 'ROLE_USER';

ALTER TABLE user_auth 
ALTER COLUMN email SET NOT NULL,
ADD CONSTRAINT IF NOT EXISTS user_auth_email_unique UNIQUE (email);

ALTER TABLE user_auth 
ALTER COLUMN password SET NOT NULL;
```

## 🚨 Common Error Responses

### 400 Bad Request
```json
{
  "email": "Email must be valid",
  "password": "Password must be at least 6 characters"
}
```

### 401 Unauthorized
```json
{
  "error": "Unauthorized",
  "message": "Authentication is required to access this resource",
  "path": "/users/1"
}
```

### 409 Conflict
```json
{
  "message": "User with email john@example.com already exists"
}
```

## 🧪 Testing with cURL

### Complete Flow
```bash
# 1. Register
TOKEN=$(curl -s -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"userName":"Test","nickName":"test","email":"test@test.com","password":"test123"}' \
  | jq -r '.token')

# 2. Use Token
curl -X GET http://localhost:8080/users/1 \
  -H "Authorization: Bearer $TOKEN"
```

## 📝 Validation Rules

### RegisterRequest
- userName: 3-50 characters, required
- nickName: 3-50 characters, required
- email: Valid email format, required, unique
- password: Minimum 6 characters, required

### LoginRequest
- email: Valid email format, required
- password: Minimum 6 characters, required

## 🔐 Security Headers

### Required for Protected Endpoints
```
Authorization: Bearer {jwt-token}
```

### Optional but Recommended
```
Content-Type: application/json
Accept: application/json
```

## 🎯 Development Tips

1. **Get Token**: Always save the token from login/register response
2. **Token Format**: Always prefix with "Bearer " (note the space)
3. **Token Expiration**: Default 24 hours, refresh by logging in again
4. **CORS**: Already configured for development (all origins allowed)
5. **Swagger**: Access at http://localhost:8080/swagger-ui.html

## 🔄 Token Lifecycle

```
Registration → Token Generated → Store Token → Use in Requests
     ↓                                              ↓
  24 hours                                    Token Expires
     ↓                                              ↓
Login Again ← Token Expired ← Request Fails (401)
```

## 🆘 Troubleshooting

| Issue | Solution |
|-------|----------|
| 401 Unauthorized | Check if token is included and valid |
| Token expired | Login again to get new token |
| 409 Conflict | Email already exists, use different email |
| 400 Bad Request | Check request body validation |
| No CORS | CORS is enabled for all origins in dev |
| Wrong password | Password must match registration |

## 📚 Additional Documentation
- Full guide: `SECURITY.md`
- Implementation details: `IMPLEMENTATION_SUMMARY.md`
- Database scripts: `sql_scripts/add_security_columns.sql`
