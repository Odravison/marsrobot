# __MarsRobot__

#### __Description__

This is a little api simulator of a robot living Mars.
This robot has only 5x5 area to walk by commands sent by "NASA" via API/REST.
###### Configuration tools
This project use:
- Spring Framework (Spring-boot) 1.5.1;
- Maven 3.1;
- Maven Wrapper 0.1.5;
- Java SDK 1.8;
- JRE 8;

____


#### __Setup environment__
To setup environment and run this project, you need:
- Install [Jdk/Jre 8](https://www.java.com/download/) and [Maven](https://maven.apache.org/download)
- Clone this repository with: `git clone git@github.com:Odravison/marsrobot.git`
- To get inside the project folder
- Run the command `./mvnw clean package` (This command will build and run all tests)
- Now, you can run the application. You can use spring-boot for this, using: `./mvnw spring-boot:run`

____

#### __Usage__
After server running you can use this endpoints (one robot is already initiated):

`/rest/mars/whereami` GET(only) - This endpoint get the current position of the robot

`/rest/mars/{command}` POST(only) - This endpoint post a `{command}` to robot. All avaliables command are `M` - Move, `L` - Left, `R` - Right.

`M` - makes the robot go forward by one step

`L` - makes the robot spin in yourself axis to the left in 90º

`R` - makes the robot spin in yourself axis to the right in 90º

###### Examples of usage:

To see values default of robot run `curl -s --request POST http://localhost:8080/rest/mars/whereami`, you will receive:

> `{
  "id": 1,
  "currentPosition": {
    "x": 0,
    "y": 0
  },
  "currentOrientation": "N"
}`

Now, run `curl -s --request POST http://localhost:8080/rest/mars/MM` and you will receive:

> `{
  "id": 1,
  "currentPosition": {
    "x": 0,
    "y": 2
  },
  "currentOrientation": "N"
}`

*PS: The prefix `/rest/mars/` in endpoints was a requirement.*

____

#### __Tests__
The test run always after build and run server, which is respectively, running the commands `./mvnw clean package` , `./mvnw spring-boot:run`

#### __The goal__

The challange was propose by [Conta Azul](www.contaazul.com) as a stage
of a emplyoment process.
