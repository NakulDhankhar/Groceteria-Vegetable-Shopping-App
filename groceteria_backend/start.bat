@echo off
echo Starting Groceteria Backend Application...
echo.
echo Make sure MySQL is running and configured properly.
echo Database: groceteria
echo Username: root
echo Password: Nakul123
echo.
echo Starting application on http://localhost:8080
echo Swagger UI: http://localhost:8080/swagger-ui.html
echo.
mvnw.cmd spring-boot:run
pause 