# Introduction
This repository comprises of two simple microservices named 
`car-info-service` and `car-ratings service` which are developed in spring boot.
The services uses MongoDB for data persistance and RabbitMQ for communication.

## Prerequisite installations
To build and run the microservices, following must be installed on your computer.

 - Java OpenJDK 11
 - Local installation of MongoDB.
 Post installation, if the MongoDB URI is other than default, then please set the URI in the environment variable named `MONGO_DB_URI`.
 - Local installation of RabbitMQ .
 Post installation, if the username, password or the host are other than the defaults, then please set the environment variables `RABBIT_MQ_HOST`, `RABBIT_MQ_USERNAME` and `RABBIT_MQ_PASSWORD` accordingly.
 
 ## Build
 Please use the `mvn clean install` command to build the microservices.
 
 ## Run
 Use the `java -jar` command from the target folder to start the microservices.
 
 Example:
````
java jar ./target/car-info-service-1.0.0.jar
java jar ./target/car-ratings-service-1.0.0.jar
````

Please not that the car-info-service will start on the port `8081`
and the car-ratings-service will start on the port `8082`

## Endpoints
### car-info-service endpoints
The car-info-service exposes REST CRUD endpoints to store or manipulate the car information.

#### POST endpoint
We can post a new car information object as demonstrated in the following example 

```
POST http://localhost:8081/cars

Ex:
Payload:
{
    "manufacturer": "Honda",
    "model": "Highlander",
    "price": "23736.62",
    "year": 2008,
    "color": "Mauv"
}

Response:
201 Created
{
    "id": "5ede90786c38b695eb8cc041",
    "manufacturer": "Honda",
    "model": "Highlander",
    "price": 23736.62,
    "year": 2008,
    "color": "Mauv",
    "avgRating": 0.0,
    "modifiedAt": "2020-06-08T19:24:40.822201Z",
    "version": 0
}

```

#### GET endpoints
We can get a specific car information object as demonstrated in the following example 

````
GET http://localhost:8081/cars/{carId}

Ex: 
http://localhost:8081/cars/5ede90786c38b695eb8cc041

Response:
200 OK

{
    "id": "5ede90786c38b695eb8cc041",
    "manufacturer": "Honda",
    "model": "Highlander",
    "price": 23736.62,
    "year": 2008,
    "color": "Mauv",
    "avgRating": 0.0,
    "modifiedAt": "2020-06-08T19:24:40.822Z",
    "version": 0
}
````
Also we can get all the car information as demonstrated in the example below,

````
GET http://localhost:8081/cars

Response:

[
    {
        "id": "5ede90786c38b695eb8cc041",
        "manufacturer": "Honda",
        "model": "Highlander",
        "price": 23736.62,
        "year": 2008,
        "color": "Mauv",
        "avgRating": 0.0,
        "modifiedAt": "2020-06-08T19:24:40.822Z",
        "version": 0
    },
    {
        "id": "5ede91ef6c38b695eb8cc042",
        "manufacturer": "BMW",
        "model": "X1",
        "price": 43736.62,
        "year": 2018,
        "color": "Red",
        "avgRating": 0.0,
        "modifiedAt": "2020-06-08T19:30:55.142Z",
        "version": 0
    }
]
````

#### PUT endpoint
We can update a specific car information as demonstrated in the following example 

````
PUT http://localhost:8081/cars/{carId}

Ex: 
http://localhost:8081/cars/5ede90786c38b695eb8cc041

Payload:
{
    "color": "Black"
}

Response:
202 Accepted


The GET will display updated result as follows
{
    "id": "5ede90786c38b695eb8cc041",
    "manufacturer": "Honda",
    "model": "Highlander",
    "price": 23736.62,
    "year": 2008,
    "color": "Black",
    "avgRating": 0.0,
    "modifiedAt": "2020-06-08T19:36:25.212Z",
    "version": 1
}
````

#### DELETE endpoint
We can delete a specific car information as demonstrated in the following example 

````
DELETE http://localhost:8081/cars/{carId}

Ex: 
http://localhost:8081/cars/5ede90786c38b695eb8cc041

Response:
202 Accepted

````

### car-ratings-service endpoints
The car-ratings-service exposes REST CRUD endpoints to store or manipulate the car ratings.

#### POST endpoint
We can post a new rating for a car as demonstrated in the following example 

```
POST http://localhost:8082/cars/{carId}/ratings

Ex:
http://localhost:8082/cars/5ede90786c38b695eb8cc041/ratings

Payload:
{
    "rating": 2.0
}

Response:
201 Created
{
    "id": "5ede954d6c38b6a3cfe6b929",
    "carId": "5ede90786c38b695eb8cc041",
    "rating": 2.0,
    "modifiedAt": "2020-06-08T19:45:17.142674Z",
    "version": 0
}
```

