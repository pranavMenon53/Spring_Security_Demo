# Spring Demo Application
### Uses Spring Security to validate the user and perform actions

## Users
### Different users have different permissions!

STUDENT - 
            username : David
            password : password
            
ADMIN - 
            username : Bob
            password : password123
            
TRAINEE - 
            username : Tom
            password : password123

## End Points -      
  1. localhost:8080/login - log in using credentials

### Only accessible to ADMIN and TRAINEE
  look at StudentController and StudentManagementController for more information

  2. http://localhost:8080/management/api/v1/students - GET

  3. http://localhost:8080/management/api/v1/students - POST
      body -  
      {
        "studentId": 1,
        "studentName": "Veronica"
      }

  4. http://localhost:8080/management/api/v1/students/1 - PUT
      body -  
      {
        "studentId": 1,
        "studentName": "Veronica"
      }

  5. http://localhost:8080/management/api/v1/students/1 - DELETE


### Only accessible to STUDENT

  6. http://localhost:8080/api/v1/students/2 - GET