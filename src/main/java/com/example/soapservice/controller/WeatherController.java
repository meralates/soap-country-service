package com.example.soapservice.controller;

import com.example.soapservice.service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    @Value("${openweather.api.key}")  // application.properties'ten API key al
    private String apiKey;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public Mono<String> getWeather(@RequestParam String city) {
        return weatherService.getWeather(city, apiKey);
    }
}