#### GET endpoints
We can get a specific car rating as demonstrated in the following example 

````
GET http://localhost:8082/cars/{carId}/ratings/{ratingId}

Ex: 
http://localhost:8082/cars/5ede90786c38b695eb8cc041/ratings/5ede954d6c38b6a3cfe6b929

Response:
200 OK

{
    "id": "5ede954d6c38b6a3cfe6b929",
    "carId": "5ede90786c38b695eb8cc041",
    "rating": 2.0,
    "modifiedAt": "2020-06-08T19:45:17.142Z",
    "version": 0
}
````

Also we can get all the ratings for a given car as demonstrated in the example below,

````
GET http://localhost:8082/cars/{carId}/ratings

Response:
[
    {
        "id": "5ede954d6c38b6a3cfe6b929",
        "carId": "5ede90786c38b695eb8cc041",
        "rating": 2.0,
        "modifiedAt": "2020-06-08T19:45:17.142Z",
        "version": 0
    },
    {
        "id": "5ede95c86c38b6a3cfe6b92a",
        "carId": "5ede90786c38b695eb8cc041",
        "rating": 3.0,
        "modifiedAt": "2020-06-08T19:47:20.207Z",
        "version": 0
    }
]
````

#### PUT endpoint
We can update a specific car rating as demonstrated in the following example 

````
PUT http://localhost:8082/cars/{carId}/ratings/{ratingId}

Ex: 
http://localhost:8082/cars/5ede90786c38b695eb8cc041/ratings/5ede954d6c38b6a3cfe6b929

Payload:
{
    "rating": 4.0
}

Response:
202 Accepted

The GET for the rating with ID will display updated result as follows
{
    "id": "5ede954d6c38b6a3cfe6b929",
    "carId": "5ede90786c38b695eb8cc041",
    "rating": 4.0,
    "modifiedAt": "2020-06-08T19:51:47.090Z",
    "version": 1
}
````

#### DELETE endpoint
We can delete a specific car rating as demonstrated in the following example 

````
DELETE http://localhost:8082/cars/{carId}/ratings/{ratingId}

Ex: 
http://localhost:8082/cars/5ede90786c38b695eb8cc041/ratings/5ede954d6c38b6a3cfe6b929

Response:
202 Accepted
````

## Service to service communication
The two services communicate with each other using messaging queues of RabbitMQ. 

The objective of `car-rating-service` and `car-info-service` to communicate with each other is that, 
when for a given car a new rating is posted or an existing rating for the car is updated/deleted, the `car-info-service` must be notified 
with the new average value of the ratings for the given car so that the GET calls of `car-info-service` show 
accurate `avgRating` values.

Please consider the following example for detailed explanation

Ex:
The GET call for car with ID 5ede99b16c38b695eb8cc043 results in the following from the car-info-service

````
GET http://localhost:8081/cars/5ede99b16c38b695eb8cc043
{
    "id": "5ede99b16c38b695eb8cc043",
    "manufacturer": "Ford",
    "model": "GT",
    "price": 82654.62,
    "year": 2000,
    "color": "Grey",
    "avgRating": 0.0,
    "modifiedAt": "2020-06-08T20:04:01.376609Z",
    "version": 0
}

````
Please note that the `avgRating`is zero and this field cannot be updated by normal PUT call of car-info-service.

Lets POST a new rating for this car 
````
POST http://localhost:8082/cars/5ede99b16c38b695eb8cc043/ratings
Payload:
{
    "rating": 4.0
}
Response:
{
    "id": "5ede9b206c38b6a3cfe6b92d",
    "carId": "5ede99b16c38b695eb8cc043",
    "rating": 4.0,
    "modifiedAt": "2020-06-08T20:10:08.662636Z",
    "version": 0
}
````

Now when we do a get on the car in car-info-service, we should see that the `avgRating` should be updated with average rating values for this car

````
GET http://localhost:8081/cars/5ede99b16c38b695eb8cc043
{
    "id": "5ede99b16c38b695eb8cc043",
    "manufacturer": "Ford",
    "model": "GT",
    "price": 82654.62,
    "year": 2000,
    "color": "Grey",
    "avgRating": 4.0,
    "modifiedAt": "2020-06-08T20:10:08.913Z",
    "version": 1
}
````

We can see that the document in car-info-service is updated with teh right vale for `avgRating`.

Similarly, the car-info-service will be notified if there are any new ratings or if existing ratings for the car updated or deleted.
