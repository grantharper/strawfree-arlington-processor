package org.grantharper.strawfreeapi;

import com.google.maps.FindPlaceFromTextRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.FindPlaceFromText;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResult;

import java.io.*;

public class GoogleMapsApiWebClient {

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

    public void searchBusinessName(String businessName) {
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
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
