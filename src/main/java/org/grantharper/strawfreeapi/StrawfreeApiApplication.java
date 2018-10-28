package org.grantharper.strawfreeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@SpringBootApplication
public class StrawfreeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StrawfreeApiApplication.class, args);
    }

    @Bean
    public Function<Flux<String>, Flux<String>> uppercase() {
        return flux -> flux.map(value -> value.toUpperCase());
    }

    @Bean
    public Function<Flux<String>, Flux<MapApiResponse>> info() {
        return (flux -> flux.map(processBusinessName()));
    }

    private Function<String, MapApiResponse> processBusinessName() {
        return businessName -> {
            // take string and compose url
            // call google maps api
               MapApiResponse mapApiResponse = new MapApiResponse();
               mapApiResponse.setName(businessName);
               mapApiResponse.setStarCount(1);
               List<Double> coordinates = new ArrayList<>();
               coordinates.add(0.0);
               coordinates.add(0.0);
               mapApiResponse.setCoordinates(coordinates);
               return mapApiResponse;
           };
    }

}
