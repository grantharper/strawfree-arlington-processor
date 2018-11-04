package org.grantharper.strawfreeapi;

import org.junit.Test;

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
        googleMapsApiWebClient.locateAddress("501 E Monroe Ave, Alexandria, VA 22301");
    }


}
