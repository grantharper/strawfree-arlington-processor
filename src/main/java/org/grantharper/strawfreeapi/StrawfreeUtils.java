package org.grantharper.strawfreeapi;

import java.net.URLEncoder;

public class StrawfreeUtils {

    public static String composeMapsUrl(String businessName) {
        String baseUrl = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?key=%s&input=%s&inputtype=textquery&fields=name,id,formatted_address,place_id,geometry";

        String encodedBusinessName = URLEncoder.encode(businessName);

        String url = String.format(baseUrl, GoogleMapsApiWebClient.API_KEY, encodedBusinessName);

        return url;
    }
}
