<h1>Weather Trial<h1>

This is a rest service implemented using Sping Boot. The service returns current temperature and
3 days forecast minimum temperature for the city.

Requirements: JDK 1.8. 

This service using openweathermap.org and api.geonames.org rest API.

Service configuration file: config/application-default.properties

Service log file: log/application.log

To run service type: mvn spring-boot:run

To test service type: mvn test

Service receives HTTP GET request with parameters:

q - the city name and country code divided by comma (use ISO 3166 country codes);

units - temperature units format.  Avaliable values: imperial (Fahrenheit), metric(Celsius, default), kelvin.

Returns the JSON representation: city name, current temperature, forecast 3 days minimum temperature.

Request example (use browser agent):

http://localhost:8080/weather?q=Moscow  

Response example:

{"city":"Moscow,RU","currentTemperature":28.1,"forecast3DaysMinTemperature":14.7}

Response example for bad request:

{"cod":404,"message":"http://openweathermap.org/data/2.5/weather?q=&APPID=8f18aeee6c736d3d84ff65f69be68c87&units=metric -> Error: Not found city"}
