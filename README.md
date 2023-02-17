# EV_Station-CRUD-operations-SpringBoot-React-JS

## EV station CRUD Application : 

## Demonstration Images : 
Home Page
![Home-1](https://github.com/Lucifer7355/EV_Station-CRUD-operations-SpringBoot-React-JS/blob/main/Demonstration_images/HOME.png)
![Home-2](https://github.com/Lucifer7355/EV_Station-CRUD-operations-SpringBoot-React-JS/blob/main/Demonstration_images/Home2.png)
Sorting is enabled for all options except station_image
![SortByPrice-1](https://github.com/Lucifer7355/EV_Station-CRUD-operations-SpringBoot-React-JS/blob/main/Demonstration_images/SortByPrice-1.png)
![SortByPrice-2](https://github.com/Lucifer7355/EV_Station-CRUD-operations-SpringBoot-React-JS/blob/main/Demonstration_images/SortByPrice-2.png)
On Clicking show only 10 records
![ShowOnlyTenRecords-1](https://github.com/Lucifer7355/EV_Station-CRUD-operations-SpringBoot-React-JS/blob/main/Demonstration_images/only10records-1.png)
![ShowOnlyTenRecords-2](https://github.com/Lucifer7355/EV_Station-CRUD-operations-SpringBoot-React-JS/blob/main/Demonstration_images/Only10records-2.png)
On Clicking create a new Station
![CreateStation-1](https://github.com/Lucifer7355/EV_Station-CRUD-operations-SpringBoot-React-JS/blob/main/Demonstration_images/Create-1.png)
![CreateStation-2](https://github.com/Lucifer7355/EV_Station-CRUD-operations-SpringBoot-React-JS/blob/main/Demonstration_images/Create-2.png)

Similarly update and delete functionality is also present...
Have fun testing the application 🤗.

## Backend (SpringBoot) :
- The entity is Station which has following attributes : 
  =>attributes = {private Long station_id,private String station_name,private byte[] station_image,private Double station_pricing,private String station_address}
    Here the station image will be mapped to the database as a large object(BLOB). Rest all the attributes will have the same data type as defined in model class.

- There is a StationRepository Interface defined to interact with the table in the database.
- There is a StationService Interface which consists of all the required crud method prototypes,namely : 

```
     => List<Station> getAllChargingStations(); 
     => Station getChargingStation(Long id); 
     =>	String addChargingStation(String station_name,String station_address,Double station_pricing,byte[] gg); 
     =>	String changeDetailsStation(Long id, String station_name,String station_address,Double station_pricing,byte[] gg); 
     =>	String deleteStation(Long id);
     =>	List<Station> getStationsByPagination(int pageNo, int pageSize);
     =>	List<Station> getStationsBySorting(String order,String parameter);
```

- The StationServiceImpl class implements the StationService interface.
  It has the following methods defined to perform various tasks->

```
    => List<Station> getAllChargingStations() : It finds all the stations present in the database using StationRepository and returns their list after decompressing the image bytes.
     => Station getChargingStation(Long id) : It finds the station with given id from database and returns its object after decompressing the compressed image in bytes.
     => String addChargingStation(String station_name,String station_address,Double station_pricing,byte[] gg) : Adds the given Station object to table after compressing the image bytes.
     => String changeDetailsStation(Long id, String station_name,String station_address,Double station_pricing,byte[] gg) : Updates the details of the station with provided id
     => String deleteStation(Long id) : Delete the station object from table of DB as per the record id provided.
     => List<Station> getStationsByPagination(int pageNo, int pageSize) : It gets the list of stations object from table leaving pageNo rows from the top and pageSize rows afterwards from the DB table.
     => List<Station> getStationsBySorting(String order,String parameter) : Returns the list of stations sorted as per the provided parameters and order for which they are sorted.
```

- There is a StationController class defined to accept the end point requests. It has the following end points defined for accepting requests from the frontened : 

```
     => GET | http://localhost:6012/api/v1/getAllStations : this end-point calls the service method "List<Station> getAllChargingStations()" which returns all the stations.
     => POST | http://localhost:6012/api/v1/addStation : this end-point calls the service methods "String addChargingStation(String station_name,String station_address,Double station_pricing,byte[] gg)" which adds the station in the DB.
     => PUT | http://localhost:6012/api/v1/updateStation/{id} : this end-point calls update request for station object having id passed in PathVariable using calls service method "String changeDetailsStation(Long id, String station_name,String station_address,Double station_pricing,byte[] gg)" for updating the station in DB.
     => GET | http://localhost:6012/api/v1/getStation/{id} : this end-point calls request for getting station object having id passed in PathVariable using service method "Station getChargingStation(Long id)".
     => DELETE | http://localhost:6012/api/v1/deleteStation/{id} : this end-point calls request for deleting Station object from DB having id in the PathVariable which in turn calls service method "String deleteStation(Long id)". 
     => GET | http://localhost:6012/api/v1/getbylimit?limit=10 : this end-point calls request for fetching only top 10 entries from the database and presenting to frontend. It uses "List<Station> getStationsByPagination(int pageNo, int pageSize)" where pageNo is 0 by default and pageSize is passed in parameters.
     => GET | http://localhost:6012/api/v1/getbysorted?sort=asc&param=station_id : this end point returns the station objects sorted by given param and order by calling "List<Station> getStationsBySorting(String order,String parameter)" function.

```

### All the responses which are received by frontend are in the format of JSON.


## FrontEnd (React JS) : 
- It has a default home page which represents all the stations images,name,address and price by fetching all from the DB table.
- Each station can be updated,deleted and created in the DB table by REST APIs call.
- There is a button which says only 10 records are to be fetched from DB table. It will fetch and display top 10 records from table.
- Each attributes in the display table can be toggled to sort except station_image, update and delete buttons.
- Whenever the JSON is received from backend, before rendering it on frontend, the image is base64 decoded and then rendered on the page.

## Steps to run the code :
- clone the repository.
- go to the backend folder and run the java-backend server using any of your favourite IDE(STS 4 preferred).
- Now go to frontend folder and open the code in VS-Code. Now run ```npm i ``` so that all the required node modules are downloaded from the npm repository. Now just fire the command ```npm start```.
- Now move to the frontend react web page which is running by default on port 3000. You can test the application now.
- One more important thing is connecting a MySQL DB to the Java Backend. So Create a DB from MySQL workbench and change the application.properties file as per the database connection and database name.
- Table creation will be taken care of by JPA repository.
- Now you can perform crud operations nicely from the frontend.

Hence all the use cases have been covered as per the question statement in Veridic HireSprint2023.	
  
