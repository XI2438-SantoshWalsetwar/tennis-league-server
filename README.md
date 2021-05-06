##1.Configurations


1.If using MYSQL Database no need to change anything.For any other relations make required changes in datasource properties such as dialect,url.
Also remove MySql Connector dependency from pom.xml and respective connector dependency in the file.

2.Specify number of participants for championship in the application.properties with property "participants.limit = 12".

3.Specify number of max matches per day in the application.properties with property "matches.per.day = 3".

4.Create database TENNISLEAGUE using command "CREATE DATABASE TENNISLEAGUE;".

-----------------------------------------------------------------------------------------------------------------------------------------------------

##2.Running Steps

1.Download the zip or clone the Git repository.
2.Unzip the zip file (if you downloaded one)
3.Open Command Prompt and Change directory (cd) to folder containing pom.xml
4.mvn spring-boot:run  - Maven should be installed and configured to run this.

------------------------------------------------------------------------------------------------------------------------------------------------------

Application URL : http://localhost:8080/ - Port number is as per your configuration
Swagger URL : http://localhost:8080/swagger-ui.html  -- All the Api information is available

------------------------------------------------------------------------------------------------------------------------------------------------------

Add Participant Request Payload Example

{
    "name": "Santosh Walsetwar",
    "email": "santosh.walsetwar@xebia.com"
}

##3.Scenarios and Steps Covered
1.Add Participants using the above payload.Participants can be added as per the limit given in the configuration file.By default newly created is qualified to play match.
2.Get List of participants craeted.
3.Create Match groups.This will start new Round as well as create teams and schedule matches as per the per day criteria and number of available qualified participants.If number of available participants are uneven then the first participant is moved to next round directly. 
4.Update match winner using match id and winner participant id which you can get from match list.This will also mark the lost participant as disqualified and he is not eligible for next rounds.Whenever last winner of that round is updated round is closed putting end date to it.
5.Repeat step 2-4 again which will create new Round of matches for the winners of previous round.this can be repeted until single participant is pending which then can be declared as winner by adding that API.

Assumption
1.Match is played by single players.

------------------------------------------------------------------------------------------------------------------------------------------------------------

##4.SQL Script to populate the participant data

USE TENNISLEAGUE;
INSERT INTO participant VALUES (1,'2021-05-05 19:21:17','santosh@xebia.com','2021-05-05 19:24:38','Santosh',TRUE);
INSERT INTO participant VALUES (2,'2021-05-05 19:21:17','bob@xebia.com','2021-05-05 19:24:38','Bob',TRUE);
INSERT INTO participant VALUES (3,'2021-05-05 19:21:17','john@xebia.com','2021-05-05 19:24:38','John',TRUE);
INSERT INTO participant VALUES (4,'2021-05-05 19:21:17','jack@xebia.com','2021-05-05 19:24:38','Jack',TRUE);
INSERT INTO participant VALUES (5,'2021-05-05 19:21:17','lily@xebia.com','2021-05-05 19:24:38','Lily',TRUE);
INSERT INTO participant VALUES (6,'2021-05-05 19:21:17','ben@xebia.com','2021-05-05 19:24:38','Ben',TRUE);
INSERT INTO participant VALUES (7,'2021-05-05 19:21:17','kane@xebia.com','2021-05-05 19:24:38','Kane',TRUE);
INSERT INTO participant VALUES (8,'2021-05-05 19:21:17','danny@xebia.com','2021-05-05 19:24:38','Danny',TRUE);
INSERT INTO participant VALUES (9,'2021-05-05 19:21:17','rock@xebia.com','2021-05-05 19:24:38','Rock',TRUE);
INSERT INTO participant VALUES (10,'2021-05-05 19:21:17','ricky@xebia.com','2021-05-05 19:24:38','Ricky',TRUE);
INSERT INTO participant VALUES (11,'2021-05-05 19:21:17','jolina@xebia.com','2021-05-05 19:24:38','Jolina',TRUE);
INSERT INTO participant VALUES (12,'2021-05-05 19:21:17','shante@xebia.com','2021-05-05 19:24:38','Shante',TRUE);

Note : Application will by default pick up the sql file kept in resource folder these queries are just for reference.