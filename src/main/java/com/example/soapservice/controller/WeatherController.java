package com.example.soapservice.controller;

import com.example.soapservice.dto.WeatherResponse;
import com.example.soapservice.service.CountryService;
import com.example.soapservice.service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class WeatherController {

    private final WeatherService weatherService;
    private final CountryService countryService;

    @Value("${openweather.api.key}")
    private String apiKey;

    public WeatherController(WeatherService weatherService, CountryService countryService) {
        this.weatherService = weatherService;
        this.countryService = countryService;
    }

    @GetMapping("/weather")
    public Mono<WeatherResponse> getWeather(@RequestParam String country) {
        String capitalCity = countryService.getCapitalByCountry(country);
        if (capitalCity == null) {
            return Mono.error(new IllegalArgumentException("Geçersiz ülke adı veya başkent bulunamadı."));
        }

        return weatherService.getWeather(capitalCity, apiKey); // hava durumu verisini al
    }
}
