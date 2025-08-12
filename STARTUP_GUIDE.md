# Groceteria Application - Complete Startup Guide

## 🚀 Quick Start (5 Minutes)

### Prerequisites Check
```bash
# Check Java version (should be 17+)
java -version

# Check Node.js version (should be 16+)
node -v

# Check if MySQL is running
mysql --version
```

## 📋 Step-by-Step Startup

### 1. Database Setup
```bash
# Start MySQL (if not running)
# Windows: Start MySQL service
# Linux/Mac: sudo systemctl start mysql

# Create database (optional - will auto-create)
mysql -u root -p
CREATE DATABASE groceteria;
```

### 2. Backend Startup
```bash
# Navigate to backend
cd groceteria_backend

# Windows
start.bat

# Linux/Mac
chmod +x start.sh
./start.sh

# Manual alternative
./mvnw spring-boot:run
```

**✅ Backend should start on http://localhost:8080**

### 3. Frontend Startup
```bash
# Navigate to frontend
cd groceteria_frontend

# Install dependencies (first time only)
npm install

# Windows
start.bat

# Linux/Mac
chmod +x start.sh
./start.sh

# Manual alternative
ng serve --open
```

**✅ Frontend should start on http://localhost:4200**

## 🔍 Verification Steps

### Backend Verification
1. **Swagger UI**: http://localhost:8080/swagger-ui.html
2. **Health Check**: http://localhost:8080/api/v1/health
3. **API Docs**: http://localhost:8080/api-docs

### Frontend Verification
1. **Home Page**: http://localhost:4200
2. **User Registration**: http://localhost:4200/user-signup
3. **User Login**: http://localhost:4200/user-login

## 🧪 Testing the Application

### 1. User Registration Flow
```
1. Go to http://localhost:4200
2. Click "Login" → "Sign Up"
3. Fill registration form
4. Submit and verify account creation
```

### 2. User Login Flow
```
1. Go to http://localhost:4200/user-login
2. Enter credentials
3. Verify successful login
4. Check user dashboard
```

### 3. Shopping Flow
```
1. Login as user
2. Browse items
3. Add items to cart
4. Place order
5. Complete payment
```

### 4. Admin Flow
```
1. Login as admin
2. Manage items
3. View orders
4. Manage users
```

## 🔧 Troubleshooting

### Backend Issues
```bash
# Port already in use
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Database connection error
# Check MySQL is running
# Verify credentials in application.yml

# Build errors
./mvnw clean install
```

### Frontend Issues
```bash
# Dependencies missing
npm install

# Port already in use
netstat -ano | findstr :4200
taskkill /PID <PID> /F

# Build errors
ng build --configuration development
```

### Common Error Solutions

1. **CORS Errors**
   - Backend has CORS enabled for all origins
   - Check if backend is running on port 8080

2. **API Connection Failed**
   - Verify backend URL in environment.ts
   - Check network tab in browser dev tools

3. **Database Connection**
   - Ensure MySQL is running
   - Check credentials in application.yml
   - Database will auto-create if it doesn't exist

4. **Angular Build Errors**
   - Clear node_modules: `rm -rf node_modules && npm install`
   - Update Angular CLI: `npm install -g @angular/cli@latest`

## 📊 Application Features

### ✅ Working Features
- User registration and authentication
- Item browsing and search
- Shopping cart management
- Order placement and tracking
- Payment processing (mock)
- Admin panel for item/order management
- Responsive design for all devices
- API documentation with Swagger

### 🎨 Design Features
- Modern, subtle color scheme
- Professional typography
- Smooth animations and transitions
- Mobile-first responsive design
- Clean, intuitive navigation

## 🔐 Default Credentials

### Database
- **Host**: localhost
- **Port**: 3306
- **Database**: groceteria
- **Username**: root
- **Password**: Nakul123

### Application
- **Backend**: http://localhost:8080
- **Frontend**: http://localhost:4200
- **Swagger**: http://localhost:8080/swagger-ui.html

## 📱 Mobile Testing

### Responsive Design
- Test on different screen sizes
- Verify mobile navigation
- Check touch interactions
- Test form inputs on mobile

### Browser Compatibility
- Chrome (recommended)
- Firefox
- Safari
- Edge

## 🚀 Production Deployment

### Backend Deployment
```bash
# Build JAR file
./mvnw clean package

# Run JAR file
java -jar target/online-grocery-store-0.0.1-SNAPSHOT.jar
```

### Frontend Deployment
```bash
# Build for production
ng build --configuration production

# Serve static files
npx http-server dist/angular-groceteria
```

## 📞 Support

### Documentation
- **Backend**: README_POSTMAN_READY.md
- **Frontend**: README_FRONTEND_READY.md
- **API**: Swagger UI at /swagger-ui.html
- **Postman**: Groceteria_API_Postman_Collection.json

### Logs
- **Backend**: Check console output
- **Frontend**: Check browser console
- **Database**: Check MySQL logs

---

## 🎉 Success Checklist

- [ ] Backend starts without errors
- [ ] Frontend starts without errors
- [ ] Database connection successful
- [ ] User can register and login
- [ ] Items can be browsed and added to cart
- [ ] Orders can be placed
- [ ] Admin panel is accessible
- [ ] Responsive design works on mobile
- [ ] All API endpoints respond correctly

**🎯 If all items are checked, your Groceteria application is fully functional!**

---

**Happy Shopping! 🛒✨** 