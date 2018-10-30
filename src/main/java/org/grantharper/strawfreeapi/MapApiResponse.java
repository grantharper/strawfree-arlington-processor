package org.grantharper.strawfreeapi;

import com.google.maps.model.PlacesSearchResult;
import lombok.Data;

@Data
public class MapApiResponse {

    private String name;
    private PlacesSearchResult[] placesSearchResults;

}
