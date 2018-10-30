package org.grantharper.strawfreeapi;

import com.google.maps.model.PlacesSearchResult;

import java.net.URLEncoder;

public class StrawfreeUtils {

    public static String composeMapsUrl(String businessName) {
        String baseUrl = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?key=%s&input=%s&inputtype=textquery&fields=name,id,formatted_address,place_id,geometry";

        String encodedBusinessName = URLEncoder.encode(businessName);

        String url = String.format(baseUrl, GoogleMapsApiWebClient.API_KEY, encodedBusinessName);

        return url;
    }

    public static MapApiResponse formMapApiResponse(String businessName, PlacesSearchResult[] googleMapsResult) {
        MapApiResponse mapApiResponse = new MapApiResponse();
        mapApiResponse.setName(businessName);
        mapApiResponse.setPlacesSearchResults(googleMapsResult);
        return mapApiResponse;
    }
}
