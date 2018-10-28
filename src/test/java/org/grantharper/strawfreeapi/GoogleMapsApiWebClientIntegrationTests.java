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


}
