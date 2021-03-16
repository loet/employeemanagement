# employeemanagement project

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

If you want to use a local Postgresql database instead of the in-memory h2, start the server with the profile postgresql (for configuration of Postgresql, see below):
```
./mvnw compile quarkus:dev -Dquarkus.profile=postgresql
````

## Running the application in a docker container

Required precondition: Installation of Docker: https://docs.docker.com/docker-for-mac/install/

Execute the following commands:
```
./mvnw package
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/employeemanagement-jvm .
docker run -i --rm -p 8000:8000 quarkus/employeemanagement-jvm
```

## Testing the APIs
Use ***jee-quarkus-learning.postman_collection.json*** for testing the APIs

## Initialize test data
As the H2 database is only configured in-memory, all the data disappears after server startup or deployment.

You can initialize the database with some nice data, by running src/test/java/ch/mobi/ueliloetscher/learning/employeemanagement/DataLoader.main()

## Configuration of local Postgresql
Install Postgresql locally, see: https://postgresapp.com/

Open the postgres database and create database for the employeemanagement:
```
create database employeemanagement;
```
## Create build for the Azure Cloud
##### see: https://docs.microsoft.com/en-us/azure/developer/java/eclipse-microprofile/deploy-microprofile-quarkus-java-app-with-maven-plugin
```
az login
mvn clean package -Dquarkus.profile=mssql
mvn azure-webapp:deploy
```
