# Strawfree Lat Lng API

For computing the lat/lng coordinates from a list of addresses 

## Setup

Obtain a Google Maps API key and place it in the file `src/main/resources/apikey`


## Run

`gradlew bootRun`

`curl localhost:8080/address -H "Content-Type:application/json" -d '"501 E Monroe Ave, Alexandria, VA 22301"'`

Expected Response

`[{"lat":38.820799,"lng":-77.05250400000001}]`
