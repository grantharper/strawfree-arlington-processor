package org.grantharper.strawfreeapi;

import lombok.Data;

import java.util.List;

@Data
public class MapApiResponse {

    private String name;
    private int starCount;
    private List<Double> coordinates;

}
