# Transformers API
Author: Allan Francisco Julian Novoa

## Description
This API took an implementation of a crud operation on  a transformers.
Also tooks a List of Transformers and decide who can fight and return the list of survivors between Autobots and decepticons

## Requirements
 * Java JDK version 8
 * Gradle 

## Tools
 * Spring Boot
 * Gradle
 * H2 database
 * Swagger
 
 
## Building and runing the application

### Unit Test
To run the unit test execute the command: **gradle test**.

### Running Application

To run the application execute the command: **gradle bootRun** 
##EndPoints
This Application use Swagger for the api documentation you can visit it at: **localhost:8080/swagger-ui.html**<br/>

#### GET /transformers
 Description: Method Which returns all the transformers <br/>
 Response:
 ```json
           [
             {
               "name": "IRONHIDE",
               "faction": "AUTOBOTS",
               "strength": 3,
               "intelligence": 3,
               "speed": 3,
               "endurance": 3,
               "rank": 3,
               "courage": 3,
               "firePower": 3,
               "skill": 15,
               "alive": true
             },
             {
               "name": "JAZZ",
               "faction": "AUTOBOTS",
               "strength": 3,
               "intelligence": 3,
               "speed": 3,
               "endurance": 3,
               "rank": 3,
               "courage": 3,
               "firePower": 3,
               "skill": 15,
               "alive": true
             },
             {
               "name": "MEGATRON",
               "faction": "DECEPTICONS",
               "strength": 3,
               "intelligence": 3,
               "speed": 3,
               "endurance": 3,
               "rank": 3,
               "courage": 3,
               "firePower": 3,
               "skill": 15,
               "alive": true
             }
           ]
   ```
#### DELETE /transformers
 Description: Delete a transformer by name <br/>
 Request: Transformer name <br/>
 Response:
 ```json
{
  "message": "Transformers deleted successfully"
}
 ```
#### POST /transformers
 Description: Endpoint which creates a transformer. <br/>
 Request: 
 ```json
 {
   "courage": 2,
   "endurance": 3,
   "faction": "DECEPTICONS",
   "firePower": 2,
   "intelligence": 2,
   "name": "SATRSCREAM",
   "rank": 2,
   "speed": 2,
   "strength": 2
 }
```
 Response:
 ```json
 {
   "message": "Transformers Created successfully"
 }
```
#### PUT /transformers
 Description: Endpoint to modify a transformer(Not all values are required)  <br/>
 Request:
 ```json
 {
   "courage": 2,
   "endurance": 3,
   "faction": "DECEPTICONS",
   "firePower": 2,
   "intelligence": 2,
   "name": "SATRSCREAM",
   "rank": 2,
   "speed": 4,
   "strength": 2
 }
```
 Response:
 ```json
{
  "message": "Transformers Created successfully"
}
```
#### POST /transformers/battle
 Description: Endpoint in which take a list of Transformers and Make them battle between factions, The result is a list of survivors and the group who win <br/>
 Request:
 ```json
{
	"transformers": [{
			"courage": 7,
			"endurance": 7,
			"faction": "DECEPTICONS",
			"firePower": 7,
			"intelligence": 7,
			"name": "SOUNDWAVE",
			"rank": 7,
			"speed": 7,
			"strength": 7
		},
		{
			"courage": 4,
			"endurance": 4,
			"faction": "AUTOBOTS",
			"firePower": 4,
			"intelligence": 4,
			"name": "BLUESTREAK",
			"rank": 1,
			"speed": 4,
			"strength": 4
		},
		{
			"courage": 2,
			"endurance": 2,
			"faction": "AUTOBOTS",
			"firePower": 2,
			"intelligence": 2,
			"name": "HUBCAP",
			"rank": 2,
			"speed": 2,
		  	"strength": 2

		}
	]
}
```
 Response:
 ```json
{
  "winner": "DECEPTICONS",
  "battles": 1,
  "survivorsAutobots": [
    {
      "name": "HUBCAP"
    }
  ],
  "survivorsDecepticons": [
    {
      "name": "SOUNDWAVE"
    }
  ]
}
```



