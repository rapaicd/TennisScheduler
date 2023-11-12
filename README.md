# TennisScheduler

## Short description
This microservices java application allows for easy and efficient scheduling of tennis matches, providing functionalities for managing players, courts, and schedules.


## Clone the repository:
Copy code
git clone https://github.com/rapaicd/TennisScheduler.git


## Configure the database:
The application uses PostgreSQL database. Create a database using the SQL script in the sql directory.

## Configure the application:
In the src/main/resources directory for all microservices, customize the application.properties file with the correct data for connecting to your PostgreSQL database.

## Run the application:
### Navigate to the project directory:

cd tennis-scheduler

### Start Eureka Service:

Navigate to the eureka-service directory:
cd .\EurekaServer\EurekaServer\

Run the Eureka Service:
mvn spring-boot:run

### Start Api Gateway:

Navigate to the api-gateway directory:
cd .\ApiGateway\api-gateway\

Run the Api Gateway:
mvn spring-boot:run

### Start Authorization Server:

Navigate to the spring-authorization-server directory:
cd .\AuthorizationServer\spring-authorization-server\

Run the Authorization Server:
mvn spring-boot:run

### Start Timeslot Resource Service:

Navigate to the TimeslotService directory:
cd .\TimeslotService\TimeslotService\

Run the Timeslot Service:
mvn spring-boot:run

### Start User Resource Service:

Navigate to the UserService directory:
cd .\UserService\UserService\ 

Run the User Service:
mvn spring-boot:run


Now, the Tennis Scheduler microservices are up and running. You can access the application at http://localhost:8000 through the API Gateway.

### Note:
Make sure each service is successfully started before proceeding to the next one to ensure proper functioning and service discovery.
