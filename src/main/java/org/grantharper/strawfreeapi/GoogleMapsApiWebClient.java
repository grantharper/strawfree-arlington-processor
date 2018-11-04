package org.grantharper.strawfreeapi;

import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.FindPlaceFromText;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class GoogleMapsApiWebClient {


    private static final Logger logger = LoggerFactory.getLogger(GoogleMapsApiWebClient.class);
    static final String API_KEY;
    public static final LatLng CENTER_OF_ARLINGTON = new LatLng(38.8816792, -77.1020571);

    static {
        InputStream in = GoogleMapsApiWebClient.class.getClassLoader()
                .getResourceAsStream("apikey");
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(reader);
        try {
            API_KEY = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    private GeoApiContext geoApiContext;

    public GoogleMapsApiWebClient() {
        geoApiContext = new GeoApiContext.Builder().apiKey(API_KEY).build();
    }

    public PlacesSearchResult[] searchBusinessName(String businessName) {
        FindPlaceFromTextRequest request = PlacesApi.findPlaceFromText(geoApiContext, businessName, FindPlaceFromTextRequest.InputType.TEXT_QUERY);
        request.fields(FindPlaceFromTextRequest.FieldMask.FORMATTED_ADDRESS, FindPlaceFromTextRequest.FieldMask.GEOMETRY,
                FindPlaceFromTextRequest.FieldMask.NAME, FindPlaceFromTextRequest.FieldMask.PLACE_ID);

        FindPlaceFromTextRequest.LocationBiasPoint locationBiasPoint = new FindPlaceFromTextRequest.LocationBiasPoint(CENTER_OF_ARLINGTON);
        request.locationBias(locationBiasPoint);

        try {
            FindPlaceFromText result = request.await();
            System.out.println(result);
            for (PlacesSearchResult searchResult : result.candidates) {
                System.out.println(searchResult);
            }
            return result.candidates;
        } catch (ApiException | InterruptedException | IOException e) {
            logger.error("Error calling Google Maps API", e);
            throw new RuntimeException("kill process");
        }
    }

    public LatLng locateAddress(String address) {
        GeocodingApiRequest geocodingApiRequest = GeocodingApi.geocode(geoApiContext, address);
        try {
            GeocodingResult[] geocodingResults = geocodingApiRequest.await();
            if (geocodingResults.length != 1) {
                throw new RuntimeException("geocodingResults returned with length=" + geocodingResults.length);
            }
            return geocodingResults[0].geometry.location;
        } catch (ApiException | InterruptedException | IOException e) {
            logger.error("Error calling Google Maps API", e);
            throw new RuntimeException("kill process");
        }

    }


}
