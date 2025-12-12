# Security Configuration - JWT Authentication

## Overview
This application now includes JWT-based authentication and authorization using Spring Security.

## New Features Added

### 1. JWT Authentication
- Token-based authentication using JWT (JSON Web Tokens)
- Token expiration: 24 hours (configurable)
- Secure token generation and validation

### 2. Custom Exceptions
- `BadRequestException` - For malformed requests (HTTP 400)
- `UnauthorizedException` - For authentication failures (HTTP 401)
- `ForbiddenException` - For authorization failures (HTTP 403)
- `EntityNotFoundException` - For missing resources (HTTP 404)
- `DuplicateEntityException` - For duplicate entries (HTTP 409)

### 3. Security Components
- **JwtUtil**: Utility class for JWT token generation and validation
- **JwtAuthenticationFilter**: Filter to intercept and validate JWT tokens
- **CustomUserDetailsService**: Loads user details from database
- **SecurityConfig**: Main security configuration with CORS and endpoint protection

## API Endpoints

### Public Endpoints (No authentication required)
- `POST /auth/register` - Register a new user
- `POST /auth/login` - Login and receive JWT token
- `GET /swagger-ui/**` - Swagger documentation
- `GET /v3/api-docs/**` - OpenAPI documentation

### Protected Endpoints (JWT token required)
- All other endpoints require a valid JWT token in the Authorization header

## Usage

### 1. Register a New User
```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "userName": "John Doe",
    "nickName": "johndoe",
    "email": "john@example.com",
    "password": "password123"
  }'
```

Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "type": "Bearer",
  "userId": 1,
  "email": "john@example.com",
  "userName": "John Doe",
  "nickName": "johndoe"
}
```

### 2. Login
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "password123"
  }'
```

Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "type": "Bearer",
  "userId": 1,
  "email": "john@example.com",
  "userName": "John Doe",
  "nickName": "johndoe"
}
```

### 3. Access Protected Endpoints
```bash
curl -X GET http://localhost:8080/users/1 \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9..."
```

## Configuration

### Environment Variables
Set these environment variables or update `application.properties`:

```properties
# Database Configuration
DB_URL=jdbc:postgresql://localhost:5432/todolist
DB_USERNAME=postgres
DB_PASSWORD=your_password

# JWT Configuration
JWT_SECRET=your-secret-key-here (at least 256 bits)
JWT_EXPIRATION=86400000 (24 hours in milliseconds)
```

### Database Migration
Add a `role` column to the `user_auth` table:

```sql
ALTER TABLE user_auth 
ADD COLUMN role VARCHAR(50) DEFAULT 'ROLE_USER';

-- Make email unique and not null
ALTER TABLE user_auth 
ALTER COLUMN email SET NOT NULL,
ADD CONSTRAINT user_auth_email_unique UNIQUE (email);

-- Make password not null
ALTER TABLE user_auth 
ALTER COLUMN password SET NOT NULL;
```

## Security Features

### Password Encryption
- Passwords are encrypted using BCrypt algorithm
- Salt is automatically generated and stored with the hash

### CORS Configuration
- Cross-Origin Resource Sharing (CORS) is enabled
- Allows all origins, methods, and headers (configure as needed for production)

### Session Management
- Stateless session management (no server-side sessions)
- All authentication state is stored in JWT tokens

### Authorization
- Role-based access control (RBAC) ready
- Default role: `ROLE_USER`
- Can be extended with additional roles (ADMIN, MODERATOR, etc.)

## Testing with Postman/Insomnia

1. **Register**: POST to `/auth/register` with user details
2. **Login**: POST to `/auth/login` with email and password
3. **Copy Token**: Copy the JWT token from the response
4. **Add Header**: Add `Authorization: Bearer {token}` to subsequent requests
5. **Make Requests**: Access protected endpoints with the token

## Error Handling

All exceptions are handled globally with appropriate HTTP status codes:

- **400 Bad Request**: Validation errors or malformed requests
- **401 Unauthorized**: Authentication failures (invalid credentials or expired token)
- **403 Forbidden**: Authorization failures (insufficient permissions)
- **404 Not Found**: Resource not found
- **409 Conflict**: Duplicate entity (e.g., email already exists)

## Security Best Practices

1. **Change the JWT Secret**: Use a strong, random secret key in production
2. **Use HTTPS**: Always use HTTPS in production to protect tokens in transit
3. **Token Expiration**: Adjust token expiration based on security requirements
4. **CORS Configuration**: Restrict allowed origins in production
5. **Password Policy**: Implement stronger password requirements as needed
6. **Rate Limiting**: Consider adding rate limiting to prevent brute force attacks

## Next Steps

Consider implementing:
- Refresh tokens for extended sessions
- Password reset functionality
- Email verification
- Two-factor authentication (2FA)
- Role-based permissions on specific endpoints
- Audit logging for security events
