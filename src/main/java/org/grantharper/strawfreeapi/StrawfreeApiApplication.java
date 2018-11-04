package org.grantharper.strawfreeapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@SpringBootApplication
public class StrawfreeApiApplication {

    @Autowired
    private GoogleMapsApiWebClient googleMapsApiWebClient;

    public static void main(String[] args) {
        SpringApplication.run(StrawfreeApiApplication.class, args);
    }

    @Bean
    public Function<Flux<String>, Flux<String>> uppercase() {
        return flux -> flux.map(String::toUpperCase);
    }

    @Bean
    public Function<Flux<String>, Flux<MapApiResponse>> businessName() {
        return (flux -> flux.map(processBusinessName()));
    }

    @Bean
    public Function<Flux<String>, Flux<LatLngResponse>> address() {
        return (addressFlux -> addressFlux.map(address -> googleMapsApiWebClient.getLatLngFromAddress(address)));
    }

    private Function<String, MapApiResponse> processBusinessName() {
        return businessName -> {
               MapApiResponse mapApiResponse = new MapApiResponse();
               mapApiResponse.setName(businessName);
               mapApiResponse.setPlacesSearchResults(googleMapsApiWebClient.searchBusinessName(businessName));
               return mapApiResponse;
           };
    }

}
