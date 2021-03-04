# employeemanagement project

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

## Running the application in a docker container

Required precondition: Installation of Docker: https://docs.docker.com/docker-for-mac/install/

Execute the following commands:
```
./mvnw package
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/employeemanagement-jvm .
docker run -i --rm -p 8080:8080 quarkus/employeemanagement-jvm
```

## Testing the APIs
Use ***jee-quarkus-learning.postman_collection.json*** for testing the APIs

## Initialize test data
As the H2 database is only configured in-memory, all the data disappears after server startup or deployment.

You can initialize the database with some nice data, by running src/test/java/ch/mobi/ueliloetscher/learning/employeemanagement/DataLoader.main()


