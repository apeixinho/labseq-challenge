# LabSeq Demo Test

## Docker / Docker Compose

To build the containers and pull the images for both backend and frontend, execute the following command,in the root project folder:

 `docker compose build`

 To run both containers, in detached mode, execute the following command,in the root project folder:

 `docker compose up -d`

 The application, backend and frontend,  will be deployed and will be available in your browser at:

`http://localhost`

Prerequisites for the application are:

- Java Development Kit (>=21)
- Maven (>= 3.x)
- Node (>= v19.x.x)
- Angular (>= 19.x)
- Docker, Docker Compose (>= 20.x.x) (*Optional*)

## Backend

To build the application backend execute the following command, in the backend folder:

`mvn clean install`

To deploy and run the application backend execute the following command, in the backend folder:

`mvn spring-boot:run`

The application will be deployed and Swagger API REST documentation will be available in your browser at:

`http://localhost:8080/swagger-ui/index.html`

## Frontend

To build the application frontend execute the following command, in the frontend folder:

`npm install`

To run the application frontend execute the following command, in the frontend folder:

`npm run start`

The application will be deployed and will be available in your browser at: 

`http://localhost:4200`
