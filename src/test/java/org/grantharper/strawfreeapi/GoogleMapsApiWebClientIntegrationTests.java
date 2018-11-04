package org.grantharper.strawfreeapi;

import com.google.maps.model.LatLng;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class GoogleMapsApiWebClientIntegrationTests {

    GoogleMapsApiWebClient googleMapsApiWebClient = new GoogleMapsApiWebClient();
    String arlingtonArtsCenter = "Arlington Arts Center";
    String busboysAndPoets = "Busboys and Poets";

    @Test
    public void testPlacesSearch() {
        googleMapsApiWebClient.searchBusinessName(busboysAndPoets);

    }

    @Test
    public void testAddressGeolocation() {
        LatLng latLng = googleMapsApiWebClient.locateAddress("501 E Monroe Ave, Alexandria, VA 22301");
        System.out.println("lat=" + latLng.lat + ", lng=" + latLng.lng);

        assertTrue(latLng != null);
        assertTrue(latLng.lat != 0.0);
        assertTrue(latLng.lng != 0.0);

    }


}
