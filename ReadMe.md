# Spring Demo Application
### Uses Spring Security to validate the user and perform actions

YouTube - https://www.youtube.com/watch?v=her_7pa0vrg&t=3364s
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

      *body -  
      {
        "studentId": 1,
        "studentName": "Veronica"
      }*

  4. http://localhost:8080/management/api/v1/students/1 - PUT

      *body -  
      {
        "studentId": 1,
        "studentName": "Veronica"
      }*

  5. http://localhost:8080/management/api/v1/students/1 - DELETE


### Only accessible to STUDENT

  6. http://localhost:8080/api/v1/students/2 - GET

### To logout - 

  7. http://localhost:8080/courses



## Notes related to commits - 
  
  1. Commit - "Implemented our custom UserDetails class and Eliminated userDetailsService function from ApplicationSecurityConfig"

    - We created a new package named "Auth" in which we create our own implementation of "UserDetails" which is an interface.

    - This Allows us to have more control on where the User data is coming from
    
    - The ApplicationUser class implements "UserDetails" and acts like a MODEL for our users
    
    - The ApplicationUserService class implements "UserDetailsService" and provides us with the "loadUserByUsername" function 

    - The ApplicationUserService class is used to fetch users from a Data source 

    - In our implementation of the "loadUserByUsername" function, It uses the method provided by the interface "ApplicationUserDAO"
    
    - The Idea is that, if in the future we ever have to change data sources, all we have to do is provide the class which implements  "ApplicationUserDAO" and Autowire it in the constructor of ApplicationUserService
    
    - The interface "ApplicationUserDAO" provides us with a function "selectApplicationUserByUsername" which is used to fetch Users from the specified Data Source

    - For a demo Data source, we use the "FakeApplicationDAOService"

    - Since we have a DataSource, we eliminate the function "userDetailsService"

    - Instead of "userDetailsService", we use "DaoAuthenticationProvider" to specify the Users retrieved from our data source.

    - Now all thats left to do is configure this "DaoAuthenticationProvider" into our application.

    - We do this using the "configure(AuthenticationManagerBuilder auth)" method

    - Finally, add 
            " 
              //
              .rememberMe()
              .userDetailsService(applicationUserService)
              //
            "

  2. *Swagger* - A popular documentation formats for RESTful services

        a) http://localhost:8080/v2/api-docs

        b) http://localhost:8080/swagger-ui.html