package org.grantharper.strawfreeapi;

import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResult;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StrawfreeUtilsTests {


    private static final String BUSINESS_NAME ="Busboys and Poets";

    @Test
    public void testUrlEncode() {
        String encoded = StrawfreeUtils.composeMapsUrl("Busboys and Poets");

        System.out.println(encoded);

    }

    @Test
    public void testCreatMapApiResponse() {
        PlacesSearchResult result = new PlacesSearchResult();
        result.name = BUSINESS_NAME;
        result.geometry = new Geometry();
        result.geometry.location = new LatLng(0.0, 0.0);
        PlacesSearchResult[] results = new PlacesSearchResult[1];
        results[0] = result;
        MapApiResponse mapApiResponse = StrawfreeUtils.formMapApiResponse(BUSINESS_NAME, results);
        assertEquals(BUSINESS_NAME, mapApiResponse.getName());
        assertEquals(BUSINESS_NAME, mapApiResponse.getPlacesSearchResults()[0].name);
    }

}
