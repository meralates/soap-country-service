package com.example.soapservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {

    private final WebClient webClient;

    public WeatherService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openweathermap.org/data/2.5").build();
    }

    public Mono<String> getWeather(String city, String apiKey) {
        String uri = UriComponentsBuilder.fromUriString("/weather")
                .queryParam("q", city)
                .queryParam("appid", apiKey)
                .toUriString();

        return webClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(), clientResponse -> Mono.error(new RuntimeException("API hatası")))
                .bodyToMono(String.class);
    }
}
