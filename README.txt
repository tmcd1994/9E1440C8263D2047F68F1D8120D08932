#BUILD USING MAVEN 
Go to directory where pom.xml is located.
Run command 'mvn clean install'
This will execute the unit test and build project. 

#Test
Change into the target directory. 
run the command java -jar PriorityWS-1.jar 
Open browser and enter URL http://127.0.0.1:8080/PriorityList/getList to retrieve list
Or enter a curl command on the command line: curl http://127.0.0.1:8080/PriorityList/getList
For test purposes I have populated the list with some work orders.


Examples of curl commands to test web services: 
Format for date: dd-MM-yyyy-HH:mm:ss i.e. 30-01-2021-12:12:00

Add to list: 
http://127.0.0.1:8080/PriorityList/addToQueue?id=19&date=30-01-2021-12:12:00

Get Position in Queue 
curl http://127.0.0.1:8080/PriorityList/getPositionInQueue?id=15

Get Top of Queue
http://127.0.0.1:8080/PriorityList/getTopOfQueue

Get Average wait times in seconds
http://127.0.0.1:8080/PriorityList/getAverageWaitTime

Delete Work Order
http://127.0.0.1:8080/PriorityList/removeFromQueue?id=15


If you attempt to add a work order that has an ID which is already in use you will receive the message:
ID is already in use

If you attempt to add a work order that has an ID outside of the range you will receive the message:
ID is out of range 

If you attempt to delete a work order which id is invalid you will receive the respone: 
Work Order: 15 does not exist. Please enter a valid ID